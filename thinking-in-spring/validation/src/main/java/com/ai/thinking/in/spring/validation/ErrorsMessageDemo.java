package com.ai.thinking.in.spring.validation;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

public class ErrorsMessageDemo {
    public static void main(String[] args) {
        //0.创建User对象
        User user = new User();
        user.setName("China");
        //1.选择Errors->BeanPropertyBindingResult
        Errors errors = new BeanPropertyBindingResult(user,"user");
        //2. 调用reject或rejectValue
        errors.reject("user.properties.not.null");
        errors.rejectValue("name","name.required");
        //reject生成ObjectError
        //rejectValue生成FieldError
        //3.获取Errors中国ObjectError和FieldError
        List<ObjectError> globalErrors = errors.getGlobalErrors();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        List<ObjectError> allErrors = errors.getAllErrors();

        //4通过ObjectError和FieldError中的code和args来关联MessageSource实现
        MessageSource messageSource= createMessageSource();
        for(ObjectError error:allErrors){
            String message = messageSource.getMessage(error.getCode(),error.getArguments(),Locale.getDefault());
            System.out.println(message);
        }
    }

    private static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.properties.not.null", Locale.getDefault(),"User属性不能为空");

        messageSource.addMessage("name.required", Locale.getDefault(),"the name of user must not be null");
        return messageSource;
    }
}
