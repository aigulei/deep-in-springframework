package com.ai.thinking.in.spring.bean.lifecycle;

import com.ai.thinking.in.spring.ioc.overview.domain.User;

public class UserHolder {
    private final User user;

    public UserHolder(User user){
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
