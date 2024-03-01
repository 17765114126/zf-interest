package com.example.application.config.mybatisInterceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Description:
 * @TableField(fill = FieldFill.INSERT)
 * 和
 * @TableField(fill = FieldFill.INSERT_UPDATE)
 * 两个注解自定义内容
 *
 * Date： 19-6-19.
 *
 * @author puyd
 */
@Component
public class ATGMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
//        this.setFieldValByName("id", SnowIdUtils.uniqueLong(), metaObject);//id雪花算法
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("lastUpdateTime", LocalDateTime.now(), metaObject);
//        this.setFieldValByName("delFlag", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("lastUpdateTime", LocalDateTime.now(), metaObject);
    }

}
