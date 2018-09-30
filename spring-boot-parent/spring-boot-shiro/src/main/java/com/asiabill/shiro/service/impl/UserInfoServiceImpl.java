package com.asiabill.shiro.service.impl;

import javax.annotation.Resource;

import com.asiabill.shiro.domain.UserInfo;
import com.asiabill.shiro.mapper.UserInfoMapper;
import com.asiabill.shiro.service.UserInfoService;

public class UserInfoServiceImpl implements UserInfoService {

	@Resource
    private UserInfoMapper userInfoMapper;
	
    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoMapper.findByUsername(username);
    }

}
