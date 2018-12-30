package com.xiaour.spring.boot.dao.read;

import com.xiaour.spring.boot.entity.UserInfo;

public interface UserInfoReaderDao {
    UserInfo selectByPrimaryKey(Integer id);
}