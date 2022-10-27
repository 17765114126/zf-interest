package Test;

import java.util.function.*;

public class Demo1 {
    public static void main(String[] args) {
        //Supplier  没有输入 只有输出
        Supplier<String> supplier = ()-> "Hello Supplier";
        System.out.println(supplier.get());

        //Consumer  只有一个输入 没有输出
        Consumer<String> consumer = x -> System.out.println("Hello " + x);
        consumer.accept("Consumer");

        //Function  输入T 输出R
        Function<Integer, Integer> function = i -> i * i;
        System.out.println("Function demo:" + function.apply(9));

        //UnaryOperator 输入输出都是T
        UnaryOperator<Integer> unaryOperator = i -> i * i;
        System.out.println("UnaryOperator demo:" + unaryOperator.apply(9));

        //BiFunction 输入T,U 输出R
        BiFunction<Integer, Integer, String> biFunction = (i, j) -> i + "*" + j + "=" + i*j;
        System.out.println("BiFunction demo:" + biFunction.apply(8, 9));
    }
}
