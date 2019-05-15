package com.jk;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@SpringBootApplication
@MapperScan("com.jk.dao")
@EnableTransactionManagement
public class ZuoyeBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuoyeBootApplication.class, args);
    }

    @Configuration
    public class MyBatisConfiguration {
        @Bean
        public PageHelper pageHelper() {
            System.out.println("MyBatisConfiguration.pageHelper()");
            PageHelper pageHelper = new PageHelper();
            Properties p = new Properties();
            p.setProperty("offsetAsPageNum", "true");
            p.setProperty("rowBoundsWithCount", "true");
            p.setProperty("reasonable", "true");
            pageHelper.setProperties(p);
            return pageHelper;
        }
    }

}
