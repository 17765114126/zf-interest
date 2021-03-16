package com.example.springboot.service.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.mapper.CmsButtonMapper;
import com.example.springboot.mapper.CmsModelMapper;
import com.example.springboot.mapper.CmsPermissionMapper;
import com.example.springboot.mapper.CmsRolePermissionMapper;
import com.example.springboot.model.entity.CmsButton;
import com.example.springboot.model.entity.CmsModel;
import com.example.springboot.model.entity.CmsPermission;
import com.example.springboot.model.entity.CmsRolePermission;
import com.example.springboot.model.enums.IntegerPool;
import com.example.springboot.model.sys.*;
import com.example.springboot.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础数据 - 菜单管理
 * Created by chen on 2019/7/1.
 */
@Slf4j
@Service
public class CmsModelService {
    @Resource
    CmsModelMapper cmsModelMapper;
    @Resource
    CmsPermissionMapper cmsPermissionMapper;
    @Resource
    CmsButtonMapper cmsButtonMapper;
    @Resource
    CmsRolePermissionMapper cmsRolePermissionMapper;
    /**
     *查询菜单列表
     */
    public Page<CmsModelVo> selectAllModel1(Integer searchPage){
        if(searchPage == null){
            searchPage = 1;
        }
        //设置分页
        IPage<CmsModel> page = new Page<>(searchPage, IntegerPool.PAGE);
        //查询数据
        LambdaQueryWrapper<CmsModel> wrapper = PageUtil.getQueryWrapper(new CmsModel()).eq(CmsModel::getStatus, 1).orderByAsc(CmsModel::getSort).orderByDesc(CmsModel::getCreateDateTime);
        IPage<CmsModel> iPage = cmsModelMapper.selectPage(page, wrapper);
        //将ipage实例化
        Page<CmsModel> cmsModelPage = PageUtil.toPage(iPage);
        //对实例化的对象进行操作
        return PageUtil.convert(cmsModelPage, cmsModel -> {

            CmsModelVo modelVo = new CmsModelVo();

            CopyUtil.copyObject(cmsModel, modelVo);

            List<CmsButton> buttonsList = cmsButtonMapper.selectList(new QueryWrapper<CmsButton>().lambda().eq(CmsButton::getModelId,modelVo.getId()).eq(CmsButton::getInvalid,0));

            modelVo.setCmsButton(buttonsList);

            return modelVo;
        });
    }
    public Page<CmsModelVo> selectAllModel(Integer searchPage){
        if(searchPage == null){
            searchPage = 1;
        }
        //设置分页
        IPage<CmsModel> page = new Page<>(searchPage, IntegerPool.PAGE);
        //查询数据
        LambdaQueryWrapper<CmsModel> wrapper = PageUtil.getQueryWrapper(new CmsModel()).eq(CmsModel::getStatus, 1)
                .eq(CmsModel::getPid,0).orderByAsc(CmsModel::getSort).orderByDesc(CmsModel::getCreateDateTime);
        IPage<CmsModel> iPage = cmsModelMapper.selectPage(page, wrapper);
        //将ipage实例化
        Page<CmsModel> cmsModelPage = PageUtil.toPage(iPage);
        //对实例化的对象进行操作
        //遍历所有的一级
        return PageUtil.convert(cmsModelPage, cmsModel -> {
            //查询每一个一级对应的二级
            List<CmsModel> models = cmsModelMapper.selectList(new QueryWrapper<CmsModel>().lambda().eq(CmsModel::getPid,cmsModel.getId())
                    .eq(CmsModel::getStatus, 1));
            CmsModelVo modelVo = new CmsModelVo();
            CopyUtil.copyObject(cmsModel,modelVo);
//            modelVo.setId(cmsModel.getId());
//            modelVo.setPid(cmsModel.getPid());
//            modelVo.setName(cmsModel.getName());

            List<CmsModelButton> cmsModelButtons = CopyUtil.copyList(models, CmsModelButton.class);
            modelVo.setCmsModels(cmsModelButtons);
            List<CmsButton> buttonsList = null;
            //查询二级下面的按钮
            for (CmsModelButton model : cmsModelButtons){
                buttonsList = cmsButtonMapper.selectList(new QueryWrapper<CmsButton>().lambda().eq(CmsButton::getModelId,model.getId()).eq(CmsButton::getInvalid,0));
                model.setCmsButtons(buttonsList);
            }
            return modelVo;
        });
    }



