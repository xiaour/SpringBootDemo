package com.xiaour.spring.boot.dao.write;

import com.xiaour.spring.boot.entity.PayInfo;

public interface PayInfoWriterDao {
    int deleteByPrimaryKey(Long id);

    int insert(PayInfo record);

    int insertSelective(PayInfo record);

    int updateByPrimaryKeySelective(PayInfo record);

    int updateByPrimaryKey(PayInfo record);
}