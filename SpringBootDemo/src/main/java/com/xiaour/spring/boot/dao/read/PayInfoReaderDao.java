package com.xiaour.spring.boot.dao.read;

import com.xiaour.spring.boot.entity.PayInfo;

public interface PayInfoReaderDao {
    PayInfo selectByPrimaryKey(Long id);
}