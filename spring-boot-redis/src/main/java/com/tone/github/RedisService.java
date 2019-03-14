package com.tone.github;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.*;

import com.alibaba.fastjson.JSON;

@Component
public class RedisService {

    private static final int LOCK_EXPIRE_TIME = 10;
    public static final Long LONG_1 = new Long(1);
    @Autowired
    private JedisPool jedisPool;

    public String set(final String key, final String value, final String nxxx, final String expx,
        final long time) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.set(key, value, nxxx, expx, time);
        } finally {
            close(jedis);
        }
    }

    /**
     * 释放分布式锁
     *
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public boolean releaseDistributedLock(String lockKey, String requestId) {
        Jedis jedis = jedisPool.getResource();
        try {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = jedis
                .eval(script, Collections.singletonList(lockKey),
                    Collections.singletonList(requestId));
            if (new Long(1).equals(result)) {
                return true;
            }
            return false;
        } finally {
            close(jedis);
        }
    }

    public Long zadd(String key, double score, String member) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zadd(key, score, member);
        } finally {
            close(jedis);
        }
    }

    public Long zadd(String key, Map<String, Double> map) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zadd(key, map);
        } finally {
            close(jedis);
        }
    }

    public Long zaddBin(String key, Map<Object, Double> map) {
        Jedis jedis = jedisPool.getResource();
        try {
            Map<String, Double> m = new HashMap<>(map.size());
            for (Map.Entry<Object, Double> entry : map.entrySet()) {
                m.put(encode(entry.getKey()), entry.getValue());
            }
            return jedis.zadd(key, m);
        } finally {
            close(jedis);
        }
    }

    public List<String> zrange(String key, long start, long end) {
        Jedis jedis = jedisPool.getResource();
        try {
            return new ArrayList<>(jedis.zrange(key, start, end));
        } finally {
            close(jedis);
        }
    }

    /**
     * 查询sorted set成员数目
     */
    public Long zcard(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zcard(key);
        } finally {
            close(jedis);
        }
    }

    public Long zaddBin(String key, double score, Object obj) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zadd(key, score, encode(obj));
        } finally {
            close(jedis);
        }
    }

    public <T> List<T> zrangeBin(String key, long start, long end, Class<T> type) {
        Jedis jedis = jedisPool.getResource();
        try {
            Set<String> set = jedis.zrange(key, start, end);
            List<T> list = new ArrayList<>(set.size());
            for (String ele : set) {
                list.add(decode(ele, type));
            }
            return list;
        } finally {
            close(jedis);
        }
    }

    private String encode(Object obj) {
        return JSON.toJSONString(obj);
    }

    private <T> T decode(String str, Class<T> type) {
        return JSON.parseObject(str, type);
    }

    public void delBigHash(String bigHashKey) {
        Jedis jedis = jedisPool.getResource();
        try {
            ScanParams scanParams = new ScanParams().count(100);
            String cursor = "0";
            do {
                ScanResult<Map.Entry<String, String>> scanResult = jedis
                    .hscan(bigHashKey, cursor, scanParams);
                List<Map.Entry<String, String>> entryList = scanResult.getResult();
                if (entryList != null && !entryList.isEmpty()) {
                    for (Map.Entry<String, String> entry : entryList) {
                        jedis.hdel(bigHashKey, entry.getKey());
                    }
                }
                cursor = scanResult.getStringCursor();
            } while (!"0".equals(cursor));
            // 删除bigkey
            jedis.del(bigHashKey);
        } finally {
            close(jedis);
        }
    }

    public void delBigList(String bigListKey) {
        Jedis jedis = jedisPool.getResource();
        try {
            long llen = jedis.llen(bigListKey);
            int counter = 0;
            int left = 100;
            while (counter < llen) {
                // 每次从左侧截掉100个
                jedis.ltrim(bigListKey, left, llen);
                counter += left;
            }
            // 最终删除key
            jedis.del(bigListKey);
        } finally {
            close(jedis);
        }
    }

    public void delBigSet(String bigSetKey) {
        Jedis jedis = jedisPool.getResource();
        try {
            ScanParams scanParams = new ScanParams().count(100);
            String cursor = "0";
            do {
                ScanResult<String> scanResult = jedis.sscan(bigSetKey, cursor, scanParams);
                List<String> memberList = scanResult.getResult();
                if (memberList != null && !memberList.isEmpty()) {
                    for (String member : memberList) {
                        jedis.srem(bigSetKey, member);
                    }
                }
                cursor = scanResult.getStringCursor();
            } while (!"0".equals(cursor));
            // 删除bigkey
            jedis.del(bigSetKey);
        } finally {
            close(jedis);
        }
    }

    public void delBigZset(String bigZsetKey) {
        Jedis jedis = jedisPool.getResource();
        try {
            ScanParams scanParams = new ScanParams().count(100);
            String cursor = "0";
            do {
                ScanResult<Tuple> scanResult = jedis.zscan(bigZsetKey, cursor, scanParams);
                List<Tuple> tupleList = scanResult.getResult();
                if (tupleList != null && !tupleList.isEmpty()) {
                    for (Tuple tuple : tupleList) {
                        jedis.zrem(bigZsetKey, tuple.getElement());
                    }
                }
                cursor = scanResult.getStringCursor();
            } while (!"0".equals(cursor));
            // 删除bigkey
            jedis.del(bigZsetKey);
        } finally {
            close(jedis);
        }
    }

    private void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 获取锁，默认锁超时时间10秒
     *
     * @param lockName 锁名
     * @return 是否成功
     */
    public boolean acquireLock(final String lockName) {
        return acquireLock(lockName, LOCK_EXPIRE_TIME);
    }

    /**
     * 获取锁
     *
     * @param lockName 锁名
     * @param lockExpireTime 超时时间，单位秒
     * @return 是否成功
     */
    public boolean acquireLock(final String lockName, final int lockExpireTime) {
        Jedis jedis = jedisPool.getResource();
        try {
            return "OK".equals(jedis.set(lockName, "lock", "NX", "EX", lockExpireTime));
        } finally {
            close(jedis);
        }

    }

    public boolean releaseLock(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = jedis
                .eval(script, Collections.singletonList(key),
                    Collections.singletonList("lock"));
            if (LONG_1.equals(result)) {
                return true;
            }
            return false;
        } finally {
            close(jedis);
        }
    }
}
