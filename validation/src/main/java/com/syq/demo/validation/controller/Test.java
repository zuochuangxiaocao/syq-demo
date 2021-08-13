package com.syq.demo.validation.controller;

import com.syq.demo.validation.exception.RRException;
import com.syq.demo.validation.pojo.User;
import com.syq.demo.validation.validator.ValidatorUtils;
import com.syq.demo.validation.validator.custom.CustomValidator;
import com.syq.demo.validation.validator.group.AddGroup;
import com.syq.demo.validation.validator.group.UpdateGroup;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@RestController
@Validated
public class Test {

    @RequestMapping("/t1")
    String hello(@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return bindingResult.getFieldError().getDefaultMessage();
        }
        return "ok";
    }


    /**
     * 对象参数校验
     * @param user
     * @return
     */
    @RequestMapping("/t2")
    String hello(User user){

        ValidatorUtils.validateEntity(user, AddGroup.class);

        return "ok";
    }

    /**
     * 单个参数校验
     * @param name
     * @param age
     * @return
     */
    @RequestMapping("/t3")
    String hello(@NotBlank(message = "那么不能为空") String name,
                 @NotNull(message = "age不能为空") Integer age){
        return "ok";
    }

}
