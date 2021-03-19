package com.example.springboot.config.IocOrDI.Ioc;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @ClassName BeanFactory
 * @Author zhaofu
 * @Date 2020/7/13
 * @Version V1.0
 **/
public class Factory {
    public static void main(String[] args) {

        //Sping 的 BeanFactory 容器
//        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("Beans.xml"));
//        HelloWorld obj = (HelloWorld) factory.getBean("helloWorld");
//        obj.getMessage();


        //Spring ApplicationContext 容器
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("E:/Workspace/zf-interest/src/main/resources/Beans.xml");
        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
        obj.getMessage();

        ////Bean 的作用域
        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("Beans.xml");
        HelloWorld objA = (HelloWorld) context1.getBean("helloWorld");
        objA.setMessage("I'm object A");
        objA.getMessage();
        HelloWorld objB = (HelloWorld) context1.getBean("helloWorld");
        objB.getMessage();

        //Bean 的生命周期
        AbstractApplicationContext context2 = new ClassPathXmlApplicationContext("Beans.xml");
        HelloWorld objC = (HelloWorld) context2.getBean("helloWorld");
        objC.getMessage();
        //在这里，你需要注册一个在 AbstractApplicationContext 类中声明的关闭 hook 的 registerShutdownHook() 方法。
        // 它将确保正常关闭，并且调用相关的 destroy 方法。
        context2.registerShutdownHook();


    }
}
/**
 * Sping 的 BeanFactory 容器
 * <p>
 * 这是一个最简单的容器，它主要的功能是为依赖注入 （DI） 提供支持，这个容器接口在 org.springframework.beans.factory.BeanFactor 中被定义。
 * BeanFactory 和相关的接口，比如，BeanFactoryAware、 DisposableBean、InitializingBean，仍旧保留在 Spring 中，
 * 主要目的是向后兼容已经存在的和那些 Spring 整合在一起的第三方框架。
 * <p>
 * 在 Spring 中，有大量对 BeanFactory 接口的实现。其中，最常被使用的是 XmlBeanFactory 类。
 * 这个容器从一个 XML 文件中读取配置元数据，由这些元数据来生成一个被配置化的系统或者应用。
 * <p>
 * 在资源宝贵的移动设备或者基于 applet 的应用当中， BeanFactory 会被优先选择。
 * 否则，一般使用的是 ApplicationContext，除非你有更好的理由选择 BeanFactory。
 * <p>
 * 例子：
 * 我们可以创建一个 Spring 应用程序。
 * <p>
 * 步骤	描述
 * 1	创建一个名为 SpringExample 的工程并在 src 文件夹下新建一个名为 com.tutorialspoint 文件夹。
 * 2	点击右键，选择 Add External JARs 选项，导入 Spring 的库文件，正如我们在 Spring Hello World Example 章节中提到的导入方式。
 * 3	在 com.tutorialspoint 文件夹下创建 HelloWorld.java 和 MainApp.java 两个类文件。
 * 4	在 src 文件夹下创建 Bean 的配置文件 Beans.xml
 * 5	最后的步骤是创建所有 Java 文件和 Bean 的配置文件的内容，按照如下所示步骤运行应用程序。
 * <p>
 * 在主程序当中，我们需要注意以下两点：
 * <p>
 * 第一步利用框架提供的 XmlBeanFactory() API 去生成工厂 bean 以及利用 ClassPathResource() API 去加载在路径 CLASSPATH 下可用的 bean 配置文件。
 * XmlBeanFactory() API 负责创建并初始化所有的对象，即在配置文件中提到的 bean。
 * <p>
 * 第二步利用第一步生成的 bean 工厂对象的 getBean() 方法得到所需要的 bean。
 * 这个方法通过配置文件中的 bean ID 来返回一个真正的对象，该对象最后可以用于实际的对象。一旦得到这个对象，就可以利用这个对象来调用任何方法。
 * <p>
 * 在主程序当中，我们需要注意以下两点：
 * <p>
 * 第一步利用框架提供的 XmlBeanFactory() API 去生成工厂 bean 以及利用 ClassPathResource() API 去加载在路径 CLASSPATH 下可用的 bean 配置文件。
 * XmlBeanFactory() API 负责创建并初始化所有的对象，即在配置文件中提到的 bean。
 * <p>
 * 第二步利用第一步生成的 bean 工厂对象的 getBean() 方法得到所需要的 bean。
 * 这个方法通过配置文件中的 bean ID 来返回一个真正的对象，该对象最后可以用于实际的对象。一旦得到这个对象，就可以利用这个对象来调用任何方法。
 */

