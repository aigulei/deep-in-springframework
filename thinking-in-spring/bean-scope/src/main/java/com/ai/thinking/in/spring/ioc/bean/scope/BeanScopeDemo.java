package com.ai.thinking.in.spring.ioc.bean.scope;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

public class BeanScopeDemo implements DisposableBean {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public static User singletonUser(){
        return createUser();
    }
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser(){
        return createUser();
    }
    @Autowired
    @Qualifier("singletonUser")
    public User singletonUser;
    @Autowired
    @Qualifier("singletonUser")
    public User singletonUser1;
    @Autowired
    @Qualifier("prototypeUser")
    public User prototypeUser;
    @Autowired
    @Qualifier("prototypeUser")
    public User prototypeUser1;
    @Autowired
    @Qualifier("prototypeUser")
    public User prototypeUser2;

    @Autowired
    private Map<String,User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory; //Resolable Dependency

    private static User createUser(){
        User user = new User();
        user.setId(System.nanoTime());
        return  user;
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
           beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
               @Override
               public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                   System.out.printf("%s Bean名称:%s在初始化后回调....%n",bean.getClass().getName(),beanName);
                   return bean;
               }
           });
        });
        applicationContext.refresh();
        //结论一:
        //SingletonBean无论依赖查找还是依赖注入，均为同一个对象
        //PrototypeBean无论依赖查找还是依赖注入，均为新生成对象
        //结论二:
        //如果依赖注入稽核类型的对象，SingletonBen和PrototypeBean均会存在一个
        //PrototypeBean有别于其他地方的依赖注入
        //结论三：
        //无论是Singleton还是PrototypeBean均会执行初始化方法回调
        //不过仅SingletonBean会执行销毁方法
        scopedBeansByLookup(applicationContext);
        scopedBeanByInjection(applicationContext);
        applicationContext.close();
    }

    private static void scopedBeanByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo beanScopeDemo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("beanScopeDemo.singletonUser="+beanScopeDemo.singletonUser);
        System.out.println("beanScopeDemo.singletonUser1="+beanScopeDemo.singletonUser1);
        System.out.println("beanScopeDemo.prototypeUser="+beanScopeDemo.prototypeUser);
        System.out.println("beanScopeDemo.prototypeUser1="+beanScopeDemo.prototypeUser1);
        System.out.println("beanScopeDemo.prototypeUser2="+beanScopeDemo.prototypeUser2);
        System.out.println("beanScopeDemo.users="+beanScopeDemo.users);
    }

    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        for(int i=0;i<3;i++){
            System.out.println("singletonUser="+applicationContext.getBean("singletonUser",User.class));
            System.out.println("prototypeUser="+applicationContext.getBean("prototypeUser",User.class));
        }
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("当前BeanScopeDemo Bean正在销毁中");
        this.prototypeUser.destroy();
        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();
        for(Map.Entry<String,User> entry:users.entrySet()){
            String beanName =entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if(beanDefinition.isPrototype()){
                User value = entry.getValue();
                value.destroy();
            }
        }
        System.out.println("当前BeanScopeDemo Bean销毁中");

    }
}
