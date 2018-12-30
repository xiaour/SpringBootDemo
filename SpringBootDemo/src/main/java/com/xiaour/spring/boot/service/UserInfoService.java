package com.xiaour.spring.boot.service;

import com.xiaour.spring.boot.entity.UserInfo;

public interface UserInfoService {
    UserInfo selectByPrimaryKey(Integer id);
}
