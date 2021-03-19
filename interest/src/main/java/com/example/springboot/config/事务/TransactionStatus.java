package com.example.springboot.config.事务;

import org.springframework.transaction.SavepointManager;
//TransactionStatus 接口为事务代码提供了一个简单的方法来控制事务的执行和查询事务状态。
public interface TransactionStatus extends SavepointManager {
    //在当前事务时新的情况下，该方法返回 true。
    boolean isNewTransaction();
    //该方法返回该事务内部是否有一个保存点，也就是说，基于一个保存点已经创建了嵌套事务。
    boolean hasSavepoint();
    //该方法设置该事务为 rollback-only 标记。
    void setRollbackOnly();
    //该方法返回该事务是否已标记为 rollback-only。
    boolean isRollbackOnly();
    //该方法返回该事务是否完成，也就是说，它是否已经提交或回滚。
    boolean isCompleted();
}
