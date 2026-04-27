package com.example.controller;

import com.example.dto.StudentDto;
import com.example.service.StudentService;
import com.example.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/allStudent")
    public Result getAllStudent(){
        return  Result.success(studentService.getAllStudent());
    }

    @GetMapping("/updateStatus")
    public Result updateStatus(){
        return  Result.success(studentService.batchUpdate());
    }
    @GetMapping("/delete")
    public Result deleteGirl(){
        return  Result.success(studentService.deleteStudent());
    }
    @PostMapping("/student")
    public Result createStudent(@RequestBody StudentDto studentDto) {
        return studentService.createStudent(studentDto);
    }

}
