package Test;

/**
 * @ClassName LogInTest
 * @Author zhaofu
 * @Date 2020/8/24
 * @Version V1.0
 **/
public class LogInTest {
//    同时支持手机号、用户名、邮箱登录
    public static void main(String[] args) {
        String e = "15315315326@163.com";
        String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String ph = "^[1][3578]\\d{9}$";

        if (e.matches(em)) {
            //邮箱登录
            System.out.println("邮箱");
        } else if (e.matches(ph)) {
            //手机号登录
            System.out.println("手机号");
        } else {
            //就是用户名登录
            System.out.println("用户名");
        }
    }
}
