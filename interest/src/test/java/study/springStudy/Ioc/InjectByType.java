package study.springStudy.Ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * DI
 * 上面我们获取的都是利用无参的构造函数得到的java bean，这和想的差的有点远，我想要的是一幅画，他却给了我一张白纸。这怎么能行！
 *
 * 为了区别通过类型注入还是名称注入，我写了两个注解用于区分
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface InjectByType {

}
