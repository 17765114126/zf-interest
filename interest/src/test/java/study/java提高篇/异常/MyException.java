package study.java提高篇.异常;

/**
 * @ClassName MyException
 * @Author zhaofu
 * @Date 2021/6/9
 * @Version V1.0
 **/
public class MyException extends Exception {
    public MyException() {
        super();
    }
    public MyException(String str) {
        super(str);
    }

    public static void main(String[] args) throws MyException {
        test();
    }

    public static void test() throws MyException {
        throw new MyException("自定义MyException！");

    }
}