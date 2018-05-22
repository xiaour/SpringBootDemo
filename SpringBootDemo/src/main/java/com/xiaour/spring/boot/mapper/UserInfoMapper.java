package com.xiaour.spring.boot.mapper;

import com.xiaour.spring.boot.entity.UserInfo;

public interface UserInfoMapper {

    UserInfo selectByPrimaryKey(Integer id);

}