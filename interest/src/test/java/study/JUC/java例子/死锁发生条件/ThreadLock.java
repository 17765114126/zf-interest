package study.JUC.java例子.死锁发生条件;

class ThreadLock implements Runnable{
    private boolean flag;
    public ThreadLock(boolean flag){
        this.flag = flag;
    }
    @Override
    public void run() {
        if(flag){
            while (true) {
                synchronized (Lock.lockOne) {
                    System.out.println(" this is lockOne");
                    synchronized (Lock.lockTwo) {
                        System.out.println("this is lockTwo");
                    }
                }
            }
        }else {
            while (true) {
                synchronized (Lock.lockTwo) {
                    System.out.println(" this is lockTwo");
                    synchronized (Lock.lockOne) {
                        System.out.println("this is lockOne");
                    }
                }
            }
        }
    }


}