    /*
    点击添加菜单 - 下拉选所有付级
     */
    public List<CmsModel> selectModelByInvalid(){
        List<CmsModel> modelList = cmsModelMapper.selectList(new QueryWrapper<CmsModel>().lambda().eq(CmsModel::getStatus,1).eq(CmsModel::getPid,0).orderByDesc(CmsModel::getCreateDateTime));
        return modelList;
    }
    @Transactional
    public Object saveCmsModel(Children children){
        String name = children.getName();
        String iconCls  = children.getIconCls();
        String path = children.getPath();

        if(path.equalsIgnoreCase("-") && children.getMeta() != null && children.getMeta().getBtnPermissions() != null && children.getMeta().getBtnPermissions().size()>0){
            return Result.buildFail("主菜没有按钮无需添加");
        }
        //添加权限
        try {
            CmsPermission cmsPermission = new CmsPermission();
            if(StringUtils.isBlank(path) || path.trim().equals("-")) {
                cmsPermission.setName(PinYin.getPinYin(name));
            }else {
                cmsPermission.setName(path);
            }
            cmsPermission.setComment(name);
            cmsPermission.setType(1);//菜单
            cmsPermissionMapper.insert(cmsPermission);
            Long cmsPermissionId = cmsPermission.getId();
            //添加模版
            CmsModel cmsModel = new CmsModel();
            cmsModel.setName(name);
            cmsModel.setImgUrl(iconCls);
            cmsModel.setUrl(path);
            cmsModel.setPermissionId(cmsPermissionId);
            if(children.getPid() == null){
                cmsModel.setPid(0);
            }else{
                cmsModel.setPid(children.getPid());
            }
            cmsModel.setStatus(1);
            cmsModel.setSort(children.getSort()==null?0:children.getSort());
            cmsModelMapper.insert(cmsModel);
            long modelId = cmsModel.getId();
            //添加按钮-往按钮表添加数据，以及权限表添加按钮的权限
            if(children.getMeta() != null){
                List<BtnPermissions> btnPermissions = children.getMeta().getBtnPermissions();
                if(btnPermissions != null && btnPermissions.size()>0){
                    for(BtnPermissions btn : btnPermissions){
                        CmsPermission btncmsPermission = new CmsPermission();
                        btncmsPermission.setName(btn.getUrl());
                        btncmsPermission.setComment(name+btn.getName());
                        btncmsPermission.setType(2);//按钮
                        cmsPermissionMapper.insert(btncmsPermission);
                        Long btncmsPermissionId = btncmsPermission.getId();//按钮的权限id

                        CmsButton button = new CmsButton();
                        button.setComment(btn.getName());
                        button.setName(btn.getName());
                        button.setModelId((int)modelId);
                        button.setPermissionId(btncmsPermissionId);
                        button.setUrl(btn.getUrl());
                        cmsButtonMapper.insert(button);
                    }
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("model/modelAdd",e);
            return Result.buildFail("保存失败");
        }
        return Result.buildSuccess();
    }


    public List<ChilrenCmsModelVo> selectCmsModelAll(){
        //ModelAndButtonVo的列表
        List<ChilrenCmsModelVo> chilrenCmsModelVoList = new ArrayList<>();
        //CmsModel条件构造器
        QueryWrapper<CmsModel> mqw = new QueryWrapper<>();
        mqw.select("id,pid,sort,name,permission_id");
        mqw.lambda().eq(CmsModel::getStatus, 1);
        List<CmsModel> cmsModels = cmsModelMapper.selectList(mqw);
        if(cmsModels==null||cmsModels.size()<1){
            return null;
        }
        List<ChilrenCmsModelVo> chilrenCmsModelVos = CopyUtil.copyList(cmsModels, ChilrenCmsModelVo.class);
        return chilrenCmsModelVos;
    }

    public List<ChilrenCmsModelVo> selectCmsModelById(Integer modelId){
        //ModelAndButtonVo的列表
        List<ChilrenCmsModelVo> chilrenCmsModelVoList = new ArrayList<>();
        //CmsModel条件构造器
        QueryWrapper<CmsModel> mqw = new QueryWrapper<>();
        mqw.select("id,pid,sort,name,permission_id");
        mqw.lambda().eq(CmsModel::getStatus, 1).eq(CmsModel::getPid,modelId);
        List<CmsModel> cmsModels = cmsModelMapper.selectList(mqw);
        if(cmsModels==null||cmsModels.size()<1){
            return null;
        }

        for (CmsModel cmsModel:cmsModels) {
            ChilrenCmsModelVo chilrenCmsModelVo = new ChilrenCmsModelVo();
            CopyUtil.copyObject(cmsModel, chilrenCmsModelVo);
            chilrenCmsModelVoList.add(chilrenCmsModelVo);
        }
        return chilrenCmsModelVoList;
    }
    public CmsModelVo editCmsModel(Long modelId){
        CmsModel model = cmsModelMapper.selectOne(new QueryWrapper<CmsModel>().lambda().eq(CmsModel::getId,modelId));
        CmsModelVo vo = new CmsModelVo();
        try {
            BeanUtils.copyProperties(vo,model);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        List<CmsButton> buttonsList = cmsButtonMapper.selectList(new QueryWrapper<CmsButton>().lambda().eq(CmsButton::getModelId,model.getId()).eq(CmsButton::getInvalid,0));
        List<CmsModel> modelList = cmsModelMapper.selectList(new QueryWrapper<CmsModel>().lambda().eq(CmsModel::getStatus,1).eq(CmsModel::getPid,0));
        vo.setCmsButton(buttonsList);
        vo.setCmsModel(modelList);
        return vo;
    }
    @Transactional
    public Result delButton(Long buttonId){
        CmsButton button = cmsButtonMapper.selectOne(new QueryWrapper<CmsButton>().lambda().eq(CmsButton::getId,buttonId));
        int a = 0;
        try {
            cmsPermissionMapper.deleteById(button.getPermissionId());
            a = cmsButtonMapper.deleteById(buttonId);
            cmsRolePermissionMapper.delete(new QueryWrapper<CmsRolePermission>().lambda().eq(CmsRolePermission::getPermissionId,button.getPermissionId()));
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("model/buttonDel",e);
            return Result.buildFail("删除失败");
        }
        if(a<=0){
            return Result.buildFail("删除失败");
        }
        return new Result();
    }
    @Transactional
    public Object saveEditCmsModel(Children children){
        int modelId = children.getId();
        String name = children.getName();
        String iconCls  = children.getIconCls();
        String path = children.getPath();

        if(path.equalsIgnoreCase("-") && children.getMeta() != null && children.getMeta().getBtnPermissions() != null && children.getMeta().getBtnPermissions().size()>0){
            return Result.buildFail("主菜没有按钮无需添加");
        }
        try {
            CmsModel cmsModel = new CmsModel();
            cmsModel.setId(modelId);
            cmsModel.setName(name);
            cmsModel.setImgUrl(iconCls);
            cmsModel.setUrl(path);
            cmsModel.setSort(children.getSort());
            cmsModel.setPid(children.getPid());
            cmsModelMapper.updateById(cmsModel);
            CmsModel cmsModel1 = cmsModelMapper.selectById(modelId);
            CmsPermission cmsPermission1 = cmsPermissionMapper.selectById(cmsModel1.getPermissionId());
            if(cmsPermission1 != null){
                cmsPermission1.setComment(name);
                if(StringUtils.isBlank(path) || path.trim().equals("-")) {
                    cmsPermission1.setName(PinYin.getPinYin(name));
                }else {
                    cmsPermission1.setName(path);
                }
            }
            cmsPermissionMapper.updateById(cmsPermission1);
            //添加按钮-往按钮表添加数据，以及权限表添加按钮的权限
            if(children.getMeta() != null){
                List<BtnPermissions> btnPermissions = children.getMeta().getBtnPermissions();
                if(btnPermissions != null && btnPermissions.size()>0){
                    for(BtnPermissions btn : btnPermissions){
                        CmsButton cmsButton = cmsButtonMapper.selectById(btn.getId());
                        //说明没有这个按钮,那么添加并给与权限
                        if(cmsButton == null){
                            CmsPermission btncmsPermission = new CmsPermission();
                            //btncmsPermission.setName(PinYin.getPinYin(name+"/"+btn.getName()));
                            btncmsPermission.setName(btn.getUrl());
                            btncmsPermission.setComment(name+"/"+btn.getName());
                            //按钮
                            btncmsPermission.setType(2);
                            cmsPermissionMapper.insert(btncmsPermission);
                            //按钮的权限id
                            Long btncmsPermissionId = btncmsPermission.getId();

                            CmsButton button = new CmsButton();
                            button.setComment(btn.getName());
                            button.setName(btn.getName());
                            button.setModelId(modelId);
                            button.setPermissionId(btncmsPermissionId);
                            button.setUrl(btn.getUrl());
                            cmsButtonMapper.insert(button);
                        }else{//修改按钮的名称
                            cmsButton.setId(btn.getId());
                            cmsButton.setComment(btn.getName());
                            cmsButton.setName(btn.getName());
                            cmsButton.setUrl(btn.getUrl());
                            cmsButtonMapper.updateById(cmsButton);
                            CmsPermission cmsPermission = cmsPermissionMapper.selectById(cmsButton.getPermissionId());
                            if(cmsPermission != null){
                                cmsPermission.setComment(name+"/"+btn.getName());
                                //cmsPermission.setName(PinYin.getPinYin(name+"/"+btn.getName()));
                                cmsPermission.setName(btn.getUrl());
                            }
                            cmsPermissionMapper.updateById(cmsPermission);
                        }
                    }
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("model/saveEditCmsModel",e);
            return Result.buildFail("保存失败");
        }
        return Result.buildSuccess();
    }

}
