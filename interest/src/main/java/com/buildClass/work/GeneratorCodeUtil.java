package com.buildClass.work;

/**
 * @ClassName GeneratorCodeUtil
 * @Author zhaofu
 * @Date 2020/12/29
 * @Version V1.0
 **/

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.io.IOException;

/**
 * 自动生成pojo，mapper, service, controller
 */
public class GeneratorCodeUtil {
    /**
     * 请自定义自己的db url
     */
    private static final String DB_URL = "jdbc:mysql://rm-8vb13nmd5388n09m6wo.mysql.zhangbei.rds.aliyuncs.com:3306/welibrary?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
//    private static final String DB_URL = "jdbc:mysql://rm-bp14f94yt54bl7l54o.mysql.rds.aliyuncs.com:3306/db_yiqian?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";

    /**
     * 请自定义自己的username
     */
    private static final String USERNAME = "zhaofu";
//    private static final String USERNAME = "db_yiqian";


    /**
     * 请自定义自己的password
     */
    private static final String PASSWORD = "2MiaoMiao";
//    private static final String PASSWORD = "xRh1rcRrgaTgm1j6";


    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    /**
     * 请自定义自己的包名，后续的代码生成会在这个包下
     */
    private static final String PACKAGE_NAME = "com.buildClass.templates";

    /**
     * 请自定义自己的模块名（可以理解为项目名称）
     */
    private static final String MODULE_NAME = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException {
        System.out.println("--------------------开始自动生成相关的类----------------------");
        System.out.println("args = " + new File("").getAbsolutePath() + "/src/main/java/");

        generateByTables(MODULE_NAME, "bl_contents");
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
     * 自定义代码生成模板, 由于我的项目中完全舍弃了xml文件和service接口，因此置null,
     * 在模版引擎的执行方法中会校验如果模版为空则不会执行writer()方法
     *
     * @return
     */
    private static TemplateConfig getTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();

        templateConfig.setEntity("templates/entity.java.vm") // entity模板采用自定义模板
                .setMapper("templates/mapper.java.vm")// mapper模板采用自定义模板
//                .setXml(null) // 不生成xml文件
//                .setService(null) // 不生成service接口
                .setServiceImpl("templates/serviceImpl.java.vm") // serviceImpl模板采用自定义模板
                .setController("templates/controller.java.vm");// controller模板采用自定义模板
//                .setEntityKt("templates/VO.java.vm");

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
                //表名命名策略(驼峰)
                .setNaming(NamingStrategy.underline_to_camel)
                //字段名命名策略(驼峰)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                // 设置要映射的表名
                .setInclude(tableNames)
                //表名前缀
                .setTablePrefix("bl_");
        return strategyConfig;
    }

    /**
     * 配置生成包名
     *
     * @return
     */
    private static PackageConfig getPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(PACKAGE_NAME)
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
                .setOutputDir(new File(module).getAbsolutePath() + "/src/main/java")
                //是否覆盖已有文件
                .setFileOverride(true)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setActiveRecord(false)
                .setAuthor("zf")
                // 去Service的I前缀
                .setServiceName("%sService")
                .setDateType(DateType.ONLY_DATE);


        return globalConfig;
    }
}