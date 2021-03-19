package study.springStudy.Ioc2;

import com.example.springboot.model.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import study.springStudy.IOC理论推导.UserDaoMySqlImpl;
import study.springStudy.IOC理论推导.UserDaoOracleImpl;
import study.springStudy.IOC理论推导.UserService;
import study.springStudy.IOC理论推导.UserServiceImpl;

/**
 * @ClassName IOCTest
 * @Author zhaofu
 * @Date 2020/12/30
 * @Version V1.0
 **/
public class IOCTest {
    @Test
    public void test1() {
//        UserService service = new UserServiceImpl();
//        service.getUser();

        UserServiceImpl service = new UserServiceImpl();
        service.setUserDao( new UserDaoMySqlImpl() );
        service.getUser();
        //那我们现在又想用Oracle去实现呢
        service.setUserDao( new UserDaoOracleImpl() );
        service.getUser();

    }


    @Test
    public void test2(){
        //解析beans.xml文件 , 生成管理相应的Bean对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //getBean : 参数即为spring配置文件中bean的id .
        Student student = (Student) context.getBean("student");
        student.getName();
    }

}
