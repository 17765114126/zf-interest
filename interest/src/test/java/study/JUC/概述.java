package study.JUC;

/**
 * @ClassName 概述
 * @Author zhaofu
 * @Date 2021/1/25
 * @Version V1.0
 **/
public class 概述 {
    /**
     * 业务：普通的线程代码 Thread
     * Runnable 没有返回值、效率相比入 Callable 相对较低！
     * <p>
     * 2、线程和进程
     * 线程、进程，如果不能使用一句话说出来的技术，不扎实！
     * 进程：一个程序，QQ.exe Music.exe 程序的集合；
     * 一个进程往往可以包含多个线程，至少包含一个！
     * <p>
     * Java默认有几个线程？
     * 2 个 mian、GC
     * <p>
     * 线程：开了一个进程 Typora，写字，自动保存（线程负责的）
     * 对于Java而言：Thread、Runnable、Callable
     * <p>
     * Java 真的可以开启线程吗？
     * 开不了
     *
     *
     * 并发编程：并发、并行
     *
     * 并发（多线程操作同一个资源）：CPU 一核 ，模拟出来多条线程，天下武功，唯快不破，快速交替
     * 并行（多个人一起行走）：CPU 多核 ，多个线程可以同时执行； 线程池
     *
     * 并发编程的本质：充分利用CPU的资源
     *
     *
     * 线程有几个状态
     *
     * public enum State {
     * // 新生
     *      NEW,
     * // 运行
     *      RUNNABLE,
     * // 阻塞
     *      BLOCKED,
     * // 等待，死死地等
     *      WAITING,
     * // 超时等待
     *      TIMED_WAITING,
     * // 终止
     *      TERMINATED;
     * }
     *
     *
     * wait/sleep 区别
     *
     * 1、来自不同的类
     * wait => Object
     * sleep => Thread
     *
     * 2、关于锁的释放
     * wait 会释放锁，sleep 睡觉了，抱着锁睡觉，不会释放！
     *
     * 3、使用的范围是不同的
     * wait必须在同步代码块中
     * sleep 可以再任何地方睡
     */
    public static void main(String[] args) {
    new Thread().start();
    }
}
