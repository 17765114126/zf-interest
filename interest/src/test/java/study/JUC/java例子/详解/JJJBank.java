package study.JUC.java例子.详解;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 *
 * 4、使用重入锁（Lock）实现线程同步
 *
 *       在JavaSE5.0中新增了一个java.util.concurrent包来支持同步。ReentrantLock类是可重入、互斥、实现了Lock接口的锁，它与使用synchronized方法和快具有相同的基本行为和语义，并且扩展了其能力。ReenreantLock类的常用方法有：
 *
 *  ReentrantLock() : 创建一个ReentrantLock实例
 *  lock() : 获得锁
 *  unlock() : 释放锁
 * 注：ReentrantLock()还有一个可以创建公平锁的构造方法，但由于能大幅度降低程序运行效率，不推荐使用
 *
 * */
public class JJJBank {
    private int account = 100;
    //需要声明这个锁
    private Lock lock = new ReentrantLock();
    public int getAccount() {
        return account;
    }
    //这里不再需要synchronized
    public void save(int money) {
        lock.lock();
        try{
            account += money;
        }finally{
            lock.unlock();
        }

    }
}
/**
 *
 * 未完成 请入地址：https://www.cnblogs.com/snow-flower/p/6114765.html
 *
 * */