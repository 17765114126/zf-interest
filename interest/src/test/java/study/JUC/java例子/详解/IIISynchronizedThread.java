package study.JUC.java例子.详解;
/**
 *
 *
 * 3、使用特殊域变量(volatile)实现线程同步
 *
 *    • volatile关键字为域变量的访问提供了一种免锁机制；
 *
 *    • 使用volatile修饰域相当于告诉虚拟机该域可能会被其他线程更新；
 *
 *    • 因此每次使用该域就要重新计算，而不是使用寄存器中的值；
 *
 *    • volatile不会提供任何原子操作，它也不能用来修饰final类型的变量。
 *
 * */
public class IIISynchronizedThread {
    class Bank {

        private volatile int account = 100;

        public int getAccount() {
            return account;
        }

        /**
         * 用同步方法实现
         *
         * @param money
         */
        public synchronized void save(int money) {
            account += money;
        }

        /**
         * 用同步代码块实现
         *
         * @param money
         */
        public void save1(int money) {
            synchronized (this) {
                account += money;
            }
        }
    }

    class NewThread implements Runnable {
        private Bank bank;

        public NewThread(Bank bank) {
            this.bank = bank;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                // bank.save1(10);
                bank.save(10);
                System.out.println(i + "账户余额为：" +bank.getAccount());
            }
        }

    }

    /**
     * 建立线程，调用内部类
     */
    public void useThread() {
        Bank bank = new Bank();
        NewThread new_thread = new NewThread(bank);
        System.out.println("线程1");
        Thread thread1 = new Thread(new_thread);
        thread1.start();
        System.out.println("线程2");
        Thread thread2 = new Thread(new_thread);
        thread2.start();
    }

    public static void main(String[] args) {
        IIISynchronizedThread st = new IIISynchronizedThread();
        st.useThread();
    }

}
/**
 *
 * 注：多线程中的非同步问题主要出现在对域的读写上，如果让域自身避免这个问题，则就不需要修改操作该域的方法。用final域，有锁保护的域和volatile域可以避免非同步的问题。
 *
 * */