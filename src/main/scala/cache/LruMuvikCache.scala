package cache

import java.util.concurrent.ConcurrentHashMap

import scala.annotation.tailrec
import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.{Failure, Success}

object LruCache {

  def apply[V](maxCapacity: Int = 500, timeToLive: Long = 0): Cache[V] = {
    new ExpiringLruCache[V](maxCapacity, timeToLive)
  }
}


/**
  * A thread-safe implementation of
  * The cache has a defined maximum number of entries is can store. After the maximum capacity has been reached new
  * entries cause old ones to be evicted in a last-recently-used manner, i.e. the entries that haven't been accessed for
  * the longest time are evicted first.
  * In addition this implementation optionally supports time-to-live as well as time-to-idle expiration.
  * The former provides an upper limit to the time period an entry is allowed to remain in the cache while the latter
  * limits the maximum time an entry is kept without having been accessed. If both values are non-zero the time-to-live
  * has to be strictly greater than the time-to-idle.
  * Note that expired entries are only evicted upon next access (or by being thrown out by the capacity constraint), so
  * they might prevent gargabe collection of their values for longer than expected.
  *
  * @param timeToLive the time-to-live in millis, zero for disabling ttl-expiration
  */
final class ExpiringLruCache[V](maxCapacity: Int, timeToLive: Long) extends Cache[V] {
  require(
    timeToLive > 0,
    s"timeToLive($timeToLive) must be greater than 0"
  )
  var store = new ConcurrentHashMap[Any, Entry[V]](maxCapacity / 10)
  //  val store = new ConcurrentLinkedHashMap.Builder[Any, Entry[V]]
  //    .initialCapacity(initialCapacity)
  //    .maximumWeightedCapacity(maxCapacity)
  //    .concurrencyLevel(concurrencyLevel)
  //    .build()

  @tailrec
  def get(key: Any): Option[Future[V]] = store.get(key) match {
    case null ⇒ None
    case entry if (isAlive(entry)) ⇒
      Some(entry.future)
    case entry ⇒
      // remove entry, but only if it hasn't been removed and reinserted in the meantime
      if (store.remove(key, entry)) None // successfully removed
      else get(key) // nope, try again
  }

  def apply(key: Any, genValue: () ⇒ Future[V])(implicit ec: ExecutionContext): Future[V] = {
    def insert() = {
      val newEntry = new Entry(Promise[V]())
      val valueFuture =
        store.put(key, newEntry) match {
          case null ⇒ genValue()
          case entry ⇒
            if (isAlive(entry)) {
              // we date back the new entry we just inserted
              // in the meantime someone might have already seen the too fresh timestamp we just put in,
              // but since the original entry is also still alive this doesn't matter
              newEntry.created = entry.created
              entry.future
            } else genValue()
        }
      valueFuture.onComplete { value ⇒ {
        newEntry.promise.tryComplete(value)
        // in case of exceptions or null  we remove the cache entry (i.e. try again later)
        if ((value.get == null) || (value.isFailure)) store.remove(key, newEntry)
      }
      }
      newEntry.promise.future
    }

    store.get(key) match {
      case null ⇒ insert()
      case entry if (isAlive(entry)) ⇒ {
        entry.future
      }
      case entry ⇒ insert()
    }
  }

  def putIfPresent(key: Any, valueFuture: Future[V])(implicit ec: ExecutionContext): Unit = {
    if (store.containsKey(key)) {
      val newPromise = Promise[V]
      val newEntry = new Entry(newPromise)
      valueFuture.onComplete {
        case Success(value) => newPromise.success(value)
        case Failure(ex) => newPromise.failure(ex)
      }
      (store.put(key, newEntry) match {
        case null ⇒ {
          store.remove(key)
          Future.failed(new Exception)
        }
        case entry ⇒
          entry.future
      }).onComplete { value ⇒
        if ((value.get == null) || (value.isFailure)) store.remove(key, newEntry)
      }
    }
  }

  def remove(key: Any) = store.remove(key) match {
    case null ⇒ None
    case entry if (isAlive(entry)) ⇒ Some(entry.future)
    case entry ⇒ None
  }

  def clear(): Unit = {
    store.clear()
  }

  def keys: Set[Any] = store.keySet().asScala.toSet

  def ascendingKeys(limit: Option[Int] = None) = {
    Iterator.empty
  }

  override def limitSize(): Int = {
    var cleaned = 0
    val keys = store.keys()
    val validTime = System.currentTimeMillis() - timeToLive
    val expiredKeys = mutable.MutableList[Any]()
    val livedKeys = mutable.MutableList[(Any, Long)]()
    while (keys.hasMoreElements) {
      val element = keys.nextElement()
      val value = store.get(element)
      if (value != null)
        if (value.created < validTime)
          expiredKeys += element
        else
          livedKeys += ((element, value.created))
    }
    for (i <- 0 to expiredKeys.size - 1) {
      store.remove(expiredKeys(i))
    }
    if (livedKeys.length > maxCapacity) {
      quickSort(livedKeys.toArray, 0, livedKeys.size - 1)
      for (i <- 0 to livedKeys.size - maxCapacity - 1) {
        store.remove(livedKeys(i)._1)
      }
      cleaned = livedKeys.size - maxCapacity - 1 + expiredKeys.length
    }else
      cleaned = expiredKeys.length
    cleaned
  }

  def partition(arr: Array[(Any, Long)], left: Int, right: Int): Int = {
    var i = left
    var j = right
    var tmp: (Any, Long) = null
    val pivot = arr((left + right) / 2)._2
    while (i <= j) {
      while (arr(i)._2 < pivot) {
        i += 1
      }
      while (arr(j)._2 > pivot) {
        j -= 1
      }
      if (i <= j) {
        tmp = arr(i)
        arr(i) = arr(j)
        arr(j) = tmp
        i += 1
        j -= 1
      }
    }
    i
  }

  def quickSort(arr: Array[(Any, Long)], left: Int, right: Int): Unit = {
    val index = partition(arr, left, right)
    if (left < index - 1) quickSort(arr, left, index - 1)
    if (index < right) quickSort(arr, index, right)
  }

  def size = store.size

  private def isAlive(entry: Entry[V]) = {
    (entry.created + timeToLive) > System.currentTimeMillis()
  }
}


class Entry[T](val promise: Promise[T]) {
  @volatile var created = System.currentTimeMillis()

  def future = promise.future

  override def toString = future.value match {
    case Some(Success(value)) ⇒ value.toString
    case Some(Failure(exception)) ⇒ exception.toString
    case None ⇒ "pending"
  }
}