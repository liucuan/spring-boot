package com.tone.druid;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDruidApplication.class)
@ActiveProfiles("filter")
public class FilterTests {
    @Resource
    private DruidDataSource dataSource;

    @Test
    public void test() {
        List<Filter> filters = dataSource.getProxyFilters();
        assertThat(filters.size()).isEqualTo(3);
    }
}
