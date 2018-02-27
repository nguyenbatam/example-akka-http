package redis.base;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MuvikJedis extends Jedis {

	public MuvikJedis(final String host, final int port, final int timeout) {
    super(host, port, timeout);
  }

	public MuvikJedis(String host, int port, int connectionTimeout, int soTimeout, boolean ssl,
			SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
		super(host, port, connectionTimeout, soTimeout, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public Set<String> smembers(final String key) {
		checkIsInMultiOrPipeline();
		client.smembers(key);
		final List<String> members = client.getMultiBulkReply();
		if (members == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		return MuvikSetFromList.of(members);
	}

	public Set<String> spop(final String key, final long count) {
		checkIsInMultiOrPipeline();
		client.spop(key, count);
		final List<String> members = client.getMultiBulkReply();
		if (members == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		return MuvikSetFromList.of(members);
	}

	public Set<String> sinter(final String... keys) {
		checkIsInMultiOrPipeline();
		client.sinter(keys);
		final List<String> members = client.getMultiBulkReply();
		if (members == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		return MuvikSetFromList.of(members);
	}

	public Set<String> sunion(final String... keys) {
		checkIsInMultiOrPipeline();
		client.sunion(keys);
		final List<String> members = client.getMultiBulkReply();
		if (members == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		return MuvikSetFromList.of(members);
	}

	public Long sremArray(final String key, final String[] members) {
		    checkIsInMultiOrPipeline();
		    client.srem(key, members);
		    return client.getIntegerReply();
	}


	public Long saddArray(final String key, final String[] members) {
		    checkIsInMultiOrPipeline();
		    client.sadd(key, members);
		    return client.getIntegerReply();
	}

	public List<String> hmgetArray(final String key, final String[] fields) {
    checkIsInMultiOrPipeline();
    client.hmget(key, fields);
    return client.getMultiBulkReply();
  }

	@Override
	public Set<String> zrange(final String key, final long start, final long end) {
		checkIsInMultiOrPipeline();
		client.zrange(key, start, end);
		final List<String> members = client.getMultiBulkReply();
		if (members == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		return MuvikSetFromList.of(members);
	}

	public Set<String> zrevrange(final String key, final long start, final long end) {
		checkIsInMultiOrPipeline();
		client.zrevrange(key, start, end);
		final List<String> members = client.getMultiBulkReply();
		if (members == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		return MuvikSetFromList.of(members);
	}

	public Set<Tuple> zrangeWithScores(final String key, final long start, final long end) {
		checkIsInMultiOrPipeline();
		client.zrangeWithScores(key, start, end);
		return getTupledSet();
	}

	public Set<Tuple> zrevrangeWithScores(final String key, final long start, final long end) {
		checkIsInMultiOrPipeline();
		client.zrevrangeWithScores(key, start, end);
		return getTupledSet();
	}

	public Set<String> zrangeByScore(final String key, final double min, final double max) {
		checkIsInMultiOrPipeline();
		client.zrangeByScore(key, min, max);
		final List<String> members = client.getMultiBulkReply();
		if (members == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		return MuvikSetFromList.of(members);
	}

	public Set<String> zrangeByScore(final String key, final String min, final String max) {
		checkIsInMultiOrPipeline();
		client.zrangeByScore(key, min, max);
		final List<String> members = client.getMultiBulkReply();
		if (members == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		return MuvikSetFromList.of(members);
	}

	public Set<String> zrangeByScore(final String key, final double min, final double max, final int offset,
			final int count) {
		checkIsInMultiOrPipeline();
		client.zrangeByScore(key, min, max, offset, count);
		final List<String> members = client.getMultiBulkReply();
		if (members == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		return MuvikSetFromList.of(members);
	}

	public Set<String> zrangeByScore(final String key, final String min, final String max, final int offset,
			final int count) {
		checkIsInMultiOrPipeline();
		client.zrangeByScore(key, min, max, offset, count);
		final List<String> members = client.getMultiBulkReply();
		if (members == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		return MuvikSetFromList.of(members);
	}

	public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max) {
		checkIsInMultiOrPipeline();
		client.zrangeByScoreWithScores(key, min, max);
		return getTupledSet();
	}

	public Set<Tuple> zrangeByScoreWithScores(final String key, final String min, final String max) {
		checkIsInMultiOrPipeline();
		client.zrangeByScoreWithScores(key, min, max);
		return getTupledSet();
	}

	public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max, final int offset,
			final int count) {
		checkIsInMultiOrPipeline();
		client.zrangeByScoreWithScores(key, min, max, offset, count);
		return getTupledSet();
	}

	public Set<Tuple> zrangeByScoreWithScores(final String key, final String min, final String max, final int offset,
			final int count) {
		checkIsInMultiOrPipeline();
		client.zrangeByScoreWithScores(key, min, max, offset, count);
		return getTupledSet();
	}

	private Set<Tuple> getTupledSet() {
		checkIsInMultiOrPipeline();
		List<String> membersWithScores = client.getMultiBulkReply();
		if (membersWithScores == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		if (membersWithScores.isEmpty()) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		List<Tuple> list = new ArrayList<>();
		int i = 0;
		while (i < membersWithScores.size()) {
			list.add(new Tuple(membersWithScores.get(i), Double.valueOf(membersWithScores.get(i + 1))));
			i = i + 2;
		}
		return MuvikSetFromList.of(list);
	}

	public Set<String> zrevrangeByScore(final String key, final double max, final double min) {
		checkIsInMultiOrPipeline();
		client.zrevrangeByScore(key, max, min);
		final List<String> members = client.getMultiBulkReply();
		if (members == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		return MuvikSetFromList.of(members);
	}

	public Set<String> zrevrangeByScore(final String key, final String max, final String min) {
		checkIsInMultiOrPipeline();
		client.zrevrangeByScore(key, max, min);
		final List<String> members = client.getMultiBulkReply();
		if (members == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		return MuvikSetFromList.of(members);
	}

	public Set<String> zrevrangeByScore(final String key, final double max, final double min, final int offset,
			final int count) {
		checkIsInMultiOrPipeline();
		client.zrevrangeByScore(key, max, min, offset, count);
		final List<String> members = client.getMultiBulkReply();
		if (members == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		return MuvikSetFromList.of(members);
	}

	public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min) {
		checkIsInMultiOrPipeline();
		client.zrevrangeByScoreWithScores(key, max, min);
		return getTupledSet();
	}

	public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min, final int offset,
			final int count) {
		checkIsInMultiOrPipeline();
		client.zrevrangeByScoreWithScores(key, max, min, offset, count);
		return getTupledSet();
	}

	public Set<Tuple> zrevrangeByScoreWithScores(final String key, final String max, final String min, final int offset,
			final int count) {
		checkIsInMultiOrPipeline();
		client.zrevrangeByScoreWithScores(key, max, min, offset, count);
		return getTupledSet();
	}

	public Set<String> zrevrangeByScore(final String key, final String max, final String min, final int offset,
			final int count) {
		checkIsInMultiOrPipeline();
		client.zrevrangeByScore(key, max, min, offset, count);
		final List<String> members = client.getMultiBulkReply();
		if (members == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		return MuvikSetFromList.of(members);
	}

	public Set<Tuple> zrevrangeByScoreWithScores(final String key, final String max, final String min) {
		checkIsInMultiOrPipeline();
		client.zrevrangeByScoreWithScores(key, max, min);
		return getTupledSet();
	}

	@Override
	public Set<String> zrangeByLex(final String key, final String min, final String max) {
		checkIsInMultiOrPipeline();
		client.zrangeByLex(key, min, max);
		final List<String> members = client.getMultiBulkReply();
		if (members == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		return MuvikSetFromList.of(members);
	}

	@Override
	public Set<String> zrangeByLex(final String key, final String min, final String max, final int offset,
			final int count) {
		checkIsInMultiOrPipeline();
		client.zrangeByLex(key, min, max, offset, count);
		final List<String> members = client.getMultiBulkReply();
		if (members == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		return MuvikSetFromList.of(members);
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min) {
		checkIsInMultiOrPipeline();
		client.zrevrangeByLex(key, max, min);
		final List<String> members = client.getMultiBulkReply();
		if (members == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		return MuvikSetFromList.of(members);
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
		checkIsInMultiOrPipeline();
		client.zrevrangeByLex(key, max, min, offset, count);
		final List<String> members = client.getMultiBulkReply();
		if (members == null) {
			return MuvikSetFromList.of(Collections.emptyList());
		}
		return MuvikSetFromList.of(members);
	}
}
