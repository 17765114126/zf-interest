package study.JUC.lock;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 各种锁的理解
 * 1、公平锁、非公平锁
 * 公平锁： 非常公平， 不能够插队，必须先来后到！
 * public ReentrantLock() {
 * sync = new NonfairSync();
 * }
 * 非公平锁：非常不公平，可以插队 （默认都是非公平）
 * public ReentrantLock(boolean fair) {
 * sync = fair ? new FairSync() : new NonfairSync();
 * }
 *
 * 2、可重入锁
 * 可重入锁（递归锁）
 */

public class LockTest {

    /**Synchronized*/
    @Test
    public void test() {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sms();
        }, "A").start();

        new Thread(() -> {
            phone.sms();
        }, "B").start();
    }

    /**Lock 版*/
    @Test
    public void test1() {
        Phone2 phone = new Phone2();

        new Thread(() -> {
            phone.sms();
        }, "A").start();

        new Thread(() -> {
            phone.sms();
        }, "B").start();
    }
}

class Phone {
    public synchronized void sms() {
        System.out.println(Thread.currentThread().getName() + "sms");
        call(); // 这里也有锁
    }
    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + "call");
    }
}

class Phone2 {
    Lock lock = new ReentrantLock();
    public void sms() {
        lock.lock(); // 细节问题：lock.lock(); lock.unlock(); // lock 锁必须配对，否则就会死在里面
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "sms");
            call(); // 这里也有锁
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public void call() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "call");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}