package com.example.service;

import com.example.dto.StudentDto;
import com.example.entity.Student;
import com.example.entity.StudentExample;
import com.example.mapper.StudentMapper;
import com.example.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;



    public List<Student> getAllStudent() {
        // 查询所有学生
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andAgeBetween((byte) 15, (byte) 30).andMajorEqualTo("软件工程");
//        studentExample.createCriteria();
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

    public int deleteStudent(){
        StudentExample example = new StudentExample();
        example.createCriteria().andGenderEqualTo( "女");
        Student updateStudent = new Student();
        updateStudent.setIsDeleted(true);
        studentMapper.updateByExampleSelective(updateStudent, example);
        return 0;
    }
    /**
     * 创建学生（幂等保证）: 利用数据库表的唯一约束，当尝试插入重复数据时，数据库会抛出DUPLICATE KEY异常，从而阻止第二次插入。
     * @param studentDto 创建学生请求
     * @return 学号
     */
    public Result createStudent(StudentDto studentDto) {
        if (studentDto == null){
            return  Result.error("学生信息必须完整！");
        }

        // 1. 生成业务唯一学生号（规则：时间戳 + 用户ID后缀 + 随机数）
//        String studentNo = generateOrderNo(Long.valueOf(studentDto.getStudentNo()));
        String studentNo = studentDto.getStudentNo();
        log.info("学生创建成功, studentNo={}", studentNo); // INFO级别
        try {
            // 2. 在事务中执行
            return transactionTemplate.execute(status -> {
                try {
                    // 2.1 构建学生信息实体
                    Student student = new Student();
                    student.setStudentNo(studentNo);
                    student.setName(studentDto.getName());
                    student.setAddress(studentDto.getAddress());
                    student.setGender(studentDto.getGender());
                    student.setBirthDate(new Date());
                    student.setMajor(studentDto.getMajor());

                    // 2.2 插入数据库（如果学号重复，抛异常）
                    studentMapper.insert(student);

                    log.info("创建学生成功," + studentNo);
                    return Result.success(studentNo);

                } catch (DuplicateKeyException e) {
                    // 学生信息已存在，说明是重复请求
                    log.warn("学号已存在，可能是重复请求, studentNo={}", studentNo);

                    // 查询已存在的订单返回
                    StudentExample example = new StudentExample();
                    example.createCriteria().andStudentNoEqualTo(studentNo);
                    List<Student> existingStudent = studentMapper.selectByExample(example);
                    return Result.success(existingStudent);
                }
            });

        } catch (Exception e) {
            log.warn("创建学生信息失败!");
            return Result.error("existingStudent");
        }
    }

    /**
     * 生成订单号（保证唯一性）
     * 格式: YYYYMMDD + 用户ID后4位 + 时间戳后5位 + 随机数3位
     * 示例: 20250425123456789012345
     */
    private String generateOrderNo(Long userId) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String userSuffix = String.format("%04d", userId % 10000);
        String timestamp = String.valueOf(System.currentTimeMillis()).substring(7, 12);
        String random = String.format("%03d", new Random().nextInt(1000));
        return date + userSuffix + timestamp + random;
    }
}
