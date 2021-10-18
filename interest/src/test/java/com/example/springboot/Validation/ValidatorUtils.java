package com.example.springboot.Validation;

import com.example.springboot.model.User;
import com.example.springboot.utils.Validator.CommonReturnConstants;
import com.example.springboot.utils.Validator.ValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 *
 * Spring中校验器(Validator)
 *
 * 手动验证参数
 *
 * @Author: weicl
 * @Date : 2021/6/10 14:51
 * @Modified By :
 */
public class ValidatorUtils {

    /**
     * 验证者
     */
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     *
     * @param object
     * @param groups
     */
    public static void validateEntity(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> validateResult = validator.validate(object, groups);
        com.example.springboot.utils.Validator.ValidationException validationException = new ValidationException(
            CommonReturnConstants.INVALID_JSON);
        if (!validateResult.isEmpty()) {
            for (ConstraintViolation violation : validateResult) {
                validationException
                    .addErrInfo(violation.getPropertyPath().toString(), violation.getMessage());
            }
            throw validationException;
        }
    }

    public static void main(String[] args) {
        User user = new User();
        ValidatorUtils.validateEntity(user);

    }
}
