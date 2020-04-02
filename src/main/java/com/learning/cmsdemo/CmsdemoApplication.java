package com.learning.cmsdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.learning.cmsdemo.mapper")
@SpringBootApplication
public class CmsdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsdemoApplication.class, args);
    }

}
