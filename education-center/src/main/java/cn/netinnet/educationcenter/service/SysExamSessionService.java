package cn.netinnet.educationcenter.service;


import cn.netinnet.common.base.Service;
import cn.netinnet.educationcenter.domain.SysExamSession;
import cn.netinnet.educationcenter.domain.dto.QuestionDoneDetail;
import cn.netinnet.educationcenter.domain.dto.SessionCompleteDetail;
import cn.netinnet.educationcenter.domain.dto.SessionDetail;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author yuyb
 * @date   2020-07-31
 */
public interface SysExamSessionService extends Service<SysExamSession> {

    /** 查询考试场次 */
    List<Map<String, Object>> queryList(Integer sessionType, String sessionName, long sessionId);

    /** 查询建套餐人的场次 */
    List<SysExamSession> getPackageSessionList(long userId, Integer sessionType, String sessionName);

    /** 查询批次下的练习、作业或考试列表 */
    List<SessionDetail> getBatchSessionList(long batchId, int sessionType, Integer sessionStatus, String sessionName);

    /** 练习作业考试详情 */
    List<SessionCompleteDetail> sessionCompleteDetail(long sessionId, Long className, Integer submitStatus, String userName, long batchId);

    /** 新增考试 */
    void addExam(SysExamSession sysExamSession, int sessionType);

    /** 编辑考试 */
    void editExam(SysExamSession sysExamSession);

    /** 删除考试 */
    void deleteExam(Long sessionId, long userId);

    /** 更新考试状态 */
    void updateExamStatus(long sessionId, int sessionStatus);

    /** 查询考试结束时间 **/
    Timestamp queryEndTime(long sessionId);

    /** 查询场次成绩 **/
    List<Map<String, Object>> resultsList(long sessionId, String userName, int sessionType, String userLogin);

    /** 更新考试参数 **/
    void setParameters(long sessionId, int resetable, int viewAnswer);

    /** 批量重置用户数据 **/
    void batchResetUserExam(List<Long> examUserIds, long sessionId, List<Long> userIds);

    /** 查询做题结果 **/
    String getQuestionResultDetail(long userId, long questionId, long sessionId);

    /** 查询批次下的场次信息 **/
    List<SessionDetail> queryListByBatchIdAndType(int sessionType, long batchId, Integer sessionStatus, String sessionName, Integer openStatus);

    /** 查询用户场次列表信息 **/
    List<SessionDetail> queryUserSessionList(int sessionType, long batchId, long userId, Integer sessionStatus, String sessionName);

    /** 题目列表及用户做题情况 **/
    List<Map<String, Object>> questQuestionDoDetaiList(long sessionId, long userId);

    /** 考试详情 **/
    List<QuestionDoneDetail> userExamDetail(long sessionId, long userId, Integer state, String questionTitle);

    /** 查询练作业绩明细 **/
    QuestionDoneDetail queryTaskScoreDetail(long sessionId, long questionId, long userId);
}
