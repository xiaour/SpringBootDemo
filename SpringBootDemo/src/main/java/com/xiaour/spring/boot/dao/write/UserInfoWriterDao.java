package com.xiaour.spring.boot.dao.write;

import com.xiaour.spring.boot.entity.UserInfo;

public interface UserInfoWriterDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}