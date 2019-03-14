package com.tone.github.cache;

import com.tone.github.RedisService;
import com.tone.github.cache.support.KeyLock;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 按key进行锁的缓存worker，用在write方法可能执行较长，且很多其他模块调用的情况下。避免因为write锁住所有读线程。
 * 做为key的Z，必须实现equal和hash, 并且.toString()方法保证是不同key生成不同的值，避免不同的key锁是同一个。
 * <p/>
 */
public abstract class KeyBlockedCacheWorker<T, Z> implements CacheWorker<T, Z> {
    protected static final int DEFAULT_CACHE_TIME = 24 * 60 * 60;
    protected int defaultCacheTime = DEFAULT_CACHE_TIME;

    private final KeyLock<Z> keyLock = new KeyLock<Z>();

    @Autowired
    protected RedisService redisService;

    /**
     * 读Redis缓存的实现
     *
     * @param z 输入参数
     * @return 输出读取结果
     */
    protected T read(Z z) { // NOSONAR
        throw new IllegalArgumentException("read 方法未定义");
    }

    /**
     * 回源并写Redis缓存的实现
     *
     * @param z 输入参数
     * @return 输出回源结果
     */
    protected T write(Z z) { // NOSONAR
        throw new IllegalArgumentException("write 方法未定义");
    }

    /**
     * 单条回源后，立即执行方法入口
     *
     * @param t 输入对象
     * @return 输出对象
     */
    protected T beforeReturn(T t) {
        return t;
    }

    @Override
    public final T find(Z z) {
        if (z == null) {
            return null;
        }
        boolean lockedFail = false;
        do {
            if (lockedFail) {
                lockedFail = false;
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException ex) {
                    return null;
                }
            }
            keyLock.lock(z);
            try {
                T t = read(z);
                if (t == null) {
                    if (redisService.acquireLock(z.toString())) {
                        try {
                            t = write(z);
                        } finally {
                            redisService.releaseLock(z.toString());
                        }
                    } else {
                        lockedFail = true;
                        continue;
                    }
                }
                return beforeReturn(t);
            } finally {
                keyLock.unlock(z);
            }
        } while (true);
    }

    @Override
    public final List<T> batchFind(List<Z> list) {
        throw new IllegalArgumentException("batchFind 方法未定义");
    }

    private String getLockKey(Z z) {
        StringBuilder sb = new StringBuilder();
        sb.append("k_lock_");
        sb.append(this.getClass().getSimpleName());
        sb.append(z);
        return sb.toString();
    }
}
