package study.java开发中常用的设计模式.w.访问者模式;


public interface Subject {
    public void accept(Visitor visitor);
    public String getSubject();
}
