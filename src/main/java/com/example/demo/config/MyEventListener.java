package com.example.demo.config;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener {

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        System.out.println("应用已就绪，执行逻辑");
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onContextRefreshed() {
        System.out.println("上下文刷新完成");
    }
}