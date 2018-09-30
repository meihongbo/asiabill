package com.asiabill.shiro.mapper;

import com.asiabill.shiro.domain.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

	UserInfo findByUsername(String username);
}