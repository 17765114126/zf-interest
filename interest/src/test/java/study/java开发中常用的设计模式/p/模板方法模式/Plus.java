package study.java开发中常用的设计模式.p.模板方法模式;


public class Plus extends AbstractCalculator {

    @Override
    public int calculate(int num1,int num2) {
        return num1 + num2;
    }
}
