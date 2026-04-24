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
        // 查询所有学生
        StudentExample studentExample = new StudentExample();
//        studentExample.createCriteria().andAgeBetween((byte) 15, (byte) 30).andMajorEqualTo("软件工程");
        studentExample.createCriteria();
        return studentMapper.selectByExample(studentExample);
    }

    public int batchUpdate(){
        // 批量更新：把所有年龄小于20岁的用户状态设为3
        StudentExample example = new StudentExample();
        example.createCriteria().andAgeLessThan((byte) 20);
        Student updateStudent = new Student();
        updateStudent.setStatus((byte)3);
        studentMapper.updateByExampleSelective(updateStudent, example);
        return 0;
    }
}
