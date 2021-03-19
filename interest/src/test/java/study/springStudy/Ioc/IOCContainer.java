package study.springStudy.Ioc;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName IOCContainer
 * @Author zhaofu
 * @Date 2020/10/16
 * @Version V1.0
 * IOC容器
 **/
public class IOCContainer {
    //思考一下在Spring中我们很容易通过bean name得到java bean，所以使用一个Map<String,Object>可以模拟一下。
    //这里我们在IOCContainer中添加一个变量
    private Map<String,Object> context;

    /**
     * 构造函数
     * */
    public IOCContainer(String packName){
        try {
            init(packName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public IOCContainer(){
        //默认扫描所有的包
        this("");
    }

    /**
     * 初始化函数：
     * */
    /**
     * @param packName 路径名在ClassScannerUtil中的函数要使用
     * @throws IOException
     * @author dreamlike_ocean
     */
    public void init(String packName) throws IOException {
        //做一个bean name 的映射。如果@Part注解中的值不为空则使用value的值做bean name
        //如果为空就用这个 java bean的类名做bean name
        Function<Object,String> keyMapper = (o) -> {
            Class<?> aClass = o.getClass();
            String s = aClass.getAnnotation(Part.class).value();
            if (StringUtils.isBlank(s)) {
                return o.getClass().getTypeName();
            }
            return s;
        };
        context = new HashMap<>();
        //获取所有添加@Part注解的类实例
        List<Object> objectList = ClassScannerUtil.findByAnnotation(packName, Part.class);
        //先把自己注入进去
        context.put("IOCContainer", this);
        for (Object o : objectList) {
            //利用上面写好的映射函数接口 获取bean name
            String beanName = keyMapper.apply(o);
            //bean name冲突情况，直接报错
            if (context.containsKey(beanName)) {
                String msg = new StringBuilder().append("duplicate bean name: ")
                        .append(beanName)
                        .append("in")
                        .append(o.getClass())
                        .append(" and ")
                        .append(context.get(beanName).getClass()).toString();
                throw new RuntimeException(msg);
            }
            //加入容器
            context.put(beanName, o);
        }
        //帮助垃圾回收，这个复杂度为O(n),理论上objectList = null也能帮助回收
        objectList.clear();
    }


    /**
     * 对外暴露的获取Bean的api
     * */
    /**
     *
     * @param beanName
     * @return 记得判断空指针
     * @author dreamlike_ocean
     */
    public Optional<Object> getBean(String beanName){
        return Optional.ofNullable(context.get(beanName));
    }

    /**
     *
     * @param beanName
     * @param aclass
     * @param <T> 需要返回的类型，类型强转
     * @exception ClassCastException 类型强转可能导致无法转化的异常
     * @return @author dreamlike_ocean
     */
    public<T> Optional<T> getBean(String beanName, Class<T> aclass){
        return Optional.ofNullable((T)context.get(beanName));
    }

    /**
     *
     * @param interfaceType
     * @param <T>
     * @return 所有继承这个接口的集合
     * @author dreamlike_ocean
     */
    public<T> List<T> getBeanByInterfaceType(Class<T> interfaceType){
        if (!interfaceType.isInterface()) {
            throw new RuntimeException("it is not an interface type:"+interfaceType.getTypeName());
        }
        return context.values().stream()
                .filter(o -> interfaceType.isAssignableFrom(o.getClass()))
                .map(o -> (T)o)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param type
     * @param <T>
     * @return 所有这个类型的集合
     * @author dreamlike_ocean
     */

    public<T> List<T> getBeanByType(Class<T> type){
        return context.values().stream()
                .filter(o -> type.isAssignableFrom(o.getClass()))
                .map(o -> (T)o)
                .collect(Collectors.toList());
    }

    /**
     *
     * @return 获取所有值
     * @author dreamlike_ocean
     */
    public Collection<Object> getBeans(){
        return context.values();
    }

    /**
     *
     * @return 获取容器
     * @author dreamlike_ocean
     */
    public Map<String,Object> getContext(){
        return context;
    }

}
