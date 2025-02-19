package com.example.application.config.Transaction;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public interface PlatformTransactionManager {
    //转自 https://wiki.jikexueyuan.com/project/spring/transaction-management/spring-programmatic-transaction-management.html

    /**
     *
     * 事务管理
     * 一个数据库事务是一个被视为单一的工作单元的操作序列。这些操作应该要么完整地执行，要么完全不执行。
     * 事务管理是一个重要组成部分，RDBMS 面向企业应用程序，以确保数据完整性和一致性。
     * 事务的概念可以描述为具有以下四个关键属性说成是 ACID：
     *
     * 原子性：事务应该当作一个单独单元的操作，这意味着整个序列操作要么是成功，要么是失败的。
     *
     * 一致性：这表示数据库的引用完整性的一致性，表中唯一的主键等。
     *
     * 隔离性：可能同时处理很多有相同的数据集的事务，每个事务应该与其他事务隔离，以防止数据损坏。
     *
     * 持久性：一个事务一旦完成全部操作后，这个事务的结果必须是永久性的，不能因系统故障而从数据库中删除。
     * 一个真正的 RDBMS 数据库系统将为每个事务保证所有的四个属性。使用 SQL 发布到数据库中的事务的简单视图如下：
     *
     * 使用 begin transaction 命令开始事务。
     *
     * 使用 SQL 查询语句执行各种删除、更新或插入操作。
     *
     * 如果所有的操作都成功，则执行提交操作，否则回滚所有操作。
     * Spring 框架在不同的底层事务管理 APIs 的顶部提供了一个抽象层。
     * Spring 的事务支持旨在通过添加事务能力到 POJOs 来提供给 EJB 事务一个选择方案。
     * Spring 支持编程式和声明式事务管理。
     * EJBs 需要一个应用程序服务器，但 Spring 事务管理可以在不需要应用程序服务器的情况下实现。
     *
     * 局部事物 vs. 全局事务
     * 局部事务是特定于一个单一的事务资源，如一个 JDBC 连接，而全局事务可以跨多个事务资源事务，如在一个分布式系统中的事务。
     *
     * 局部事务管理在一个集中的计算环境中是有用的，该计算环境中应用程序组件和资源位于一个单位点，而事务管理只涉及到一个运行在一个单一机器中的本地数据管理器。
     * 局部事务更容易实现。
     *
     * 全局事务管理需要在分布式计算环境中，所有的资源都分布在多个系统中。在这种情况下事务管理需要同时在局部和全局范围内进行。
     * 分布式或全局事务跨多个系统执行，它的执行需要全局事务管理系统和所有相关系统的局部数据管理人员之间的协调。
     *
     * 编程式 vs. 声明式
     * Spring 支持两种类型的事务管理:
     *
     * 编程式事务管理 ：这意味着你在编程的帮助下有管理事务。这给了你极大的灵活性，但却很难维护。
     *
     * 声明式事务管理 ：这意味着你从业务代码中分离事务管理。你仅仅使用注释或 XML 配置来管理事务。
     * 声明式事务管理比编程式事务管理更可取，尽管它不如编程式事务管理灵活，但它允许你通过代码控制事务。
     * 但作为一种横切关注点，声明式事务管理可以使用 AOP 方法进行模块化。Spring 支持使用 Spring AOP 框架的声明式事务管理。
     *
     * */



    //Spring 事务抽象
    //Spring 事务抽象的关键是由 org.springframework.transaction.PlatformTransactionManager 接口定义，如下所示：

    //根据指定的传播行为，该方法返回当前活动事务或创建一个新的事务。
    TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException;
    //该方法提交给定的事务和关于它的状态。
    void commit(TransactionStatus status) throws TransactionException;
    //该方法执行一个给定事务的回滚。
    void rollback(TransactionStatus status) throws TransactionException;



    //TransactionDefinition 是在 Spring 中事务支持的核心接口，它的定义如下：

    //该方法返回传播行为。Spring 提供了与 EJB CMT 类似的所有的事务传播选项。
    int getPropagationBehavior();
    //该方法返回该事务独立于其他事务的工作的程度。
    int getIsolationLevel();
    //该方法返回该事务的名称。
    String getName();
    //该方法返回以秒为单位的时间间隔，事务必须在该时间间隔内完成
    int getTimeout();
    //该方法返回该事务是否是只读的。
    boolean isReadOnly();

    /**
     * 下面是隔离级别的可能值:
     *
     * 序号	隔离 & 描述
     * 1
     * TransactionDefinition.ISOLATION_DEFAULT
     *
     * 这是默认的隔离级别。
     *
     * 2
     * TransactionDefinition.ISOLATION_READ_COMMITTED
     *
     * 表明能够阻止误读；可以发生不可重复读和虚读。
     *
     * 3
     * TransactionDefinition.ISOLATION_READ_UNCOMMITTED
     *
     * 表明可以发生误读、不可重复读和虚读。
     *
     * 4
     * TransactionDefinition.ISOLATION_REPEATABLE_READ
     *
     * 表明能够阻止误读和不可重复读；可以发生虚读。
     *
     * 5
     * TransactionDefinition.ISOLATION_SERIALIZABLE
     *
     * 表明能够阻止误读、不可重复读和虚读。





     * 下面是传播类型的可能值:
     *
     * 序号	传播 & 描述
     * 1
     * TransactionDefinition.PROPAGATION_MANDATORY
     *
     * 支持当前事务；如果不存在当前事务，则抛出一个异常。
     *
     * 2
     * TransactionDefinition.PROPAGATION_NESTED
     *
     * 如果存在当前事务，则在一个嵌套的事务中执行。
     *
     * 3
     * TransactionDefinition.PROPAGATION_NEVER
     *
     * 不支持当前事务；如果存在当前事务，则抛出一个异常。
     *
     * 4
     * TransactionDefinition.PROPAGATION_NOT_SUPPORTED
     *
     * 不支持当前事务；而总是执行非事务性。
     *
     * 5
     * TransactionDefinition.PROPAGATION_REQUIRED
     *
     * 支持当前事务；如果不存在事务，则创建一个新的事务。
     *
     * 6
     * TransactionDefinition.PROPAGATION_REQUIRES_NEW
     *
     * 创建一个新事务，如果存在一个事务，则把当前事务挂起。
     *
     * 7
     * TransactionDefinition.PROPAGATION_SUPPORTS
     *
     * 支持当前事务；如果不存在，则执行非事务性。
     *
     * 8
     * TransactionDefinition.TIMEOUT_DEFAULT
     *
     * 使用默认超时的底层事务系统，或者如果不支持超时则没有。
     * */

}
