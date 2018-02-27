package utils

/**
  * Created by tamnb on 7/12/17.
  */

import java.security.MessageDigest
import java.text.Normalizer
import java.util.Base64
import java.util.regex.Pattern

import scala.collection.mutable

object Utils {



  def normalizeMarkUpperCase(text: String): String = Pattern.compile("\\p{InCombiningDiacriticalMarks}+").matcher(Normalizer.normalize(text.toLowerCase, Normalizer.Form.NFD)).replaceAll("").replace('đ', 'd').trim


  @throws[Exception]
  def getMd5(bytes: Array[Byte]): String = {
    val md = MessageDigest.getInstance("MD5")
    val mdbytes = md.digest(bytes)
    val sb = new StringBuffer
    var i = 0
    while (i < mdbytes.length) {
      sb.append(Integer.toString((mdbytes(i) & 0xFF) + 256, 16).substring(1))
      i += 1
    }
    sb.toString
  }

}

