package study.springStudy.IOC理论推导;

/**
 * @ClassName UserServiceImpl
 * @Author zhaofu
 * @Date 2020/12/30
 * @Version V1.0
 **/
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    // 利用set实现
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
