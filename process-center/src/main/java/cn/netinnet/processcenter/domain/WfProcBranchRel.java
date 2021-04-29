/*
 * WfDefAnsStuRel.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-09-08 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2020-09-08
 **/
public class WfProcBranchRel implements Serializable {
    private Long id;

    /**
     * 答案定义流程id
     */
    private Long ansDefId;

    /**
     * 学生定义流程id
     */
    private Long stuDefId;

    /**
     * 答案流程分支id
     */
    private Long ansBranchId;

    /**
     * 学生流程分支id
     */
    private Long stuBranchId;

    /**
     * 场次id
     */
    private Long sessionId;

    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 结果：0 错误； 1正确
     */
    private Integer result;

    /**
     * 逻辑删除：0表示正常； 1表示删除
     */
    private Integer delFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 286886574688804864L;

    public WfProcBranchRel() {
    }

    public WfProcBranchRel(Long id, Long ansDefId, Long stuDefId, Long ansBranchId, Long stuBranchId, Integer result) {
        this.id = id;
        this.ansDefId = ansDefId;
        this.stuDefId = stuDefId;
        this.ansBranchId = ansBranchId;
        this.stuBranchId = stuBranchId;
        this.result = result;
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取答案定义流程id
     *
     * @return ans_def_id - 答案定义流程id
     */
    public Long getAnsDefId() {
        return ansDefId;
    }

    /**
     * 设置答案定义流程id
     *
     * @param ansDefId 答案定义流程id
     */
    public void setAnsDefId(Long ansDefId) {
        this.ansDefId = ansDefId;
    }

    /**
     * 获取学生定义流程id
     *
     * @return stu_def_id - 学生定义流程id
     */
    public Long getStuDefId() {
        return stuDefId;
    }

    /**
     * 设置学生定义流程id
     *
     * @param stuDefId 学生定义流程id
     */
    public void setStuDefId(Long stuDefId) {
        this.stuDefId = stuDefId;
    }

    /**
     * 获取答案流程分支id
     *
     * @return ans_branch_id - 答案流程分支id
     */
    public Long getAnsBranchId() {
        return ansBranchId;
    }

    /**
     * 设置答案流程分支id
     *
     * @param ansBranchId 答案流程分支id
     */
    public void setAnsBranchId(Long ansBranchId) {
        this.ansBranchId = ansBranchId;
    }

    /**
     * 获取学生流程分支id
     *
     * @return stu_branch_id - 学生流程分支id
     */
    public Long getStuBranchId() {
        return stuBranchId;
    }

    /**
     * 设置学生流程分支id
     *
     * @param stuBranchId 学生流程分支id
     */
    public void setStuBranchId(Long stuBranchId) {
        this.stuBranchId = stuBranchId;
    }

    /**
     * 获取场次id
     *
     * @return session_id - 场次id
     */
    public Long getSessionId() {
        return sessionId;
    }

    /**
     * 设置场次id
     *
     * @param sessionId 场次id
     */
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * 获取题目id
     *
     * @return question_id - 题目id
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * 设置题目id
     *
     * @param questionId 题目id
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取结果：0 错误； 1正确
     *
     * @return result - 结果：0 错误； 1正确
     */
    public Integer getResult() {
        return result;
    }

    /**
     * 设置结果：0 错误； 1正确
     *
     * @param result 结果：0 错误； 1正确
     */
    public void setResult(Integer result) {
        this.result = result;
    }

    /**
     * 获取逻辑删除：0表示正常； 1表示删除
     *
     * @return del_flag - 逻辑删除：0表示正常； 1表示删除
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置逻辑删除：0表示正常； 1表示删除
     *
     * @param delFlag 逻辑删除：0表示正常； 1表示删除
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ansDefId=").append(ansDefId);
        sb.append(", stuDefId=").append(stuDefId);
        sb.append(", ansBranchId=").append(ansBranchId);
        sb.append(", stuBranchId=").append(stuBranchId);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", questionId=").append(questionId);
        sb.append(", userId=").append(userId);
        sb.append(", result=").append(result);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}