package com.example.application.config.mybatisInterceptor;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.text.DateFormat;
import java.util.*;


/**
 * 拦截Executor update方法
 */
@Intercepts({
//        @Signature(
//                method = "query",
//                type = Executor.class,
//                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
//        ),
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}
        )
})
@Slf4j
public class LogInterceptor implements Interceptor {
    //
//    @Resource
//    @Lazy
//    BizOrderMapper bizOrderMapper;
//
//    @Resource
//    @Lazy
//    BizOrderAllUpdateLogMapper bizOrderAllUpdateLogMapper;

    /**
     * 是否显示语句的执行时间
     */
    public static final String PROPERTIES_KEY_ENABLE_EXECUTOR_TIME = "enableExecutorTIme";
    public static final String ENABLE_EXECUTOR_TIME = "0"; // 显示

    private boolean enableExecutorTime = false;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object re = invocation.proceed();

        // 获取执行方法的MappedStatement参数,不管是Executor的query方法还是update方法，第一个参数都是MappedStatement
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        String sqlId = mappedStatement.getId();

        if (!(invocation.getArgs().length > 1)) {
            log.info("invocation.getArgs()[1]无数据异常");
            return re;
        }
        //获取更新数据并转换为map
        Object parameter = invocation.getArgs()[1];
        String orderJsonData = JSONObject.toJSONString(parameter);
        HashMap data = JSONObject.parseObject(orderJsonData, HashMap.class);

        //若为biz_order表则进行相关拦截操作
        if (filterMapperById(sqlId,"BizOrderMapper")) {
            orderIntercept(data);
        }

        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();
        long sqlStartTime = System.currentTimeMillis();
        long sqlEndTime = System.currentTimeMillis();
        // 打印mysql执行语句
        String sql = getSql(configuration, boundSql, sqlId);
        System.out.println(sql);
        // 打印mysql执行时间
        if (enableExecutorTime) {
            String sqlTimeLog = sqlId + " 方法对应sql执行时间:" + (sqlEndTime - sqlStartTime) + " ms";
            System.out.println(sqlTimeLog);
        }
        return re;
    }


    public void orderIntercept (HashMap data){
        try {
            //订单更新数据log
//            BizOrderAllUpdateLogDO bizOrderAllUpdateLogDO = new BizOrderAllUpdateLogDO();
//            if (Objects.nonNull(data.get("id"))) {
//                BizOrderDO orderDO = new BizOrderDO();
//                orderDO.setId(Long.parseLong(data.get("id").toString()));
//                orderDO.setDeleted(CommonConstants.NOT_DELETE);
//                BizOrderDO bizOrderDO = bizOrderMapper.selectOne(orderDO);
//
//                if (Objects.nonNull(bizOrderDO)) {
//                    bizOrderAllUpdateLogDO.setId(IdGeneratorSnowflakeUtils.genId());
//                }
//                bizOrderAllUpdateLogMapper.insertSelective(bizOrderAllUpdateLogDO);
//            }
        }catch (Exception e){
            log.error("orderIntercept exception:{}",e.getMessage());
        }
    }

    /**
     * 根据获取到执行 id 找到对应的方法，只在 mapper 上执行拦截
     *
     * @param sqlId 根据 MappedStatement 获取到的 id 属性
     * @return 是否是 mapper
     */
    private boolean filterMapperById(String sqlId,String mapper) {
        String[] names = sqlId.split("\\.");
        return names[names.length - 2].equals(mapper);
    }

    /**
     * 通过该方法决定要返回的对象是目标对象还是对应的代理
     * 不要想的太复杂，一般就两种情况：
     * <p>
     * 1. return target;  直接返回目标对象，相当于当前Interceptor没起作用，不会调用上面的intercept()方法
     * 2. return Plugin.wrap(target, this);  返回代理对象，会调用上面的intercept()方法
     *
     * @param target 目标对象
     * @return 目标对象或者代理对象
     */
    @Override
    public Object plugin(Object target) {
        //做拦截处理:默认都是这一句
        return Plugin.wrap(target, this);
        //不做拦截: return o;
    }

    /**
     * 用于获取在Configuration初始化当前的Interceptor时时候设置的一些参数
     *
     * @param properties Properties参数
     */
    @Override
    public void setProperties(Properties properties) {
        if (properties != null) {
            String executorTImeValue = properties.getProperty(PROPERTIES_KEY_ENABLE_EXECUTOR_TIME);
            if (executorTImeValue != null) {
                enableExecutorTime = executorTImeValue.equals(ENABLE_EXECUTOR_TIME);
            }
        }
    }

    private static String getSql(Configuration configuration, BoundSql boundSql, String sqlId) {
        return sqlId + " 方法对应sql执行语句:" + assembleSql(configuration, boundSql);
    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{}
     * \\需要第一个替换，否则replace方法替换时会有逻辑bug
     */
    private static String makeQueryStringAllRegExp(String str) {
        if (str != null && !str.equals("")) {
            return str.replace("\\", "\\\\").replace("*", "\\*")
                    .replace("+", "\\+").replace("|", "\\|")
                    .replace("{", "\\{").replace("}", "\\}")
                    .replace("(", "\\(").replace(")", "\\)")
                    .replace("^", "\\^").replace("$", "\\$")
                    .replace("[", "\\[").replace("]", "\\]")
                    .replace("?", "\\?").replace(",", "\\,")
                    .replace(".", "\\.").replace("&", "\\&");
        }
        return str;
    }

    /**
     * 获取参数对应的string值
     *
     * @param obj 参数对应的值
     * @return string
     */
    private static String getParameterValue(Object obj) {
        String value;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }

        }
        // 对特殊字符进行转义，方便之后处理替换
        return value != null ? makeQueryStringAllRegExp(value) : value;
    }

    /**
     * 组装完整的sql语句 -- 把对应的参数都代入到sql语句里面
     *
     * @param configuration Configuration
     * @param boundSql      BoundSql
     * @return sql完整语句
     */
    private static String assembleSql(Configuration configuration, BoundSql boundSql) {
        // 获取mapper里面方法上的参数
        Object sqlParameter = boundSql.getParameterObject();
        // sql语句里面需要的参数 -- 真实需要用到的参数 因为sqlParameter里面的每个参数不一定都会用到
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        // sql原始语句(?还没有替换成我们具体的参数)
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && sqlParameter != null) {
            // sql语句里面的?替换成真实的参数
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(sqlParameter.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(sqlParameter));
            } else {
                MetaObject metaObject = configuration.newMetaObject(sqlParameter);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    // 一个一个把对应的值替换进去 按顺序把?替换成对应的值
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }
}
