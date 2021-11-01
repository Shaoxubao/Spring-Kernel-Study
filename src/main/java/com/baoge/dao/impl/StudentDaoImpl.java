package com.baoge.dao.impl;

import com.baoge.bean.Student;
import com.baoge.dao.StudentDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class StudentDaoImpl implements StudentDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveStudent(Student student) {
        String sql = "insert into student(name, age) values (?, ?)";
        this.jdbcTemplate.update(sql, student.getName(), student.getAge());
    }
}
