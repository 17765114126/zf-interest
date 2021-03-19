package com.example.springboot.service.sys.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.mapper.CmsButtonMapper;
import com.example.springboot.mapper.CmsModelMapper;
import com.example.springboot.mapper.CmsRoleMapper;
import com.example.springboot.mapper.CmsRolePermissionMapper;
import com.example.springboot.model.entity.CmsButton;
import com.example.springboot.model.entity.CmsModel;
import com.example.springboot.model.entity.CmsRole;
import com.example.springboot.model.entity.CmsRolePermission;
import com.example.springboot.model.sys.ChilrenCmsModelVo;
import com.example.springboot.model.sys.CmsButtonVo;
import com.example.springboot.model.sys.ModelAndButtonVo;
import com.example.springboot.service.sys.CmsModelService;
import com.example.springboot.service.sys.PermissionListService;
import com.example.springboot.utils.CopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author <a href="mailto:cjw_0709@163.com">cjw</a>
 * @create 2019/7/1 10:23
 */
@Service
@Slf4j
public class PermissionListServiceImp implements PermissionListService {

    @Resource
    private CmsRolePermissionMapper cmsRolePermissionMapper;

    @Resource
    private CmsRoleMapper cmsRoleMapper;

    @Resource
    private CmsModelMapper cmsModelMapper;

    @Resource
    private CmsButtonMapper cmsButtonMapper;

    @Autowired
    private CmsModelService cmsModelService;


