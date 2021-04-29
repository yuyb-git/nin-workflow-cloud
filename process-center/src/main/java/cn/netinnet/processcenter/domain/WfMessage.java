/*
 * WfMessage.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-09-23 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2020-09-23
 **/
public class WfMessage implements Serializable {
    /**
     * 消息id
     */
    private Long messageId;

    /**
     * 考试id
     */
    private Long examId;

    /**
     * 试题id
     */
    private Long questionId;

    /**
     * 流程实例id
     */
    private Long procInstId;

    /**
     * 任务id
     */
    private Long runTaskId;

    /**
     * 消息类型（0：其他消息；1：审批消息；2：抄送消息）
     */
    private Integer messageType;

    /**
     * 发送者
     */
    private Long sender;

    /**
     * 发送者姓名
     */
    private String serderName;

    /**
     * 接收者
     */
    private Long receiver;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 是否已读
     */
    private Integer ifRead;

    /**
     * 创建人
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 8081033184372052992L;

    /**
     * 获取消息id
     *
     * @return message_id - 消息id
     */
    public Long getMessageId() {
        return messageId;
    }

    /**
     * 设置消息id
     *
     * @param messageId 消息id
     */
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    /**
     * 获取考试id
     *
     * @return exam_id - 考试id
     */
    public Long getExamId() {
        return examId;
    }

    /**
     * 设置考试id
     *
     * @param examId 考试id
     */
    public void setExamId(Long examId) {
        this.examId = examId;
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
     * 获取流程实例id
     *
     * @return proc_inst_id - 流程实例id
     */
    public Long getProcInstId() {
        return procInstId;
    }

    /**
     * 设置流程实例id
     *
     * @param procInstId 流程实例id
     */
    public void setProcInstId(Long procInstId) {
        this.procInstId = procInstId;
    }

    /**
     * 获取任务id
     *
     * @return run_task_id - 任务id
     */
    public Long getRunTaskId() {
        return runTaskId;
    }

    /**
     * 设置任务id
     *
     * @param runTaskId 任务id
     */
    public void setRunTaskId(Long runTaskId) {
        this.runTaskId = runTaskId;
    }

    /**
     * 获取消息类型（0：其他消息；1：审批消息；2：抄送消息）
     *
     * @return message_type - 消息类型（0：其他消息；1：审批消息；2：抄送消息）
     */
    public Integer getMessageType() {
        return messageType;
    }

    /**
     * 设置消息类型（0：其他消息；1：审批消息；2：抄送消息）
     *
     * @param messageType 消息类型（0：其他消息；1：审批消息；2：抄送消息）
     */
    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    /**
     * 获取发送者
     *
     * @return sender - 发送者
     */
    public Long getSender() {
        return sender;
    }

    /**
     * 设置发送者
     *
     * @param sender 发送者
     */
    public void setSender(Long sender) {
        this.sender = sender;
    }

    /**
     * 获取发送者姓名
     *
     * @return serder_name - 发送者姓名
     */
    public String getSerderName() {
        return serderName;
    }

    /**
     * 设置发送者姓名
     *
     * @param serderName 发送者姓名
     */
    public void setSerderName(String serderName) {
        this.serderName = serderName;
    }

    /**
     * 获取接收者
     *
     * @return receiver - 接收者
     */
    public Long getReceiver() {
        return receiver;
    }

    /**
     * 设置接收者
     *
     * @param receiver 接收者
     */
    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }

    /**
     * 获取消息内容
     *
     * @return content - 消息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置消息内容
     *
     * @param content 消息内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取发送时间
     *
     * @return send_time - 发送时间
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 设置发送时间
     *
     * @param sendTime 发送时间
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 获取是否已读
     *
     * @return if_read - 是否已读
     */
    public Integer getIfRead() {
        return ifRead;
    }

    /**
     * 设置是否已读
     *
     * @param ifRead 是否已读
     */
    public void setIfRead(Integer ifRead) {
        this.ifRead = ifRead;
    }

    /**
     * 获取创建人
     *
     * @return create_user_id - 创建人
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建人
     *
     * @param createUserId 创建人
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
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
        sb.append(", messageId=").append(messageId);
        sb.append(", examId=").append(examId);
        sb.append(", questionId=").append(questionId);
        sb.append(", procInstId=").append(procInstId);
        sb.append(", runTaskId=").append(runTaskId);
        sb.append(", messageType=").append(messageType);
        sb.append(", sender=").append(sender);
        sb.append(", serderName=").append(serderName);
        sb.append(", receiver=").append(receiver);
        sb.append(", content=").append(content);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", ifRead=").append(ifRead);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}