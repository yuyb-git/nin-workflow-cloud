package cn.netinnet.educationcenter.service.impl;

import cn.netinnet.cloudcommon.base.BaseService;
import cn.netinnet.cloudcommon.constant.CacheConstant;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.exception.CustomException;
import cn.netinnet.cloudcommon.utils.*;
import cn.netinnet.educationcenter.constant.ParaConstant;
import cn.netinnet.educationcenter.dao.*;
import cn.netinnet.educationcenter.domain.SysExamScore;
import cn.netinnet.educationcenter.domain.SysExamSession;
import cn.netinnet.educationcenter.domain.SysExamUser;
import cn.netinnet.educationcenter.domain.SysQuestion;
import cn.netinnet.educationcenter.domain.dto.QuestionDoneDetail;
import cn.netinnet.educationcenter.domain.dto.SessionCompleteDetail;
import cn.netinnet.educationcenter.domain.dto.SessionDetail;
import cn.netinnet.educationcenter.service.SysExamQuestionService;
import cn.netinnet.educationcenter.service.SysExamSessionService;
import cn.netinnet.educationcenter.service.SysUserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yuyb
 * @date   2020-07-31
 */
@Service
public class SysExamSessionServiceImpl extends BaseService<SysExamSession> implements SysExamSessionService {

    @Resource
    private SysExamSessionMapper sysExamSessionMapper;
    @Resource
    SysExamUserMapper sysExamUserMapper;
    @Resource
    SysExamScoreMapper sysExamScoreMapper;
//    @Resource
//    CleanDataUtil cleanDataUtil;
    @Resource
    SysQuestionMapper sysQuestionMapper;
    @Resource
    SysUserService sysUserService;
    @Resource
    SysExamQuestionMapper sysExamQuestionMapper;
    @Resource
    SysExamQuestionService sysExamQuestionService;
    @Resource
    SysBatchStudentMapper sysBatchStudentMapper;

    @Override
    public int insertSelective(SysExamSession sysExamSession, long userId) {
        return sysExamSessionMapper.insertSelective(sysExamSession);
    }

    @Override
    public Class getClazz(){
        return SysExamSession.class;
    }

    /**  方法描述
     * @Description 查询考试场次
     * @Author yuyb
     * @Date 8:22 2020/8/3
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamSession>
     **/
    @Override
    public List<Map<String, Object>> queryList(Integer sessionType, String sessionName, long sessionId) {
        UserInfo userInfo = UserUtil.getUser();
        long userId = userInfo.getUserId();
        String roleCode = userInfo.getRoleCode();
        if(!StringUtil.isBlankOrNull(sessionName)){
            sessionName = "%"+sessionName+"%";
        }
        return sysExamSessionMapper.queryList(sessionType, sessionName, roleCode, userId, sessionId);
    }

    /**  方法描述
     * @Description 查询建套餐人的场次
     * @Author yuyb
     * @Date 14:04 2020/8/28
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamSession>
     **/
    @Override
    public List<SysExamSession> getPackageSessionList(long userId, Integer sessionType, String sessionName) {
        if(!StringUtil.isBlankOrNull(sessionName)){
            sessionName = "%" + sessionName +"%";
        }
        return sysExamSessionMapper.getPackageSessionList(userId, sessionType, sessionName);
    }

    /**  方法描述
     * @Description 查询批次下的练习、作业或考试列表
     * @Author yuyb
     * @Date 11:35 2020/8/27
     * @return java.util.List<cn.netinnet.workflow.sys.domain.dto.SessionDetail>
     **/
    @Override
    public List<SessionDetail> getBatchSessionList(long batchId, int sessionType, Integer sessionStatus, String sessionName) {
        List<SessionDetail> sessionList = queryListByBatchIdAndType(sessionType, batchId, sessionStatus, sessionName, null);
        if(sessionList.isEmpty()){
            return sessionList;
        }
        Set<Long> sessionIds = new HashSet<>();
        sessionList.forEach(session -> sessionIds.add(session.getSessionId()));
        Map<Long, Long> submitMap = null;
        if(sessionType != ParaConstant.SESSION_TYPE_TRAIN){
            List<Long> submitList = sysExamUserMapper.getUserSubmitList(sessionIds);
            //根据sessionId以及提交状态分组计数
            submitMap = submitList.parallelStream()
                    .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        }
        Map<Long, Long> questionCountMap = new HashMap<>();
        if(sessionType == ParaConstant.SESSION_TYPE_EXAM){
            sysExamQuestionMapper.countBySessionId(sessionIds).forEach(session -> questionCountMap.put(session.get("sessionId"), session.get("num")));
        }
        int studentCount = sysBatchStudentMapper.countByBatchId(batchId);

        for(SessionDetail sessionDetail : sessionList){
            long sessionId = sessionDetail.getSessionId();
            if(sessionType != ParaConstant.SESSION_TYPE_TRAIN){
                sessionDetail.setTotalStudent(studentCount);
                if(submitMap.containsKey(sessionId)){
                    //有sessionId说明场次下有学生已交卷
                    sessionDetail.setSubmitStudent(submitMap.get(sessionId).intValue());
                }else{
                    //说明场次下还没有一个学生有进入做题,全部为0
                    sessionDetail.setSubmitStudent(0);
                }
            }
            if(sessionType == ParaConstant.SESSION_TYPE_EXAM){
                sessionDetail.setQuestionCount(questionCountMap.containsKey(sessionId)?questionCountMap.get(sessionId).intValue():0);
            }
        }
        return sessionList;
    }

