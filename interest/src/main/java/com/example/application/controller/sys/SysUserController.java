package com.example.application.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.application.SysUserService;
import com.example.application.mapper.CmsButtonMapper;
import com.example.application.mapper.CmsModelMapper;
import com.example.application.model.entity.CmsButton;
import com.example.application.model.entity.CmsModel;
import com.example.application.model.form.Result;
import com.example.application.model.sys.MenuVO;
import com.example.application.model.sys.MetaVO;
import com.example.application.model.sys.SysPermission;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/sysuser")
public class SysUserController {

    @Resource
    SysUserService sysUserService;

    @Resource
    CmsModelMapper cmsModelMapper;

    @Resource
    CmsButtonMapper cmsButtonMapper;

    @RequestMapping("/permission_list")
    public Object permission_list() {
        List<MenuVO> resultList = new ArrayList<>();
        List<SysPermission> pl = sysUserService.getPermissionList();
        if (CollectionUtils.isEmpty(pl)) {
            return Result.with(resultList);
        }
        Map<Integer, List<CmsModel>> mmap = new HashMap<>();
        Map<Integer, List<CmsButton>> bmap = new HashMap<>();
        //筛选
        for (SysPermission p : pl) {
            if (p.getType() == 1) {
                CmsModel m = cmsModelMapper.selectOne(new QueryWrapper<CmsModel>().lambda()
                        .eq(CmsModel::getPermissionId, p.getId())
                        .eq(CmsModel::getStatus, 1));
                if (m != null) {
                    if (!mmap.containsKey(m.getPid())) {
                        List<CmsModel> ml = new ArrayList<>();
                        ml.add(m);
                        mmap.put(m.getPid(), ml);
                    } else {
                        mmap.get(m.getPid()).add(m);
                    }
                }
            } else if (p.getType() == 2) {
                CmsButton b = cmsButtonMapper.selectOne(new QueryWrapper<CmsButton>().lambda()
                        .eq(CmsButton::getPermissionId, p.getId())
                );
                if (!bmap.containsKey(b.getModelId())) {
                    List<CmsButton> bl = new ArrayList<>();
                    bl.add(b);
                    bmap.put(b.getModelId(), bl);
                } else {
                    bmap.get(b.getModelId()).add(b);
                }
            }
        }
        if (MapUtils.isNotEmpty(mmap)) {
            List<CmsModel> oneLevels = mmap.get(0);
            Collections.sort(oneLevels, (a, b) -> (a.getSort() - b.getSort()));
            //输出
            resultList = createChildMenu(0, mmap, bmap);
        }
        return Result.with(resultList);
    }

    private static List<MenuVO> createChildMenu(Integer menuPid, Map<Integer, List<CmsModel>> mmap, Map<Integer, List<CmsButton>> bmap) {
        if (!mmap.containsKey(menuPid)) {
            return null;
        }
        List<CmsModel> menuList = mmap.get(menuPid);
        Collections.sort(menuList, (a, b) -> (a.getSort() - b.getSort()));
        //输出
        List<MenuVO> ml = new ArrayList<>();
        for (CmsModel m : menuList) {
            MenuVO mv = new MenuVO();
            mv.setId(m.getId());
            mv.setIconCls(m.getImgUrl());
            mv.setName(m.getName());
            mv.setPath(m.getUrl());
            MetaVO mtv = new MetaVO();
            List<CmsButton> bl = bmap.get(m.getId());
            if (CollectionUtils.isNotEmpty(bl)) {
                List<String> bts = new ArrayList<>();
                bl.stream().forEach(a -> bts.add(a.getName()));
                mtv.setBtnPermissions(bts);
            }
            mv.setMeta(mtv);
            ml.add(mv);
            List<MenuVO> ll = createChildMenu(m.getId(), mmap, bmap);
            if (CollectionUtils.isNotEmpty(ll)) {
                mv.setChildren(ll);
            }
        }
        return ml;
    }

}
