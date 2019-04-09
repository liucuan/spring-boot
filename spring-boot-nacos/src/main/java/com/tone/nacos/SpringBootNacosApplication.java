package com.tone.nacos;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "nacosApp", autoRefreshed = true)
public class SpringBootNacosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootNacosApplication.class, args);
	}

}
