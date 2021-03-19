package study.java开发中常用的设计模式.q.观察者模式;


public class MySubject extends AbstractSubject {

    @Override
    public void operation() {
        System.out.println("update self!");
        notifyObservers();
    }

}