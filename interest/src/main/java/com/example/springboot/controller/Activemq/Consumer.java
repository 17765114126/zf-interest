package com.example.springboot.controller.Activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

/**
 * @ClassName Consumer
 * @Author zhaofu
 * @Date 2019/10/9
 * @Version V1.0
 * @Description: 消费者通过消息监听器监听服务器上的信息
 **/
public class Consumer {
    //设置连接地址
    private static final String url = "tcp://127.0.0.1:61616";
    //设置消息队列名称
    private static final String queueName = "topic-name";

    private static final String URL = "tcp://127.0.0.1:61616";
    //订阅模式名称
    private static final String topicName = "topic-name";
    /**
     * 点对点模式
     * */
/*    public static void main(String[] args) throws JMSException {
        // 1、创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        // 2、创建连接对象
        Connection createConnection = connectionFactory.createConnection();
        // 3、启动连接
        createConnection.start();
        // 4、创建会话　　   createSession第一个参数表示是否支持事务，第二个参数是客户端接收确认模式，Session.AUTO_ACKNOWLEDGE是自动确认，Session.CLIENT_ACKNOWLEDGE 客户端通过调用消息的 acknowledge 方法签收消息。
        Session createSession = createConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 5、创建消息目标
        Queue createQueue = createSession.createQueue(queueName);
        // 6 、创建消费者
        MessageConsumer createConsumer = createSession.createConsumer(createQueue);
        // 7、设置消费者监听
        createConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接收的消息为" + textMessage.getText());
                } catch (JMSException e) {
                    // TODO Auto-generated catch block e.printStackTrace();
                }
            }
        });
    }*/
    /**
     *
     * 同时运行三个消费者（Consumer.java执行三次后可在console切换不同类的控制台），在运行一个生产者，消息被这三个消费者平分了。
     * 点对点模式主要用于消除程序高并发高峰对数据库造成的巨大压力，可以通过使用消息队列，让消费者进程从消息队列中获取数据，
     * 然后异步将数据写入数据库，由于消息队列的服务处理速度远快于数据库，因此用户的响应延迟可得到有效改善。
     *
     * */

    /**
     * 订阅模式（非持久订阅）
     * 与上面写法相同，只是session不再是创建队列消费者，而是创建主题消费者
     * */

/*    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        //创建连接
        Connection createConnection = connectionFactory.createConnection();//打开连接
        createConnection.start();
        //创建会话
        Session createSession = createConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建发布/订阅模式消息
        Topic createTopic = createSession.createTopic(topicName);//        非持久订阅
        //创建消费者
        MessageConsumer createConsumer = createSession.createConsumer(createTopic);
        //设置消费者监听
        createConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接收的消息为："+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    */

    /**
     * 订阅模式（持久订阅）
     *
     * 持久订阅模式的客户端需要创建一个链接id，以保证服务器确认该客户端是否已消费信息，
     * 创建完订阅模式，之后不再是创建一个消费者，而是创建一个带有id的用户，这个用户id是唯一的，若有两个相同的id连接，则会报错。
     *
     * 运行用户（Consumer.java），创建连接id，之后将用户连接关闭，启动生产者（Producer.java）发布消息，最后在重新连接用户获取信息。
     * 当用户离线状态时，发布者发布的消息会将信息存在activeMQ服务器上，等待用户监听时将消息发送给用户。
     * */
    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        //创建连接
        Connection createConnection = connectionFactory.createConnection();
        //创建客户端ID
        createConnection.setClientID("333");
        //打开连接
        createConnection.start();
        //创建会话
        Session createSession = createConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //创建发布/订阅模式消息
        Topic createTopic = createSession.createTopic(topicName);

        //创建持久订阅 即未在发布者发布时监听消息，在之后也能接收消息
        TopicSubscriber subscriber = createSession.createDurableSubscriber(createTopic, "333");

        subscriber.setMessageListener(new MessageListener() {

            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接受消息：" + textMessage.getText());
                } catch (JMSException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        /**
         * 持久传输和非持久传输最大的区别是：采用持久传输时，传输的消息会保存到磁盘中(messages are persisted to disk/database)，
         * 即“存储转发”方式。先把消息存储到磁盘中，然后再将消息“转发”给订阅者。
         *
         * 采用非持久传输时，发送的消息不会存储到磁盘中。
         *
         * 采用持久传输时，当Borker宕机 恢复后，消息还在。
         * 采用非持久传输，Borker宕机重启后，消息丢失。
         * 比如，当生产者将消息投递给Broker后，Broker将该消息存储到磁盘中，在Broker将消息发送给Subscriber之前，Broker宕机了，
         * 如果采用持久传输，Broker重启后，从磁盘中读出消息再传递给Subscriber；如果采用非持久传输，这条消息就丢失了。
         *
         * */
    }
}
