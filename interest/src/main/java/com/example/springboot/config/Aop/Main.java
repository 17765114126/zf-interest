package com.example.springboot.config.Aop;

import com.example.springboot.config.事务.StudentDAO;
import com.example.springboot.config.事务.StudentJDBCTemplate;
import com.example.springboot.config.事务.StudentMarks;
import com.example.springboot.model.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @ClassName Main
 * @Author zhaofu
 * @Date 2020/7/22
 * @Version V1.0
 **/
public class Main {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        Student student = (Student) context.getBean("student");
        student.getName();
        student.getAge();
        student.printThrowException();

        System.out.println("---------------------------------------------------------------------------");
        //Spring 编程式事务管理

        //编程式事务管理方法允许你在对你的源代码编程的帮助下管理事务。这给了你极大地灵活性，但是它很难维护。
        //让我们直接使用 PlatformTransactionManager 来实现编程式方法从而实现事务。
        // 要开始一个新事务，你需要有一个带有适当的 transaction 属性的 TransactionDefinition 的实例。
        // 这个例子中，我们使用默认的 transaction 属性简单的创建了 DefaultTransactionDefinition 的一个实例。
        //
        //当 TransactionDefinition 创建后，你可以通过调用 getTransaction() 方法来开始你的事务，该方法会返回 TransactionStatus 的一个实例。
        // TransactionStatus 对象帮助追踪当前的事务状态，
        // 并且最终，如果一切运行顺利，你可以使用 PlatformTransactionManager 的 commit() 方法来提交这个事务，
        // 否则的话，你可以使用 rollback() 方法来回滚整个操作
        ClassPathXmlApplicationContext context1 =
                new ClassPathXmlApplicationContext("Beans.xml");
        StudentJDBCTemplate studentJDBCTemplate =
                (StudentJDBCTemplate) context1.getBean("studentJDBCTemplate");
        System.out.println("------Records creation--------");
        studentJDBCTemplate.create("Zara", 11, 99, 2010);
        studentJDBCTemplate.create("Nuha", 20, 97, 2010);
        studentJDBCTemplate.create("Ayan", 25, 100, 2011);
        System.out.println("------Listing all the records--------");
        List<StudentMarks> studentMarks = studentJDBCTemplate.listStudents();
        for (StudentMarks record : studentMarks) {
            System.out.print("ID : " + record.getId());
            System.out.print(", Name : " + record.getName());
            System.out.print(", Marks : " + record.getMarks());
            System.out.print(", Year : " + record.getYear());
            System.out.println(", Age : " + record.getAge());
        }


        //Spring 声明式事务管理

        //声明式事务管理方法允许你在配置的帮助下而不是源代码硬编程来管理事务。这意味着你可以将事务管理从事务代码中隔离出来。你可以只使用注释或基于配置的 XML 来管理事务。 bean 配置会指定事务型方法。这是与声明式事务相关的步骤：
        //
        //我们使用 标签，它创建一个事务处理的建议，同时，我们定义一个匹配所有方法的切入点，我们希望这些方法是事务型的并且会引用事务型的建议。
        //
        //如果在事务型配置中包含了一个方法的名称，那么创建的建议在调用方法之前就会在事务中开始进行。
        //
        //目标方法会在 try / catch 块中执行。
        //
        //如果方法正常结束，AOP 建议会成功的提交事务，否则它执行回滚操作。
        ClassPathXmlApplicationContext context3 =
                new ClassPathXmlApplicationContext("Beans.xml");
        StudentDAO studentJDBCTemplate1 =
                (StudentDAO) context3.getBean("studentJDBCTemplate");
        System.out.println("------Records creation--------");
        studentJDBCTemplate1.create("Zara", 11, 99, 2010);
        studentJDBCTemplate1.create("Nuha", 20, 97, 2010);
        studentJDBCTemplate1.create("Ayan", 25, 100, 2011);
        System.out.println("------Listing all the records--------");
        List<StudentMarks> studentMarks1 = studentJDBCTemplate1.listStudents();
        for (StudentMarks record : studentMarks1) {
            System.out.print("ID : " + record.getId());
            System.out.print(", Name : " + record.getName());
            System.out.print(", Marks : " + record.getMarks());
            System.out.print(", Year : " + record.getYear());
            System.out.println(", Age : " + record.getAge());
        }
    }
}

