package com.ai.thinking.in.spring.bean.factory;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link com.ai.thinking.in.spring.ioc.overview.domain.User}
 * {@link org.springframework.beans.factory.FactoryBean}
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