    /**  方法描述
     * @Description 查询开始、作业、练习下用户的考试情况
     * @Author yuyb
     * @Date 16:24 2020/8/27
     * @param sessionId
     * @return java.util.List<cn.netinnet.workflow.sys.domain.dto.SessionCompleteDetail>
     **/
    @Override
    public List<SessionCompleteDetail> sessionCompleteDetail(long sessionId,
                                                             Long classId,
                                                             Integer submitStatus,
                                                             String userName,
                                                             long batchId) {
        if(!StringUtil.isBlankOrNull(userName)){
            userName = "%"+userName+"%";
        }
        return sysExamUserMapper.getSessionUserDeatil(sessionId, classId, submitStatus, userName, batchId);
    }

    /**  方法描述
     * @Description 新增考试
     * @Author yuyb
     * @Date 8:22 2020/8/3
     * @param sysExamSession
     * @return void
     **/
    @Override
    public void addExam(SysExamSession sysExamSession, int sessionType) {
        //正保云对接模拟登录,UserUtil无值要取传参
        long userId = sysExamSession.getCreateUserId() == null ?
                UserUtil.getUser().getUserId() : sysExamSession.getCreateUserId();
        //新增考试
        SysExamSession addExam = new SysExamSession();
        Long sessionId = sysExamSession.getSessionId() == null ? DateUtil.getUID() : sysExamSession.getSessionId();
        addExam.setSessionId(sessionId);
        addExam.setSessionFrom(ParaConstant.SESSION_FROM_SYSTEM);
        addExam.setSessionType(sessionType);
        addExam.setSessionName(sysExamSession.getSessionName());
        if(sessionType == ParaConstant.SESSION_TYPE_TASK){
            //作业需要指定试题id和查看答案
            addExam.setQuestionId(sysExamSession.getQuestionId());
            addExam.setViewAnswer(sysExamSession.getViewAnswer());
            //考试状态-新建
            addExam.setSessionStatus(ParaConstant.EXAM_STATUS_NEW);
        }else if(sessionType == ParaConstant.SESSION_TYPE_EXAM){
            addExam.setViewAnswer(sysExamSession.getViewAnswer());
            //考试状态-新建
            addExam.setSessionStatus(ParaConstant.EXAM_STATUS_NEW);
        } else {
            // 练习默认开启
            addExam.setSessionStatus(ParaConstant.EXAM_STATUS_START);
        }
        if(sysExamSession.getBatchId() != null){
            addExam.setBatchId(sysExamSession.getBatchId());
        }
        //考试信息
        addExam.setStartTime(sysExamSession.getStartTime());
        addExam.setEndTime(sysExamSession.getEndTime());
        addExam.setCreateUserId(userId);
        addExam.setModifyUserId(userId);

        sysExamSessionMapper.insertSelective(addExam);
    }

