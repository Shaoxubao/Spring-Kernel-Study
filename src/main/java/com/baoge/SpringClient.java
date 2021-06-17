package com.baoge;

import com.baoge.bean.Student;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * IOC: 控制反转（Inverse of control）
 * DI: 依赖注入（Dependency Injection）
 *
 * 关于Spring容器管理Bean的过程以及加载模式：
 * 1.需要将bean的定义信息声明在Spring的配置文件中；
 * 2.需要通过Spring抽象出的各种Resource来指定对应的配置文件；
 * 3.需要显示声明一个Spring工厂，该工厂用来掌控我们在配置文件中所声明的各种bean以及bean之间的依赖关系与注入关系；
 * 4.需要定义一个配置信息读取器，用来读取所定义的bean配置文件信息；
 * 5.读取器的作用是读取所声明的配置文件信息，并且将读取后的信息装配到所声明的工厂之中；
 * 6.需要将读取器与工厂以及资源对象进行相应的关联处理；
 * 7.工厂所管理的全部对象装配完毕，可以供客户端直接调用，获取客户端想要使用的各种bean对象。
 *
 * Spring对于Bean管理的核心组件：
 * 1.资源抽象
 * 2.工厂
 * 3.配置信息读取器
 *
 * BeanFactory是Spring Bean工厂最顶层的抽象
 */
public class SpringClient {

    public static void main(String[] args) {
        // 定义资源
        Resource resource = new ClassPathResource("applicationContext.xml");

        // 创建工厂
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        // 创建读取器
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);

        beanDefinitionReader.loadBeanDefinitions(resource);

        // 从工厂中获取bean
        Student student = (Student) defaultListableBeanFactory.getBean("student");
        System.out.println(student.getName() + ": " + student.getAge());
    }
}
