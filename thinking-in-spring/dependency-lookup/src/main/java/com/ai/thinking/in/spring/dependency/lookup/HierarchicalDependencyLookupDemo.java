package com.ai.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        //创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类作为配置类
        applicationContext.register(HierarchicalDependencyLookupDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        //1.获取HierarchicalBeanFactory <-ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("当前BeanFactory的ParentBeanFactory："+beanFactory.getParentBeanFactory());
        HierarchicalBeanFactory parentBeanFactory = createBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前BeanFactory的ParentBeanFactory："+beanFactory.getParentBeanFactory());
        displayContainsLocalBean(beanFactory,"user");
        displayContainsLocalBean(parentBeanFactory,"user");
        displayContainsBean(beanFactory,"user");
        displayContainsBean(parentBeanFactory,"user");
        //2.设置Parent BeanFactory
        //关闭应用上下文
        applicationContext.close();
    }
    private static void displayContainsBean(HierarchicalBeanFactory beanFactory,String beanName){
        System.out.printf("当前BeanFactory[%s]是否包含  bean [name:%s]:%s\n",beanFactory,beanName
                ,containsBean(beanFactory,beanName));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory,String beanName){
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if(parentBeanFactory instanceof HierarchicalBeanFactory){
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if(containsBean(parentHierarchicalBeanFactory,beanName)){
                return true;
            }
        }
        return beanFactory.containsBean(beanName);
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory,String beanName){
        System.out.printf("当前BeanFactory[%s]是否包含 local bean [name:%s]:%s\n",beanFactory,beanName
                ,beanFactory.containsLocalBean(beanName));
    }
    private static HierarchicalBeanFactory createBeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(location);
        return beanFactory;
    }
}
