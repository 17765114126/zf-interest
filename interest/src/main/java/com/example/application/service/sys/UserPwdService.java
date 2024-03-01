package com.example.application.service.sys;


import com.example.application.model.form.Result;

public interface UserPwdService {
        Result updatePwd(String oldPwd, String newPwd);
        Result updateDefaultPwd(String mobile);
}
