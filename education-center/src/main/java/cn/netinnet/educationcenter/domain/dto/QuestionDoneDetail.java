package cn.netinnet.educationcenter.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 做题情况
 * @date 2020/8/28 15:39
 * @author by ousp
 */
public class QuestionDoneDetail {
    /***
    * 题目id
    */
    private Long questionId;
    /***
     * 题目名称
     */
    private String questionTitle;
    /***
     * 题目分值
     */
    private BigDecimal questionSocre;
    /***
     * 题目提交状态
     */
    private Integer questionState;
    /***
     * 题目得分
     */
    private BigDecimal score;
    /***
     * 流程实例得分
     */
    private BigDecimal procRunScore;
    /***
     * 流程定义得分
     */
    private BigDecimal procDesignScore;
    /***
     * 岗位架构得分
     */
    private BigDecimal positionScore;
    /***
     * 部门架构得分
     */
    private BigDecimal orgScore;
    /***
     * 部门架构分值
     */
    private BigDecimal sysOrgScore;
    /***
     * 岗位分值
     */
    private BigDecimal sysPositionScore;
    /***
     * 流程定义分值
     */
    private BigDecimal sysProcDesignScore;
    /***
     * 流程实例分值
     */
    private BigDecimal sysProcRunScore;
    /***
    * 创建时间
    */
    private Date createTime;

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

    public BigDecimal getQuestionSocre() {
        return questionSocre;
    }

    public void setQuestionSocre(BigDecimal questionSocre) {
        this.questionSocre = questionSocre;
    }

    public Integer getQuestionState() {
        return questionState;
    }

    public void setQuestionState(Integer questionState) {
        this.questionState = questionState;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getProcRunScore() {
        return procRunScore;
    }

    public void setProcRunScore(BigDecimal procRunScore) {
        this.procRunScore = procRunScore;
    }

    public BigDecimal getProcDesignScore() {
        return procDesignScore;
    }

    public void setProcDesignScore(BigDecimal procDesignScore) {
        this.procDesignScore = procDesignScore;
    }

    public BigDecimal getPositionScore() {
        return positionScore;
    }

    public void setPositionScore(BigDecimal positionScore) {
        this.positionScore = positionScore;
    }

    public BigDecimal getOrgScore() {
        return orgScore;
    }

    public void setOrgScore(BigDecimal orgScore) {
        this.orgScore = orgScore;
    }

    public BigDecimal getSysOrgScore() {
        return sysOrgScore;
    }

    public void setSysOrgScore(BigDecimal sysOrgScore) {
        this.sysOrgScore = sysOrgScore;
    }

    public BigDecimal getSysPositionScore() {
        return sysPositionScore;
    }

    public void setSysPositionScore(BigDecimal sysPositionScore) {
        this.sysPositionScore = sysPositionScore;
    }

    public BigDecimal getSysProcDesignScore() {
        return sysProcDesignScore;
    }

    public void setSysProcDesignScore(BigDecimal sysProcDesignScore) {
        this.sysProcDesignScore = sysProcDesignScore;
    }

    public BigDecimal getSysProcRunScore() {
        return sysProcRunScore;
    }

    public void setSysProcRunScore(BigDecimal sysProcRunScore) {
        this.sysProcRunScore = sysProcRunScore;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserQuestionDoneDetail{" +
                "questionId=" + questionId +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionSocre=" + questionSocre +
                ", questionState=" + questionState +
                ", score=" + score +
                ", procRunScore=" + procRunScore +
                ", procDesignScore=" + procDesignScore +
                ", positionScore=" + positionScore +
                ", orgScore=" + orgScore +
                ", sysOrgScore=" + sysOrgScore +
                ", sysPositionScore=" + sysPositionScore +
                ", sysProcDesignScore=" + sysProcDesignScore +
                ", sysProcRunScore=" + sysProcRunScore +
                ", createTime=" + createTime +
                '}';
    }
}
