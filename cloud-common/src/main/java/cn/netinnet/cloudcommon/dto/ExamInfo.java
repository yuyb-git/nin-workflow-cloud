package cn.netinnet.cloudcommon.dto;

import java.io.Serializable;

/**
 * @ClassName ExamInfo
 * @Description
 * @Author yuyb
 * @Date 2020/6/12 15:06
 */
public class ExamInfo implements Serializable {

    /**
     * 系统考试id
     */
    private Long examId = 0L;
    /**
     * 题目id
     */
    private Long questionId = 0L;

    /**
     * 批次id
     */
    private Long batchId = 0L;

    /**
     * 场次ID或任务id
     */
    private Long sessionId = 0L;

    /***
    * 场次来源
    */
    private Integer sessionForm = 0;

    /**
    * 场次类型
    */
    private Integer sessionType;

    /**
     * 考试提价状态
     */
    private Integer submitState = 0;

    public ExamInfo() {
    }

    public ExamInfo(long examId, long batchId, long sessionId, Integer submitState, int sessionForm, int sessionType) {
        this.examId = examId;
        this.batchId = batchId;
        this.sessionId = sessionId;
        this.submitState = submitState;
        this.sessionForm = sessionForm;
        this.sessionType = sessionType;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getSubmitState() {
        return submitState;
    }

    public void setSubmitState(Integer submitState) {
        this.submitState = submitState;
    }

    public Integer getSessionForm() {
        return sessionForm;
    }

    public void setSessionForm(Integer sessionForm) {
        this.sessionForm = sessionForm;
    }

    public Integer getSessionType() {
        return sessionType;
    }

    public void setSessionType(Integer sessionType) {
        this.sessionType = sessionType;
    }

    @Override
    public String toString() {
        return "ExamInfo{" +
                "examId=" + examId +
                ", questionId=" + questionId +
                ", batchId=" + batchId +
                ", sessionId=" + sessionId +
                ", sessionForm=" + sessionForm +
                ", sessionType=" + sessionType +
                ", submitState=" + submitState +
                '}';
    }
}
