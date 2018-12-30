package com.xiaour.spring.boot.service.impl;

import com.xiaour.spring.boot.dao.read.PayInfoReaderDao;
import com.xiaour.spring.boot.dao.write.PayInfoWriterDao;
import com.xiaour.spring.boot.entity.PayInfo;
import com.xiaour.spring.boot.service.PayInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PayInfoServiceImpl implements PayInfoService {
    @Resource
    private PayInfoReaderDao payInfoReaderDao;
    @Resource
    private PayInfoWriterDao payInfoWriterDao;

    @Override
    public PayInfo selectByPrimaryKey(Long id) {
        return payInfoReaderDao.selectByPrimaryKey(id);
    }

    @Override
    public int insertSelective(PayInfo record) {
        return payInfoWriterDao.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return payInfoWriterDao.deleteByPrimaryKey(id);
    }
}
