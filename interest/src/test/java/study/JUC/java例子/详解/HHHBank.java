package study.JUC.java例子.详解;

/**四、线程同步

        java允许多线程并发控制，当多个线程同时操作一个可共享的资源变量时（如数据的增删改查），将会导致数据不准确，相互之间产生冲突，
        因此加入同步锁以避免在该线程没有完成操作之前，被其他线程的调用，从而保证了该变量的唯一性和准确性。

        1、同步方法

        即有synchronized关键字修饰的方法。由于java的每个对象都有一个内置锁，当用此关键字修饰方法时，内置锁会保护整个方法。
        在调用该方法前，需要获得内置锁，否则就处于阻塞状态。

        public synchronized void save(){}
        注： synchronized关键字也可以修饰静态方法，此时如果调用该静态方法，将会锁住整个类

        2、同步代码块

        即有synchronized关键字修饰的语句块。被该关键字修饰的语句块会自动被加上内置锁，从而实现同步。
*/
public class  HHHBank {

    private int count =0;//账户余额

    //存钱
    public   void addMoney(int money){

        synchronized (this) {
            count +=money;
        }
        System.out.println(System.currentTimeMillis()+"存进："+money);
    }

    //取钱
    public   void subMoney(int money){

        synchronized (this) {
            if(count-money < 0){
                System.out.println("余额不足");
                return;
            }
            count -=money;
        }
        System.out.println(+System.currentTimeMillis()+"取出："+money);
    }

    //查询
    public void lookMoney(){
        System.out.println("账户余额："+count);
    }
}
/**
  注：同步是一种高开销的操作，因此应该尽量减少同步的内容。通常没有必要同步整个方法，使用synchronized代码块同步关键代码即可。*/
