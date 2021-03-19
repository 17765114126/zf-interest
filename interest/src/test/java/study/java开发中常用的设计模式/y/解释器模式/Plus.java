package study.java开发中常用的设计模式.y.解释器模式;

public class Plus implements Expression {

    @Override
    public int interpret(Context context) {
        return context.getNum1()+context.getNum2();
    }
}
