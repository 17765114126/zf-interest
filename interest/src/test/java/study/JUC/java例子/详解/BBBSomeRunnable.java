package study.JUC.java例子.详解;
/**
*
 * （2）实现Runnable接口创建线程类
 *
 * 通过实现Runnable接口创建线程类的具体步骤和具体代码如下：
 *
 *    • 定义Runnable接口的实现类，并重写该接口的run()方法；
 *
 *    • 创建Runnable实现类的实例，并以此实例作为Thread的target对象，即该Thread对象才是真正的线程对象。
*
* */
class BBBSomeRunnable implements Runnable   {
    public void run()   {
        System.out.println("====");
        //do something here
    }
    public static void main(String[] args){
        Runnable oneRunnable = new BBBSomeRunnable();
        Thread oneThread = new Thread(oneRunnable);
        oneThread.start();
    }
};
