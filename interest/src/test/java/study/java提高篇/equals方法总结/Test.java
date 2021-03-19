package study.java提高篇.equals方法总结;

/**
 * @ClassName 概述
 * @Author zhaofu
 * @Date 2020/7/24
 * @Version V1.0
 **/
public class Test {
//equals()
//超类 Object 中有这个 equals() 方法，该方法主要用于比较两个对象是否相等。该方法的源码如下：


//    public boolean equals(Object obj) {
//        return (this == obj);
//        }

//我们知道所有的对象都拥有标识(内存地址)和状态(数据)，同时“==”比较两个对象的的内存地址，
// 所以说使用 Object 的 equals() 方法是比较两个对象的内存地址是否相等，
// 即若 object1.equals(object2) 为 true，则表示 equals1 和 equals2 实际上是引用同一个对象。
// 虽然有时候 Object 的 equals() 方法可以满足我们一些基本的要求，但是我们必须要清楚我们很大部分时间都是进行两个对象的比较，
// 这个时候 Object 的 equals() 方法就不可以了，实际上 JDK 中，String、Math 等封装类都对 equals() 方法进行了重写。
// 下面是 String 的 equals() 方法：


//    public boolean equals(Object anObject) {
//        if (this == anObject) {
//            return true;
//        }
//        if (anObject instanceof String) {
//            String anotherString = (String)anObject;
//            int n = count;
//            if (n == anotherString.count) {
//            char v1[] = value;
//            char v2[] = anotherString.value;
//            int i = offset;
//            int j = anotherString.offset;
//            while (n-- != 0) {
//                if (v1[i++] != v2[j++])
//                return false;
//            }
//            return true;
//            }
//        }
//        return false;
//        }
//对于这个代码段:if (v1[i++] != v2[j++])return false;
// 我们可以非常清晰的看到 String 的 equals() 方法是进行内容比较，而不是引用比较。至于其他的封装类都差不多。

//在 Java 规范中，它对 equals() 方法的使用必须要遵循如下几个规则：

//equals 方法在非空对象引用上实现相等关系：

//1、自反性：对于任何非空引用值 x，x.equals(x) 都应返回 true。

//2、对称性：对于任何非空引用值 x 和 y，当且仅当 y.equals(x) 返回 true 时，x.equals(y) 才应返回 true。

//3、传递性：对于任何非空引用值 x、y 和 z，如果 x.equals(y) 返回 true，并且 y.equals(z) 返回 true，那么 x.equals(z) 应返回 true。

//4、一致性：对于任何非空引用值 x 和 y，多次调用 x.equals(y) 始终返回 true 或始终返回 false，前提是对象上 equals 比较中所用的信息没有被修改。

//5、对于任何非空引用值 x，x.equals(null) 都应返回 false。

//对于上面几个规则，我们在使用的过程中最好遵守，否则会出现意想不到的错误。

//在 java 中进行比较，我们需要根据比较的类型来选择合适的比较方式：

//1) 对象域，使用 equals 方法 。

//2) 类型安全的枚举，使用 equals 或== 。

//3) 可能为 null 的对象域 : 使用 == 和 equals 。

//4) 数组域 : 使用 Arrays.equals 。

//5)除 float 和 double 外的原始数据类型 : 使用 == 。

//6) float 类型: 使用 Float.foatToIntBits 转换成 int 类型，然后使用==。

//7) double 类型: 使用 Double.doubleToLongBit 转换成 long 类型，然后使用==。

//至于6）、7）为什么需要进行转换，我们可以参考他们相应封装类的 equals() 方法，下面的是 Float 类的：


//    public boolean equals(Object obj) {
//        return (obj instanceof Float)
//               && (floatToIntBits(((Float)obj).value) ==   floatToIntBits(value));
//        }
//原因嘛，里面提到了两点：


//    However, there are two exceptions:
//    If f1 and f2 both represent
//    Float.NaN, then the equals method returns
//    true, even though Float.NaN==Float.NaN
//    has the value false.
//    If <code>f1 represents +0.0f while
//    f2 represents -0.0f, or vice
//    versa, the equal test has the value
//    false, even though 0.0f==-0.0f
//    has the value true.

//在 equals() 中使用 getClass 进行类型判断
//我们在覆写 equals() 方法时，一般都是推荐使用 getClass 来进行类型判断，不是使用 instanceof。
// 我们都清楚 instanceof 的作用是判断其左边对象是否为其右边类的实例，返回 boolean 类型的数据。
// 可以用来判断继承中的子类的实例是否为父类的实现。注意后面这句话：可以用来判断继承中的子类的实例是否为父类的实现，正是这句话在作怪。
// 我们先看如下实例(摘自《高质量代码 改善 Java 程序的 151 个建议》)。

//父类：Person

//子类：Employee

//上面父类 Person 和子类 Employee 都重写了 equals(),不过 Employee 比父类多了一个id属性。测试程序如下：


        public static void main(String[] args) {

            String x = "1";
            boolean equals = x.equals("2");
            System.out.println(equals);

            Employee e1 = new Employee("chenssy", 23);
            Employee e2 = new Employee("chenssy", 24);
            Person p1 = new Person("chenssy");

            System.out.println(p1.equals(e1));
            System.out.println(p1.equals(e2));
            System.out.println(e1.equals(e2));
        }

//上面定义了两个员工和一个普通人，虽然他们同名，但是他们肯定不是同一人，所以按理来说输出结果应该全部都是 false，但是事与愿违，结果是：true、true、false。
//
//对于那 e1!=e2 我们非常容易理解，因为他们不仅需要比较 name,还需要比较 ID。
// 但是 p1 即等于 e1 也等于 e2，这是非常奇怪的，因为 e1、e2 明明是两个不同的类，但为什么会出现这个情况？
// 首先 p1.equals(e1)，是调用 p1 的 equals 方法，该方法使用 instanceof 关键字来检查 e1 是否为 Person 类，
// 这里我们再看看 instanceof：判断其左边对象是否为其右边类的实例，也可以用来判断继承中的子类的实例是否为父类的实现。
// 他们两者存在继承关系，肯定会返回 true 了，而两者 name 又相同，所以结果肯定是 true。

//所以出现上面的情况就是使用了关键字 instanceof，这是非常容易“专空子”的。
// 故在覆写 equals 时推荐使用 getClass 进行类型判断。而不是使用 instanceof。

}
