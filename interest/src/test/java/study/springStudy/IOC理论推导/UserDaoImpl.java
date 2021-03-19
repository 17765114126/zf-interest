package study.springStudy.IOC理论推导;

/**
 * @ClassName UserDaoImpl
 * @Author zhaofu
 * @Date 2020/12/30
 * @Version V1.0
 **/
public class UserDaoImpl implements UserDao {
    @Override
    public void getUser() {
        System.out.println("获取用户数据");
    }
}
