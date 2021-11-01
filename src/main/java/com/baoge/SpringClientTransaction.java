package com.baoge;

import com.baoge.bean.Student;
import com.baoge.service.StudentService;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Spring事务
 */
public class SpringClientTransaction {

    public static void main(String[] args) {
        // 定义资源
        Resource resource = new ClassPathResource("applicationContext-transaction.xml");

        // 创建工厂
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        // 创建读取器
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);

        beanDefinitionReader.loadBeanDefinitions(resource);

        // 从工厂中获取bean
        StudentService studentService = (StudentService) defaultListableBeanFactory.getBean("studentServiceProxy");

        Student student = new Student();
        student.setAge(22);
        student.setName("baoge");
        studentService.saveStudent(student);
    }
}
