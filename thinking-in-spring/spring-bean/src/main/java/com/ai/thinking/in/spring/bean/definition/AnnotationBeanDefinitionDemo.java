package com.ai.thinking.in.spring.bean.definition;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
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
        //注册Configuration Class(配置类)
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        //通过BeanDefinition注册API实现
        //1.命名Bean的注册方式
        registerUserBeanDefinition(applicationContext,"sky-user");
        //2.非命名Bean的方式
        registerUserBeanDefinition(applicationContext);
        //启动应用上下文
        applicationContext.refresh();
        System.out.println("Config类型的所有Beans："+applicationContext.getBeansOfType(Config.class));
        System.out.println("User类型的所有Beans："+applicationContext.getBeansOfType(User.class));
        //显示的关闭Spring应用上下文
        applicationContext.close();
    }

    /**
     * 命名Bean的注册方式
     * @param registry
     * @param beanName
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry,String beanName){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id","1").addPropertyValue("name","AI");
        if(StringUtils.hasText(beanName)){
            //注册BeanDefinition
            registry.registerBeanDefinition(beanName,beanDefinitionBuilder.getBeanDefinition());
        }else{
            //非命名Bean注册方法
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(),registry);
        }

    }
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry){
        registerUserBeanDefinition(registry,null);
    }
    //2通过@Component方式
    @Component//定义当前类作为SpringBean(组件）
    public static class Config{
        //1通过@Bean方式定义
        /**
         * 通过Java注解方式，定义了一个Bean
         * @return
         */
        @Bean(name = {"user","ai-user"})
        public User user(){
            User user = new User();
            user.setId(1L);
            user.setName("AI");
            return user;
        }
    }

}
