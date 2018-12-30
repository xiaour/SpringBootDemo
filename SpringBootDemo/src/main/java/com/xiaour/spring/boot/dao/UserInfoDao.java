package com.xiaour.spring.boot.dao;

import com.xiaour.spring.boot.entity.UserInfo;

public interface UserInfoDao {

    UserInfo selectByPrimaryKey(Integer id);

}