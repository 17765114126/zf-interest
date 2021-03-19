package study.JUC.java例子.详解;
/**
 *
 * 2、线程让步——yield
 *
 *       yield()方法和sleep()方法有点相似，它也是Thread类提供的一个静态的方法，它也可以让当前正在执行的线程暂停，让出cpu资源给其他的线程。
 *       但是和sleep()方法不同的是，它不会进入到阻塞状态，而是进入到就绪状态。yield()方法只是让当前线程暂停一下，重新进入就绪的线程池中，
 *       让系统的线程调度器重新调度器重新调度一次，完全可能出现这样的情况：当某个线程调用yield()方法之后，线程调度器又将其调度出来重新进入到运行状态执行。
 *
 *      实际上，当某个线程调用了yield()方法暂停之后，优先级与当前线程相同，或者优先级比当前线程更高的就绪状态的线程更有可能获得执行的机会，
 *      当然，只是有可能，因为我们不可能精确的干涉cpu调度线程。用法如下：
 *
 * */
public class EEETest2 {
    public static void main(String[] args) throws InterruptedException {
        new MyThread2("1低级", 1).start();
        new MyThread2("2中级", 5).start();
        new MyThread2("3高级", 10).start();
    }
}

class MyThread2 extends Thread {
    public MyThread2(String name, int pro) {
        super(name);// 设置线程的名称
        this.setPriority(pro);// 设置优先级
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.getName() + "线程第" + (i+1) + "次执行！+++++++");
            if (i % 5 == 0)
                Thread.yield();
        }
    }
}
/**
 *
 *
 *注：关于sleep()方法和yield()方的区别如下：
 *
 * ①、sleep方法暂停当前线程后，会进入阻塞状态，只有当睡眠时间到了，才会转入就绪状态。而yield方法调用后 ，是直接进入就绪状态，所以有可能刚进入就绪状态，又被调度到运行状态。
 *
 * ②、sleep方法声明抛出了InterruptedException，所以调用sleep方法的时候要捕获该异常，或者显示声明抛出该异常。而yield方法则没有声明抛出任务异常。
 *
 * ③、sleep方法比yield方法有更好的可移植性，通常不要依靠yield方法来控制并发线程的执行。
 *
 * */


/**
 *
 * 3、线程合并——join
 * 线程的合并的含义就是将几个并行线程的线程合并为一个单线程执行，应用场景是当一个线程必须等待另一个线程执行完毕才能执行时，Thread类提供了join方法来完成这个功能，注意，它不是静态方法。
 * 从上面的方法的列表可以看到，它有3个重载的方法：
 * void join()
 *      当前线程等该加入该线程后面，等待该线程终止。
 * void join(long millis)
 *      当前线程等待该线程终止的时间最长为 millis 毫秒。 如果在millis时间内，该线程没有执行完，那么当前线程进入就绪状态，重新等待cpu调度
 * void join(long millis,int nanos)
 *      等待该线程终止的时间最长为 millis 毫秒 + nanos 纳秒。如果在millis时间内，该线程没有执行完，那么当前线程进入就绪状态，重新等待cpu调度
 *
 *
 *
 * */