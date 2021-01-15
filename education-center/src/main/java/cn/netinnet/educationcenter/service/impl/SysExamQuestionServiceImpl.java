package cn.netinnet.educationcenter.service.impl;

import cn.netinnet.cloudcommon.base.BaseService;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.exception.CustomException;
import cn.netinnet.cloudcommon.utils.DateUtil;
import cn.netinnet.cloudcommon.utils.StrUtil;
import cn.netinnet.cloudcommon.utils.StringUtil;
import cn.netinnet.educationcenter.constant.ParaConstant;
import cn.netinnet.educationcenter.dao.*;
import cn.netinnet.educationcenter.domain.*;
import cn.netinnet.educationcenter.domain.dto.ExamQuestion;
import cn.netinnet.educationcenter.service.SysExamQuestionService;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author yuyb
 * @date   2020-06-12
 */
@Service
public class SysExamQuestionServiceImpl extends BaseService<SysExamQuestion> implements SysExamQuestionService {

    @Resource
    private SysExamQuestionMapper sysExamQuestionMapper;
    @Resource
    SysExamUserMapper sysExamUserMapper;
    @Resource
    SysExamSessionMapper sysExamSessionMapper;
    @Resource
    SysExamScoreMapper sysExamScoreMapper;
//    @Resource
//    CleanDataUtil cleanDataUtil;
    @Resource
    SysQuestionMapper sysQuestionMapper;
    @Resource
    SysUserMapper sysUserMapper;


    @Override
    public int insertSelective(SysExamQuestion sysExamQuestion, long userId) {
        return sysExamQuestionMapper.insertSelective(sysExamQuestion);
    }
    @Override
    public Class getClazz(){
        return SysExamQuestion.class;
    }

    /**
    * 初始化考试信息
    * @param initExamMap
    * @author ousp
    * @date 2020/6/12
    * @return void
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchInit(Map<Long, List<SysExamQuestion>> initExamMap, List<SysExamSession> examSessions) {
        long[] sessionIds = new long[initExamMap.size()];
        List<SysExamQuestion> initList = new ArrayList<>();
        int i = 0;
        for (Long sessioinId : initExamMap.keySet()) {
            sessionIds[i++] = sessioinId;
            initList.addAll(initExamMap.get(sessioinId));
        }
        sysExamQuestionMapper.delBySessionIds(sessionIds);
        sysExamQuestionMapper.batchInsertSelective(initList);
        sysExamSessionMapper.batchInsertSelective(examSessions);
    }

    /**  方法描述
     * @Description 查询考试使用的试题id
     * @Author yuyb
     * @Date 17:46 2020/8/3
     * @param sessionId
     * @return java.util.List<java.lang.Long>
     **/
    @Override
    public List<Long> getQuestionIdsBySessionId(Long sessionId) {
        return sysExamQuestionMapper.getQuestionIdsBySessionId(sessionId);
    }

    /**  方法描述
     * @Description 查询考试已添加的试题信息
     * @Author yuyb
     * @Date 17:46 2020/8/3
     * @param sessionId
     * @return java.util.List<cn.netinnet.workflow.sys.domain.dto.ExamQuestion>
     **/
    @Override
    public List<ExamQuestion> getExamQuestionList(Long sessionId, String questionTitle) {
        if(!StringUtil.isBlankOrNull(questionTitle)){
            questionTitle = "%" + questionTitle +"%";
        }
        return sysExamQuestionMapper.getExamQuestionList(sessionId, questionTitle);
    }

    /**  方法描述
     * @Description 查询考试未添加的试题信息
     * @Author yuyb
     * @Date 17:45 2020/8/3
     * @param questionIds
     * @return java.util.List<cn.netinnet.workflow.sys.domain.dto.ExamQuestion>
     **/
    @Override
    public List<ExamQuestion> getExamQuestionListNotAdd(List<Long> questionIds, String questionTitle, int userType, long userId) {
        if(!StringUtil.isBlankOrNull(questionTitle)){
            questionTitle = "%" + questionTitle +"%";
        }
        return sysExamQuestionMapper.getExamQuestionListNotAdd(questionIds, questionTitle, userType, userId);
    }

