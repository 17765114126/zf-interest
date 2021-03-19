package study.JUC.java例子.购票窗口实现票数同步;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTicket {
    public static void main(String[] args) {
        new Booking("1-军人售票口").start();
        new Booking("2-学生售票口").start();
        new Booking("3-老年人售票口").start();
        new Booking("4-网上售票口").start();
    }
}

/**
 * 多窗口卖票系统。多线程
 * 票数为静态的，共享数据
 * synchronized(对象){}代码块中的内容是加锁的，
 * 即当一个线程在使用时，其他线程不可以进入。
 * 使得共享资源数据的安全。
 */
class Booking extends Thread{
    public  Booking(String name){
        super(name);
    }
    static  int ticket = 10;//票数共10张
    Lock lock = new ReentrantLock();//明锁
    /**
    *
    * ReentrantLock根据传入构造方法的布尔型参数实例化出Sync的实现类FairSync和NonfairSync
    * ，分别表示公平的Sync和非公平的Sync。
    * 由于ReentrantLock我们用的比较多的是非公平锁
    *
    *     ReentrantLock 和synchronized 均为重入锁
    *
    *     * 1. ReenTrantLock可以指定是公平锁还是非公平锁。而synchronized只能是非公平锁。所谓的公平锁就是先等待的线程先获得锁。
    *
    *     2. ReenTrantLock提供了一个Condition（条件）类，用来实现分组唤醒需要唤醒的线程们，而不是像synchronized要么随机唤醒一个线程要么唤醒全部线程。
    *
    *     3. ReenTrantLock提供了一种能够中断等待锁的线程的机制，通过lock.lockInterruptibly()来实现这个机制。
    *
    *        对ReentrantLock的可重入锁这篇博客使用简单的例子进行讲解，  http://blog.csdn.net/yanyan19880509/article/details/52345422
    *
    *
    *       Lock是个接口，只能实例化它的子类
    *       明锁适合高并发，上万
    *       暗锁适合并发率不高时，效率高
    *
    * */
    public void run(){
        while(ticket>0){
            synchronized (Booking.class) {
                if (ticket>0) {
                    System.out.println(super.getName()+"窗口---->卖出的车票号No."+ticket);
                    ticket--;
                }else {
                    System.out.println(super.getName()+"票已售罄！！!");
                }
                try {
                    sleep(100);//睡100毫秒，抛出多线程异常
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
                    lock.lock();//加锁，锁定以下代码
                    if (ticket>0) {
                        System.out.println(super.getName()+"卖票："+ticket);
                        ticket--;
                    }else {
                        System.out.println(super.getName()+"票已售罄！！！");
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.unlock();//解锁
     }

    }
}