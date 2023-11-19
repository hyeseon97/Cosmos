package com.ssafy.bicycle.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.bicycle.model.dao")
public class DBConfig {

}