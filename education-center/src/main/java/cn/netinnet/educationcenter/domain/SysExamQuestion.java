/*
 * SysExamQuestion.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-08-26 Created
 */
package cn.netinnet.educationcenter.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2020-08-26
 **/
public class SysExamQuestion implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 场次ID
     */
    private Long sessionId;

    /**
     * 题目ID
     */
    private Long qustionId;

    /**
     * 试题分值
     */
    private Integer scoreValue;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    private static final long serialVersionUID = 3649511150676879360L;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取场次ID
     *
     * @return session_id - 场次ID
     */
    public Long getSessionId() {
        return sessionId;
    }

    /**
     * 设置场次ID
     *
     * @param sessionId 场次ID
     */
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * 获取题目ID
     *
     * @return qustion_id - 题目ID
     */
    public Long getQustionId() {
        return qustionId;
    }

    /**
     * 设置题目ID
     *
     * @param qustionId 题目ID
     */
    public void setQustionId(Long qustionId) {
        this.qustionId = qustionId;
    }

    /**
     * 获取试题分值
     *
     * @return score_value - 试题分值
     */
    public Integer getScoreValue() {
        return scoreValue;
    }

    /**
     * 设置试题分值
     *
     * @param scoreValue 试题分值
     */
    public void setScoreValue(Integer scoreValue) {
        this.scoreValue = scoreValue;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", qustionId=").append(qustionId);
        sb.append(", scoreValue=").append(scoreValue);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
