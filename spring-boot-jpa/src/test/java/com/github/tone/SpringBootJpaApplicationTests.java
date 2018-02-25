package com.github.tone;

import com.github.tone.dao.PoetryRepository;
import com.github.tone.dao.UserRepository;
import com.github.tone.domain.Poetry;
import com.github.tone.domain.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJpaApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PoetryRepository poetryRepository;

    @Test
    public void test() throws Exception {

        // 创建10条记录
        userRepository.save(new User("AAA", 10));
        userRepository.save(new User("BBB", 20));
        userRepository.save(new User("CCC", 30));
        userRepository.save(new User("DDD", 40));
        userRepository.save(new User("EEE", 50));
        userRepository.save(new User("FFF", 60));
        userRepository.save(new User("GGG", 70));
        userRepository.save(new User("HHH", 80));
        userRepository.save(new User("III", 90));
        userRepository.save(new User("JJJ", 100));

        // 测试findAll, 查询所有记录
        Assert.assertEquals(10, userRepository.findAll().size());

        // 测试findByName, 查询姓名为FFF的User
        Assert.assertEquals(60, userRepository.findByName("FFF").getAge().longValue());

        // 测试findUser, 查询姓名为FFF的User
        Assert.assertEquals(60, userRepository.findUser("FFF").getAge().longValue());

        // 测试findByNameAndAge, 查询姓名为FFF并且年龄为60的User
        Assert.assertEquals("FFF", userRepository.findByNameAndAge("FFF", 60).getName());

        // 测试删除姓名为AAA的User
        userRepository.delete(userRepository.findByName("AAA"));

        // 测试findAll, 查询所有记录, 验证上面的删除是否成功
        Assert.assertEquals(9, userRepository.findAll().size());

    }

    @Test
    public void initPoetry() throws IOException {
        String path = "/Users/echolau/Documents/《宋词三百首》.txt";
        String line;
        String name = "";
        StringBuilder sb = null;
        Poetry poetry = null;
        List<Poetry> list = new ArrayList<>(1000);
        boolean flag = true;
        try (LineIterator it = FileUtils.lineIterator(new File(path), "UTF-8");) {
            while (it.hasNext()) {
                line = it.nextLine();
                if (StringUtils.isBlank(line) && sb.length() == 0) {
                    continue;
                }
                line = line.trim();
                if (line.contains(" ")) {
                    if (poetry != null) {
                        poetry.setContent(sb.toString());
                        list.add(poetry);
                    }
                    sb = new StringBuilder(1000);
                    flag = true;
                    String[] strs = line.split(" ");
                    if (strs.length == 2) {
                        if (strs[1].length() <= 4) {
                            poetry = new Poetry();
                            name = strs[0];
                            poetry.setName(name);
                            poetry.setAuthor(strs[1]);
                            System.out.println(strs[1]);
                            continue;
                        }
                    } else {
                        if (strs[strs.length - 1].length() <= 4) {
                            poetry = new Poetry();
                            for (int i = 0; i < strs.length - 2; i++) {
                                name += strs[i];
                            }
                            poetry.setName(name);
                            poetry.setAuthor(strs[strs.length - 1]);
                            System.out.println(strs[strs.length - 1]);
                            continue;
                        }
                    }

                }
                if (flag && StringUtils.isNoneBlank(line) && !line.contains("，") && !line.contains("。")) {
                    poetry.setName(poetry.getName() + "-" + line);
                    continue;
                }
                if (sb.length() > 0) {
                    sb.append('\n');
                }
                sb.append(line);
                flag = false;
            }
        }
        list.stream().forEach(p -> System.out.println(p.getName()));
//        poetryRepository.save(list);
//        System.out.println(list);
    }

}
