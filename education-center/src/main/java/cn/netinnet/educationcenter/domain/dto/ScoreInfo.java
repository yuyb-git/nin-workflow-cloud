package cn.netinnet.educationcenter.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName ScoreInfo
 * @Description
 * @Author yuyb
 * @Date 2020/12/3 17:11
 */
public class ScoreInfo implements Serializable {

    /**
     * 题目id
     */
    private Long qustionId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 成绩
     */
    private BigDecimal score;

    public Long getQustionId() {
        return qustionId;
    }

    public void setQustionId(Long qustionId) {
        this.qustionId = qustionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
