package study.java开发中常用的设计模式.r.迭代子模式;


public interface Collection {

    public Iterator iterator();

    /*取得集合元素*/
    public Object get(int i);

    /*取得集合大小*/
    public int size();
}