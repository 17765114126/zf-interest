package com.buildClass;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;

import java.util.*;

/**
 * @ClassName MyAutoGenerator
 * @Author zhaofu
 * @Date 2020/12/29
 * @Version V1.0
 **/
public class MyAutoGenerator extends AutoGenerator {

    @Override
    protected List<TableInfo> getAllTableInfoList(ConfigBuilder config) {
        List<TableInfo> allTableInfoList = super.getAllTableInfoList(config);
        allTableInfoList.forEach(tableInfo -> {
            List<TableField> fields = tableInfo.getFields();
            Set<String> importPackages = tableInfo.getImportPackages();

            fields.forEach(field -> {
                String propertyName = field.getPropertyName();
                // 将字段名称的首字母小写
                // field.setPropertyName(StringUtils.firstCharToLower(propertyName));

                // 如果存在LocalDateTime类型时，将其修改为Date类型
                if ("LocalDateTime".equals(field.getPropertyType())) {
                    field.setColumnType(DbColumnType.DATE);
                    importPackages.remove("java.time.LocalDateTime");
                    importPackages.add("java.util.Date");
                }
            });

            /*
             * 可以将ServiceImpl名称修改为 => Service（本人改了回来）
             * ps：UserServiceImpl -> UserService
             */
            tableInfo.setServiceImplName(tableInfo.getServiceImplName());
            tableInfo.setServiceName(tableInfo.getServiceName());

            // TODO 后续需要对自动代码生成进行相关拓展，可在此进行操作
        });

        // 自定义配置模版， 如果你想添加一个新的类，可以在资源文件目录中的templates文件夹下添加自定义的模版文件
        config.setInjectionConfig(getInjectionConfig(config));
        return allTableInfoList;
    }

    public InjectionConfig getInjectionConfig(ConfigBuilder config) {

        String projectPath = System.getProperty("user.dir");

        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                //自定义生成模板参数
//                Map<String, Object> map = new HashMap<>();
////                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
//                //模板中获取值：${cfg.req}
//                map.put("req", "com.buildClass.templates");//req生成地址
//                map.put("vo", "com.buildClass.templates");//vo生成地址
//                this.setMap(map);
            }
        };

        List<FileOutConfig> forList = new ArrayList<>();
        forList.add(new FileOutConfig("templates/entityReq.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
//                return String.format((config.getPathInfo().get(ConstVal.SERVICE_IMPL_PATH) + File.separator + tableInfo.getServiceImplName() + ConstVal.JAVA_SUFFIX), tableInfo.getServiceImplName());
            return projectPath +"\\interest\\src\\main\\java\\com\\example\\springboot\\templates\\entity\\" + tableInfo.getEntityName() + "Req" + StringPool.DOT_JAVA;
            }
        });
        forList.add(new FileOutConfig("templates/entityResp.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath +"\\interest\\src\\main\\java\\com\\example\\springboot\\templates\\entity\\" + tableInfo.getEntityName() + "Resp" + StringPool.DOT_JAVA;
            }
        });
        injectionConfig.setFileOutConfigList(forList);
        return injectionConfig;
    }
}
