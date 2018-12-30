package com.xiaour.spring.boot.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayInfo implements Serializable {
    /** 主键编号 */
    private Long id;

    /** 企业id */
    private String corpId;

    /** 平台类型 */
    private String platformType;

    /** 付费金额 */
    private BigDecimal payAmount;

    /** 付费时企业设定人数 */
    private Integer userCount;

    /** 付款人 */
    private String payer;

    /** 操作人id */
    private String operUserId;

    /** 备注 */
    private String memo;

    /** 扩展字段 */
    private String extFields;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /** 主键编号 */
    public Long getId() {
        return id;
    }

    /** 主键编号 */
    public void setId(Long id) {
        this.id = id;
    }

    /** 企业id */
    public String getCorpId() {
        return corpId;
    }

    /** 企业id */
    public void setCorpId(String corpId) {
        this.corpId = corpId == null ? null : corpId.trim();
    }

    /** 平台类型 */
    public String getPlatformType() {
        return platformType;
    }

    /** 平台类型 */
    public void setPlatformType(String platformType) {
        this.platformType = platformType == null ? null : platformType.trim();
    }

    /** 付费金额 */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /** 付费金额 */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /** 付费时企业设定人数 */
    public Integer getUserCount() {
        return userCount;
    }

    /** 付费时企业设定人数 */
    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    /** 付款人 */
    public String getPayer() {
        return payer;
    }

    /** 付款人 */
    public void setPayer(String payer) {
        this.payer = payer == null ? null : payer.trim();
    }

    /** 操作人id */
    public String getOperUserId() {
        return operUserId;
    }

    /** 操作人id */
    public void setOperUserId(String operUserId) {
        this.operUserId = operUserId == null ? null : operUserId.trim();
    }

    /** 备注 */
    public String getMemo() {
        return memo;
    }

    /** 备注 */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    /** 扩展字段 */
    public String getExtFields() {
        return extFields;
    }

    /** 扩展字段 */
    public void setExtFields(String extFields) {
        this.extFields = extFields == null ? null : extFields.trim();
    }

    /** 创建时间 */
    public Date getCreateTime() {
        return createTime;
    }

    /** 创建时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 修改时间 */
    public Date getUpdateTime() {
        return updateTime;
    }

    /** 修改时间 */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}