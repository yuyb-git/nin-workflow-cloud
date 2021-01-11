package cn.netinnet.educationcenter.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName SessionDetail
 * @Description
 * @Author yuyb
 * @Date 2020/8/26 16:45
 */
public class SessionDetail implements Serializable {

    /**
     * 场次id
     */
    private Long sessionId;
    /**
     * 场次名称
     */
    private String sessionName;

    /**
     * 开启状态
     */
    private Integer sessionStatus;
    /**
     * 批次id
     */
    private Long batchId;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 查看答案
     */
    private int viewAnswer;
    /**
     * 考试提交状态
     */
    private int submitState;
    /**
     * 创建人
     */
    private Long createUserId;
    /**
     * 创建人姓名
     */
    private String createUserName;
    /**
     * 试题id-作业才会有
     */
    private Long questionId;

    private String questionTitle;
    /**
     * 试题数量-考试才会有
     */
    private int questionCount;
    /**
     * 作业试卷分值
     */
    private int sessionScore;
    /**
     * 得分
     */
    private BigDecimal score;
    /**
     * 应交人数
     */
    private int totalStudent;
    /**
     * 已交人数
     */
    private int submitStudent;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(Integer sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getViewAnswer() {
        return viewAnswer;
    }

    public void setViewAnswer(int viewAnswer) {
        this.viewAnswer = viewAnswer;
    }

    public int getSubmitState() {
        return submitState;
    }

    public void setSubmitState(int submitState) {
        this.submitState = submitState;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public int getSessionScore() {
        return sessionScore;
    }

    public void setSessionScore(int sessionScore) {
        this.sessionScore = sessionScore;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public int getTotalStudent() {
        return totalStudent;
    }

    public void setTotalStudent(int totalStudent) {
        this.totalStudent = totalStudent;
    }

    public int getSubmitStudent() {
        return submitStudent;
    }

    public void setSubmitStudent(int submitStudent) {
        this.submitStudent = submitStudent;
    }
}
