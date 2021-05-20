package com.example.springboot;

import com.example.springboot.controller.RabbitMQ.ThemeMode.MsgSender;
import com.example.springboot.controller.RabbitMQ.NormalMode.HelloSender;
import com.example.springboot.controller.RabbitMQ.SubscriptionModel.MsgSenderFanout;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName RabbitMqHelloTest
 * @Author zhaofu
 * @Date 2019/10/22
 * @Version V1.0
 * @Description: RabbitMq测试
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {

    @Autowired
    private HelloSender helloSender;
    /**
    * @Date: 2019/10/22
    * @Author: zhaofu
    * @Description: NormalMode
    **/
    @Test
    public void hello() {
        helloSender.send();
    }
    /**
     * @Date: 2019/10/22
     * @Author: zhaofu
     * @Description: Work
     **/
    @Test
    public void oneToMany() throws Exception {
        for (int i=0;i<100;i++){
            helloSender.send(i);
            Thread.sleep(300);
        }
    }
    /**
     * @Date: 2019/10/22
     * @Author: zhaofu
     * @Description: ThemeMode
     **/
    @Autowired
    private MsgSender msgSender;

    @Test
    public void send1() {
        msgSender.send1();
    }

    @Test
    public void send2() {
        msgSender.send2();
    }
    /**
     * @Date: 2019/10/22
     * @Author: zhaofu
     * @Description: SubscriptionModel
     **/
    @Autowired
    private MsgSenderFanout msgSenderFanout;

    @Test
    public void send3() {
        msgSenderFanout.send();
    }
}