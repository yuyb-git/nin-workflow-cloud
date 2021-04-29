/*
 * WfErrorPoint.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-09-09 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;

/**
 * @author admin
 * @date   2020-09-09
 **/
public class WfErrorPoint implements Serializable {
    /**
     * 错误id
     */
    private Long pointId;

    /**
     * 错误类型：0企业名称，1部门，2岗位，3员工，4流程分类，5流程名称，6流程节点，7流程表达式，8传递字段，9表单错误
     */
    private Integer errorType;

    /**
     * 关联主id
     */
    private Long relIdMain;

    /**
     * 关联子id，根据错误类型为对应表的主键id
     */
    private Long relId;

    /**
     * 错误内容
     */
    private String errorItem;

    /**
     * 场次id
     */
    private Long sessionId;

    /**
     * 试题id
     */
    private Long questionId;

    /**
     * 用户id
     */
    private Long userId;

    private static final long serialVersionUID = 3259882579032880128L;

    /**
     * 获取错误id
     *
     * @return point_id - 错误id
     */
    public Long getPointId() {
        return pointId;
    }

    /**
     * 设置错误id
     *
     * @param pointId 错误id
     */
    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }

    /**
     * 获取错误类型：0企业名称，1部门，2岗位，3员工，4流程分类，5流程名称，6流程节点，7流程表达式，8传递字段，9表单错误
     *
     * @return error_type - 错误类型：0企业名称，1部门，2岗位，3员工，4流程分类，5流程名称，6流程节点，7流程表达式，8传递字段，9表单错误
     */
    public Integer getErrorType() {
        return errorType;
    }

    /**
     * 设置错误类型：0企业名称，1部门，2岗位，3员工，4流程分类，5流程名称，6流程节点，7流程表达式，8传递字段，9表单错误
     *
     * @param errorType 错误类型：0企业名称，1部门，2岗位，3员工，4流程分类，5流程名称，6流程节点，7流程表达式，8传递字段，9表单错误
     */
    public void setErrorType(Integer errorType) {
        this.errorType = errorType;
    }

    /**
     * 获取关联主id
     *
     * @return rel_id_main - 关联主id
     */
    public Long getRelIdMain() {
        return relIdMain;
    }

    /**
     * 设置关联主id
     *
     * @param relIdMain 关联主id
     */
    public void setRelIdMain(Long relIdMain) {
        this.relIdMain = relIdMain;
    }

    /**
     * 获取关联子id，根据错误类型为对应表的主键id
     *
     * @return rel_id - 关联子id，根据错误类型为对应表的主键id
     */
    public Long getRelId() {
        return relId;
    }

    /**
     * 设置关联子id，根据错误类型为对应表的主键id
     *
     * @param relId 关联子id，根据错误类型为对应表的主键id
     */
    public void setRelId(Long relId) {
        this.relId = relId;
    }

    /**
     * 获取错误内容
     *
     * @return error_item - 错误内容
     */
    public String getErrorItem() {
        return errorItem;
    }

    /**
     * 设置错误内容
     *
     * @param errorItem 错误内容
     */
    public void setErrorItem(String errorItem) {
        this.errorItem = errorItem;
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
     * 获取试题id
     *
     * @return question_id - 试题id
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * 设置试题id
     *
     * @param questionId 试题id
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pointId=").append(pointId);
        sb.append(", errorType=").append(errorType);
        sb.append(", relIdMain=").append(relIdMain);
        sb.append(", relId=").append(relId);
        sb.append(", errorItem=").append(errorItem);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", questionId=").append(questionId);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}