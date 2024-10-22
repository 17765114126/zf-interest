package com.example.application;

/**
 * @ClassName GeneratorCodeUtil
 * @Author zhaofu
 * @Date 2020/12/29
 * @Version V1.0
 **/

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.io.IOException;

/**
 * 自动生成pojo，mapper, service, controller
 */
public class GeneratorCodeUtil {
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/welibrary?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
//    private static final String DB_URL = "jdbc:mysql://rm-bp14f94yt54bl7l54o.mysql.rds.aliyuncs.com:3306/db_new_mall_template?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/zhaofu?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";

//    private static final String DB_URL = "jdbc:mysql://rm-bp14f94yt54bl7l54o.mysql.rds.aliyuncs.com:3306/db_dora_syj?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
//    private static final String DB_URL = "jdbc:mysql://rm-bp14f94yt54bl7l54o.mysql.rds.aliyuncs.com:3306/db_yimai?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
//    private static final String DB_URL = "jdbc:mysql://zeekrlfie-dev-rds-public.mysql.rds.aliyuncs.com:3306/zeekrlife_mp_pay?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";

//    private static final String USERNAME = "root";
//    private static final String USERNAME = "db_template";
//    private static final String USERNAME = "zeekrlife";

//    private static final String PASSWORD = "";
//    private static final String PASSWORD = "wUuKOKAuNK!9%5w1$";
//    private static final String PASSWORD = "Zeekrlife#dev";

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    /**
     * 请自定义自己的包名，后续的代码生成会在这个包下
     */
    private static final String PACKAGE_NAME = "com.example.application.templates";

    /**
     * 生成文件的输出目录
     * */
    private static final String OUT_PUT_DIR = "/generator/src/main/java/";

    /**
     * 请自定义自己的模块名（可以理解为项目名称）
     */
    private static final String MODULE_NAME = System.getProperty("user.dir");

    /**
     * 请自定义自己的db url
     */
    private static final String DB_URL = "jdbc:mysql://192.168.0.130:3306/server_home?useSSL=false&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8";

    /**
     * 请自定义自己的username
     */
    private static final String USERNAME = "root";

    /**
     * 请自定义自己的password
     */
    private static final String PASSWORD = "123qwer.";

    public static void main(String[] args) throws IOException {
        System.out.println("--------------------开始自动生成相关的类----------------------");
        System.out.println("args = " + new File("").getAbsolutePath() + OUT_PUT_DIR);
        generateByTables(MODULE_NAME,
                "coupon_present_log");
        System.out.println("--------------------------生成成功------------------------");
    }

    /**
     * 通过表名生成相关类
     *
     * @param module
     * @param tableNames
     */
    private static void generateByTables(String module, String... tableNames) {
        moduleGenerator(module, tableNames);
    }

    private static void moduleGenerator(String module, String[] tableNames) {
        // 全局配置
        GlobalConfig globalConfig = getGlobalConfig(module);
        // 数据源配置
        DataSourceConfig dataSourceConfig = getDataSourceConfig();
        // 包配置
        PackageConfig packageConfig = getPackageConfig();
        // 策略配置
        StrategyConfig strategyConfig = getStrategyConfig(tableNames);
        // 模板配置
        TemplateConfig templateConfig = getTemplateConfig();
        // 采用自定义代码生成器来完成
        new MyAutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)
                .setStrategy(strategyConfig)
                .setTemplate(templateConfig)
                .execute();
    }

    /**
     * 自定义代码生成模板
     * 在模版引擎的执行方法中会校验如果模版为空则不会执行writer()方法
     *
     * @return
     */
    private static TemplateConfig getTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig
                // entity模板采用自定义模板
                .setEntity("templates/entity.java.vm")
                // mapper模板采用自定义模板
                .setMapper("templates/mapper.java.vm")
                // xml文件
                .setXml("templates/mapper.xml.vm")
                // service接口
                .setService("templates/service.java.vm")
                // serviceImpl模板采用自定义模板
                .setServiceImpl("templates/serviceImpl.java.vm")
                // controller模板采用自定义模板
                .setController("templates/controller.java.vm");
        return templateConfig;
    }

    /**
     * 定义策略
     *
     * @param tableNames
     * @return
     */
    private static StrategyConfig getStrategyConfig(String... tableNames) {
        StrategyConfig strategyConfig = new StrategyConfig();
//        strategyConfig.setNaming()
        strategyConfig
                .setCapitalMode(false)
                // 自动lombok；
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setControllerMappingHyphenStyle(true)
                .setLogicDeleteFieldName("del_flag")
                // entity忽略生成字段
//                .setSuperEntityColumns("del_flag", "last_updated_time", "last_updated_by_id", "created_time", "created_by_id")
//                .setSuperEntityColumns("del_flag", "last_update_time", "last_update_user_uuid", "create_time", "create_user_uuid")

                //表名命名策略(驼峰)
                .setNaming(NamingStrategy.underline_to_camel)
                //字段名命名策略(驼峰)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                // 设置要映射的表名
                .setInclude(tableNames)
                //表名前缀
                .setTablePrefix();
        return strategyConfig;
    }

    /**
     * 配置生成包名
     *
     * @return
     */
    private static PackageConfig getPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig
                .setParent(PACKAGE_NAME)
//                .setModuleName(PACKAGE_NAME)
                .setEntity("entity")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("Impl")
                .setController("controller");
        return packageConfig;
    }

    /**
     * 配置数据源
     *
     * @return
     */
    private static DataSourceConfig getDataSourceConfig() {
        String dbUrl = DB_URL;
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setDriverName(DRIVER_NAME)
                .setUsername(USERNAME)
                .setPassword(PASSWORD)
                .setUrl(dbUrl);
        return dataSourceConfig;
    }

    /**
     * 全局配置，配置生成文件的目录
     *
     * @return
     */
    private static GlobalConfig getGlobalConfig(String module) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOpen(false)
                // new File("module").getAbsolutePath()得到当前模块根目录路径
                //生成文件的输出目录
                .setOutputDir(new File(module).getAbsolutePath() + OUT_PUT_DIR)
                //是否覆盖已有文件
                .setFileOverride(true)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setActiveRecord(false)
                .setAuthor("zf")
                .setSwagger2(false) //默认false
                // 去Service的I前缀
                .setServiceName("%sService")
                .setDateType(DateType.ONLY_DATE);
        return globalConfig;
    }
}