package com.onus.taskExecute;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.onus.taskExecute.mapper"})


public class TaskExecuteApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskExecuteApplication.class, args);
    }
}
