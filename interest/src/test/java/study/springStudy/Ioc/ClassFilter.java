package study.springStudy.Ioc;

@FunctionalInterface
public interface ClassFilter{
    boolean test(Class c);
}
//通过这个接口我们就很容易地构造这么一个函数帮我们把所有有@Part注解的类生成好
