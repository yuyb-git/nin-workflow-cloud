package cn.netinnet.educationcenter.controller;

import cn.netinnet.cloudcommon.annotation.LogMark;
import cn.netinnet.cloudcommon.annotation.PreventRepeatSubmit;
import cn.netinnet.cloudcommon.annotation.RequiresPermission;
import cn.netinnet.cloudcommon.base.BaseController;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.constant.ParaConstant;
import cn.netinnet.cloudcommon.constant.UserConstant;
import cn.netinnet.cloudcommon.dto.ExamInfo;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.globol.ResultEnum;
import cn.netinnet.cloudcommon.utils.SessionUtil;
import cn.netinnet.cloudcommon.utils.StringUtil;
import cn.netinnet.cloudcommon.utils.UserUtil;
import cn.netinnet.educationcenter.dao.SysExamUserMapper;
import cn.netinnet.educationcenter.domain.SysExamSession;
import cn.netinnet.educationcenter.service.SysExamQuestionService;
import cn.netinnet.educationcenter.service.SysExamSessionService;
import cn.netinnet.educationcenter.service.SysExamUserService;
import cn.netinnet.educationcenter.service.SysUserService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

/**
 * @ClassName SysSessionController
 * @Description
 * @Author yuyb
 * @Date 2020/6/12 11:27
 */
@RestController
@RequestMapping("/exam")
public class SysExamController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SysExamController.class);

    @Resource
    SysExamSessionService sysExamSessionService;
    @Resource
    SysExamQuestionService sysExamQuestionService;
