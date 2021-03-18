package com.example.springboot.config.Shiro.filter;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.config.Shiro.security.SecurityConstants;
import com.example.springboot.config.Shiro.security.SecurityUtil;
import com.example.springboot.config.Shiro.security.SpringUtils;
import com.example.springboot.mapper.CmsButtonMapper;
import com.example.springboot.mapper.CmsModelMapper;
import com.example.springboot.mapper.CmsPermissionMapper;
import com.example.springboot.model.sys.SysUser;
import com.example.springboot.model.entity.CmsButton;
import com.example.springboot.model.entity.CmsModel;
import com.example.springboot.model.entity.CmsPermission;
import com.example.springboot.service.sys.SysUserService;
import com.example.springboot.model.form.Result;
import com.example.springboot.model.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            String requri = req.getRequestURI();
//            Map<String, String> params = new HashMap<>(16);
//            for (Object name : request.getParameterMap().keySet()) {
//                String v = request.getParameter(name.toString());
//                if (v != null && v != "") {
//                    params.put(name.toString(), v);
//                }
//            }

            if(!SecurityConstants.PATH_WHITE_LIST.contains(requri)){
                SysUser sysUser = SecurityUtil.sysUser();
                if(sysUser == null){
                    response.getWriter().write(JSON.toJSONString(Result.with(ResultCodeEnum.NOT_LOGIN)));
                    return;
                }
                SysUserService sysUserService = SpringUtils.getBean("sysUserService");
                CmsPermissionMapper cmsPermissionMapper = SpringUtils.getBean("cmsPermissionMapper");
                int count = cmsPermissionMapper.selectCount(new QueryWrapper<CmsPermission>().lambda().eq(CmsPermission::getName,requri));
                //判断权限
                if(count>0 && !sysUserService.hasPermission(requri)){
                    response.getWriter().write(JSON.toJSONString(Result.with(ResultCodeEnum.NOT_AUTH)));
                    return;
                }
                SysUser user = SecurityUtil.sysUser();
                String mobile = user.getMobile();
                CmsButtonMapper cmsButtonMapper = SpringUtils.getBean("cmsButtonMapper");
                CmsButton b = cmsButtonMapper.selectOne(new QueryWrapper<CmsButton>().lambda().eq(CmsButton::getUrl,requri));
                String optName = "";
                if(b!=null){
                    CmsModelMapper cmsModelMapper = SpringUtils.getBean("cmsModelMapper");
                    optName = b.getName();
                    Integer modelId = b.getModelId();
                    CmsModel mm = cmsModelMapper.selectById(modelId);
                    optName = mm.getName() + ":" + optName;
                    Integer parentId = mm.getPid();
                    while (parentId != 0) {
                        CmsModel pm = cmsModelMapper.selectById(parentId);
                        parentId = pm.getPid();
                        optName = pm.getName() + "-" + optName;
                    }
                }
//                if(StringUtils.isNotBlank(optName)) {
//                    SyjOptLogService syjCmsOptLogService = SpringUtils.getBean("syjOptLogService");
//                    syjCmsOptLogService.log(mobile, 2, optName, JSON.toJSONString(params), null);
//                }
            }
            chain.doFilter(request, response);
        }catch (Exception e){
            log.error("",e);
//            response.getWriter().write(JSON.toJSONString(Result.with(ResultCodeEnum.INNER_ERROR)));
        }
    }

    @Override
    public void destroy() {
    }
}
