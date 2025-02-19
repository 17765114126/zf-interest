package com.example.application.config.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
/**
 * @ClassName: ProductIdStrRule
 * @Description:
 * @date 2020/5/23 10:20
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = ProductIdStrValidated.class)
public @interface ProductIdStrRule {

    String message() default "逗号拼接格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
