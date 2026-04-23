package com.example.controller;

import com.example.entity.Student;
import com.example.service.StudentService;
import com.example.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/allStudent")
    public Result getAllStudent(){
        return  Result.success(studentService.getAllStudent());
    }

}
