package study.springStudy.IOC理论推导;

/**
 * @ClassName UserDaoMySqlImpl
 * @Author zhaofu
 * @Date 2020/12/30
 * @Version V1.0
 **/
public class UserDaoMySqlImpl implements UserDao {
    @Override
    public void getUser() {
        System.out.println("MySql获取用户数据");
    }
}
