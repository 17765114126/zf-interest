package study.JUC.java例子.死锁发生条件;

class Lock{
    static Object lockOne = new Object();//资源A
    static Object lockTwo = new Object();//资源B
}