    /**  方法描述
     * @Description 编辑考试
     * @Author yuyb
     * @Date 8:22 2020/8/3
     * @param sysExamSession
     * @return void
     **/
    @Override
    public void editExam(SysExamSession sysExamSession) {
        long userId = sysExamSession.getCreateUserId() == null ?
                UserUtil.getUser().getUserId() : sysExamSession.getCreateUserId();
        //考试id为空时, 参数异常
        if (sysExamSession.getSessionId() == null) {
            throw new CustomException("考试id不能为空");
        }
        String columns = "session_name,session_type,question_id,start_time,end_time,view_answer";
        SysExamSession examSession = sysExamSessionMapper.selectColumnsById(sysExamSession.getSessionId(), columns);
        if(examSession == null){
            throw new CustomException("未找到系统数据！");
        }
        //编辑考试
        SysExamSession editExam = new SysExamSession();
        editExam.setSessionId(sysExamSession.getSessionId());
        if(!sysExamSession.getSessionName().equals(examSession.getSessionName())){
            editExam.setSessionName(sysExamSession.getSessionName());
        }
        if(sysExamSession.getStartTime() != null && sysExamSession.getStartTime().compareTo(examSession.getStartTime()) != 0){
            editExam.setStartTime(sysExamSession.getStartTime());
        }
        if(sysExamSession.getEndTime() != null && sysExamSession.getEndTime().compareTo(examSession.getEndTime()) != 0){
            editExam.setEndTime(sysExamSession.getEndTime());
        }
        if (examSession.getSessionType() != ParaConstant.SESSION_TYPE_TRAIN) {
            if(!sysExamSession.getViewAnswer().equals(examSession.getViewAnswer())){
                editExam.setViewAnswer(sysExamSession.getViewAnswer());
            }
        }
        if(examSession.getSessionType() == ParaConstant.SESSION_TYPE_TASK){
            if(!sysExamSession.getQuestionId().equals(examSession.getQuestionId())){
                Integer existUser =sysExamUserMapper.existUserBySessionId(sysExamSession.getSessionId());
                if(existUser != null){
                    throw new CustomException("该作业下已有学生，不能修改试题！");
                }
                editExam.setQuestionId(sysExamSession.getQuestionId());
            }
        }
        editExam.setModifyUserId(userId);
        // 删除缓存
        RedisUtil.del( CacheConstant.EXAM_END_TIME + editExam.getSessionId());
        sysExamSessionMapper.updateByPrimaryKeySelective(editExam);
    }

    /**  方法描述
     * @Description 删除考试
     * @Author yuyb
     * @Date 8:22 2020/8/3
     * @param sessionId
     * @param userId
     * @return void
     **/
    @Override
    public void deleteExam(Long sessionId, long userId) {
        if (sessionId == null) {
            throw new CustomException("考试id不能为空");
        }
        sysExamSessionMapper.logicalDeleteById(sessionId);
    }

    /**  方法描述
     * @Description 更新考试状态
     * @Author yuyb
     * @Date 8:23 2020/8/3
     * @param sessionId
     * @param sessionStatus
     * @return void
     **/
    @Override
    public void updateExamStatus(long sessionId, int sessionStatus) {
        SysExamSession sysExamSession = new SysExamSession();
        sysExamSession.setSessionId(sessionId);
        sysExamSession.setSessionStatus(sessionStatus);
        sysExamSessionMapper.updateByPrimaryKeySelective(sysExamSession);
    }

    /**
     * 查询考试结束时间
     * @param sessionId
     * @author ousp
     * @date 2020/6/12
     * @return java.sql.Timestamp
     */
    @Override
    @Cacheable(value = CacheConstant.EXAM_END_TIME, key = "#root.target.bulidExamEndTimeKey() + #p0")
    public Timestamp queryEndTime(long sessionId) {
        return new Timestamp(sysExamSessionMapper.queryEndTime(sessionId).getTime());
    }

    public String bulidExamEndTimeKey() {
        return CacheConstant.EXAM_END_TIME;
    }

    @Override
    public List<Map<String, Object>> resultsList(long sessionId, String userName, int sessionType, String userLogin) {
        if(!StringUtil.isBlankOrNull(userName)){
            userName = "%"+userName+"%";
        }

        if (!StringUtil.isBlankOrNull(userLogin)) {
            userLogin = "%" + userLogin + "%";
        }
        List<Map<String, Object>> resultsList = null;
        if (sessionType == ParaConstant.SESSION_TYPE_TASK) {
            // 作业模式只有一道题直接查询
            resultsList = sysExamScoreMapper.getResultsList(sessionId, userName, userLogin);
        } else {
            // 考试类型获取考试结果
            resultsList = sysExamScoreMapper.getExamResultsList(sessionId, userName, userLogin);
        }
        resultsList.forEach(r -> {
            r.put("userLogin", r.get("userLogin").toString().replace(GlobalConstant.ZBY_USER_PREFIX, ""));
        });
        return resultsList;
    }

