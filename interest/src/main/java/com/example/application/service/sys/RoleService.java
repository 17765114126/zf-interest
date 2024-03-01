package com.example.application.service.sys;


import com.example.application.model.entity.CmsRole;
import com.example.application.model.form.Result;

import java.util.List;
import java.util.Map;

public interface RoleService<T> {

    boolean save(CmsRole cmsRole);

    boolean updateById(CmsRole cmsRole);

    Result deleteById(Long id);

    List<T> listByMap(Map<String, Object> columnMap);

    List<T> listByName(String name);

    Result getRoleBox();

    Result copyRole(Long id);
}
