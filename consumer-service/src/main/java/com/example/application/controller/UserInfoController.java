package com.example.application.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.application.api.UserInfoService;
import com.example.application.pojo.UserInfoDTO;
import com.example.application.pojo.UserInfoVO;
import com.example.application.pojo.UserInfoParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 客户信息控制层
 * @Author lz.wang
 * @Since 1.0
 * @Date 2019/11/19
 *
 * @Reference ：远程引用指定的服务，他会按照全类名进行匹配，看谁给注册中心注册了这个全类名
 */
@RestController
public class UserInfoController {

    /**忽略启动校验*/
    @Reference(check=false)
    private UserInfoService userInfoService;


    @RequestMapping("/getUserInfoById")
    public UserInfoVO getUserInfoById(Long id){

        return userInfoService.getUserInfoById(id);
    }

    @RequestMapping("/getUserInfo")
    public UserInfoVO getUserInfo(UserInfoParam userInfoParam){

        UserInfoDTO dto = new UserInfoDTO();
        dto.setId(userInfoParam.getId());
        dto.setName(userInfoParam.getName());
        dto.setAge(userInfoParam.getAge());
        return userInfoService.getUserInfo(dto);
    }

}