    /**  方法描述
     * @Description 修改考试参数
     * @Author yuyb
     * @Date 19:17 2020/8/20
     * @param sessionId
     * @param resetable
     * @param viewAnswer
     * @return void
     **/
    @Override
    public void setParameters(long sessionId, int resetable, int viewAnswer) {
        SysExamSession sysExamSession = sysExamSessionMapper.selectColumnsById(sessionId, "resetable, view_answer");
        if(sysExamSession == null){
            throw new CustomException("未找到考试！");
        }
        SysExamSession update = new SysExamSession();
        update.setSessionId(sessionId);
        if(sysExamSession.getResetable() != resetable){
            update.setResetable(resetable);
        }
        if(sysExamSession.getViewAnswer() != viewAnswer){
            update.setViewAnswer(viewAnswer);
        }
        if(update.getResetable() == null && update.getViewAnswer() == null){
            throw new CustomException("参数没有变化！");
        }
        sysExamSessionMapper.updateByPrimaryKeySelective(update);
    }

    /***
    * 批量重置用户数据
    * @param examUserIds
    * @param sessionId
    * @param userIds
    * @author ousp
    * @date 2020/8/20
    * @return void
    */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchResetUserExam(List<Long> examUserIds, long sessionId, List<Long> userIds) {
        if (examUserIds == null || examUserIds.isEmpty()) {
            return;
        }
        // todo
//        cleanDataUtil.logicalDelCompanyAndDef(examUserIds, null);
//        cleanDataUtil.batchDelUserQuestionResult(sessionId, userIds);
        sysExamUserMapper.batchResetUserExam(examUserIds, sessionId, userIds);
    }

    /***
    * 查询做题结果
    * @param userId
    * @param questionId
    * @author ousp
    * @date 2020/8/20
    * @return java.util.Map<java.lang.String,java.lang.Object>
    */
    @Override
    public String getQuestionResultDetail(long userId, long questionId, long sessionId) {
        SysQuestion sysQuestion = sysQuestionMapper.selectByPrimaryKey(questionId);
        SysExamScore sysExamScore = sysExamScoreMapper.queryQuestionScoreDetail(sessionId, questionId, userId);
        int sessionType = sysExamSessionMapper.selectColumnsById(sessionId, "session_type").getSessionType();
        if (sysQuestion == null) {
            throw new CustomException("未查到相关试题！");
        }
        if (sysExamScore == null) {
            throw new CustomException("未查到相关做题结果！");
        }

        JSONObject r =new JSONObject();

        if (sessionType == ParaConstant.SESSION_TYPE_EXAM) {
            BigDecimal score;
            BigDecimal questionScore = BigDecimal.valueOf(sysExamQuestionService.queryQuestionScore(questionId, sessionId));
            // 考试的转换成绩
            if (sysQuestion.getOrgScoreRate() > 0) {
                score = new BigDecimal(sysQuestion.getOrgScoreRate()).multiply(questionScore).divide(BigDecimal.valueOf(100), 4, BigDecimal.ROUND_HALF_UP);
                r.put("org",  sysExamScore.getOrgScore().compareTo(score) == 0 ? 1 : 0);
            }
            if (sysQuestion.getPositionScoreRate() > 0) {
                score = new BigDecimal(sysQuestion.getPositionScoreRate()).multiply(questionScore).divide(BigDecimal.valueOf(100), 4, BigDecimal.ROUND_HALF_UP);
                r.put("position",  sysExamScore.getPositionScore().compareTo(score) == 0 ? 1 : 0);
            }
            if (sysQuestion.getProcDesignScoreRate() > 0) {
                score = new BigDecimal(sysQuestion.getProcDesignScoreRate()).multiply(questionScore).divide(BigDecimal.valueOf(100), 4, BigDecimal.ROUND_HALF_UP);
                r.put("procDesign",  sysExamScore.getProcDesignScore().compareTo(score) == 0 ? 1 : 0);
            }
            if (sysQuestion.getProcRunScoreRate() > 0) {
                score = new BigDecimal(sysQuestion.getProcRunScoreRate()).multiply(questionScore).divide(BigDecimal.valueOf(100), 4, BigDecimal.ROUND_HALF_UP);
                r.put("procInst",  sysExamScore.getProcRunScore().compareTo(score) == 0 ? 1 : 0);
            }
        } else {
            if (sysQuestion.getOrgScoreRate() > 0) {
                r.put("org",  sysExamScore.getOrgScore().compareTo(new BigDecimal(sysQuestion.getOrgScoreRate())) == 0 ? 1 : 0);
            }
            if (sysQuestion.getPositionScoreRate() > 0) {
                r.put("position",  sysExamScore.getPositionScore().compareTo(new BigDecimal(sysQuestion.getPositionScoreRate())) == 0 ? 1 : 0);
            }
            if (sysQuestion.getProcDesignScoreRate() > 0) {
                r.put("procDesign",  sysExamScore.getProcDesignScore().compareTo(new BigDecimal(sysQuestion.getProcDesignScoreRate())) == 0 ? 1 : 0);
            }
            if (sysQuestion.getProcRunScoreRate() > 0) {
                r.put("procInst",  sysExamScore.getProcRunScore().compareTo(new BigDecimal(sysQuestion.getProcRunScoreRate())) == 0 ? 1 : 0);
            }
        }
        return r.toJSONString();
    }

