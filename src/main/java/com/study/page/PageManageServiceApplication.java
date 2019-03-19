package com.study.page;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties({LiquibaseProperties.class })
@MapperScan(value = "com.study.page.mapper")
@EnableScheduling
public class PageManageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PageManageServiceApplication.class, args);
    }

}
