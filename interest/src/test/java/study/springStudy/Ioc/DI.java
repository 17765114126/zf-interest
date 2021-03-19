package study.springStudy.Ioc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName DI
 * @Author zhaofu
 * @Date 2020/10/16
 * @Version V1.0
 **/
public class DI {
    /**首先DI先必须知道到底对哪个容器注入，所以通过构造函数传入一个*/
    private IOCContainer iocContainer;
    public DI(IOCContainer iocContainer) {
        Objects.requireNonNull(iocContainer);
        this.iocContainer = iocContainer;
    }

    /**
     *先是对字段的按类型注入
     * @param o 需要被注入的类
     * @author dreamlike_ocean
     */

    private void InjectFieldByType(Object o){
        try {
            //获取内部所有字段
            Field[] declaredFields = o.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                //判断当前字段是否有注解标识
                if (field.getAnnotation(InjectByType.class) != null) {
                    //防止因为private而抛出异常
                    field.setAccessible(true);
                    List list = iocContainer.getBeanByType(field.getType());
                    //如果找不到，那么注入失败
                    //这里我选择抛出异常，也可给他赋值为null
                    if(list.size() == 0){
                        throw new RuntimeException("not find "+field.getType());
                    }
                    //多于一个也注入失败，和Spring一致
                    if (list.size()!=1){
                        throw new RuntimeException("too many");
                    }
                    //正常注入
                    field.set(o, list.get(0));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     *对字段按名称注入
     * @param o 需要被注入的类
     * @author dreamlike_ocean
     */
    private void InjectFieldByName(Object o){
        try {
            Field[] declaredFields = o.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                InjectByName annotation = field.getAnnotation(InjectByName.class);
                if (annotation != null) {
                    field.setAccessible(true);
                    //通过注解中的bean name寻找注入的值
                    //这里optional类没有发挥它自己的函数式优势，因为我觉得在lambda表达式里面写异常处理属实不好看
                    //借用在Stack overflow看的一句话，Oracle用受检异常把lambda玩砸了
                    Object v = iocContainer.getBean(annotation.value()).get();
                    if (v != null) {
                        field.set(o, v);
                    }else{
                        //同样找不到就抛异常
                        throw new RuntimeException("not find "+field.getType());
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对函数按类型注入
     *
     * 这个函数必须是setter函数
     * @param o 要被注入的类
     * @author dreamlike_ocean
     */
    private void InjectMethod(Object o){
        Method[] declaredMethods = o.getClass().getDeclaredMethods();
        try {
            for (Method method : declaredMethods) {
                //获取添加注解的函数
                if (method.getAnnotation(InjectByType.class) != null) {
                    //获取参数列表
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    method.setAccessible(true);
                    int i = method.getParameterCount();
                    //为储存实参做准备
                    Object[] param = new Object[i];
                    //变量重用，现在它代表当前下标了
                    i=0;
                    for (Class<?> parameterType : parameterTypes) {
                        List<?> list = iocContainer.getBeanByType(parameterType);
                        if(list.size() == 0){
                            throw new RuntimeException("not find "+parameterType+"。method :"+method+"class:"+o.getClass());
                        }
                        if (list.size()!=1){
                            throw new RuntimeException("too many");
                        }
                        //暂时存储实参
                        param[i++] = list.get(0);
                    }
                    //调用对应实例的函数
                    method.invoke(o, param);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    //你会发现上面都是私有方法，因为我想对外暴露一个简洁的API

    /**
     * 对字段依次进行按类型注入和按名称注入
     * 再对setter方法注入
     * @author dreamlike_ocean
     */
    public void inject(){
        iocContainer.getBeans().forEach(o -> {
            InjectFieldByType(o);
            InjectFieldByName(o);
            InjectMethod(o);
        });
    }


}
