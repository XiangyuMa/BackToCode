package com.example.service;

import com.example.entity.Student;
import com.example.entity.StudentExample;
import com.example.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;


    public List<Student> getAllStudent() {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andAgeBetween((byte) 15, (byte) 30).andMajorEqualTo("软件工程");
        return studentMapper.selectByExample(studentExample);
    }
}
