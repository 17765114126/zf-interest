package study.java8特性.lambda表达式推导;

/**
 * @ClassName testLambda
 * @Description: 推导lambda表达式
 * @Author zhaofu
 * @Date 2021/1/6
 * @Version V1.0
 **/
public class testLambda {
    // 3.静态内部类
    static class Like2 implements ILike {
        @Override
        public void lambda() {
            System.out.println(" i like lambda2!");
        }
    }

    public static void main(String[] args) {
        // 测试 常规方式
        ILike like = new Like();
        like.lambda();

        // 测试 静态内部类
        ILike like2 = new Like2();
        like2.lambda();

        // 4.局部内部类
        class Like3 implements ILike {
            @Override
            public void lambda() {
                System.out.println(" i like lambda3!");
            }
        }
        // 测试 局部内部类
        ILike like3 = new Like3();
        like3.lambda();

        // 5.匿名类
        // 测试 匿名类
        new ILike() {
            @Override
            public void lambda() {
                System.out.println(" i like lambda4!");
            }
        }.lambda();

        // 6.简化lambda：对于函数式接口，我们可以通过lambda表达式来创建该接口的对象
        like = () -> {
            System.out.println(" i like lambda5!");
        };
        // 测试 lambda表达式
        like.lambda();
    }
}

/**
 * 1.定义一个函数式接口：任何接口，如果只包含一个抽象方法，那么它就是一个函数式接口
 */
interface ILike {
    // 隐式申明抽象
    void lambda();
}

/**
 * 2.实现类
 */
class Like implements ILike {

    @Override
    public void lambda() {
        System.out.println(" i like lambda!");
    }
}

