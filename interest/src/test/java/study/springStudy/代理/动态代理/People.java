package study.springStudy.代理.动态代理;

import lombok.Data;

/**
 * @ClassName People
 * @Author zhaofu
 * @Date 2019/8/6
 * @Version V1.0
 **/
@Data
public class People implements Italk {
    public String username;
    public String age;

    public People(String name1, String age1) {
        this.username = name1;
        this.age = age1;
    }

    @Override
    public void talk(String msg) {
        System.out.println(msg + "!你好,我是" + username + "，我年龄是" + age);
    }
}
