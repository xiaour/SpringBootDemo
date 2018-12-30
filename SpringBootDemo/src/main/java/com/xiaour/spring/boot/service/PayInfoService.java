package com.xiaour.spring.boot.service;

import com.xiaour.spring.boot.entity.PayInfo;

public interface PayInfoService {
    PayInfo selectByPrimaryKey(Long id);

    int insertSelective(PayInfo record);

    int deleteByPrimaryKey(Long id);
}
