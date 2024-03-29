package study.JUC.Java并发性和多线程;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class 线程池 {
//线程池（Thread Pool）对于限制应用程序中同一时刻运行的线程数很有用。因为每启动一个新线程都会有相应的性能开销，每个线程都需要给栈分配一些内存等等。
//
//我们可以把并发执行的任务传递给一个线程池，来替代为每个并发执行的任务都启动一个新的线程。只要池里有空闲的线程，任务就会分配给一个线程执行。在线程池的内部，任务被插入一个阻塞队列（Blocking Queue），线程池里的线程会去取这个队列里的任务。当一个新任务插入队列时，一个空闲线程就会成功的从队列中取出任务并且执行它。
//
//线程池经常应用在多线程服务器上。每个通过网络到达服务器的连接都被包装成一个任务并且传递给线程池。线程池的线程会并发的处理连接上的请求。以后会再深入有关 Java 实现多线程服务器的细节。
//
//Java 5 在 java.utils.concurrent 包中自带了内置的线程池，所以你不用非得实现自己的线程池。你可以阅读我写的 java.utils.concurrent.ExecutorService 的文章以了解更多有关内置线程池的知识。不过无论如何，知道一点关于线程池实现的知识总是有用的。
//
//这里有一个简单的线程池实现：
//
//public class ThreadPool {
//
//  private BlockingQueue taskQueue = null;
//  private List<PoolThread> threads = new ArrayList<PoolThread>();
//  private boolean isStopped = false;
//
//  public ThreadPool(int noOfThreads, int maxNoOfTasks) {
//    taskQueue = new BlockingQueue(maxNoOfTasks);
//
//    for (int i=0; i<noOfThreads; i++) {
//      threads.add(new PoolThread(taskQueue));
//    }
//    for (PoolThread thread : threads) {
//      thread.start();
//    }
//  }
//
//  public void synchronized execute(Runnable task) {
//    if(this.isStopped) throw
//      new IllegalStateException("ThreadPool is stopped");
//
//    this.taskQueue.enqueue(task);
//  }
//
//  public synchronized boolean stop() {
//    this.isStopped = true;
//    for (PoolThread thread : threads) {
//      thread.stop();
//    }
//  }
//
//}
//(校注：原文有编译错误，我修改了下)
//
//public class PoolThread extends Thread {
//
//  private BlockingQueue<Runnable> taskQueue = null;
//  private boolean       isStopped = false;
//
//  public PoolThread(BlockingQueue<Runnable> queue) {
//    taskQueue = queue;
//  }
//
//  public void run() {
//    while (!isStopped()) {
//      try {
//        Runnable runnable =taskQueue.take();
//        runnable.run();
//      } catch(Exception e) {
//        // 写日志或者报告异常,
//        // 但保持线程池运行.
//      }
//    }
//  }
//
//  public synchronized void toStop() {
//    isStopped = true;
//    this.interrupt(); // 打断池中线程的 dequeue() 调用.
//  }
//
//  public synchronized boolean isStopped() {
//    return isStopped;
//  }
//}
//线程池的实现由两部分组成。类 ThreadPool 是线程池的公开接口，而类 PoolThread 用来实现执行任务的子线程。
//
//为了执行一个任务，方法 ThreadPool.execute(Runnable r)用 Runnable 的实现作为调用参数。在内部，Runnable 对象被放入阻塞队列 (Blocking Queue) ，等待着被子线程取出队列。
//
//一个空闲的 PoolThread 线程会把 Runnable 对象从队列中取出并执行。你可以在 PoolThread.run()方法里看到这些代码。执行完毕后，PoolThread 进入循环并且尝试从队列中再取出一个任务，直到线程终止。
//
//调用 ThreadPool.stop()方法可以停止 ThreadPool。在内部，调用 stop 先会标记 isStopped 成员变量（为 true）。然后，线程池的每一个子线程都调用 PoolThread.stop()方法停止运行。注意，如果线程池的 execute()在 stop()之后调用，execute()方法会抛出 IllegalStateException 异常。
//
//子线程会在完成当前执行的任务后停止。注意 PoolThread.stop() 方法中调用了 this.interrupt()。它确保阻塞在 taskQueue.dequeue() 里的 wait()调用的线程能够跳出 wait()调用（校对注：因为执行了中断 interrupt，它能够打断这个调用），并且抛出一个 InterruptedException 异常离开 dequeue()方法。这个异常在 PoolThread.run()方法中被截获、报告，然后再检查 isStopped 变量。由于 isStopped 的值是 true, 因此 PoolThread.run()方法退出，子线程终止。
//
//（校对注：看完觉得不过瘾？更详细的线程池文章参见 Java 线程池的分析和使用）
}
