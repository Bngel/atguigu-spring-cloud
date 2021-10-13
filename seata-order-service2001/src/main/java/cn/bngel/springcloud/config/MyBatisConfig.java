package cn.bngel.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"cn.bngel.springcloud.dao"})
public class MyBatisConfig {
}