    @Override
    public List<CmsRolePermission> selectByRoleId(Long roleId) {
        QueryWrapper<CmsRolePermission> qw = new QueryWrapper<>();
        qw.select("permission_id,type");
        qw.lambda().eq(CmsRolePermission::getRoleId, roleId);
        List<CmsRolePermission> cmsRolePermissions = cmsRolePermissionMapper.selectList(qw);
        return cmsRolePermissions;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindPermission(Long roleId, String modelPermission, String buttonPermission) {
        QueryWrapper<CmsRolePermission> qw = new QueryWrapper<>();
        qw.lambda().eq(CmsRolePermission::getRoleId, roleId);
        cmsRolePermissionMapper.delete(qw);
        //添加
        String[] modelPermissionList = modelPermission.split(",");
        for (String permission : modelPermissionList) {
            Long permissionId = Long.parseLong(permission);
            CmsRolePermission crp = new CmsRolePermission();
            crp.setRoleId(roleId);
            crp.setPermissionId(permissionId);
            crp.setType(1);
            cmsRolePermissionMapper.insert(crp);
            System.out.println(crp.getId());
        }
        if(StringUtils.isNotBlank(buttonPermission)) {
            String[] buttonPermissionList = buttonPermission.split(",");
            for (String permission : buttonPermissionList) {
                Long permissionId = Long.parseLong(permission);
                CmsRolePermission crp = new CmsRolePermission();
                crp.setRoleId(roleId);
                crp.setPermissionId(permissionId);
                crp.setType(2);
                cmsRolePermissionMapper.insert(crp);
                System.out.println(crp.getId());
            }
        }
    }

    @Override
    public List<CmsRole> selectAllCmsRole() {
        QueryWrapper<CmsRole> qw = new QueryWrapper<>();
        qw.select("id,name,comment");
        qw.lambda().eq(CmsRole::getInvalid, 0);
        return cmsRoleMapper.selectList(qw);
    }

    @Override
    public ModelAndButtonVo selectModelAndButton() {
        //查询所有菜单
        List<ChilrenCmsModelVo> cmsModelVos = cmsModelService.selectCmsModelAll();
        if (cmsModelVos == null) {
            return null;
        }
        //组成一级菜单List
        List<ChilrenCmsModelVo> chilrenCmsModelVos = CopyUtil.copyList(cmsModelVos, ChilrenCmsModelVo.class);
        chilrenCmsModelVos = chilrenCmsModelVos
                .stream()
                .filter((a) -> a.getPid().equals(0))
                .map(b->{
                    b.setParentPermissionId(0l);
                    return b;
                })
                .collect(Collectors.toList());
        //组成菜单按钮树形列表
        for (ChilrenCmsModelVo cmsModelVo : chilrenCmsModelVos) {
            cmsModelVo.setChilrenCmsModels(getChild(cmsModelVo.getPermissionId(), cmsModelVo.getId(), cmsModelVos));
        }
        //ModelAndButtonVo的列表
        ModelAndButtonVo modelAndButtonVo = new ModelAndButtonVo();
        modelAndButtonVo.setChilrenCmsModels(chilrenCmsModelVos);
        return modelAndButtonVo;
    }

    @Override
    public ModelAndButtonVo selectModelAndButtonByParam(List<CmsModel> list) {
       /* //查询所有菜单
        List<ChilrenCmsModelVo> cmsModelVos = cmsModelService.selectCmsModelAll();
        if(cmsModelVos==null){
            return null;
        }*/

        //ModelAndButtonVo的列表
        List<ChilrenCmsModelVo> cmsModelVos = new ArrayList<>();

        for (CmsModel cmsModel : list) {
            ChilrenCmsModelVo chilrenCmsModelVo = new ChilrenCmsModelVo();
            chilrenCmsModelVo.setId(cmsModel.getId());
            chilrenCmsModelVo.setPid(cmsModel.getPid());
            chilrenCmsModelVo.setSort(cmsModel.getSort());
            chilrenCmsModelVo.setName(cmsModel.getName());
            chilrenCmsModelVo.setPermissionId(cmsModel.getPermissionId());
            cmsModelVos.add(chilrenCmsModelVo);
        }


        //组成一级菜单List
        List<ChilrenCmsModelVo> cmsModelVoList = new ArrayList<>();
        for (ChilrenCmsModelVo cmsModelVo : cmsModelVos) {
            if (cmsModelVo.getPid().equals(0)) {
                cmsModelVoList.add(cmsModelVo);
            }
        }
        //组成菜单按钮树形列表
        for (ChilrenCmsModelVo cmsModelVo : cmsModelVoList) {
            cmsModelVo.setChilrenCmsModels(getChild(cmsModelVo.getParentPermissionId(), cmsModelVo.getId(), cmsModelVos));
        }
        //ModelAndButtonVo的列表
        ModelAndButtonVo modelAndButtonVo = new ModelAndButtonVo();
        modelAndButtonVo.setChilrenCmsModels(cmsModelVoList);
        return modelAndButtonVo;
    }


    /**
     * 子菜单递归
     *
     * @param id
     * @param list
     * @return
     */
    private List<ChilrenCmsModelVo> getChild(Long permissionId, Integer id, List<ChilrenCmsModelVo> list) {
        // 子菜单
        List<ChilrenCmsModelVo> childList = new ArrayList<>();
        for (ChilrenCmsModelVo cmsModelVo : list) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (cmsModelVo.getPid().equals(id)) {
                QueryWrapper<CmsButton> qw = new QueryWrapper<>();
                qw.select("id,model_id,name,comment,permission_id");
                qw.lambda().eq(CmsButton::getModelId, cmsModelVo.getId()).eq(CmsButton::getInvalid, 0);
                List<CmsButton> cmsButtons = cmsButtonMapper.selectList(qw);
                List<CmsButtonVo> cmsButtonVos = CopyUtil.copyList(cmsButtons, CmsButtonVo.class);
                cmsButtonVos.stream().forEach(e -> {
                    e.setParentPermissionId(cmsModelVo.getPermissionId());});
                cmsModelVo.setCmsButtons(cmsButtonVos);
                cmsModelVo.setParentPermissionId(permissionId);
                childList.add(cmsModelVo);
            }
        }

        // 把子菜单的子菜单再循环一遍
        for (ChilrenCmsModelVo chilrenCmsModelVo : childList) {
            chilrenCmsModelVo.setChilrenCmsModels(getChild(chilrenCmsModelVo.getPermissionId(), chilrenCmsModelVo.getId(), list));// 递归
        }

        // 判断递归结束
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

}
