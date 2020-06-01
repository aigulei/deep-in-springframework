package com.ai.thinking.in.spring.bean.factory;

import com.ai.thinking.in.spring.ioc.overview.domain.User;

/**
 * {@link User}工厂类
 */
public interface UserFactory {
    default User createUser(){
        return User.createUser();
    }
}
