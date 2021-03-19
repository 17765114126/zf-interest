package com.example.springboot.service.sys;


import com.example.springboot.model.form.Result;

public interface UserPwdService {
        Result updatePwd(String oldPwd, String newPwd);
        Result updateDefaultPwd(String mobile);
}
