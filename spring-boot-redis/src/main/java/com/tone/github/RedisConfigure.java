package com.tone.github;

import java.util.ArrayList;
import java.util.List;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zhaoxiang.liu
 * @date 2018/3/20
 */
@Configuration
@EnableCaching
public class RedisConfigure extends CachingConfigurerSupport {
    private final Logger logger = LoggerFactory.getLogger(RedisConfigure.class);

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.pool.max-wait}")
    private int maxWaitMillis;

    @Bean
    public JedisPool redisPoolFactory() {
        logger.info("JedisPool注入成功！！");
        logger.info("redis地址：" + host + ":" + port);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 是否开启空闲资源监测
        // jedisPoolConfig.setTestWhileIdle(true);
        // 资源池中资源最小空闲时间(单位为毫秒)，达到此值后空闲资源将被移除
        // jedisPoolConfig.setMinEvictableIdleTimeMillis(60000);
        // 空闲资源的检测周期(单位为毫秒)
        // jedisPoolConfig.setTimeBetweenEvictionRunsMillis(30000);
        // 做空闲资源检测时，每次的采样数
        // jedisPoolConfig.setNumTestsPerEvictionRun(-1);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, null);
        // 预热池子
        List<Jedis> minIdleJedisList = new ArrayList<Jedis>(jedisPoolConfig.getMinIdle());
        for (int i = 0; i < jedisPoolConfig.getMinIdle(); i++) {
            Jedis jedis = null;
            try {
                jedis = jedisPool.getResource();
                minIdleJedisList.add(jedis);
                jedis.ping();
            }catch (Exception e) {
                logger.error(e.getMessage(), e);
            }finally {
            }
        }
        for (int i = 0; i < jedisPoolConfig.getMinIdle(); i++) {
            Jedis jedis = null;
            try {
                jedis = minIdleJedisList.get(i);
                jedis.close();
            }catch (Exception e) {
                logger.error(e.getMessage(), e);
            }finally {
            }
        }
        return jedisPool;
    }

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
        // 可以用"rediss://"来启用SSL连接
                .setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        // 设置缓存过期时间
        cacheManager.setDefaultExpiration(10000);
        return cacheManager;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        // 设置序列化工具
        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }

    private void setSerializer(StringRedisTemplate template) {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
    }
}
