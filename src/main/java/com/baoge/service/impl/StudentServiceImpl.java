package com.baoge.service.impl;

import com.baoge.bean.Student;
import com.baoge.dao.StudentDao;
import com.baoge.service.StudentService;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void saveStudent(Student student) {
        this.studentDao.saveStudent(student);

        throw new RuntimeException("saveStudent run error.");
    }
}
