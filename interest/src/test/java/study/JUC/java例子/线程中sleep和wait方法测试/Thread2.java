package study.JUC.java例子.线程中sleep和wait方法测试;


class Thread2 implements Runnable {

    @Override
    public void run() {
        synchronized (TestSleepaWait.class) {
            System.out.println("Thread2 is enter..........");
            System.out.println("Thread2 is sleep.......");
            //只有针对对象调用notify()方法后本线程才进入对象锁定池
            //准备获取对象进入运行状态
            TestSleepaWait.class.notify();
            //===============
            //如果把上句注释掉。即对象锁调用了wait方法，但是没有调用notify
            //程序就一致处于挂起状态
            try {
                Thread.sleep(5000);
                //sleep方法暂停执行时间，让出CPU，监控状态保持，
                //时间到 了就回复运行， 不会释放对象锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread2 is going on..........");
            System.out.println("Thread2 is over!!!!");
        }

    }

}