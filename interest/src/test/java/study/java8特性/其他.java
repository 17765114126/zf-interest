package study.java8特性;

/**
 * @ClassName 其他
 * @Author zhaofu
 * @Date 2019/8/1
 * @Version V1.0
 **/
public class 其他 {
    /**
     * F.其他
     *
     * 1.默认方法
     *
     * 接口和它的实现类保持兼容非常重要
     *
     * 因为你开发的类库可能正在被多个开发者广泛的使用着
     *
     * 若上层接口需要做改变的时候（如增加新的空方法）
     *
     * 下层接口就需要实现新增的方法，在某些情况下，变得不灵活
     *
     * 因此：使用默认方法
     *
     * 接口中定义的default修饰的有具体实现的方法，是给接口增加的公用方法
     *
     * 是对象方法，需要使用对象来进行访问
     *
     * 添加默认方法，对以前代码不会有影响，避免其实现类都做出相应的改动
     *
     * 且default方法通常使用已经实现类已存在的方法进行定义
     *
     * 2.问：
     *
     * 如果一个接口中定义一个默认方法
     *
     * 另外一个父类或接口中定义同名方法(不管是否default)时候，子类如何处理
     *
     * a.选择父类方法。当父类提供具体实现时，接口同名同参方法被忽略
     *
     * b.覆盖父类方法。当一个父接口提供默认方法，另一个父接口提供同名同参方法时候
     *
     * 3.静态方法
     *
     * 接口中定义的static修饰的有具体实现的方法
     *
     * 类似于default方法，但在实现类中不能覆盖(override)该方法
     *
     * 接口的static方法只对接口里的方法可见，和其他静态方法类似
     *
     * 可以使用接口名来调用接口中的静态方法
     *
     * 接口的static方法有利于提供通用的方法，比如判空检查，集合排序等
     *
     * 可以移除工具类中方法到接口static方法中
     *
     * 例如：sList.sort((s,t)->Integer.compare(s.length(), t.length()));
     *
     * 转换为静态方法：sList.sort(Comparator.comparing(String::length));
     *
     * 4.String新增join(字符串集合或数组)方法
     *
     * 使用指定连接符连接多个字符串
     *
     *
     * */
    public static void main(String[] args) {
        String[] str = {"a", "b", "c"};

        String join = String.join("-", str);
        System.out.println(join);
    }
/**
 * 5.可能错过的Java7特性
 *
 * try--with-resources
 *
 * 在 JDK 7 之前，文件资源需要手动finally中关闭
 *
 * try-with-resources 能够很容易地关闭在 try-catch 语句块中使用的资源
 *
 * 所谓的资源（resource）是指在程序完成后，必须关闭的对象
 *
 * try-with-resources 语句确保了每个资源在语句结束时关闭
 *
 * 所有实现了 java.lang.AutoCloseable 接口的对象可以使用作为资源
 *
 * 在try( ...)里声明的资源，会在try-catch代码块结束后自动关闭掉
 *
 * */
}
