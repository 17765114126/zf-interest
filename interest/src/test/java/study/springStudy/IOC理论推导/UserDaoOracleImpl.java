package study.springStudy.IOC理论推导;

/**
 * @ClassName UserDaoOracleImpl
 * @Author zhaofu
 * @Date 2020/12/30
 * @Version V1.0
 **/
public class UserDaoOracleImpl implements UserDao {
    @Override
    public void getUser() {
        System.out.println("Oracle获取用户数据");
    }
}
