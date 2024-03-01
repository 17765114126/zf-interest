package com.example.application;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.*;

/**
 * @author: wanglin
 * @date: 2023-09-12 周二
 * @Version: 1.0
 * @Description:
 */
@SuppressWarnings("all")
public class MybatisGeneratorSystem {

    /**
     * 指定具体项目模块
     */
    public static String MOUDLE_NAME = "cloud-system";
    public static String AUTHOR_NAME = "wanglin";
    /**
     * 数据源信息
     */
    public static String URL = "jdbc:mysql://localhost:3306/test_db?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
    public static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static String USERNAME = "root";
    public static String PASSWORD = "123456";
    /**
     * 包设置
     */
    public static String PARENT_PACKAGE = "com.wl.cloud.system";
    public static String MAPPER_PATH = "/src/main/resources/mapper/";
    /**
     * DTO/VO/QueryDTO 包路径
     */
    public static String PARENT_DTO_PATH = "\\src\\main\\java\\com\\wl\\cloud\\system\\support\\dto\\";
    public static String PARENT_VO_PATH = "\\src\\main\\java\\com\\wl\\cloud\\system\\support\\vo\\";
    public static String PARENT_Query_DTO_PATH = "\\src\\main\\java\\com\\wl\\cloud\\system\\support\\dto\\query\\";
    /**
     * DTO/VO/QueryDTO 包名
     */
    public static String PARENT_DTO_PACKAGE = "com.wl.cloud.system.support.dto";
    public static String PARENT_VO_PACKAGE = "com.wl.cloud.system.support.vo";
    public static String PARENT_Query_DTO_PACKAGE = "com.wl.cloud.system.support.dto.query";
    /**
     * DTO/VO/QueryDTO 自定义模板路径
     */
    public static String TEMPLATE_CONTROLLER_PATH = "/vm/java/controller.java";
    public static String TEMPLATE_SERVICE_PATH = "/vm/java/service.java";
    public static String TEMPLATE_SERVICEIMPL_PATH = "/vm/java/serviceImpl.java";
    public static String TEMPLATE_MAPPER_PATH = "/vm/java/mapper.java";
    public static String TEMPLATE_MAPPER_XML_PATH = "/vm/xml/mapper.xml.vm";
    /**
     * DTO/VO/QueryDTO 自定义模板路径
     */
    public static String TEMPLATE_DTO_PATH = "/vm/java/dto.java.vm";
    public static String TEMPLATE_VO_PATH = "/vm/java/vo.java.vm";
    public static String TEMPLATE_QUERY_DTO_PATH = "/vm/java/queryDto.java.vm";

    /**
     * controller 层请求路径前缀
     */
    public static String REQUEST_PATH_PREFIX = "cloud/sample/api/v1/";

    /**
     * 表前缀,生成实体类时，会自动去除表前缀，如：table: tb_task, class: Task
     */
    public static String TABLE_PREFIX_ENTITY = "gl_";

    /**
     * 需要生成的表名前缀,若为空，则默认是需要生成的表名是 TABLE_SUFFIX
     */
    public static String TABLE_PREFIX = "gl_sys_";
    /**
     * 需要生成的表名后缀，多个用逗号隔开
     */
//    public static String TABLE_SUFFIX = "menu,role,user_role,role_menu";
    public static String TABLE_SUFFIX = "menu";

    /**
     * DTO 忽略的字段
     */
    public static String DTO_IGNORE_FIELD = "id,createTime,updateTime,deleted";

