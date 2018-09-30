package com.asiabill.shiro.mapper;

import com.asiabill.shiro.domain.SysPermission;

public interface SysPermissionMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(SysPermission record);

	int insertSelective(SysPermission record);

	SysPermission selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SysPermission record);

	int updateByPrimaryKey(SysPermission record);

}
