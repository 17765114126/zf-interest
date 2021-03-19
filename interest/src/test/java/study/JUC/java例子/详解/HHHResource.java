package study.JUC.java例子.详解;
/**
 *
 * 五、线程通信
 *
 * 1、借助于Object类的wait()、notify()和notifyAll()实现通信
 *
 *      线程执行wait()后，就放弃了运行资格，处于冻结状态；
 *
 *      线程运行时，内存中会建立一个线程池，冻结状态的线程都存在于线程池中，notify()执行时唤醒的也是线程池中的线程，线程池中有多个线程时唤醒第一个被冻结的线程。
 *       notifyall(), 唤醒线程池中所有线程。
 * 注： （1） wait(), notify(),notifyall()都用在同步里面，因为这3个函数是对持有锁的线程进行操作，而只有同步才有锁，所以要使用在同步中；
 *        （2） wait(),notify(),notifyall(),  在使用时必须标识它们所操作的线程持有的锁，因为等待和唤醒必须是同一锁下的线程；而锁可以是任意对象，所以这3个方法都是Object类中的方法。
 *
 * 单个消费者生产者例子如下：
 *
 * */
public class HHHResource {
    private String name;
    private int count=1;
    private boolean flag=false;
    public synchronized void set(String name){
        if(flag)
            try{wait();}catch(Exception e){}
        this.name=name+"---"+count++;
        System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);
        flag=true;
        this.notify();
    }
    public synchronized void out(){
        if(!flag)
            try{wait();}catch(Exception e){}
        System.out.println(Thread.currentThread().getName()+"...消费者..."+this.name);
        flag=false;
        this.notify();
    }
}
class Producer implements Runnable{
    private HHHResource res;
    Producer(HHHResource res){
        this.res=res;
    }
    public void run(){
        while(true){
            res.set("商品");
        }
    }
}
class Consumer implements Runnable{
    private HHHResource res;
    Consumer(HHHResource res){
        this.res=res;
    }
    public void run(){
        while(true){
            res.out();
        }
    }
}
/*public class ProducerConsumerDemo{
    public static void main(String[] args){
        HHHResource r=new HHHResource();
        Producer pro=new Producer(r);
        Consumer con=new Consumer(r);
        Thread t1=new Thread(pro);
        Thread t2=new Thread(con);
        t1.start();
        t2.start();
    }
}*///运行结果正常，生产者生产一个商品，紧接着消费者消费一个商品。
/**
 *
 * 但是如果有多个生产者和多个消费者，上面的代码是有问题，比如2个生产者，2个消费者，运行结果就可能出现生产的1个商品生产了一次而被消费了2次，或者连续生产2个商品而只有1个被消费，这是因为此时共有4个线程在操作Resource对象r,  而notify()唤醒的是线程池中第1个wait()的线程，所以生产者执行notify()时，唤醒的线程有可能是另1个生产者线程，这个生产者线程从wait()中醒来后不会再判断flag，而是直接向下运行打印出一个新的商品，这样就出现了连续生产2个商品。
 * 为了避免这种情况，修改代码如下：
 *
 * */
class Resource{
    private String name;
    private int count=1;
    private boolean flag=false;
    public synchronized void set(String name){
        while(flag) /*原先是if,现在改成while，这样生产者线程从冻结状态醒来时，还会再判断flag.*/
            try{wait();}catch(Exception e){}
        this.name=name+"---"+count++;
        System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);
        flag=true;
        this.notifyAll();/*原先是notity(), 现在改成notifyAll(),这样生产者线程生产完一个商品后可以将等待中的消费者线程唤醒，否则只将上面改成while后，可能出现所有生产者和消费者都在wait()的情况。*/
    }
    public synchronized void out(){
        while(!flag) /*原先是if,现在改成while，这样消费者线程从冻结状态醒来时，还会再判断flag.*/
            try{wait();}catch(Exception e){}
        System.out.println(Thread.currentThread().getName()+"...消费者..."+this.name);
        flag=false;
        this.notifyAll(); /*原先是notity(), 现在改成notifyAll(),这样消费者线程消费完一个商品后可以将等待中的生产者线程唤醒，否则只将上面改成while后，可能出现所有生产者和消费者都在wait()的情况。*/
    }
}
/*
public class ProducerConsumerDemo{
    public static void main(String[] args){
        Resource r=new Resource();
        Producer pro=new Producer(r);
        Consumer con=new Consumer(r);
        Thread t1=new Thread(pro);
        Thread t2=new Thread(con);
        Thread t3=new Thread(pro);
        Thread t4=new Thread(con);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}  */
