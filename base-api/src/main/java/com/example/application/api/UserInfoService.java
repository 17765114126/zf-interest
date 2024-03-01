package com.example.application.api;

import com.example.application.pojo.UserInfoDTO;
import com.example.application.pojo.UserInfoVO;

public interface UserInfoService {

    UserInfoVO getUserInfo(UserInfoDTO dto);

    UserInfoVO getUserInfoById(Long id);
}
