package com.example.application.config.annotation;

import org.jsoup.helper.StringUtil;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import static com.example.application.utils.string.StringUtil.isNumeric;

/**
 * @ClassName: ProductIdStrValidated
 * @Description:
 * @date 2020/5/23 10:23
 */
public class ProductIdStrValidated implements ConstraintValidator<ProductIdStrRule, String> {

    private String message;

    @Override
    public void initialize(ProductIdStrRule constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(value);
        if (StringUtil.isBlank(value)) {
            return true;
        }
        if (value.lastIndexOf(",") + 1 == value.length() || value.indexOf(",") == 0) {
            return false;
        }
        String[] split = value.split(",");
        boolean flag = true;
        for (String s : split) {
            if (!isNumeric(s)) {
                flag = false;
            }
        }
        return flag;
    }
}
