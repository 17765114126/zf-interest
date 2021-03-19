package study.JUC.tvolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  volatile 不保证原子性
 * 原子性 : 不可分割
 * 线程A在执行任务的时候，不能被打扰的，也不能被分割。要么同时成功，要么同时失败
 * 如果不加 lock 和 synchronized ，怎么样保证原子性
 * 使用原子类，解决 原子性问题
 * */
public class VolatileTest {
    // volatile 不保证原子性
//    private volatile static int num = 0;

    // 原子类的 Integer
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add() {
        // num++; // 不是一个原子性操作
        num.getAndIncrement(); // AtomicInteger + 1 方法， CAS
    }

    public static void main(String[] args) {
        //理论上num结果应该为 2 万
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
//                    num++;// 不是一个原子性操作
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) { // main  gc
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + num);
    }
    /**这些类的底层都直接和操作系统挂钩！在内存中修改值！Unsafe类是一个很特殊的存*/

    /**
     * 指令重排
     *
     * 什么是 指令重排：你写的程序，计算机并不是按照你写的那样去执行的。
     *
     * 源代码-->编译器优化的重排--> 指令并行也可能会重排--> 内存系统也会重排---> 执行
     *
     * 处理器在进行指令重排的时候，考虑：数据之间的依赖性！
     *
     * int x = 1; // 1
     * int y = 2; // 2
     * x = x + 5; // 3
     * y = x * x; // 4
     * 我们所期望的：1234 但是可能执行的时候回变成 2134 1324
     * 可不可能是 4123！
     *
     * 可能造成影响的结果： a b x y 这四个值默认都是 0；
     * |线程A |线程B
     * |x=a  |y=b
     * |b=1  |a=2
     *
     * 正常的结果： x = 0；y = 0；但是可能由于指令重排
     * |线程A | 线程B
     * |b=1  | a=2
     * |x=a  | y=b
     *
     * 指令重排导致的诡异结果： x = 2；y = 1；
     *
     *
     *
     * 非计算机专业
     *
     * volatile可以避免指令重排：
     *
     * 内存屏障。
     * CPU指令。
     * 作用：
     * 1、保证特定的操作的执行顺序！
     * 2、可以保证某些变量的内存可见性 （利用这些特性volatile实现了可见性）
     *
     *
     *
     * Volatile 是可以保持 可见性。
     * 不能保证原子性，由于内存屏障，可以保证避免指令重排的现象产生！
     * */
}
