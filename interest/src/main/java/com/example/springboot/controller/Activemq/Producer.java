package com.example.springboot.controller.Activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

/**
 * @ClassName Producer
 * @Author zhaofu
 * @Date 2019/10/9
 * @Version V1.0
 * @Description: 生产者跟消费者差不多，这是第6步开始，变成创建生产者，并发送消息，发送完成之后需要关闭连接。
 **/
public class Producer{
    //设置连接地址
    private static final String url = "tcp://127.0.0.1:61616";
    //设置消息队列名称
    private static final String queueName = "queue-text";

    private static final String URL = "tcp://127.0.0.1:61616";
    //发布/订阅模式名称
    private static final String topicName = "topic-name";
    /**
     * 点对点模式
     * */
/*    public static void main( String[] args ) throws JMSException{
        // 1、创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        // 2、创建连接对象
        Connection createConnection = connectionFactory.createConnection();
        // 3、启动连接
        createConnection.start();
        // 4、创建会话
        Session createSession = createConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 5、创建消息目标
        Queue createQueue = createSession.createQueue(queueName);
        // 6、创建生产者
        MessageProducer createProducer = createSession.createProducer(createQueue);
        for (int i=0;i<100;++i) {
            // 7、创建消息
            TextMessage textMessage = createSession.createTextMessage("666  " + i + "条数据");
            // 8、发布消息
            createProducer.send(textMessage);
            System.out.println("发送的消息为："+ textMessage.getText());
        }

        // 9、关闭连接
        createConnection.close();
    }*/



    /**
     * 订阅模式（非持久订阅）
     *
     * 先运行消费者，在运行生产者，消费者才能接受到信息，否则生产者发布信息时若没有在监听的消费者则会将信息丢弃，这样消费者是接收不到信息的。
     * 同时运行多个消费者，在运行生产者，消费者将获取生产者发布的所有消息
     * */

/*    public static void main(String[] args) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);

        Connection createConnection = connectionFactory.createConnection();

        createConnection.start();

        Session createSession = createConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //发布/订阅模式
        Topic createTopic = createSession.createTopic(topicName);

        MessageProducer createProducer = createSession.createProducer(createTopic);

        for (int i = 0; i < 100; i++) {
            TextMessage textMessage = createSession.createTextMessage("666 " + i + "条数据");
            createProducer.send(textMessage);
            System.out.println("发送的消息为：" + textMessage.getText());
        }
        createConnection.close();
    }*/

    /**
     * 订阅模式（持久订阅）
     *
     * */
    public static void main(String[] args) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);

        Connection createConnection = connectionFactory.createConnection();

        createConnection.start();

        Session createSession = createConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //发布/订阅模式
        Topic createTopic = createSession.createTopic(topicName);

        MessageProducer createProducer = createSession.createProducer(createTopic);
        //设置为持久订阅模式
        createProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

        for (int i = 0; i < 100; i++) {
            TextMessage textMessage = createSession.createTextMessage("666 " + i + "条数据");
            createProducer.send(textMessage);
            System.out.println("发送的消息为：" + textMessage.getText());
        }
        createConnection.close();
    }

}
