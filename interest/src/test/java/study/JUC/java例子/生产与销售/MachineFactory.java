package study.JUC.java例子.生产与销售;

public interface MachineFactory {
    public void production(int num);
    public void consume(int num);
}
