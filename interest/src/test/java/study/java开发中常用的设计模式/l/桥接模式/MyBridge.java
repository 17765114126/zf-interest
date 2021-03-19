package study.java开发中常用的设计模式.l.桥接模式;


public class MyBridge extends Bridge {
    public void method(){
        getSource().method();
    }
}