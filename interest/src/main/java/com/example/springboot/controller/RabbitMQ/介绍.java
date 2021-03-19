package com.example.springboot.controller.RabbitMQ;

/**
 * @ClassName 介绍
 * @Author zhaofu
 * @Date 2019/10/22
 * @Version V1.0
 **/
public class 介绍 {
    /**
     *
     * 本文参考地址：https://blog.csdn.net/hellozpc/article/details/81436980
     *
     * */

    /**
     *RabbitMQ实战教程
     * 1.什么是MQ
     * 消息队列（Message Queue，简称MQ），从字面意思上看，本质是个队列，FIFO先入先出，只不过队列中存放的内容是message而已。
     * 其主要用途：不同进程Process/线程Thread之间通信。
     * 为什么会产生消息队列？有几个原因：
     *
     * 不同进程（process）之间传递消息时，两个进程之间耦合程度过高，改动一个进程，引发必须修改另一个进程，为了隔离这两个进程，在两进程间抽离出一层（一个模块），所有两进程之间传递的消息，都必须通过消息队列来传递，单独修改某一个进程，不会影响另一个；
     *
     * 不同进程（process）之间传递消息时，为了实现标准化，将消息的格式规范化了，并且，某一个进程接受的消息太多，一下子无法处理完，并且也有先后顺序，必须对收到的消息进行排队，因此诞生了事实上的消息队列；
     *
     * 关于消息队列的详细介绍请参阅：
     * 《Java帝国之消息队列》
     * 《一个故事告诉你什么是消息队列》
     * 《到底什么时候该使用MQ》
     *
     * MQ框架非常之多，比较流行的有RabbitMq、ActiveMq、ZeroMq、kafka，以及阿里开源的RocketMQ。本文主要介绍RabbitMq。

     * */
    /**
     * RabbitMQ 是一个开源的消息代理和队列服务器，用来通过普通协议在完全不同的应用之间共享数据，
     * RabbitMQ是使用 Erlang语言来编写的，并且RabbitMQ是基于AMQP协议的
     *
     * RabbitMQ的优点:
     *
     * 开源、性能优秀、稳定性保障
     * 提供可靠性消息投递模式(confirm)、返回模式(return)
     * 与SpringAMQP完美的整合、API丰富
     * 集群模式丰富，表达式配置，HA模式，镜像队列模型
     * 保证数据不丢失的前提下做到高可靠性、可用性
     *
     *
     * AMQP全称: Advanced Message Queuing Protocol
     *
     * AMQP翻译: 高级消息队列协议
     *
     * AMQP定义: 是具有现代特征的二进制协议。是一个提供统一消息服务的应用层标准高级消息队列协议，是应用层协议的一个开放标准，为面向消息的中间件设计
     *
     * */




    /**
     * 安装Erlang
     * 安装RabbitMQ
     *
     * 1、启用管理工具
     * 2、进入C:\Program Files (x86)\RabbitMQ Server\rabbitmq_server-3.4.1\sbin输入命令：
     * rabbitmq-plugins enable rabbitmq_management
     *
     * 这样就启动了管理工具，可以试一下命令：
     * 停止：net stop RabbitMQ
     * 启动：net start RabbitMQ
     *
     * 3、在浏览器中输入地址查看：http://127.0.0.1:15672/
     * 4、使用默认账号登录：guest/ guest
     *
     * 通过默认账户 guest/guest 登录
     * 如果能够登录，说明安装成功。
     * 5.添加用户
     * 5.1.添加admin用户
     * 5.2.用户角色
     * 1、超级管理员(administrator)
     * 可登陆管理控制台，可查看所有的信息，并且可以对用户，策略(policy)进行操作。
     * 2、监控者(monitoring)
     * 可登陆管理控制台，同时可以查看rabbitmq节点的相关信息(进程数，内存使用情况，磁盘使用情况等)
     * 3、策略制定者(policymaker)
     * 可登陆管理控制台, 同时可以对policy进行管理。但无法查看节点的相关信息(上图红框标识的部分)。
     * 4、普通管理者(management)
     * 仅可登陆管理控制台，无法看到节点信息，也无法对策略进行管理。
     * 5、其他
     * 无法登陆管理控制台，通常就是普通的生产者和消费者。
     *
     * 5.3.创建Virtual Hosts
     *
     *     选中Admin用户，设置权限
     * */

    /**
     *
     * 简单队列
     * 1、配置pom文件，主要是添加spring-boot-starter-amqp的支持
     *
     * <dependency>
     *    <groupId>org.springframework.boot</groupId>
     *    <artifactId>spring-boot-starter-amqp</artifactId>
     * </dependency>
     *
     * 配置application.properties文件
     * 配置rabbitmq的安装地址、端口以及账户信息
     *
     * spring.application.name=spirng-boot-rabbitmq
     * spring.rabbitmq.host=127.0.0.1
     * spring.rabbitmq.port=5672
     * spring.rabbitmq.username=admin
     * spring.rabbitmq.password=admin
     *
     * */
}
