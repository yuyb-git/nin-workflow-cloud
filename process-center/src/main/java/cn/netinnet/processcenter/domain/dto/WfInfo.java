package cn.netinnet.processcenter.domain.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author xiangra
 * @date 2021/4/16
 **/
public class WfInfo implements Serializable {
    private static final long serialVersionUID = 2743768874073508076L;


    /**
     * 考试id
     */
    @NotNull(message = "考试id不可为空")
    private Long examId;

    /**
     * 试题id
     */
    @NotNull(message = "试题id不可为空")
    private Long questionId;

    /**
     * 企业id
     */
    @NotNull(message = "企业id不可为空")
    private Long companyId;


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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
