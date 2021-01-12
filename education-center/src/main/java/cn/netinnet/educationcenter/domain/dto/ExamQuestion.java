package cn.netinnet.educationcenter.domain.dto;

import java.io.Serializable;

/**
 * @ClassName ExamQuestion
 * @Description
 * @Author yuyb
 * @Date 2020/8/3 15:08
 */
public class ExamQuestion implements Serializable {

    /**
     * ExamQuestion的主键id
     */
    private Long id;

    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 题目标题
     */
    private String questionTitle;

    /**
     * 题目类型
     */
    private String questionType;
    /**
     * 试题归类（0：系统，1：教师）
     */
    private Integer questionCategory;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 资料数量
     */
    private int attachCount;

    /**
     * 试题分值
     */
    private int scoreValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Integer getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(Integer questionCategory) {
        this.questionCategory = questionCategory;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getAttachCount() {
        return attachCount;
    }

    public void setAttachCount(int attachCount) {
        this.attachCount = attachCount;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }
}
