package com.ai.thinking.in.spring.bean.definition;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 注解BeanDefinition示例
 */
//3通过@Import来进行导入
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class配置类
        applicationContext.register(Config.class);
        //通过BeanDefinition注册API实现
        //1：命名Bean的注册方式
        registerUserBeanDefinition(applicationContext,"mercyblitz-user");
        //2：非命名Bean的注册方式
        registerUserBeanDefinition(applicationContext);
        //启动Spring应用上下文
        applicationContext.refresh();
        Map<String, Config> configBeans = applicationContext.getBeansOfType(Config.class);
        //依赖查找
        System.out.println("Config Beans的所有Beans:"+applicationContext.getBeansOfType(Config.class));
        System.out.println("User类型的所有Beans："+applicationContext.getBeansOfType(User.class));

        //显示关闭Spring应用上下文
        applicationContext.close();
    }

    /**
     * 命名Bean的注册方式
     * @param registry
     * @param beanName
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry,String beanName){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id",1L);
        beanDefinitionBuilder.addPropertyValue("name","AI");
        //判断如果beanName参数存在时
        if(StringUtils.hasText(beanName)){
            //注册BeanDefinition
            registry.registerBeanDefinition(beanName,beanDefinitionBuilder.getBeanDefinition());
        }else{
            //非命名方式
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(),registry);
        }

    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry){
        registerUserBeanDefinition(registry,null);
    }

    //2通过@Component方式
    @Component
    public static class Config{
        //1通过@Bean方式定义
        @Bean(name = {"user","ai-user"})
        public User user(){
            User user = new User();
            user.setId(1L);
            user.setName("AI");
            return user;
        }
    }

}