    /**  方法描述
     * @Description 新增考试下题目
     * @Author yuyb
     * @Date 17:46 2020/8/3
     * @param sessionId
     * @param questionIds
     * @return void
     **/
    @Override
    public void addExamQuestions(Long sessionId, List<Long> questionIds) {
        Integer existUser =sysExamUserMapper.existUserBySessionId(sessionId);
        if(existUser != null){
            throw new CustomException("该考试下已有学生，不能新增试题！");
        }
        SysExamQuestion sysExamQuestion;
        List<SysExamQuestion> insertExamQuestions = new ArrayList<>();
        for(Long questionId : questionIds){
            sysExamQuestion = new SysExamQuestion();
            sysExamQuestion.setId(DateUtil.getUID());
            sysExamQuestion.setSessionId(sessionId);
            sysExamQuestion.setQustionId(questionId);
            insertExamQuestions.add(sysExamQuestion);
        }
        sysExamQuestionMapper.batchInsertSelective(insertExamQuestions);
    }

    /**  方法描述
     * @Description 删除考试下题目
     * @Author yuyb
     * @Date 17:46 2020/8/3
     * @param sessionId
     * @param questionIds
     * @return void
     **/
    @Override
    public int delExamQuestions(Long sessionId, List<Long> questionIds) {
        Integer existUser = sysExamUserMapper.existUserBySessionId(sessionId);
        if(existUser != null){
            throw new CustomException("该考试下已有学生，不能删除试题！");
        }
        List<Long> ids = sysExamQuestionMapper.getIdsByQuestionIds(questionIds, sessionId);
        return sysExamQuestionMapper.batchDeleteByList(ids);
    }

