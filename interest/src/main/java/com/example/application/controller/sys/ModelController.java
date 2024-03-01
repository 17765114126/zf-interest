package com.example.application.controller.sys;

import com.alibaba.fastjson.JSON;
import com.example.application.model.entity.CmsModel;
import com.example.application.model.form.Result;
import com.example.application.model.sys.Children;
import com.example.application.model.sys.CmsModelVo;
import com.example.application.service.sys.CmsModelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**基础数据 - 菜单管理
 * Created by chen on 2019/7/1.
 */
@RestController
@Slf4j
@RequestMapping(value = "/model")
public class ModelController {
    @Resource
    CmsModelService cmsModelService;

    @RequestMapping(value = "/modelList")
    /*
    查询菜单列表
     */
    public Object selectModelList(Integer page) {
        try {
            return Result.buildSuccess(cmsModelService.selectAllModel(page));
        }catch (Exception e){
            log.error("查询结果异常,",e);
            return Result.buildFail("查询结果异常:"+e);
        }
    }
    /*
    点击下拉选弹出
     */
    @RequestMapping(value = "/models")
    public Object selectModel() {
        try {
            List<CmsModel> modelList = cmsModelService.selectModelByInvalid();
            return Result.with(modelList);
        }catch (Exception e){
            log.error("查询结果异常,",e);
            return Result.buildFail("查询结果异常:"+e);
        }
    }
    /*
    点击添加菜单-点击保存
     */
    @PostMapping(value = "/modelAdd" )
    public Object saveModel(@RequestBody Children children) {
//        Children children =  JSON.parseObject(json, Children.class);
        try {
            if(children != null && StringUtils.isNotBlank(children.getName()) && StringUtils.isNotBlank(children.getPath())){
                return cmsModelService.saveCmsModel(children);
            }else{
                return Result.buildFail("参数错误!");
            }
        }catch (Exception e){
            log.error("保存异常,",e);
            return Result.buildFail("保存异常:"+e);
        }
    }

    /*
    点击修改将数据返回给前端
     */
    @RequestMapping(value = "/modelEdit")
    public Object editModel(Long modelId) {
        try {
            if(modelId == null){
                return Result.buildFail("参数不可以为空");
            }
            //向权限表添加数据
            CmsModelVo modelVo = cmsModelService.editCmsModel(modelId);
            return Result.with(modelVo);
        }catch (Exception e){
            log.error("查询结果异常,",e);
            return Result.buildFail("查询结果异常:"+e);
        }
    }
    /*
    按钮删除
     */
    @RequestMapping(value = "/buttonDel")
    public Object delButton(Long buttonId) {
        try {
            if(buttonId == null){
                return Result.buildFail("参数不可以为空");
            }
            //向权限表添加数据
            return cmsModelService.delButton(buttonId);
        }catch (Exception e){
            log.error("/buttonDel,",e);
            return Result.buildFail("删除失败"+e);
        }
    }
    /*
    修改菜单
     */
    @RequestMapping(value = "/saveEditCmsModel")
    public Object saveEditCmsModel(String json) {
        Children children =  JSON.parseObject(json, Children.class);
        try {
            if(children != null && StringUtils.isNotBlank(children.getName()) && StringUtils.isNotBlank(children.getPath())){
                return cmsModelService.saveEditCmsModel(children);
            }else{
                return Result.buildFail("参数错误!");
            }
        }catch (Exception e){
            log.error("保存异常,",e);
            return Result.buildFail("保存异常:"+e);
        }
    }

}