//    @Resource
//    SysQuestionService sysQuestionService;
    @Resource
    SysUserService sysUserService;
    @Resource
    SysExamUserMapper sysExamUserMapper;
    @Resource
    SysExamUserService sysExamUserService;

    /**
    * 同步考试时间
    * @param sessionId
    * @author ousp
    * @date 2020/6/12
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
    @GetMapping("/syncExamTime")
    @ResponseBody
    public HttpResultEntry syncExamTime(@RequestParam("sessionId") long sessionId, HttpSession session) {
        ExamInfo examInfo = UserUtil.getExamInfo(session);
        Date extraEndTime =  sysExamUserMapper.queryExtraTimeById(examInfo.getExamId());
        Timestamp endTime;
        if (extraEndTime == null) {
            endTime = sysExamSessionService.queryEndTime(sessionId);
        } else {
            endTime = new Timestamp (extraEndTime.getTime());
        }

        Date currentTime = new Date();
        Map<String, Object> data = new HashMap<>();
        data.put("currentTime",  new Timestamp(currentTime.getTime()));
        data.put("endTime", endTime);
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, data);
    }

    @PostMapping("/answerSet")
    public HttpResultEntry answerSet(@RequestParam("questionId")Long questionId){
//          todo
//        SysQuestion sysQuestion = sysQuestionService.selectById(questionId);
//        if(sysQuestion == null){
//            return HttpResultEntry.error("未找到该题目！");
//        }
//        ExamInfo examInfo = new ExamInfo();
//        examInfo.setQuestionId(questionId);
//        SessionUtil.setSession(GlobalConstant.SESSION_EXAM, examInfo);
        return HttpResultEntry.ok();
    }

    /**
    * 查看答案或者返回
    * @param examId
    * @param session
    * @author ousp
    * @date 2020/7/1
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
    @PostMapping("/viewAnswerOrBackStu")
    public HttpResultEntry viewAnswerOrBackStu(@RequestParam(value = "examId",required = false)Long examId,
                                               HttpSession session) {
        ExamInfo examInfo = UserUtil.getExamInfo(session);
        UserInfo userInfo = UserUtil.getUser();

        if (examId == null) {
            examInfo.setExamId(0L);
            userInfo.setUserType(UserConstant.TEACHER);
        } else {
            examInfo.setExamId(examId);
            userInfo.setUserType(UserConstant.STUDENT);
        }
        SessionUtil.setSession(GlobalConstant.SESSION_EXAM, examInfo);
        SessionUtil.setSession(GlobalConstant.SESSION_STAFF, null);
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, sysUserService.userInfoSet(userInfo, false, null));
    }

    @RequiresPermission("sysExam:view")
    @GetMapping("/queryList")
    public HttpResultEntry queryList(@RequestParam(required = false) String page,
                                     @RequestParam(required = false) String size,
                                     @RequestParam(required = false) String sessionName,
                                     @RequestParam(required = false) Integer sessionType,
                                     HttpSession session) {
        ExamInfo examInfo = UserUtil.getExamInfoWithOutCheck(session);
        PageInfo pageInfo = getPage(() -> sysExamSessionService.queryList(sessionType, sessionName, examInfo.getSessionId()));
        JSONObject result = new JSONObject();
        result.put("pageInfo", pageInfo);
        result.put("now", new Date());
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, result);
    }

    @RequiresPermission("sysExam:add")
    @LogMark("新增考试")
    @PostMapping("/addExam")
    @PreventRepeatSubmit
    public HttpResultEntry addExam(SysExamSession sysExamSession){
        HttpResultEntry resultEntry = validateSessionParameter(sysExamSession);
        if(resultEntry != null){
            return resultEntry;
        }
        sysExamSessionService.addExam(sysExamSession, ParaConstant.SESSION_TYPE_EXAM);
        return HttpResultEntry.ok();
    }

    @RequiresPermission("sysExam:edit")
    @LogMark("修改考试")
    @PostMapping("/editExam")
    @PreventRepeatSubmit
    public HttpResultEntry editExam(SysExamSession sysExamSession) {
        sysExamSessionService.editExam(sysExamSession);
        return HttpResultEntry.ok();
    }

    @RequiresPermission("sysExam:delete")
    @LogMark("删除考试")
    @PostMapping("/deleteExam")
    @PreventRepeatSubmit
    public HttpResultEntry deleteExam(Long sessionId) {
        long userId = UserUtil.getUser().getUserId();
        sysExamSessionService.deleteExam(sessionId, userId);
        return HttpResultEntry.ok();
    }

    /**
     * @Author wangyt
     * @Date 2020/4/9 17:41
     * @Description 开始考试
     */
    //@RequiresPermission("sysExam:status")
    @LogMark("开始考试")
    @PostMapping("/startExam")
    @PreventRepeatSubmit
    public HttpResultEntry startExam(Long sessionId) {
        //考试id为空时, 参数异常
        if (sessionId == null) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        sysExamSessionService.updateExamStatus(sessionId, ParaConstant.EXAM_STATUS_START);
        return HttpResultEntry.ok();
    }

    /**  方法描述
     * @Description 查询考试题目
     * @Author yuyb
     * @Date 15:20 2020/8/3
     * @param sessionId
     * @param page
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @RequiresPermission("examQuestion:view")
    @GetMapping("/examQuestions")
    @PreventRepeatSubmit
    public HttpResultEntry examQuestions(Long sessionId,
                                         @RequestParam(required = false) String questionTitle,
                                         @RequestParam(required = false) String page,
                                         @RequestParam(required = false) String size){
        PageInfo pageInfo = getPage(() -> sysExamQuestionService.getExamQuestionList(sessionId, questionTitle));
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, pageInfo);
    }

    /**  方法描述
     * @Description 查询考试未添加的试题
     * @Author yuyb
     * @Date 15:36 2020/8/3
     * @param sessionId
     * @param page
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @GetMapping("/examQuestionsNotAdd")
    @PreventRepeatSubmit
    public HttpResultEntry examQuestionsNotAdd(Long sessionId,
                                               @RequestParam(required = false) String questionTitle,
                                               @RequestParam(required = false) String page,
                                               @RequestParam(required = false) String size){
        UserInfo userInfo = UserUtil.getUser();
        List<Long> questionIds = sysExamQuestionService.getQuestionIdsBySessionId(sessionId);
        PageInfo pageInfo = getPage(() -> sysExamQuestionService.getExamQuestionListNotAdd(questionIds, questionTitle, userInfo.getUserType(), userInfo.getUserId()));
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, pageInfo);
    }

    /**  方法描述
     * @Description 新增考试题目
     * @Author yuyb
     * @Date 15:21 2020/8/3
     * @param sessionId
     * @param questionIds
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @RequiresPermission("examQuestion:add")
    @LogMark("新增考试题目")
    @PostMapping("/addExamQuestions")
    @PreventRepeatSubmit
    public HttpResultEntry addExamQuestions(Long sessionId, String questionIds){
        if(StringUtil.isBlankOrNull(questionIds)){
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        sysExamQuestionService.addExamQuestions(sessionId, JSONArray.parseArray(questionIds, Long.class));
        return HttpResultEntry.ok();
    }

    /**  方法描述
     * @Description 删除考试题目
     * @Author yuyb
     * @Date 15:21 2020/8/3
     * @param sessionId
     * @param questionIds
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @RequiresPermission("examQuestion:del")
    @LogMark("删除考试题目")
    @PostMapping("/delExamQuestions")
    @PreventRepeatSubmit
    public HttpResultEntry delExamQuestions(Long sessionId, String questionIds){
        if(StringUtil.isBlankOrNull(questionIds)){
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        int result = sysExamQuestionService.delExamQuestions(sessionId, JSONArray.parseArray(questionIds, Long.class));
        return HttpResultEntry.ok("成功删除"+result+"条记录！");
    }

    /**
     * @Author: Xielx
     * @Date: 2020/6/17
     * @Description: 考试成绩列表
     */
    @GetMapping("/queryResultList")
    public HttpResultEntry queryResultList(@RequestParam long sessionId,
                                           @RequestParam int sessionType,
                                           @RequestParam(required = false) String userName,
                                           @RequestParam(required = false) String userLogin,
                                           @RequestParam(required = false) String page,
                                           @RequestParam(required = false) String size) {
        PageInfo pageInfo = getPage(() -> sysExamSessionService.resultsList(sessionId, userName, sessionType, userLogin));
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, pageInfo);
    }

    /** 方法描述
     * @description 重置考试试题
     * @param sessionId
     * @param questionId
     * @param userId
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     * @author Caicm
     * @date 2020/8/5 15:37
     */
    @LogMark("重置学生试题数据")
    @PostMapping("/resetExamQuestion")
    public HttpResultEntry resetExamQuestion(@RequestParam("sessionId")Long sessionId,
                                             @RequestParam("questionId")Long questionId,
                                             @RequestParam("userId")Long userId){
        sysExamQuestionService.resetExamQuestion(sessionId, questionId, userId);
        return HttpResultEntry.ok();
    }

    /**  方法描述
     * @Description 学生端自己重置题目数据
     * @Author yuyb
     * @Date 9:02 2020/8/21
     * @param sessionId
     * @param questionId
     * @param userId
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @LogMark("学生自行重置试题")
    @PostMapping("/studentResetData")
    public HttpResultEntry studentResetData(@RequestParam("sessionId")Long sessionId,
                                             @RequestParam("questionId")Long questionId,
                                             @RequestParam("userId")Long userId){
        sysExamQuestionService.resetExamQuestion(sessionId, questionId, userId);
        return HttpResultEntry.ok();
    }

    /***
    * 整个场次重置
    * @param sessionId
    * @author ousp
    * @date 2020/8/20
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
    @LogMark("重置考试")
    @PostMapping("/examReset")
    public HttpResultEntry examReset(@RequestParam("sessionId")Long sessionId) {
        List<Long> examUserIds = sysExamUserMapper.queryExamUserBySessionIds(Collections.singletonList(sessionId));
        if (!examUserIds.isEmpty()) {
            sysExamSessionService.batchResetUserExam(examUserIds, sessionId, null);
        }
        return HttpResultEntry.ok();
    }

    /***
    * 批量重置场次下的指定的用户数据
    * @param sessionId
    * @param examUserIds
    * @param userIds
    * @description
    * @author ousp
    * @date 2020/8/20
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
    @LogMark("批量重置学生数据")
    @PostMapping("/batchResetUserExam")
    public HttpResultEntry batchResetUserExam(@RequestParam("sessionId")Long sessionId,
                                              @RequestParam("examUserIds") String examUserIds,
                                              @RequestParam("userIds") String userIds) {
        if (StringUtil.isBlankOrNull(examUserIds) || StringUtil.isBlankOrNull(userIds)) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        sysExamSessionService.batchResetUserExam(JSONArray.parseArray(examUserIds,Long.class), sessionId, JSONArray.parseArray(userIds,Long.class));
        return HttpResultEntry.ok();
    }

    /**  方法描述
     * @Description 设置考试参数
     * @Author yuyb
     * @Date 19:19 2020/8/20
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @LogMark("设置考试参数")
    @PostMapping("/setSessionParameter")
    public HttpResultEntry setResultAndReset(@RequestParam("sessionId")Long sessionId,
                                             @RequestParam("resetable") int resetable,
                                             @RequestParam("viewAnswer") int viewAnswer){
        sysExamSessionService.setParameters(sessionId, resetable, viewAnswer);
        return HttpResultEntry.ok();
    }

    /**
    * 导出成绩
    * @aram sessionId
    * @param response
    * @author ousp
    * @date 2020/8/14
    * @return void
    */
    @RequestMapping("/exportScore")
    public void exportScore(@RequestParam("sessionId") long sessionId, HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = sysExamQuestionService.exportScore(sessionId);
        response.setHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode(workbook.getSheetName(0), "UTF-8") + ".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.flush();
        outputStream.close();
    }

    /***
    *   查询做题结果
    * @param userId
    * @param questionId
    * @description
    * @author ousp
    * @date 2020/8/20
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
    @RequestMapping("/getQuestionResultDetail")
    public HttpResultEntry getQuestionResultDetail(@RequestParam("userId") long userId,
                                                   @RequestParam("questionId") long questionId,
                                                   @RequestParam("sessionId") long sessionId) {
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, sysExamSessionService.getQuestionResultDetail(userId, questionId, sessionId));
    }

    /** 校验场次参数 */
    private HttpResultEntry validateSessionParameter(SysExamSession sysExamSession){
        //id不为空/参数为空, 参数异常
        if (sysExamSession.getSessionId() != null || StringUtils.isBlank(sysExamSession.getSessionName())) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        //开始时间, 结束时间不可为空
        if (sysExamSession.getStartTime() == null || sysExamSession.getEndTime() == null) {
            return HttpResultEntry.error(GlobalConstant.FAILURE, "时间不可为空");
        }
        //时间比对
        if (sysExamSession.getStartTime().after(sysExamSession.getEndTime())) {
            return HttpResultEntry.error(GlobalConstant.FAILURE, "开始时间应早于结束时间");
        }
        return null;
    }

    @LogMark("新增练习")
    @PostMapping("/addTrain")
    @PreventRepeatSubmit
    public HttpResultEntry addTrain(SysExamSession sysExamSession){
        HttpResultEntry resultEntry = validateSessionParameter(sysExamSession);
        if(resultEntry != null){
            return resultEntry;
        }
        sysExamSessionService.addExam(sysExamSession, ParaConstant.SESSION_TYPE_TRAIN);
        return HttpResultEntry.ok();
    }

    @LogMark("新增作业")
    @PostMapping("/addTask")
    @PreventRepeatSubmit
    public HttpResultEntry addTask(SysExamSession sysExamSession){
        HttpResultEntry resultEntry = validateSessionParameter(sysExamSession);
        if(resultEntry != null){
            return resultEntry;
        }
        if (sysExamSession.getQuestionId() == null) {
            return HttpResultEntry.error(GlobalConstant.FAILURE, "试题不能为空");
        }
        if (sysExamSession.getViewAnswer() == null) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        sysExamSessionService.addExam(sysExamSession, ParaConstant.SESSION_TYPE_TASK);
        return HttpResultEntry.ok();
    }

    @LogMark("修改练习")
    @PostMapping("/editTrain")
    @PreventRepeatSubmit
    public HttpResultEntry editTrain(SysExamSession sysExamSession) {
        sysExamSessionService.editExam(sysExamSession);
        return HttpResultEntry.ok();
    }

    @LogMark("修改作业")
    @PostMapping("/editTask")
    @PreventRepeatSubmit
    public HttpResultEntry editTask(SysExamSession sysExamSession) {
        sysExamSessionService.editExam(sysExamSession);
        return HttpResultEntry.ok();
    }

    /**  方法描述
     * @Description 查询批次下的练习、作业或考试列表
     * @Author yuyb
     * @Date 11:34 2020/8/27
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @GetMapping("/getBatchSessionList")
    public HttpResultEntry getBatchSessionList(@RequestParam Long batchId,
                                               @RequestParam Integer sessionType,
                                               @RequestParam(required = false) Integer sessionStatus,
                                               @RequestParam(required = false) String sessionName,
                                               @RequestParam(required = false) String page,
                                               @RequestParam(required = false) String size){
        PageInfo pageInfo = getPage(() -> sysExamSessionService.getBatchSessionList(batchId, sessionType, sessionStatus, sessionName));
        JSONObject result = new JSONObject();
        result.put("pageInfo", pageInfo);
        result.put("now", new Date());
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, result);
    }

    /**  方法描述
     * @Description 查询开始、作业、练习下用户的考试情况
     * @Author yuyb
     * @Date 16:25 2020/8/27
     * @param sessionId
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @GetMapping("/sessionCompleteDetail")
    public HttpResultEntry sessionCompleteDetail(long sessionId,
                                                 @RequestParam(required = false) Long classId,
                                                 @RequestParam(required = false) Integer submitStatus,
                                                 @RequestParam(required = false) String userName,
                                                 @RequestParam long batchId){
        PageInfo pageInfo = getPage(() -> sysExamSessionService.sessionCompleteDetail(sessionId, classId, submitStatus, userName, batchId));
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, pageInfo);
    }

    /**  方法描述
     * @Description 更新试题分值
     * @Author yuyb
     * @Date 16:06 2020/8/26
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @LogMark("更新试题分值")
    @PostMapping("/setQuestionScore")
    public HttpResultEntry setQuestionScore(Long id, int scoreValue, Long sessionId){
        sysExamQuestionService.setQuestionScore(id, scoreValue, sessionId);
        return HttpResultEntry.ok();
    }

    /***
    * 题目列表及用户做题情况
    * @param sessionId
    * @param userId
    * @description
    * @author ousp
    * @date 2020/8/26
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
    @GetMapping("/questQuestionDoDetaiList")
    public HttpResultEntry questQuestionDoDetaiList(@RequestParam("sessionId") long sessionId,
                                                    @RequestParam("userId") long userId) {
       return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG,
               sysExamSessionService.questQuestionDoDetaiList(sessionId, userId));
    }

    /***
    * 考试详情
    * @Param sessionId  场次id
    * @param userId     用户id
    * @param state      提交状态
    * @param questionTitle  题目名称
    * @author ousp
    * @date 2020/8/26
    * @return void
    */
    @GetMapping("/userExamDetail")
    public HttpResultEntry userExamDetail(@RequestParam("sessionId") long sessionId,
                               @RequestParam("userId") long userId,
                               @RequestParam(value = "state", required = false) Integer state,
                               @RequestParam(value = "questionTitle", required = false) String questionTitle) {
        PageInfo pageInfo = getPage(() ->  sysExamSessionService.userExamDetail(sessionId, userId, state, questionTitle));
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, pageInfo);
    }

    /***
    * 进入我的练习、作业、考试
    * @param sessionId  场次id
    * @param batchId    批次id
    * @param userId     用户id
    * @param sessionType    场次类型
    * @author ousp
    * @date 2020/8/27
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
    @PostMapping("/intoMySession")
    public HttpResultEntry intoMySession(@RequestParam("sessionId") long sessionId,
                                         @RequestParam("batchId") long batchId,
                                         @RequestParam("userId") long userId,
                                         @RequestParam("sessionType") int sessionType) {
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG,
                sysExamUserService.intoMySession(sessionId, batchId, userId, sessionType));
    }

    /***
    * 进入做题
    * @param questionId 题目id
    * @param session    '
    * @author ousp
    * @date 2020/8/27
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
    @PostMapping("/intoQuestion")
    public HttpResultEntry intoQuestion(@RequestParam("questionId") long questionId, HttpSession session) {
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, sysExamUserService.intoQuestion(questionId, session));
    }

    /***
    * 结束考试
    * @param examId         ''
    * @param sessionId      场次id
    * @param userId         用户id
    * @author ousp
    * @date 2020/8/28
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
    @PostMapping("/submitExam")
    @LogMark("结束考试")
    public HttpResultEntry submitExam(@RequestParam("examId") long examId,
                                      @RequestParam("sessionId") long sessionId,
                                      @RequestParam("userId") long userId,
                                      HttpSession session) {
        ExamInfo examInfo = UserUtil.getExamInfo(session);
        sysExamUserService.submitExam(examId, sessionId, userId, examInfo.getSessionForm());
        examInfo.setSubmitState(1);
        SessionUtil.setSessionAttr(session, GlobalConstant.SESSION_EXAM, examInfo);
        return HttpResultEntry.ok();
    }

    /***
    *   查询练作业绩明细
    * @param sessionId  场次id
    * @param questionId 题目id
    * @param userId     用户id
    * @author ousp
    * @date 2020/8/28
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
    @PostMapping("/queryTaskScoreDetail")
    public HttpResultEntry queryTaskScoreDetail(@RequestParam("sessionId") long sessionId,
                                                @RequestParam("questionId") long questionId,
                                                @RequestParam("userId") long userId) {
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, sysExamSessionService.queryTaskScoreDetail(sessionId, questionId, userId));
    }

    /***
    * 取消考试提交（继续考试）
    * @author ousp
    * @date 2020/9/24
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
    @LogMark("继续考试")
    @PostMapping("/cancelSubmitExam")
    public HttpResultEntry cancelSubmitExam(@RequestParam("examId") long examId,
                                            @RequestParam("sessionId") long sessionId,
                                            @RequestParam("userId") long userId,
                                            @RequestParam(value = "questionId", required = false) Long questionId,
                                            HttpSession session) {
        sysExamUserService.cancelSubmitExam(examId, sessionId, userId, questionId);
        ExamInfo examInfo = UserUtil.getExamInfo(session);
        examInfo.setSubmitState(0);
        SessionUtil.setSessionAttr(session, GlobalConstant.SESSION_EXAM, examInfo);
        return HttpResultEntry.ok();
    }
}
