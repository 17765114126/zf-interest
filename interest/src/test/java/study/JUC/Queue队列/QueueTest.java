package study.JUC.Queue队列;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 *
 * 写入：如果队列满了，就必须阻塞等待
 * 取：如果队列是空的，必须阻塞等待生产
 *
 * BlockingQueue BlockingQueue 不是新的东西
 *
 * 什么情况下我们会使用 阻塞队列：多线程并发处理，线程池！
 *
 * 学会使用队列：添加、移除
 *
 * 四组API
 *
 * | 方式         | 抛出异常 | 有返回值，不抛出异常 | 阻塞等待 | 超时等待      |
 * | ------------ | -------- | -------------------- | -------- | ------------- |
 * | 添加         | add      | offer（）            | put（）  | offer（，，） |
 * | 移除         | remove   | poll（）             | take（） | pool（，）    |
 * | 检测队首元素 | element  | peek                 | -        | -             |
 *
 */

/**
 * ArrayBlockingQueue是一个阻塞式的队列，继承自AbstractBlockingQueue,间接的实现了Queue接口和Collection接口。
 * 底层以数组的形式保存数据(实际上可看作一个循环数组)。
 * 常用的操作包括 add，offer，put，remove，poll，take，peek。
 *
 * 根据 ArrayBlockingQueue 的名字我们都可以看出，它是一个队列，并且是一个基于数组的阻塞队列。
 *
 * ArrayBlockingQueue 是一个有界队列，有界也就意味着，它不能够存储无限多数量的对象。
 * 所以在创建 ArrayBlockingQueue 时，必须要给它指定一个队列的大小。
 *
 * 我们先来熟悉一下 ArrayBlockingQueue 中的几个重要的方法。
 *
 * add(E e)：把 e 加到 BlockingQueue 里，即如果 BlockingQueue 可以容纳，则返回 true，否则报异常
 * offer(E e)：表示如果可能的话，将 e 加到 BlockingQueue 里，即如果 BlockingQueue 可以容纳，则返回 true，否则返回 false
 * put(E e)：把 e 加到 BlockingQueue 里，如果 BlockQueue 没有空间，则调用此方法的线程被阻断直到 BlockingQueue 里面有空间再继续
 * poll(time)：取走 BlockingQueue 里排在首位的对象，若不能立即取出，则可以等 time 参数规定的时间,取不到时返回 null
 * take()：取走 BlockingQueue 里排在首位的对象，若 BlockingQueue 为空，阻断进入等待状态直到 Blocking 有新的对象被加入为止
 * remainingCapacity()：剩余可用的大小。等于初始容量减去当前的 size
 * ArrayBlockingQueue 使用场景。
 *
 * 先进先出队列（队列头的是最先进队的元素；队列尾的是最后进队的元素）
 * 有界队列（即初始化时指定的容量，就是队列最大的容量，不会出现扩容，容量满，则阻塞进队操作；容量空，则阻塞出队操作）
 * 队列不支持空元素
 * */
public class QueueTest {
    /**
     * 抛出异常
     */
    @Test
    public void test1() {
        // 队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // IllegalStateException: Queue full 抛出异常！
//         System.out.println(blockingQueue.add("d"));

        System.out.println("=-===========");

        System.out.println(blockingQueue.element()); // 查看队首元素是谁
        System.out.println(blockingQueue.remove());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        // java.util.NoSuchElementException 抛出异常！
        // System.out.println(blockingQueue.remove());
    }

    /**
     * 有返回值，没有异常
     */
    @Test
    public void test2() {
        // 队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        System.out.println(blockingQueue.peek());
        // System.out.println(blockingQueue.offer("d")); // false 不抛出异常！
        System.out.println("============================");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll()); // null  不抛出异常！
    }

    /**
     * 等待，阻塞（一直阻塞）
     */
    @Test
    public void test3() throws InterruptedException {
        // 队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        // 一直阻塞
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
         blockingQueue.put("d"); // 队列没有位置了，一直阻塞
//        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take()); // 没有这个元素，一直阻塞

    }

    /**
     * 等待，阻塞（等待超时）
     */
    @Test
    public void test4() throws InterruptedException {
        // 队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        // blockingQueue.offer("d",2,TimeUnit.SECONDS); // 等待超过2秒就退出
        System.out.println("===============");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        blockingQueue.poll(2, TimeUnit.SECONDS); // 等待超过2秒就退出
    }

}
