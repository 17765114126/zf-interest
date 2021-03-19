package study.java8特性.lambda表达式推导;

/**
 * @ClassName testLambda2
 * @Author zhaofu
 * @Date 2021/1/6
 * @Version V1.0
 **/
public class testLambda2 {

    public static void main(String[] args) {
        // 局部内部类
        class Love implements Ilove {
            @Override
            public void love(int a) {
                System.out.println("i love you --> " + a + " --> 局部内部类");
            }
        }
        Love love = new Love();
        love.love(10);


        // 匿名类
        new Ilove() {
            @Override
            public void love(int a) {
                System.out.println("i love you --> " + a + " --> 匿名类");
            }
        }.love(11);

        // lambda
        Ilove ilove = null;
        // lambda表达式1
        ilove = (int a) -> {
            System.out.println("i love you --> " + a + " --> lambda表达式1");
        };
        ilove.love(12);

        // lambda表达式2
        ilove = (a) -> {
            System.out.println("i love you --> " + a + " --> lambda表达式2");
        };
        ilove.love(13);

        // lambda表达式3
        ilove = a -> {
            System.out.println("i love you --> " + a + " --> lambda表达式3");
        };
        ilove.love(14);

        // lambda表达式4
        ilove = a -> System.out.println("i love you --> " + a + " --> lambda表达式3");

        ilove.love(15);

    }
}

interface Ilove {
    void love(int a);
}
