package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(String name) {
        return "hello ,welcome to code world!";
    }

    @GetMapping("/data")
    public Map<String, Object> jsonData( ) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "welcome to Java development");
        return result;
    }

    @GetMapping("/test")
    public String test(@RequestParam Integer id) {
        return "hello" + id ;
    }

    @GetMapping("/{code}")
    public String path(@PathVariable String code) {
        return "hello " + code ;
    }

}