    /***
    * 查询批次下的场次信息
    * @param sessionType    类型
    * @param batchId        批次id
    * @param sessionStatus  状态
    * @param sessionName    场次名
    * @author ousp
    * @date 2020/8/26
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    */
    @Override
    public List<SessionDetail> queryListByBatchIdAndType(int sessionType, long batchId, Integer sessionStatus, String sessionName, Integer openStatus) {
        String startData = null, endData = null;
        Date now = new Date();
        if (sessionStatus != null) {
            if (sessionStatus == ParaConstant.SESSION_STATUS_NEW) {
                startData = DateStrUtil.yyyy_mm_ddhh_18(now);
            } else if (sessionStatus == ParaConstant.SESSION_STATUS_START) {
                startData = endData = DateStrUtil.yyyy_mm_ddhh_18(now);
            } else if (sessionStatus == ParaConstant.SESSION_STATUS_END) {
                endData = DateStrUtil.yyyy_mm_ddhh_18(now);
            }
        }
        return sysExamSessionMapper.queryListByBatchIdAndType(batchId, sessionType, startData, endData, sessionName, openStatus, now);
    }

    /****
    *   查询用户场次列表信息
    * @param sessionType    场次类型
    * @param batchId        批次id
    * @param userId         用户id
    * @author ousp
    * @date 2020/8/26
    * @return
    */
    @Override
    public List<SessionDetail> queryUserSessionList(int sessionType, long batchId, long userId, Integer sessionStatus, String sessionName) {
        List<SessionDetail> sessionList = queryListByBatchIdAndType(sessionType, batchId, sessionStatus, sessionName, 1);
        if (!sessionList.isEmpty()) {
            if (sessionType == ParaConstant.SESSION_TYPE_TRAIN) {
                Set<Long> userIds = new HashSet<>();
                sessionList.forEach(session -> userIds.add(session.getCreateUserId()));
                Map<Long, String> nameMap = sysUserService.queryNameMap(userIds);
                sessionList.forEach(session -> {
                    session.setCreateUserName(nameMap.get(session.getCreateUserId()));
                });
            } else {
                Set<Long> userIds = new HashSet<>();
                List<Long> sessionIds = new ArrayList<>();
                sessionList.forEach(session -> {
                    userIds.add(session.getCreateUserId());
                    sessionIds.add(session.getSessionId());
                });
                Map<Long, String> nameMap = sysUserService.queryNameMap(userIds);
                Map<Long, SysExamUser> userMap = new HashMap<>();
                sysExamUserMapper.queryUserExamData(sessionIds, batchId, userId).forEach(examUser -> {
                    userMap.put(examUser.getSessionId(), examUser);
                });
                sessionList.forEach(session -> {
                    session.setCreateUserName(nameMap.get(session.getCreateUserId()));
                    if (sessionType == ParaConstant.SESSION_TYPE_TASK) {
                        session.setSessionScore(100);
                    }
                    SysExamUser sysExamUser = userMap.get(session.getSessionId());
                    if (sysExamUser != null) {
                        session.setSubmitState(sysExamUser.getSubmitState());
                        session.setScore(sysExamUser.getTotalScore() == null ? BigDecimal.ZERO : sysExamUser.getTotalScore());
                    } else {
                        session.setScore(BigDecimal.ZERO);
                    }
                });
            }
        }
        return sessionList;
    }

    /***
    * 查询场次下题目信息
    * @param sessionId  场次id
    * @param userId     用户id
    * @author ousp
    * @date 2020/8/26
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    */
    @Override
    public List<Map<String, Object>> questQuestionDoDetaiList(long sessionId, long userId) {
        // 查询题目列表
        List<Map<String, Object>> questionDetail = sysExamQuestionMapper.queryQuestionDetail(sessionId);
        // 查询用户做题信息
        Map<Long, Integer> questionStateMap = new HashMap<>();
        sysExamScoreMapper.queryQuestionState(sessionId, userId).forEach(q -> questionStateMap.put(q.getQustionId(), q.getQuestionState()));
        questionDetail.forEach(q -> q.put("state", questionStateMap.get(Long.valueOf(q.get("questionId").toString()))));
        return questionDetail;
    }

