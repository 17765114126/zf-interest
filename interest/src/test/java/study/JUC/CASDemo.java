package study.JUC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 深入理解CAS
 *
 * 什么是 CAS: CAS compareAndSet : 比较并交换！
 *
 * 大厂你必须要深入研究底层！有所突破！ 修内功，操作系统，计算机网络原理
 *
 *
 * CAS ： 比较当前工作内存中的值和主内存中的值，如果这个值是期望的，那么则执行操作！如果不是就
 * 一直循环！
 * 缺点：
 * 1、 循环会耗时
 * 2、一次性只能保证一个共享变量的原子性
 * 3、ABA问题
 * CAS ： ABA 问题（狸猫换太子）
 * 解决ABA 问题，引入原子引用！ 对应的思想：乐观锁！
 * 带版本号 的原子操作！
 * 注意：
 * Integer 使用了对象缓存机制，默认范围是 -128 ~ 127 ，推荐使用静态工厂方法 valueOf 获取对象实
 * 例，而不是 new，因为 valueOf 使用缓存，而 new 一定会创建新的对象分配新的内存空间；
 *
 */
public class CASDemo {
    //AtomicStampedReference 注意，如果泛型是一个包装类，注意对象的引用问题

    // 正常在业务操作，这里面比较的都是一个个对象
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1);

    public static void main(String[] args) {

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp(); // 获得版本号
            System.out.println("a1=>" + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Lock lock = new ReentrantLock(true);

            atomicStampedReference.compareAndSet(1, 2,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);

            System.out.println("a2=>" + atomicStampedReference.getStamp());


            System.out.println(atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

            System.out.println("a3=>" + atomicStampedReference.getStamp());

        }, "a").start();

        // 乐观锁的原理相同！
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp(); // 获得版本号
            System.out.println("b1=>" + stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedReference.compareAndSet(1, 6,
                    stamp, stamp + 1));

            System.out.println("b2=>" + atomicStampedReference.getStamp());

        }, "b").start();
    }
}
