package com.tone.druid;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.druid.pool.DruidDataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDruidApplication.class)
@ActiveProfiles("multi-datasource")
public class MultiDataSourceTests {
    @Resource
    private DruidDataSource dataSourceOne;

    @Resource
    private DruidDataSource dataSourceTwo;

    @Test
    public void testDataSourceOne() throws SQLException {
        assertThat(dataSourceOne.getUrl()).isEqualTo("jdbc:h2:file:./demo-db");
        assertThat(dataSourceOne.getUsername()).isEqualTo("sa");
        assertThat(dataSourceOne.getPassword()).isEqualTo("sa");
        assertThat(dataSourceOne.getDriverClassName()).isEqualTo("org.h2.Driver");
        assertThat(dataSourceOne.getInitialSize()).isEqualTo(5);
        assertThat(dataSourceOne.getMaxActive()).isEqualTo(10);
        assertThat(dataSourceOne.getMaxWait()).isEqualTo(10000);
    }

    @Test
    public void testDataSourceTwo() throws SQLException {
        assertThat(dataSourceTwo.getUrl()).isEqualTo("jdbc:h2:file:./demo-db");
        assertThat(dataSourceTwo.getUsername()).isEqualTo("sa");
        assertThat(dataSourceTwo.getPassword()).isEqualTo("sa");
        assertThat(dataSourceTwo.getDriverClassName()).isEqualTo("org.h2.Driver");
        assertThat(dataSourceTwo.getInitialSize()).isEqualTo(5);
        assertThat(dataSourceTwo.getMaxActive()).isEqualTo(20);
        assertThat(dataSourceTwo.getMaxWait()).isEqualTo(20000);
    }
}
