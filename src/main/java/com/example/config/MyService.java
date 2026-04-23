package com.example.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class MyService {

    @PostConstruct
    public void init(){
        System.out.println("bean初始化后执行...");
    }
}
