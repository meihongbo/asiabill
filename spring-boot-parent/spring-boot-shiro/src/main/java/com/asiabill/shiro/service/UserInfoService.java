package com.asiabill.shiro.service;

import com.asiabill.shiro.domain.UserInfo;

public interface UserInfoService {
	
	/**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);

}