    /** 方法描述
     * @description 重置考试试题
     * @param sessionId
     * @param questionId
     * @param userId
     * @return void
     * @author Caicm
     * @date 2020/8/5 15:34
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetExamQuestion(Long sessionId, Long questionId, Long userId) {
        SysExamUser sysExamUser = sysExamUserMapper.queryBySessionIdAndUserId(sessionId, userId);
        Long examId = sysExamUser.getExamId();
        List<Long> examIds = new ArrayList<>(1);
        examIds.add(examId);
//        todo
//        cleanDataUtil.logicalDelCompanyAndDef(examIds, questionId);
//        cleanDataUtil.delUserQuestionResult(sessionId, questionId, userId);
        // 获取当前成绩
        SysExamScore sysExamScore = sysExamScoreMapper.queryQuestionScoreDetail(sessionId, questionId, userId);
        // 更新当前题目成绩和总成绩
        sysExamScoreMapper.resetExamQuestion(sysExamScore.getId(), examId, sysExamScore.getScore());
    }

    /**
    * 导出成绩
    * @param sessionId
    * @author ousp
    * @date 2020/8/14
    * @return org.apache.poi.xssf.usermodel.XSSFWorkbook
    */
    @Override
    public XSSFWorkbook exportScore(long sessionId) {
        // 场次信息
        SysExamSession examSession = sysExamSessionMapper.selectByPrimaryKey(sessionId);
        if (examSession == null) {
            throw new CustomException("未找到场次信息！");
        }
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("成绩表");
        XSSFRow sheetRow = sheet.createRow((short) 0);
        //设置行高
        sheetRow.setHeight((short) 500);
        // 设置表头
        sheet.createFreezePane(0, 1);
        cteateCell(wb, sheetRow, (short) 0, "考试名称");
        sheet.setColumnWidth(0, 3500);
        cteateCell(wb, sheetRow, (short) 1, "姓名");
        cteateCell(wb, sheetRow, (short) 2, "学号");
        cteateCell(wb, sheetRow, (short) 3, "总成绩");
        sheet.setColumnWidth(3, 3000);
        cteateCell(wb, sheetRow, (short) 4, "试题");
        cteateCell(wb, sheetRow, (short) 5, "试题总得分");
        sheet.setColumnWidth(5, 4000);
        cteateCell(wb, sheetRow, (short) 6, "岗位架构分数");
        sheet.setColumnWidth(6, 4000);
        cteateCell(wb, sheetRow, (short) 7, "部门架构分数");
        sheet.setColumnWidth(7, 4000);
        cteateCell(wb, sheetRow, (short) 8, "流程设计分数");
        sheet.setColumnWidth(8, 4000);
        cteateCell(wb, sheetRow, (short) 9, "流程执行分数");
        sheet.setColumnWidth(9, 4000);
        cteateCell(wb, sheetRow, (short) 10, "提交时间");
        sheet.setColumnWidth(10, 8000);

        //冻结 两个参数分别代表起始行,冻结行数
        sheet.createFreezePane(0,1);

        List<Long> questionIds;
        wb.setSheetName(0,examSession.getSessionName() + "成绩表");
        if (examSession.getSessionType() == ParaConstant.SESSION_TYPE_TASK) {
            questionIds = Arrays.asList(examSession.getQuestionId());
        } else {
            questionIds = sysExamQuestionMapper.getQuestionsBySessionId(sessionId);
        }
        if (questionIds.isEmpty()) {
            return wb;
        }
        // 题目信息
        Map<Long, SysQuestion> questionMap = new HashMap<>();
        sysQuestionMapper.queryQuestionByQuestionIds(questionIds).forEach(q -> questionMap.put(q.getQuestionId(), q));
        if (questionMap.isEmpty()) {
            return wb;
        }

        // 用户总成绩
        Map<Long, BigDecimal>totalScoreMap = new HashMap<>();
        Map<Long, String> submitStateMap = new HashMap<>();
        Set<Long> userIds = new HashSet<>();
        sysExamUserMapper.queryExamUsersBySessionId(sessionId).forEach(u -> {
            userIds.add((Long)u.get("studentId"));
            totalScoreMap.put((Long)u.get("studentId"), StrUtil.null2BigDecimal(u.get("score")));
            submitStateMap.put((Long)u.get("studentId"), (Integer)u.get("submitState") == 1 ? u.get("submitTime").toString() : "--");
        });
        if (userIds.isEmpty()) {
            return wb;
        }
        // 查询用户信息
        List<SysUser> sysUsers = sysUserMapper.queryUserName(userIds);
        if(sysUsers.isEmpty()){
            return wb;
        }
        // 用户成绩单题成绩明细
        Map<Long, List<SysExamScore>> detailMap = sysExamScoreMapper.queryExamScoreBySessionId(sessionId).stream()
                .sorted(Comparator.comparing(SysExamScore::getUserId)).collect(Collectors.groupingBy(SysExamScore::getUserId));
        if(detailMap.isEmpty()){
            throw new CustomException("没有成绩数据可导出！");
        }
        short row = 1;
        CellRangeAddress cra;
        List<SysExamScore> scoreList;
        Long questionId;
        String questionTitle;
        for (SysUser sysUser : sysUsers) {
            // 题目信息
            scoreList = detailMap.get(sysUser.getUserId());
            //每道题成绩明细
            for (SysExamScore examScore : scoreList) {
                questionId = examScore.getQustionId();
                // 试题名称
                sheetRow = sheet.createRow(row);
                sheetRow.setHeight((short) 500);
                questionTitle = questionMap.get(questionId).getQuestionTitle();
                cteateCell(wb, sheetRow, (short)4, questionTitle);
                setColumnWidth(sheet, 4, questionTitle.length() * 512);
                // 试题总得分
                cteateCell(wb, sheetRow, (short)5, String.valueOf(examScore.getScore()));
                // 岗位组织架构分数
                cteateCell(wb, sheetRow, (short)6, String.valueOf(examScore.getPositionScore()));
                // 部门组织架构分数
                cteateCell(wb, sheetRow, (short)7, String.valueOf(examScore.getOrgScore()));
                // 流程设计分数
                cteateCell(wb, sheetRow, (short)8, String.valueOf(examScore.getProcDesignScore()));
                // 流程执行分数
                cteateCell(wb, sheetRow, (short)9, String.valueOf(examScore.getProcRunScore()));
                row ++;
            }

            // 学生姓名
            sheetRow = sheet.getRow(row - scoreList.size());
            cteateCell(wb, sheetRow, (short)1, sysUser.getUserName());
            // 合并单元格
            cra = new CellRangeAddress(row - scoreList.size(), row - 1, 1, 1);
            sheet.addMergedRegion(cra);
            // 学生姓名
            sheetRow = sheet.getRow(row - scoreList.size());
            cteateCell(wb, sheetRow, (short)2, sysUser.getUserLogin().replace(GlobalConstant.ZBY_USER_PREFIX, ""));
            // 合并单元格
            cra = new CellRangeAddress(row - scoreList.size(), row - 1, 2, 2);
            sheet.addMergedRegion(cra);
            // 只有一道题设置名字适应大小
            if (scoreList.size() == 1) {
                setColumnWidth(sheet, 1, sysUser.getUserName().length() * 512);
            }
            //总成绩成绩
            cteateCell(wb, sheetRow, (short)3, String.valueOf(totalScoreMap.get(sysUser.getUserId())));
            // 合并单元格
            cra = new CellRangeAddress(row - scoreList.size(), row - 1, 3, 3);
            sheet.addMergedRegion(cra);

            // 提交时间
            questionTitle = submitStateMap.get(sysUser.getUserId());
            int subIndex = questionTitle.indexOf(".") ;
            cteateCell(wb, sheetRow, (short)10, subIndex < 0 ? questionTitle : questionTitle.substring(0, subIndex));
            // 合并单元格
            cra = new CellRangeAddress(row - scoreList.size(), row - 1, 10, 10);
            sheet.addMergedRegion(cra);
        }
        // 场次名
        sheetRow = sheet.getRow(1);
        cteateCell(wb, sheetRow, (short)0, examSession.getSessionName());
        cra = new CellRangeAddress(1, row - 1, 0, 0);
        sheet.addMergedRegion(cra);
        return wb;

    }
    /**
    * 设置单元格大小
    * @param sheet
    * @param col
    * @param size
    * @author ousp
    * @date 2020/8/14
    * @return void
    */
    private void setColumnWidth(XSSFSheet sheet, int col, int size) {
        if (sheet.getColumnWidth(col) < size) {
            sheet.setColumnWidth(col, size);
        }
    }

