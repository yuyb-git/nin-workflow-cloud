package cn.netinnet.educationcenter.service;

import cn.netinnet.cloudcommon.base.Service;
import cn.netinnet.cloudcommon.dto.ExamInfo;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.utils.DateUtil;
import cn.netinnet.educationcenter.domain.SysExamScore;
import cn.netinnet.educationcenter.domain.SysExamUser;
import cn.netinnet.educationcenter.domain.SysUser;
import cn.netinnet.educationcenter.domain.dto.ScoreInfo;
import com.alibaba.fastjson.JSONArray;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author yuyb
 * @date   2020-06-12
 */
public interface SysExamUserService extends Service<SysExamUser> {

    /** 构建SysExamUser对象 */
    static SysExamUser genExamUser(long sessionId, long batchId, long userId){
        SysExamUser sysExamUser = new SysExamUser();
        sysExamUser.setExamId(DateUtil.getUID());
        sysExamUser.setSessionId(sessionId);
        sysExamUser.setBatchId(batchId);
        sysExamUser.setUserId(userId);
        sysExamUser.setSubmitState(0);
        sysExamUser.setTotalScore(BigDecimal.ZERO);
        sysExamUser.setCreateTime(new Date());
        sysExamUser.setModifyTime(new Date());
        return sysExamUser;
    }

    /** 构建SysExamScore对象 */
    static SysExamScore genExamScore(long sessionId, long userId, long questionId){
        SysExamScore sysExamScore = new SysExamScore();
        sysExamScore.setId(DateUtil.getUID());
        sysExamScore.setSessionId(sessionId);
        sysExamScore.setUserId(userId);
        sysExamScore.setQustionId(questionId);
        sysExamScore.setScore(BigDecimal.ZERO);
        sysExamScore.setCreateTime(new Date());
        return sysExamScore;
    }

    /** 插入学生数据 **/
    void addStudents(List<SysExamUser> users, List<SysExamScore> sysExamScores);

    /** 将examUser设进session **/
    ExamInfo setExamUserToSession(long batchId, long userId, long sessionId, long questionId, int sessionType, int sessionFrom);

    List<Map<String, Object>> queryExamStudentList(Long sessionId, String userLogin, String userName);

    /** 新增学生查询相关的班级学生 **/
    List<Map<String, Object>> queryAddExamStudentList(Long sessionId, String className);

    /** 新增考试学生 **/
    void addExamStudent(Long sessionId, List<Long> userIdList);

    /** 导入考试学生 **/
    HttpResultEntry importExamStudent(SysUser sysUser, JSONArray list, Long sessionId);

    /** 删除考试学生 **/
    void delExamUserByIdList(List<Long> examUserIdList, Long sessionId, String bpmnPath);

    /** 进入我的练习、作业、考试 **/
    Map<String, Object> intoMySession(long sessionId, long batchId, long userId, int sessionType);

    /** （考试界面）进入做题 **/
    ExamInfo intoQuestion(long questionId, HttpSession session);

    /** 结束考试 **/
    void submitExam(long examId, long sessionId, long userId, int sessionFrom);

    /** 交卷判分 **/
    Map<Long, List<ScoreInfo>> submitSession(Map<Long, Set<Long>> userMap, boolean fromExam);

    /** 取消考试提交 **/
    void cancelSubmitExam(long examId, long sessionId, long userId, Long questionId);
}
