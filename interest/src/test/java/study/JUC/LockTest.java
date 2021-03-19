package study.JUC;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Lock
 * @Author zhaofu
 * @Date 2021/1/25
 * @Version V1.0
 **/
public class LockTest {

    /**
     * 传统 Synchronized
     *
     * 真正的多线程开发，公司中的开发，降低耦合性
     * 线程就是一个单独的资源类，没有任何附属的操作！
     * 1、 属性、方法
     * */
    @Test
    public void test() {
        // 并发：多线程操作同一个资源类, 把资源类丢入线程
        Ticket ticket = new Ticket();
        // @FunctionalInterface 函数式接口，jdk1.8  lambda表达式 (参数)->{ 代码 }
        new Thread(()->{
            for (int i = 1; i < 40 ; i++) {
                ticket.sale();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i < 40 ; i++) {
                ticket.sale();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1; i < 40 ; i++) {
                ticket.sale();
            }
        },"C").start();
    }

    /**
     * Lock 接口
     *
     * 公平锁：十分公平：可以先来后到
     * 非公平锁：十分不公平：可以插队 （默认）
     * */
    @Test
    public void Test1() {
        // 并发：多线程操作同一个资源类, 把资源类丢入线程
        Ticket2 ticket2 = new Ticket2();
        // @FunctionalInterface 函数式接口，jdk1.8  lambda表达式 (参数)->{ 代码 }
        new Thread(()->{for (int i = 1; i < 40 ; i++) ticket2.sale();},"A").start();
        new Thread(()->{for (int i = 1; i < 40 ; i++) ticket2.sale();},"B").start();
        new Thread(()->{for (int i = 1; i < 40 ; i++) ticket2.sale();},"C").start();
    }
}

// 资源类 OOP
class Ticket {
    // 属性、方法
    private int number = 30;

    // 卖票的方式
    // synchronized 本质: 队列，锁
    public synchronized void sale(){
        if (number>0){
            System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票,剩余："+number);
        }
    }

}

// Lock三部曲
// 1、 new ReentrantLock();
// 2、 lock.lock(); // 加锁
// 3、 finally=>  lock.unlock(); // 解锁
class Ticket2 {
    // 属性、方法
    private int number = 30;
    //可重入锁（常用）
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock(); // 加锁
        try {
            // 业务代码
            if (number>0){
                System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票,剩余："+number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // 解锁
        }
    }
}


/**
 * Synchronized 和 Lock 区别
 *
 * 1、Synchronized 内置的Java关键字， Lock 是一个Java类
 * 2、Synchronized 无法判断获取锁的状态，Lock 可以判断是否获取到了锁
 * 3、Synchronized 会自动释放锁，lock 必须要手动释放锁！如果不释放锁，死锁
 * 4、Synchronized 线程 1（获得锁，阻塞）、线程2（等待，傻傻的等）；Lock锁就不一定会等待下去；
 * 5、Synchronized 可重入锁，不可以中断的，非公平；Lock ，可重入锁，可以 判断锁，非公平（可以自己设置）；
 * 6、Synchronized 适合锁少量的代码同步问题，Lock 适合锁大量的同步代码！
 *
 * 锁是什么，如何判断锁的是谁！
 * */