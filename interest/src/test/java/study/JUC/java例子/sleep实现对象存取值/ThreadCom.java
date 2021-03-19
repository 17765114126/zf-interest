package study.JUC.java例子.sleep实现对象存取值;

public class ThreadCom {
    public static void main(String[] args) {
        Person person = new Person();
        new Thread(new Producer(person)).start();
        new Thread(new Consumer(person)).start();
    }

}


