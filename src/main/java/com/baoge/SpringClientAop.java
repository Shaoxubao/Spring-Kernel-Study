package com.baoge;

import com.baoge.aop.service.MyService;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class SpringClientAop {

    public static void main(String[] args) {
        // 定义资源
        Resource resource = new ClassPathResource("applicationContext-aop.xml");

        // 创建工厂
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        // 创建读取器
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);

        beanDefinitionReader.loadBeanDefinitions(resource);

        // 从工厂中获取bean
        MyService myService = (MyService) defaultListableBeanFactory.getBean("myAop");
        myService.myMethod();
    }
}
