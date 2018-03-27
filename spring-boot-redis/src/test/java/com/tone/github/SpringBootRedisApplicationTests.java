package com.tone.github;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.RedissonLock;
import org.redisson.api.RLock;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.codec.KryoCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisApplicationTests {
    @Autowired
    DemoService demoService;

    @Autowired
    RedisService redisService;

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {
    }

    @Test
    public void test1() {
        for (int i = 0; i < 10; i++) {
            demoService.getStr((i % 2) + "");
        }
    }

    @Test
    public void test2() {
        String key = "lock:1";
        String l = redisService.set(key, "1", "NX", "PX", 30 * 1000L);
        System.out.println(l);
        boolean b = redisService.releaseDistributedLock(key, "2");
        System.out.println(b);
    }

    @Test
    public void test3() {
        RLock rLock = redissonClient.getLock("rLock:xx");
        rLock.lock(40, TimeUnit.SECONDS);
        RLock lock2 = new RedissonLock(((Redisson) redissonClient).getConnectionManager().getCommandExecutor(),
                "rLock:xx");
        lock2.unlock();
    }

    @Test
    public void test5() {
        RScoredSortedSet<Product> rs = redissonClient.getScoredSortedSet("p:o:p", new KryoCodec());
        rs.addAll(products().stream().collect(Collectors.toMap(p -> p, p -> p.getPrice())));
        RScoredSortedSet<Product> rs2 = redissonClient.getScoredSortedSet("p:o:p", new KryoCodec());
        Collection<Product> ps = rs2.valueRange(1, 5);
        for (Product p : ps) {
            System.out.println(p);
        }
    }

    @Test
    public void test4() {
        String key = "p:o:p";
        List<Product> products = products();
        Map<String, Double> map = toMap(products);
        redisService.zadd(key, map);
        List<String> set = redisService.zrange(key, 1, 8);
        System.out.println(redisService.zcard(key));
        for (String s : set) {
            System.out.println(decode(s, Product.class));
        }
        System.out.println("-------------------------");
        redisService.zadd(key, 3.0D, encode(new Product("xx", 3.0D)));
        System.out.println(redisService.zcard(key));
        set = redisService.zrange(key, 1, 8);
        for (String s : set) {
            System.out.println(decode(s, Product.class));
        }
        redisService.delBigZset(key);
    }

    private List<Product> products() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            products.add(new Product("name" + i, i));
        }
        return products;
    }

    private Map<String, Double> toMap(List<Product> products) {
        return products.stream().collect(Collectors.toMap(p -> encode(p), p -> p.getPrice()));
    }

    private String encode(Object obj) {
        return JSON.toJSONString(obj);
    }

    private <T> T decode(String str, Class<T> type) {
        return JSON.parseObject(str, type);
    }

    private static class Product {
        private String name;

        private double price;

        public Product() {
        }

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Product{" + "name='" + name + '\'' + ", price=" + price + '}';
        }
    }
}
