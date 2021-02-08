package com.ai.thinking.in.spring.dependency.injection;

import com.ai.thinking.in.spring.dependency.injection.annotation.InjectedUser;
import com.ai.thinking.in.spring.dependency.injection.annotation.MyAutowired;
import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

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
    @MyAutowired
    private Optional<User> optionalUser;

    @Inject
    private User injectUser;

    @InjectedUser
    private User myInjectUser;

    /*@Bean(name=AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        Set<Class<? extends Annotation>> autowiredAnnotationTypes =
                new LinkedHashSet<>(asList(Autowired.class,Inject.class,InjectedUser.class));
        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
        return beanPostProcessor;
    }*/
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return beanPostProcessor;
    }

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
        System.out.println("bean.myInjectUser="+bean.myInjectUser);
        applicationContext.close();
    }
}
