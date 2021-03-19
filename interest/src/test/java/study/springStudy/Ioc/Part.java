package study.springStudy.Ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName Part
 * @Author zhaofu
 * @Date 2020/10/16
 * @Version V1.0
 * 如何把一个类注册进去呢？首先我们要让容器“发现”它，所以使用注解，声明它应当加入容器
 * 其中的value即对应的是Spring中的Bean name
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Part {
    String value() default "";
}