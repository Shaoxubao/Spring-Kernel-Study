package com.baoge;

import com.baoge.aop.service.MyService;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Spring中BeanFactory和FactoryBean的区别:
 * BeanFactory是Spring IOC的工厂，它里面管理着Spring所创建出来的各种Bean对象，
 * 当我们在配置文件(注解)中声明了某个bean的id后，通过这个id就可以获取到与该id所对应的class对象的实例
 * （可能新建，也可能从缓冲中获取）
 * FactoryBean本质上也是一个bean，它与其它bean一样，也是由BeanFactory所管理和维护的，
 * 当然它的实例也会缓存到Spring的工厂中（如果是单例），它与普通的bean的唯一区别在于，
 * 当Spring创建一个FactoryBean的实例后，它接下来会判断当前所创建的bean是否是一个FactoryBean实例，
 * 如果不是，那么就直接将创建出来的bean返回给客户端；如果是，那么它会对其进行进一步的处理 ，
 * 根据配置文件所配置的target，advisor与interfaces等信息，在运行期动态构建出一个类，并生成该类的一个实例，
 * 最后将该实例返回给客户端，因此，我们在声明一个FactoryBean时，通过id获取到的并非这个FactoryBean的实例，
 * 而是它动态生成出来的一个代理对象（通过三种方式来进行生成）
 *
 * 关于Spring AOP代理的生成过程：
 * 1.通过ProxyFactoryBean(FactoryBean接口的实现)来配置相应的代理对象的相关信息；
 * 2.在获取ProxyFactoryBean实例时，本质上并不是获取到ProxyFactoryBean的对象，而是获取到了由ProxyFactoryBean所返回
 *   的那个对象实例。
 * 3.在整个ProxyFactoryBean实例的构建与缓存过程中，其流程与普通的bean完全一致。
 * 4.差别在于，当创建了ProxyFactoryBean对象后，Spring会判断当前所创建的对象是否是一个FactoryBean实例，如果不是，那么
 *   Spring直接将所创建的对象返回。
 * 5.如果是，Spring则会进入到一个新的流程分支中；
 * 6.Spring会根据我们在配置信息中所指定的各种元素，如目标对象是否实现了接口以及Advisor等信息，使用动态代理或CGLIB等方式
 *   来为目标对象创建相应的代理对象。
 * 7.当相应的代理对象创建完毕后，Spring就会通过ProxyFactoryBean的getObject方法，将所创建的对象返回。
 * 8.对象返回到调用端(客户端)，它本质上是一个代理对象，可以代理对目标对象的访问与调用，这个代理对象对用户来说，就像目标
 *   对象一样，客户端在使用代理对象时，可以正常调用目标对象的方法，同时在执行过程中，会根据在配置文件中配置的信息在调用
 *   前后执行额外的附加逻辑。
 */
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
