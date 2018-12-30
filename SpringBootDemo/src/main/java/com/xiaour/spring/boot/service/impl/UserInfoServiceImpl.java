package com.xiaour.spring.boot.service.impl;

import com.xiaour.spring.boot.entity.UserInfo;
import com.xiaour.spring.boot.dao.UserInfoDao;
import com.xiaour.spring.boot.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo selectByPrimaryKey(Integer id) {
        return userInfoDao.selectByPrimaryKey(id);
    }
}
