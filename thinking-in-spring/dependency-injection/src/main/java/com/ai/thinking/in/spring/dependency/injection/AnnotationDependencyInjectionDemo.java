package com.ai.thinking.in.spring.dependency.injection;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;

import java.util.Map;
import java.util.Optional;

/**
 * 注解驱动的依赖注入过程
 */
public class AnnotationDependencyInjectionDemo {

    @Autowired
    @Lazy
    private User lazyUser;

    @Autowired          //依赖查找（处理)
    private User user;  //DependencyDescriptor ->
                        //必须(required=true)
                        //实时注入（eager=true）
                        //通过类型(User.class)
                        //字段名称("user")
                        //是否首要(primary=true
    @Autowired
    private Map<String,User> users;

    @Autowired
    private Optional<User> optionalUser;
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjectionDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("classpath:META-INF/dependency-lookup-context.xml");
        applicationContext.refresh();

        AnnotationDependencyInjectionDemo bean = applicationContext.getBean(AnnotationDependencyInjectionDemo.class);
        //System.out.println("bean.user="+bean.user);
        System.out.println("bean.users="+bean.users);
        System.out.println("bean.optional="+bean.optionalUser);
        applicationContext.close();
    }

}