    /**
    * 创建单元格
    * @param wb
    * @param row
    * @param col
    * @param val
    * @author ousp
    * @date 2020/8/14
    * @return void
    */
    private void cteateCell(XSSFWorkbook wb, XSSFRow row, short col, String val) {
        XSSFCell cell = row.createCell(col);
        cell.setCellValue(val);
        XSSFCellStyle cellstyle = wb.createCellStyle();
        cellstyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 创建一个居中格式
        cellstyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //自动换行
        cellstyle.setWrapText(true);
        cell.setCellStyle(cellstyle);
    }

    /**  方法描述
     * @Description 更新试题分值
     * @Author yuyb
     * @Date 16:04 2020/8/26
     * @param examQuestionId
     * @param scoreValue
     * @return void
     **/
    @Override
    public void setQuestionScore(long examQuestionId, int scoreValue, long sessionId) {
        SysExamQuestion sysExamQuestion = sysExamQuestionMapper.selectByPrimaryKey(examQuestionId);
        if(sysExamQuestion == null){
            throw new CustomException("未找到数据！");
        }
        if(sysExamQuestion.getScoreValue().equals(scoreValue)){
            throw new CustomException("分值没变化，不用修改！");
        }
        long questionId = sysExamQuestion.getQustionId();
        Integer existQuestion = sysExamScoreMapper.existQuestionId(sessionId, questionId);
        if(existQuestion != null){
            throw new CustomException("该试题已经被使用，不能再修改分值！");
        }
        SysExamQuestion updateDomain = new SysExamQuestion();
        updateDomain.setId(examQuestionId);
        updateDomain.setScoreValue(scoreValue);
        sysExamQuestionMapper.updateByPrimaryKeySelective(updateDomain);
    }

    /***
    * 查询题目分值
    * @param quetionId
    * @param sessionId
    * @author ousp
    * @date 2020/8/28
    * @return java.lang.Integer
    */
    @Override
    public Integer queryQuestionScore(long quetionId, long sessionId) {
        return sysExamQuestionMapper.queryQuestionScore(quetionId, sessionId);
    }
}
