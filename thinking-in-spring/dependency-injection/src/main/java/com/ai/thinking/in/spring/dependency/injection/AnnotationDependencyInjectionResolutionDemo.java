package com.ai.thinking.in.spring.dependency.injection;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

/**
 * 注解驱动的依赖注入处理过程
 */
public class AnnotationDependencyInjectionResolutionDemo {

    @Autowired        //依赖查找（处理）
    private User user;//DependencyDescriptor->
                      //必须(required=true)
                     // 实时注入(eager=true)
                      //通过类型查找
                     //字段名称("user")
                     //是否首要(primary=true)
    @Autowired
    private Map<String,User> users;
    @Autowired
    private Optional<User> optionalUser;

    @Inject
    private User injectUser;
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("classpath:META-INF/dependency-lookup-context.xml");

        applicationContext.refresh();
        AnnotationDependencyInjectionResolutionDemo bean = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);
        System.out.println("bean.user="+bean.user);
        System.out.println("bean.injectUser="+bean.injectUser);
        System.out.println("bean.uses="+bean.users);
        System.out.println("bean.optionalUser="+bean.optionalUser);
        applicationContext.close();
    }
}
