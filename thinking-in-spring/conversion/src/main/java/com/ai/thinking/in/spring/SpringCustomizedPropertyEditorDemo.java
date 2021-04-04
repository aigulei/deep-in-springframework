package com.ai.thinking.in.spring;


import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCustomizedPropertyEditorDemo {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:/META-INF/property-editors-context.xml");
        //AbstractApplicationContext -> "conversionService" ConversionService Bean
        //-> ConfigurableBeanFactory#setConversionService -> AbstractBeanFactory#getConversionService ->BeanDefinition->BeanWrapper

        //BeanDefinition -> BeanWrapper ->属性转换(数据来源：PropertyValue)
        //setPropertyValue(PropertyValues) -> TypeConverter#convertIfNecessnary
        //TypeConverterDelegate#convertInNecessnary -> PropertyEditor or ConversionService

        User user = applicationContext.getBean(User.class);
        System.out.println(user);

        applicationContext.close();
    }
}
