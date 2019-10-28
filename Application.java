package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //启动应用程序，不需要一行xml配置，真的很赞啊！！！纯100%的Java了解一下~~~
        SpringApplication.run(Application.class, args);
    }
}
