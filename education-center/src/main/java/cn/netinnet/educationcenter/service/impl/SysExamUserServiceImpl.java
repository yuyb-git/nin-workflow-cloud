package cn.netinnet.educationcenter.service.impl;

import cn.netinnet.cloudcommon.constant.ErrorMsgConstant;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.constant.UserConstant;
import cn.netinnet.cloudcommon.dto.ExamInfo;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.exception.CustomException;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.globol.ResultEnum;
import cn.netinnet.cloudcommon.utils.CommonUtil;
import cn.netinnet.cloudcommon.utils.RegUtil;
import cn.netinnet.cloudcommon.utils.UserUtil;
import cn.netinnet.common.base.BaseService;
import cn.netinnet.common.util.DateUtil;
import cn.netinnet.common.util.Md5EncryptUtil;
import cn.netinnet.common.util.StringUtil;
import cn.netinnet.common.util.httpclient.SessionUtil;
import cn.netinnet.educationcenter.constant.ParaConstant;
import cn.netinnet.educationcenter.dao.*;
import cn.netinnet.educationcenter.domain.SysExamScore;
import cn.netinnet.educationcenter.domain.SysExamSession;
import cn.netinnet.educationcenter.domain.SysExamUser;
import cn.netinnet.educationcenter.domain.SysUser;
import cn.netinnet.educationcenter.domain.dto.ScoreInfo;
import cn.netinnet.educationcenter.service.SysExamUserService;
import cn.netinnet.educationcenter.service.SysUserService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author yuyb
 * @date   2020-06-12
 */
@Service
public class SysExamUserServiceImpl extends BaseService<SysExamUser> implements SysExamUserService {

    @Resource
    SysExamSessionMapper sysExamSessionMapper;
    @Resource
    SysExamQuestionMapper sysExamQuestionMapper;
    @Resource
    SysExamUserMapper sysExamUserMapper;
    @Resource
    SysExamScoreMapper sysExamScoreMapper;
    @Resource
    SysUserService sysUserService;
    @Resource
    SysUserMapper sysUserMapper;
//    @Resource
//    CleanDataUtil cleanDataUtil;
    @Resource
    SysExamUserService sysExamUserService;
//    @Resource
//    JudgeQuestionUtil judgeQuestionUtil;

    @Override
    public int updateByPrimaryKeySelective(SysExamUser sysExamUser, long l) {
        return 0;
    }

    @Override
    public int insertSelective(SysExamUser sysExamUser, long userId) {
        return sysExamUserMapper.insertSelective(sysExamUser);
    }
    @Override
    public Class getClazz(){
        return SysExamUser.class;
    }

