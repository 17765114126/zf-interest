package study.JUC.java例子.详解;

/**
 *
 * 三、线程管理
 *
 *      Java提供了一些便捷的方法用于会线程状态的控制。具体如下：
 *
 * 1、线程睡眠——sleep
 *       如果我们需要让当前正在执行的线程暂停一段时间，并进入阻塞状态，则可以通过调用Thread的sleep方法。
 *
 * 注：
 *   （1）sleep是静态方法，最好不要用Thread的实例对象调用它，因为它睡眠的始终是当前正在运行的线程，而不是调用它的线程对象，它只对正在运行状态的线程对象有效。
 *    如下面的例子：
 *
 * （2）Java线程调度是Java多线程的核心，只有良好的调度，才能充分发挥系统的性能，提高程序的执行效率。
 *      但是不管程序员怎么编写调度，只能最大限度的影响线程执行的次序，而不能做到精准控制。因为使用sleep方法之后，
 *      线程是进入阻塞状态的，只有当睡眠的时间结束，才会重新进入到就绪状态，
 *      而就绪状态进入到运行状态，是由系统控制的，我们不可能精准的去干涉它，所以如果调用Thread.sleep(1000)使得线程睡眠1秒，可能结果会大于1秒。
 *
 * */
public class DDDTest1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"******************");
        MyThread1 myThread=new MyThread1();
        myThread.start();
        myThread.sleep(1000);//这里sleep的就是main线程，而非myThread线程
        for(int i=0;i<10;i++){
            System.out.println("main====="+i);
        }
    }
}
class MyThread1 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.getName() + "线程第" + i + "次执行！-----------");
            if (i % 5 == 0)
                Thread.yield();
        }
    }
}