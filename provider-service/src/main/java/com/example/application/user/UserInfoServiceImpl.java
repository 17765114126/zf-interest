package com.example.application.user;

import com.example.application.api.UserInfoService;
import com.example.application.pojo.UserInfoDTO;
import com.example.application.pojo.UserInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//不推荐使用@Service，因为需要的是com.alibaba.dubbo.config.annotation.Service;容易和org.springframework.stereotype.Service弄混，新版Dubbo更新变成了@DubboService
//@Service//将服务发布出去
@Component//放在容器中
public class UserInfoServiceImpl implements UserInfoService {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Override
    public UserInfoVO getUserInfo(UserInfoDTO dto) {

        UserInfoVO vo = new UserInfoVO();
        vo.setId(dto.getId());
        vo.setAge(dto.getAge());
        vo.setName(dto.getName());
        vo.setAddress("西湖");
        return vo;
    }

    @Override
    public UserInfoVO getUserInfoById(Long id) {
        UserInfoVO vo = new UserInfoVO();
        vo.setId(id);
        vo.setAge(20);
        vo.setName("小明");
        vo.setAddress("茅家埠");
        return vo;
    }
}
