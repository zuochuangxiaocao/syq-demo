package com.syq.demo.validation.validator.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义校验实现
 */
public class HaveNoBlankValidator implements ConstraintValidator<CustomValidator,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // null 不做检验
        if (value == null) {
            return true;
        }
        if (value.contains("1")) {
            // 校验失败
            return false;
        }
        // 校验成功
        return true;
    }
}
