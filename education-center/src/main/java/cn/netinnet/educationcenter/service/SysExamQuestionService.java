package cn.netinnet.educationcenter.service;


import cn.netinnet.common.base.Service;
import cn.netinnet.educationcenter.domain.SysExamQuestion;
import cn.netinnet.educationcenter.domain.SysExamSession;
import cn.netinnet.educationcenter.domain.dto.ExamQuestion;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.util.Map;


/**
 * @author yuyb
 * @date   2020-06-12
 */
public interface SysExamQuestionService extends Service<SysExamQuestion> {

    /** 初始化考试信息 **/
    void batchInit(Map<Long, List<SysExamQuestion>> initExamMap, List<SysExamSession> examSessions);

    /** 查询考试使用的试题id **/
    List<Long> getQuestionIdsBySessionId(Long sessionId);

    /** 查询考试已添加的试题信息 **/
    List<ExamQuestion> getExamQuestionList(Long sessionId, String questionTitle);

    /** 查询考试未添加的试题信息 **/
    List<ExamQuestion> getExamQuestionListNotAdd(List<Long> questionIds, String questionTitle, int userType, long userId);

    /** 新增考试下题目 **/
    void addExamQuestions(Long sessionId, List<Long> questionIds);

    /** 删除考试下题目 **/
    int delExamQuestions(Long sessionId, List<Long> questionIds);

    /** 重置考试试题 **/
    void resetExamQuestion(Long sessionId, Long questionId, Long userId);

    /** 导出成绩 **/
    XSSFWorkbook exportScore(long sessionId);

    /** 更新试题分值 **/
    void setQuestionScore(long examQuestionId, int scoreValue, long sessionId);

    /** 查询题目分值 **/
    Integer queryQuestionScore(long quetionId, long sessionId);
}