    /***
    * 考试详情
    * @param sessionId 场次id
    * @param userId     用户id
    * @param state      状态
    * @param questionTitle  题目名称
    * @author ousp
    * @date 2020/8/26
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    */
    @Override
    public List<QuestionDoneDetail> userExamDetail(long sessionId, long userId, Integer state, String questionTitle) {
        // 算分数
        List<QuestionDoneDetail> userExamDetails = sysExamScoreMapper.userExamDetail(sessionId, state, questionTitle, userId);
        BigDecimal questionScore;
        for(QuestionDoneDetail userExamDetail : userExamDetails) {
            questionScore = userExamDetail.getQuestionSocre();
            userExamDetail.setSysOrgScore(userExamDetail.getSysOrgScore().divide(BigDecimal.valueOf(100)).multiply(questionScore));
            userExamDetail.setSysPositionScore(userExamDetail.getSysPositionScore().divide(BigDecimal.valueOf(100)).multiply(questionScore));
            userExamDetail.setSysProcDesignScore(userExamDetail.getSysProcDesignScore().divide(BigDecimal.valueOf(100)).multiply(questionScore));
            userExamDetail.setSysProcRunScore(userExamDetail.getSysProcRunScore().divide(BigDecimal.valueOf(100)).multiply(questionScore));
        }
        return userExamDetails;
    }

    /***
    * 查询练作业绩明细
    * @param sessionId  场次id
    * @param questionId 题目id
    * @param userId     用户id
    * @author ousp
    * @date 2020/8/28
    * @return cn.netinnet.workflow.sys.domain.dto.QuestionDoneDetail
    */
    @Override
    public QuestionDoneDetail queryTaskScoreDetail(long sessionId, long questionId, long userId) {
        SysExamScore sysExamScore = sysExamScoreMapper.queryQuestionScoreDetail(sessionId, questionId, userId);
        SysQuestion sysQuestion = sysQuestionMapper.selectByPrimaryKey(questionId);
        QuestionDoneDetail questionDoneDetail = new QuestionDoneDetail();
        questionDoneDetail.setOrgScore(sysExamScore.getOrgScore());
        questionDoneDetail.setPositionScore(sysExamScore.getPositionScore());
        questionDoneDetail.setProcRunScore(sysExamScore.getProcRunScore());
        questionDoneDetail.setProcDesignScore(sysExamScore.getProcDesignScore());
        questionDoneDetail.setScore(sysExamScore.getScore());
        Integer questionScore = sysExamQuestionMapper.queryQuestionScore(questionId, sessionId);
        if (questionScore != null) {
            // 考试模式questionScore不为空
            BigDecimal tempScore = BigDecimal.valueOf(questionScore);
            questionDoneDetail.setSysOrgScore(BigDecimal.valueOf(sysQuestion.getOrgScoreRate()).divide(BigDecimal.valueOf(100)).multiply(tempScore));
            questionDoneDetail.setSysPositionScore(BigDecimal.valueOf(sysQuestion.getPositionScoreRate()).divide(BigDecimal.valueOf(100)).multiply(tempScore));
            questionDoneDetail.setSysProcDesignScore(BigDecimal.valueOf(sysQuestion.getProcDesignScoreRate()).divide(BigDecimal.valueOf(100)).multiply(tempScore));
            questionDoneDetail.setSysProcRunScore(BigDecimal.valueOf(sysQuestion.getProcRunScoreRate()).divide(BigDecimal.valueOf(100)).multiply(tempScore));
        } else {
            questionDoneDetail.setSysOrgScore(BigDecimal.valueOf(sysQuestion.getOrgScoreRate()));
            questionDoneDetail.setSysPositionScore(BigDecimal.valueOf(sysQuestion.getPositionScoreRate()));
            questionDoneDetail.setSysProcDesignScore(BigDecimal.valueOf(sysQuestion.getProcDesignScoreRate()));
            questionDoneDetail.setSysProcRunScore(BigDecimal.valueOf(sysQuestion.getProcRunScoreRate()));
        }
        questionDoneDetail.setQuestionTitle(sysQuestion.getQuestionTitle());
        return questionDoneDetail;
    }
}
