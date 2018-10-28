package com.tone.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 下面将使用Spring Batch将csv文件中数据使用JDBC批处理的方式插入数据库。
 */
@SpringBootApplication
public class SpringBootBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBatchApplication.class, args);
    }
}