    /**
    * 插入学生数据
    * @param users
    * @param sysExamScores
    * @author ousp
    * @date 2020/6/15
    * @return void
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addStudents(List<SysExamUser> users, List<SysExamScore> sysExamScores) {
        sysExamUserMapper.batchInsertSelective(users);
        sysExamScoreMapper.batchInsertSelective(sysExamScores);
    }

    /**
    * 将examUser设进session
    * @param batchId
    * @param userId
    * @author ousp
    * @date 2020/7/29
    * @return cn.netinnet.workflow.sys.domain.SysExamUser
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExamInfo setExamUserToSession(long batchId, long userId, long sessionId, long questionId, int sessionType, int sessionFrom) {
        SysExamUser sysExamUser = sysExamUserMapper.queryBySessionIdAndUserId(sessionId, userId);
        if(sysExamUser == null){
            //保存更新SysExamUser
            sysExamUser = new SysExamUser();
            sysExamUser.setExamId(DateUtil.getUID());
            sysExamUser.setBatchId(batchId);
            sysExamUser.setUserId(userId);
            sysExamUser.setSessionId(sessionId);
            sysExamUser.setSubmitState(0);
            List<SysExamScore> userScores = new ArrayList<>();

            if (sessionType == ParaConstant.SESSION_TYPE_TASK) {
                // 作业1道题
                if (questionId == 0L) {
                    throw new CustomException("题目id有误！");
                }
                SysExamScore examScore = new SysExamScore();
                examScore.setId(DateUtil.getUID());
                examScore.setSessionId(sessionId);
                examScore.setQustionId(questionId);
                examScore.setUserId(userId);
                userScores.add(examScore);
            } else if (sessionType == ParaConstant.SESSION_TYPE_EXAM){
                // 查询题目
                List<Long> questionIds = sysExamQuestionMapper.getQuestionsBySessionId(sessionId);
                if (questionIds.isEmpty()) {
                    throw new CustomException("该试卷下没有题目！");
                }
                SysExamScore examScore;
                for (Long quest : questionIds) {
                    examScore = new SysExamScore();
                    examScore.setId(DateUtil.getUID());
                    examScore.setSessionId(sessionId);
                    examScore.setQustionId(quest);
                    examScore.setUserId(userId);
                    userScores.add(examScore);
                }
            }

            sysExamUserMapper.insertSelective(sysExamUser);
            if (!userScores.isEmpty()) {
                sysExamScoreMapper.batchInsertSelective(userScores);
            }
        }
        ExamInfo examInfo = new ExamInfo(sysExamUser.getExamId(), sysExamUser.getBatchId(),
                sysExamUser.getSessionId(), sysExamUser.getSubmitState(), sessionFrom, sessionType);
        examInfo.setQuestionId(questionId);
        SessionUtil.setSession(GlobalConstant.SESSION_EXAM, examInfo);
        return examInfo;
    }

    @Override
    public List<Map<String, Object>> queryExamStudentList(Long sessionId, String userLogin, String userName) {
        if(!StringUtil.isBlankOrNull(userLogin)){
            userLogin = "%"+userLogin+"%";
        }
        if(!StringUtil.isBlankOrNull(userName)){
            userName = "%"+userName+"%";
        }
        List<Map<String, Object>> result = sysExamUserMapper.queryExamStudentList(sessionId, userLogin, userName);
        result.forEach(r -> {
            r.put("userLogin", r.get("userLogin").toString().replace(GlobalConstant.ZBY_USER_PREFIX, ""));
        });
        return result;
    }

    /**  方法描述
     * @Description 新增学生查询相关的班级学生
     * @Author yuyb
     * @Date 9:32 2020/8/3
     * @param sessionId
     * @param className
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @Override
    public List<Map<String, Object>> queryAddExamStudentList(Long sessionId, String className) {
        Long schoolId = UserUtil.getUser().getSchoolId();
        //查询该考试下已存在的考试学生
        List<Long> studentIdList = sysExamUserMapper.queryUserIdBySessionId(sessionId);
        return sysUserService.getUserGroupByClass(schoolId, className, studentIdList, ParaConstant.PROJECT_CODE_STANDARD, null);
    }

    /**  方法描述
     * @Description 新增考试学生
     * @Author yuyb
     * @Date 9:32 2020/8/3
     * @param sessionId
     * @param userIdList
     * @return void
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addExamStudent(Long sessionId, List<Long> userIdList) {
        //查询该考试下已存在的考试学生
        List<Long> studentIdList = sysExamUserMapper.queryUserIdBySessionId(sessionId);
        //重复idList
        List<Long> existUserIdList = studentIdList.stream().filter(userIdList :: contains).collect(Collectors.toList());
        if (existUserIdList.size() > 0) {
            throw new CustomException("存在重复添加的学生，请检查！");
        }
        //查询对应考试所选择的题目表
        List<Long> questionIdList = sysExamQuestionMapper.getQuestionIdsBySessionId(sessionId);
        if(questionIdList.isEmpty()){
            throw new CustomException("考试没有添加试题，请先添加试题！");
        }
        //添加考试学生以及考试题目
        List<SysExamUser> sysExamUsers = new ArrayList<>();
        List<SysExamScore> sysExamScores = new ArrayList<>();
        SysExamScore sysExamScore;
        SysExamUser addStudent;
        if (userIdList.size() > 0) {
            for (Long userId : userIdList) {
                //考试学生
                addStudent = SysExamUserService.genExamUser(sessionId, 0L, userId);
                sysExamUsers.add(addStudent);
                //考试题目
                for (Long questionId : questionIdList) {
                    sysExamScore = SysExamUserService.genExamScore(sessionId, addStudent.getUserId(), questionId);
                    sysExamScores.add(sysExamScore);
                }
            }
        }
        sysExamUserMapper.batchInsertSelective(sysExamUsers);
        sysExamScoreMapper.batchInsertSelective(sysExamScores);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResultEntry importExamStudent(SysUser sysUser, JSONArray list, Long sessionId) {
        //查询对应考试所选择的题目表
        List<Long> questionIdList = sysExamQuestionMapper.getQuestionIdsBySessionId(sessionId);
        if(questionIdList.isEmpty()){
            throw new CustomException("考试没有添加试题，请先添加试题！");
        }
        //检验数据
        List<String> errList = checkExamStudentImportInfo(list);
        //数据初始化
        if (!CollectionUtils.isEmpty(errList)){
            return HttpResultEntry.customize(ResultEnum.R_IMPORT_CHECK, errList);
        }
        String userName, userLogin, password, passwordMd5;
        UserInfo userInfo = UserUtil.getUser();
        //学生数据
        List<SysUser> addStudentList = new ArrayList<>();
        //考试学生数据
        List<SysExamUser> sysExamUsers = new ArrayList<>();
        int size = list.size();
        //添加考试题目
        List<SysExamScore> sysExamScores = new ArrayList<>();
        SysExamScore sysExamScore;
        SysExamUser sysExamUser;
        SysUser importUser;
        JSONObject student;
        for (int i = 0; i < size; i++) {
            student = list.getJSONObject(i);
            userName = student.getString("userName");
            userLogin = student.getString("login");
            password = StringUtils.isBlank(student.getString("password")) ? UserConstant.DEFAULT_PASSWORD : student.getString("password");
            passwordMd5 = Md5EncryptUtil.getMD5Str(password);
            //用户学生
            importUser = SysUserService.genSysUser(sysUser, userInfo, userLogin, userName, 1, "", passwordMd5);
            Long importUserId = importUser.getUserId();
            addStudentList.add(importUser);
            //考试学生
            sysExamUser = SysExamUserService.genExamUser(sessionId, 0L, importUserId);
            sysExamUsers.add(sysExamUser);
            //考试题目
            for (Long questionId : questionIdList) {
                sysExamScore = SysExamUserService.genExamScore(sessionId, importUserId, questionId);
                sysExamScores.add(sysExamScore);
            }
        }
        sysUserMapper.batchInsertSelective(addStudentList);
        sysExamUserMapper.batchInsertSelective(sysExamUsers);
        sysExamScoreMapper.batchInsertSelective(sysExamScores);
        return HttpResultEntry.ok("上传成功");
    }

    /**
     * @Author xiangra
     * @Date 2020/4/10/010 9:43
     * @Description 验证导入考试学生
     */
    private List<String> checkExamStudentImportInfo(JSONArray list) {
        // 校验错误信息列表
        List<String> errList = new ArrayList<>();
        // 姓名map，校验是否重复
        Map<String, String> nameMap = new HashMap<>();
        // 姓名重复错误信息
        List<String> nameRepeat = new ArrayList<>();
        // 账号map，校验是否重复
        Map<String, String> loginMap = new HashMap<>();
        // 账号重复错误信息
        List<String> loginRepeat = new ArrayList<>();
        // 校验账号是否已存在
        JSONObject student;
        List<String> loginList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            StringBuilder err = new StringBuilder();
            student = list.getJSONObject(i);
            String rows = student.getString("rows");
            String name = student.getString("userName");
            String login = student.getString("login");
            String password = student.getString("password");
            // 校验信息
            if (StringUtil.isBlankOrNull(name)) {
                err.append("第").append(rows).append("行 第B列").append(ErrorMsgConstant.USER_NAME_NULL);
            }
            if (name.length() > 15) {
                err.append("第").append(rows).append("行 第B列").append(ErrorMsgConstant.USER_NAME_LENGTH);
            }
            if (StringUtils.isBlank(login)) {
                err.append("第").append(rows).append("行 第C列").append(ErrorMsgConstant.USER_LOGIN_NULL);
            }
            if (login.length() > 30 || login.length() < 1) {
                err.append("第").append(rows).append("行 第C列").append(ErrorMsgConstant.USER_LOGIN_LENGTH);
            }
            if (RegUtil.isNotMatch(RegUtil.REGEX_LETTER_NUM, login)) {
                err.append("第").append(rows).append("行 第C列").append(ErrorMsgConstant.USER_LOGIN_CHAR);
            }
            if (StringUtils.isNotBlank(password)&&(password.length() > 16 || password.length() < 6)) {
                err.append("第").append(rows).append("行 第D列").append(ErrorMsgConstant.USER_PWD_LENGTH);
            }
            // 添加错误信息
            if (err.length() > 0) {
                errList.add(err.toString());
            }
            // 名称判断是否重复
            if (!nameMap.containsKey(name)) {
                nameMap.put(name, rows);
            } else {
                if (!nameRepeat.contains(name)) {
                    nameRepeat.add(name);
                }
                // 拼接重复的行次
                nameMap.put(name, nameMap.get(name) + "," + rows);
            }
            // 账号判断是否重复
            CommonUtil.judgeLoginRepeat(loginMap, loginList, loginRepeat, login, rows);
        }
        // 如果存在重复，添加重复信息
        if (!nameRepeat.isEmpty()) {
            CommonUtil.genErrInfo(nameMap, nameRepeat, errList, "B", "信息重复");
        }
        if (!loginRepeat.isEmpty()) {
            CommonUtil.genErrInfo(loginMap, loginRepeat, errList, "C", "信息重复");
        }
        // 判断账号是否已存在
        if (!loginList.isEmpty()) {
            List<String> existList = sysUserMapper.checkLoginsExist(loginList);
            if (!existList.isEmpty()) {
                CommonUtil.genErrInfo(loginMap, existList, errList, "C", "系统已经存在");
            }
        }
        return errList;
    }

    @Override
    public void delExamUserByIdList(List<Long> examUserIdList, Long sessionId, String bpmnPath) {
        int sessionStatus = sysExamSessionMapper.getSessionStatus(sessionId);
        if (sessionStatus == ParaConstant.EXAM_STATUS_START) {
            throw new CustomException("考试进行中，不能删除！");
        }
        //获取删除的学生id
        List<SysExamUser> examUsers = sysExamUserMapper.queryExamUserByExamUserId(examUserIdList);
        //todo：除了考试学生表记录后续还需要删除相关的任务记录
        // todo
//        cleanDataUtil.delExamUserData(examUsers, bpmnPath);
    }

    /***
    * 进入我的练习、作业、考试
    * @param sessionId  场次id
    * @param batchId    批次id
    * @param userId     用户id
    * @param sessionType    场次类型
    * @author ousp
    * @date 2020/8/27
    * @return java.util.Map<java.lang.String,java.lang.Object>
    */
    @Override
    public Map<String, Object> intoMySession(long sessionId, long batchId, long userId, int sessionType) {
        SysExamSession sysExamSession = sysExamSessionMapper.selectByPrimaryKey(sessionId);
        String name = null;
        long questionId = 0L;
        Map<String, Object> result = new HashMap<>();
        if (sessionType == ParaConstant.SESSION_TYPE_TRAIN) {
            name = "练习";
        } else if (sessionType == ParaConstant.SESSION_TYPE_TASK){
            questionId = sysExamSession.getQuestionId();
            name = "作业";
        } else if (sessionType == ParaConstant.SESSION_TYPE_EXAM) {
            name = "考试";
        } else {
            throw new CustomException("场次类型有误！");
        }
        if (sysExamSession == null || sysExamSession.getDelFlag() == 1) {
            throw new CustomException("该" + name + "已被删除!");
        }
        if (sessionType != ParaConstant.SESSION_TYPE_TRAIN && sysExamSession.getSessionStatus() == 0) {
            throw new CustomException("该" + name + "未启用!");
        }
        ExamInfo examInfo = sysExamUserService.setExamUserToSession(batchId, userId, sessionId, questionId, sessionType, sysExamSession.getSessionFrom());
        result.put("examInfo", examInfo);
        if (sessionType == ParaConstant.SESSION_TYPE_TASK || sessionType == ParaConstant.SESSION_TYPE_EXAM) {
            Date now = new Date();
            result.put("startTime", sysExamSession.getStartTime());
            result.put("endTime", sysExamSession.getEndTime());
            result.put("endState", now.after(sysExamSession.getEndTime()) ? 1 : 0);
            result.put("viewAnswer", 0);
            if (sysExamSession.getViewAnswer() == ParaConstant.VIEW_ANSWER_SUBMIT && examInfo.getSubmitState() == 1) {
                // 用户提交后
                result.put("viewAnswer", 1);
            } else if (sysExamSession.getViewAnswer() == ParaConstant.VIEW_ANSWER_END && sysExamSession.getEndTime().before(new Date())) {
                // 考试结束后
                result.put("viewAnswer", 1);
            }
            result.put("resetable", sysExamSession.getResetable());
            result.put("now", now);
        }
        return result;
    }

    /***
    * （考试界面）进入做题
    * @param questionId
    * @param session
    * @author ousp
    * @date 2020/8/27
    * @return void
    */
    @Override
    public ExamInfo intoQuestion(long questionId, HttpSession session) {
        ExamInfo examInfo = UserUtil.getExamInfo(session);
        examInfo.setQuestionId(questionId);
        SessionUtil.setSessionAttr(session, GlobalConstant.SESSION_EXAM, examInfo);
        return examInfo;
    }

    /***
    * 结束考试
    * @param examId     id
    * @param sessionId  场次id
    * @param userId     用户id
    * @author ousp
    * @date 2020/8/28
    * @return void
    */
    @Override
    public void submitExam(long examId, long sessionId, long userId, int sessionFrom) {
        // 结束考试只改变状态不帮用户提交
        sysExamUserMapper.updateSubmitStateByIds(Collections.singletonList(examId), GlobalConstant.ONE);
    }

    /** 方法描述
     * @description  交卷判分
     * @param userMap
     * @return void
     * @author Caicm
     * @date 2020/8/31 14:13
     */
    @Override
    public Map<Long, List<ScoreInfo>> submitSession(Map<Long, Set<Long>> userMap, boolean fromExam) {
        Map<Long, List<ScoreInfo>> scoreMap = new HashMap<>();
        List<ScoreInfo> scoreInfos;
        ScoreInfo scoreInfo;
        BigDecimal score;
        List<SysExamUser> examUsers;
        List<Long> examQuestions;
        for (Long sessionId : userMap.keySet()){
            scoreInfos = new ArrayList<>();
            scoreMap.put(sessionId, scoreInfos);
            Set<Long> userList = userMap.get(sessionId);
            examUsers = sysExamUserMapper.querySessionUser(sessionId);
            //考试场次对应的题目
            examQuestions = sysExamQuestionMapper.getQuestionsBySessionId(sessionId);
            examUsers = examUsers.stream().filter(user -> userList.contains(user.getUserId())).collect(Collectors.toList());
            for(SysExamUser examUser : examUsers){
                for(Long questionId : examQuestions){
                    scoreInfo = new ScoreInfo();
                    scoreInfo.setUserId(examUser.getUserId());
                    scoreInfo.setQustionId(questionId);
                    //先不考虑发送消息
                    // todo
//                    score = judgeQuestionUtil.judgeQuestion(questionId, examUser.getUserId(), examUser.getExamId(),
//                            sessionId, ParaConstant.SESSION_FROM_EXAM, ParaConstant.SESSION_TYPE_EXAM, fromExam);
//                    scoreInfo.setScore(score);
                    scoreInfos.add(scoreInfo);
                }
            }
        }
        return scoreMap;
    }

    /***
    * 取消考试提交
    * @author ousp
    * @date 2020/9/24
    * @return void
    */
    @Override
    public void cancelSubmitExam(long examId, long sessionId, long userId, Long questionId) {
        if (questionId != null) {
            //有题目id的是单道题的取消提交（考试平台）
            sysExamScoreMapper.updateQuestionState(sessionId, userId, questionId, 0);
        } else {
            sysExamUserMapper.cancelSubmitExam(examId, sessionId, userId);
        }
    }
}
