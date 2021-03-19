package study.JUC.java例子.线程中sleep和wait方法测试;

class Thread1 implements Runnable{
    public void run() {
        synchronized (TestSleepaWait.class) {
            System.out.println("Thread1 is start........");
            System.out.println("Thread1 is wait..............");
            try {
                //调用wait方法，线程会放弃对象锁，进入等待对象的等待锁定池
                TestSleepaWait.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread1 is go on........");
            System.out.println("Thread1 is over!");
        }
    }
}
