package cn.netinnet.educationcenter.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName SessionCompleteDetail
 * @Description
 * @Author yuyb
 * @Date 2020/8/27 13:29
 */
public class SessionCompleteDetail implements Serializable {

    /**
     * 场次id
     */
    private Long sessionId;

    /**
     * 试题id-作业才会有
     */
    private Long questionId;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 班级名称
     */
    private String className;
    /**
     * 提价状态
     */
    private int submitState;
    /**
     * 提交时间
     */
    private Date submitTime;
    /**
     * 得分
     */
    private BigDecimal score;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getSubmitState() {
        return submitState;
    }

    public void setSubmitState(int submitState) {
        this.submitState = submitState;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
