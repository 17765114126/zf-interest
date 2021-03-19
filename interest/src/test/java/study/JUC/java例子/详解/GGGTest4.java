package study.JUC.java例子.详解;
/**
 *
 *
 * 6、正确结束线程
 * Thread.stop()、Thread.suspend、Thread.resume、Runtime.runFinalizersOnExit这些终止线程运行的方法已经被废弃了，使用它们是极端不安全的！想要安全有效的结束一个线程，可以使用下面的方法：
 *
 *     • 正常执行完run方法，然后结束掉；
 *
 *     • 控制循环条件和判断条件的标识符来结束掉线程。
 *
 * */
public class GGGTest4 extends Thread {
    int i=0;
    boolean next=true;
    @Override
    public void run() {
        while (next) {
            if(i==10)
                next=false;
            i++;
            System.out.println(i);
        }
    }
}