    public static void main(String[] args) {
        if ("".equals(TABLE_SUFFIX)) {
            System.out.println("----->>>需要生成的表名为空");
            return;
        }
        //表拼接
        String[] tables;
        if ("".equals(TABLE_PREFIX)) {
            tables = TABLE_SUFFIX.split(",");
        } else {
            List<String> tableList = Arrays.asList(TABLE_SUFFIX.split(","));
            tables = tableList.stream().map(MybatisGeneratorSystem::apply).toArray(String[]::new);

        }
        System.out.println("表：");
        Arrays.stream(tables).forEach(System.out::println);

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/" + MOUDLE_NAME + "/src/main/java");//设置代码生成路径

        gc.setFileOverride(true);//是否覆盖以前文件
        gc.setOpen(false);//是否打开生成目录
        gc.setAuthor(AUTHOR_NAME);//设置项目作者名称
        // gc.setIdType(IdType.AUTO);//设置主键策略
        gc.setIdType(IdType.ID_WORKER);//设置主键策略
        gc.setBaseResultMap(true);//生成基本ResultMap
        gc.setBaseColumnList(true);//生成基本ColumnList
        gc.setServiceName("%sService");//去掉服务默认前缀
        gc.setDateType(DateType.ONLY_DATE);//设置时间类型
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        dsc.setDriverName(DRIVER_NAME);
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(PARENT_PACKAGE);
        pc.setMapper("dao");
        //不生成实体类
        pc.setEntity("domain");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        //自定义配置，配置自定义属性注入
        InjectionConfig cfg = new InjectionConfig() {
            //在.ftl(或者是.vm)模板中，通过${cfg.abc}获取属性
            @Override
            public void initMap() {
                //自定义生成模板参数
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                //模板中获取值：${cfg.requestPathPrefix}
                map.put("requestPathPrefix", REQUEST_PATH_PREFIX);
                map.put("dtoPackage", PARENT_DTO_PACKAGE);
                map.put("voPackage", PARENT_VO_PACKAGE);
                map.put("queryDtoPackage", PARENT_Query_DTO_PACKAGE);
                map.put("dtoIgnoreFields", DTO_IGNORE_FIELD);
                this.setMap(map);
            }
        };

        // 如果模板引擎是 freemarker
        // String templatePath = "/templates/mapper.xml.vm.ftl";
        // 如果模板引擎是 velocity：
        // String templatePath = "/templates/mapper.xml.vm" ;
        //自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(TEMPLATE_MAPPER_XML_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/" + MOUDLE_NAME + MAPPER_PATH + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        //自定义生成类DTO/VO/QueryDOT
        focList.add(new FileOutConfig(TEMPLATE_DTO_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "\\" + MOUDLE_NAME + PARENT_DTO_PATH + tableInfo.getEntityName() + "DTO" + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig(TEMPLATE_VO_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "\\" + MOUDLE_NAME + PARENT_VO_PATH + tableInfo.getEntityName() + "VO" + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig(TEMPLATE_QUERY_DTO_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "\\" + MOUDLE_NAME + PARENT_Query_DTO_PATH + tableInfo.getEntityName() + "QueryDTO" + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 自定义模板
        TemplateConfig templateConfig = new TemplateConfig();
        //控制:不生java下成xml
        templateConfig.setXml(null);
        templateConfig.setEntity(null);

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        templateConfig.setController(TEMPLATE_CONTROLLER_PATH);
        templateConfig.setService(TEMPLATE_SERVICE_PATH);
        templateConfig.setServiceImpl(TEMPLATE_SERVICEIMPL_PATH);
        templateConfig.setMapper(TEMPLATE_MAPPER_PATH);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        sc.setEntityLombokModel(true);//自动lombok
        sc.setRestControllerStyle(true);
        sc.setControllerMappingHyphenStyle(true);
        sc.setLogicDeleteFieldName("deleted");//设置逻辑删除

        //设置自动填充配置
        TableFill gmt_create = new TableFill("create_time", FieldFill.INSERT);
        TableFill gmt_modified = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmt_create);
        tableFills.add(gmt_modified);
        sc.setTableFillList(tableFills);

        //乐观锁
        sc.setVersionFieldName("version");
        sc.setRestControllerStyle(true);//驼峰命名

        sc.setTablePrefix(TABLE_PREFIX_ENTITY); //设置表名前缀
        sc.setInclude(tables);//表名，多个英文逗号分割
        mpg.setStrategy(sc);

        // 生成代码
        mpg.execute();
    }

    private static String apply(String t) {
        return TABLE_PREFIX + t;
    }
}