/**
 * 在主程序当中，我们需要注意以下两点：
 *
 * 第一步利用框架提供的 XmlBeanFactory() API 去生成工厂 bean 以及利用 ClassPathResource() API 去加载在路径 CLASSPATH 下可用的 bean 配置文件。
 * XmlBeanFactory() API 负责创建并初始化所有的对象，即在配置文件中提到的 bean。
 *
 * 第二步利用第一步生成的 bean 工厂对象的 getBean() 方法得到所需要的 bean。
 * 这个方法通过配置文件中的 bean ID 来返回一个真正的对象，该对象最后可以用于实际的对象。一旦得到这个对象，就可以利用这个对象来调用任何方法。
 *
 * */




/*
* Application Context 是 spring 中较高级的容器。
*
* 和 BeanFactory 类似，它可以加载配置文件中定义的 bean，将所有的 bean 集中在一起，当有请求的时候分配 bean。
* 另外，它增加了企业所需要的功能，比如，从属性文件从解析文本信息和将事件传递给所指定的监听器。
* 这个容器在 org.springframework.context.ApplicationContext interface 接口中定义。

ApplicationContext 包含 BeanFactory 所有的功能，一般情况下，相对于 BeanFactory，ApplicationContext 会被推荐使用。
BeanFactory 仍然可以在轻量级应用中使用，比如移动设备或者基于 applet 的应用程序。

最常被使用的 ApplicationContext 接口实现：

FileSystemXmlApplicationContext：该容器从 XML 文件中加载已被定义的 bean。在这里，你需要提供给构造器 XML 文件的完整路径

ClassPathXmlApplicationContext：该容器从 XML 文件中加载已被定义的 bean。
在这里，你不需要提供 XML 文件的完整路径，只需正确配置 CLASSPATH 环境变量即可，因为，容器会从 CLASSPATH 中搜索 bean 配置文件。

WebXmlApplicationContext：该容器会在一个 web 应用程序的范围内加载在 XML 文件中已被定义的 bean。
我们已经在 Spring Hello World Example章节中看到过 ClassPathXmlApplicationContext 容器，
并且，在基于 spring 的 web 应用程序这个独立的章节中，我们讨论了很多关于 XmlWebApplicationContext。
所以，接下来，让我们看一个关于 FileSystemXmlApplicationContext 的例子。

例子:
我们可以创建一个 Spring 应用程序。

步骤	描述
1	创建一个名为 SpringExample 的工程， 在 src 下新建一个名为 com.tutorialspoint 的文件夹src
2	点击右键，选择 Add External JARs 选项，导入 Spring 的库文件，正如我们在 Spring Hello World Example 章节中提到的导入方式。
3	在 com.tutorialspoint 文件夹下创建 HelloWorld.java 和 MainApp.java 两个类文件。
4	文件夹下创建 Bean 的配置文件 Beans.xml。
5	最后的步骤是编辑所有 JAVA 文件的内容和 Bean 的配置文件,按照以前我们讲的那样去运行应用程序。
* */
/*
* 在主程序当中，我们需要注意以下两点：

第一步生成工厂对象。加载完指定路径下 bean 配置文件后，利用框架提供的 FileSystemXmlApplicationContext API 去生成工厂 bean。
FileSystemXmlApplicationContext 负责生成和初始化所有的对象，比如，所有在 XML bean 配置文件中的 bean。

第二步利用第一步生成的上下文中的 getBean() 方法得到所需要的 bean。
这个方法通过配置文件中的 bean ID 来返回一个真正的对象。
一旦得到这个对象，就可以利用这个对象来调用任何方法。
* */








