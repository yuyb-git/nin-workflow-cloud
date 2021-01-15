package cn.netinnet.processcenter.controller;

import cn.netinnet.cloudcommon.annotation.LogMark;
import cn.netinnet.cloudcommon.annotation.PreventRepeatSubmit;
import cn.netinnet.cloudcommon.annotation.RequiresPermission;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.constant.ParaConstant;
import cn.netinnet.cloudcommon.constant.UserConstant;
import cn.netinnet.cloudcommon.dto.ExamInfo;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.utils.UserUtil;
import cn.netinnet.common.base.BaseController;
import cn.netinnet.common.util.httpclient.SessionUtil;
import cn.netinnet.processcenter.domain.WfCompany;
import cn.netinnet.processcenter.service.WfCompanyService;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yuyb
 * @date   2020-04-10
 */
@RestController
@RequestMapping("/wfCompany")
public class WfCompanyController extends BaseController {

    @Resource
    WfCompanyService wfCompanyService;

    /**
    * @description 学生新增企业
    * @param  companyName 企业名称
    * @author ousp
    * @date 2020/4/14 
    * @return cn.netinnet.workflow.common.global.HttpResultEntry  
    */
    @LogMark("前台新增企业")
    @PostMapping("/add")
    @PreventRepeatSubmit
    public HttpResultEntry add(@RequestParam String companyName,
                               HttpSession session) {
        long templateId = GlobalConstant.DEF_FORM_TEMPLATE;
        ExamInfo examInfo = UserUtil.getExamInfo(session);
        long questionId = examInfo.getQuestionId();
        long examId =examInfo.getExamId();
        Integer companyCategory = UserUtil.getUser().getUserType() != UserConstant.STUDENT ? ParaConstant.COMPANY_ANSWER : ParaConstant.COMPANY_STUDENT;
        wfCompanyService.addCompany(companyName, companyCategory, "", templateId, examId, questionId);
        return HttpResultEntry.ok();
    }

    /**
     * @description 后台新增企业
     * @param  companyName 企业名称
     * @author ousp
     * @date 2020/4/14
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     */
    @RequiresPermission("company:add")
    @LogMark("后台新增企业")
    @PostMapping("/addSystemCompany")
    @PreventRepeatSubmit
    public HttpResultEntry addSystemCompany(@RequestParam String companyName,
                               @RequestParam(required = false) String remarks,
                               @RequestParam(required = false) Long templateId) {
        Integer companyCategory = UserUtil.getUser().getUserType() == UserConstant.TEACHER ? ParaConstant.COMPANY_TEACHER : ParaConstant.COMPANY_SYSTEM;
        wfCompanyService.addCompany(companyName, companyCategory, remarks, templateId, 0L, 0L);
        return HttpResultEntry.ok();
    }

    @RequiresPermission("company:edit")
    @LogMark("后台编辑企业")
    @PostMapping("/editSystemCompany")
    @PreventRepeatSubmit
    public HttpResultEntry editSystemCompany(@RequestParam Long  companyId,
                                             @RequestParam String companyName,
                                             @RequestParam(required = false) String remarks,
                                             @RequestParam(required = false) Long templateId){
        wfCompanyService.editCompany(0L, companyId, companyName, remarks, templateId);
        return HttpResultEntry.ok();
    }


    /**  方法描述
     * @Description 选择企业
     * @Author yuyb
     * @Date 10:00 2020/4/27
     * @param companyId
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @PostMapping("/chooseCompany")
    public HttpResultEntry chooseCompany(@RequestParam long companyId){
        /*WfCompany wfCompany = wfCompanyService.selectById(companyId);
        if(wfCompany == null){
            return HttpResultEntry.error("未找到该企业！");
        }*/
        SessionUtil.setSession(GlobalConstant.SESSION_COMPANYID, companyId);
        return HttpResultEntry.ok();
    }

    /**
    * @description 学生查询企业列表
    * @param companyName 企业名称
    * @param pageFun
    * @param session
    * @author ousp
    * @date 2020/4/14
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
    @GetMapping("/list")
    public HttpResultEntry list(@RequestParam(required = false) String companyName,
                                @RequestParam(required = false) Integer page,
                                @RequestParam(required = false) String pageFun,
                                @RequestParam(required = false) Long questionId,
                                HttpSession session) {
        PageInfo pageInfo;
        ExamInfo examInfo = UserUtil.getExamInfo(session);
        if (questionId != null) {
            //考试做题模式
            pageInfo = getPage(() -> wfCompanyService.seachCompanyListInQuestion(examInfo.getExamId(), questionId, companyName));
        } else {
            //练习模式
            pageInfo = getPage(() -> wfCompanyService.seachCompanyList(ParaConstant.COMPANY_STUDENT, companyName, 0, examInfo.getExamId()));
        }
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, pageInfo);
    }

    /**
    * 后台获取企业列表
    * @param enable
    * @param companyName
    * @param page
    * @param pageFun
    * @author ousp
    * @date 2020/5/22
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
    @RequiresPermission("company:view")
    @GetMapping("/sysList")
    public HttpResultEntry sysList(@RequestParam(value = "enable", required = false) Integer enable,
                                   @RequestParam(required = false) String companyName,
                                   @RequestParam(required = false) Integer page,
                                   @RequestParam(required = false) String pageFun) {
        Integer companyCategory = UserUtil.getUser().getUserType() == UserConstant.TEACHER ? ParaConstant.COMPANY_TEACHER : ParaConstant.COMPANY_SYSTEM;
        PageInfo pageInfo = getPage(() -> wfCompanyService.seachCompanyList(companyCategory, companyName, enable, 0));
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, pageInfo);
    }

    /**  方法描述
     * @Description 查询我的企业列表,用于首页下拉框企业列表
     * @Author yuyb 
     * @Date 2020/4/27
     * @param 
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @GetMapping("/myList")
    public HttpResultEntry myList(@RequestParam(required = false) Long questionId,
                                  HttpSession session){
        ExamInfo examInfo = UserUtil.getExamInfo(session);
        return HttpResultEntry.ok("操作成功", wfCompanyService.getMyCompanyList(questionId == null ? 0L : questionId, examInfo.getExamId()));
    }

    /**
    * @description 学生删除企业
    * @param  companyId     企业id
    * @author ousp
    * @date 2020/4/14
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
    @LogMark("学生删除企业")
    @PostMapping("/delCompany")
    @PreventRepeatSubmit
    public HttpResultEntry delCompany(@RequestParam long companyId,
                                      HttpSession session) {
        wfCompanyService.deleteCompanyData(Collections.singletonList(companyId));
        UserInfo userInfo = UserUtil.getUser();
        return HttpResultEntry.ok();
    }

    /**  方法描述
     * @Description 后台删除企业
     * @Author yuyb
     * @Date 10:15 2020/6/5
     * @param companyId
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @RequiresPermission("company:delete")
    @LogMark("后台删除企业")
    @PostMapping("/delete")
    @PreventRepeatSubmit
    public HttpResultEntry delete(@RequestParam long companyId) {
        wfCompanyService.deleteCompanyData(Collections.singletonList(companyId));
        return HttpResultEntry.ok();
    }
    
    /** 方法描述
     * @description 批量删除企业
     * @param companyIds
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     * @author Caicm
     * @date 2020/5/26 10:40
     */
    @RequiresPermission("company:batchDelete")
    @LogMark("批量删除企业")
    @PostMapping("/batchDelete")
    @PreventRepeatSubmit
    public HttpResultEntry batchDelete(@RequestParam("companyIds")String companyIds){
        List<Long> companyIdList = JSONArray.parseArray(companyIds).toJavaList(Long.class);
        wfCompanyService.deleteCompanyData(companyIdList);
        return HttpResultEntry.ok();
    }

    /** 方法描述
     * @description 获取企业下拉框数据-用于新增试题时指定企业-查询所有系统企业
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     * @author Caicm
     * @date 2020/5/14 10:28
     */ 
    @GetMapping("/queryCompanyOptions")
    public HttpResultEntry queryCompanyOptions(Integer questionCategory) {
        UserInfo userInfo = UserUtil.getUser();
        List<WfCompany> companyOptions = wfCompanyService.queryCompanyOptions(questionCategory, userInfo.getUserId());
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, companyOptions);
    }

    /** 方法描述
     * @description 启用企业
     * @param companyId
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     * @author Caicm
     * @date 2020/5/26 10:09
     */
    @RequiresPermission("company:active")
    @PostMapping("/enableCompany")
    public HttpResultEntry  enableCompany(@RequestParam("companyId")Long companyId){
        List<Long> companyIdList = new ArrayList<>();
        companyIdList.add(companyId);
        wfCompanyService.changeCompanyStatus(companyIdList, "on");
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG);
    }

    /** 方法描述
     * @description 禁用企业
     * @param companyId
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     * @author Caicm
     * @date 2020/5/26 10:09
     */
    @RequiresPermission("company:forbidden")
    @PostMapping("/disableCompany")
    public HttpResultEntry  disableCompany(@RequestParam("companyId")Long companyId){
        List<Long> companyIdList = new ArrayList<>();
        companyIdList.add(companyId);
        /*List<String> companyInQuestion = sysQuestionService.checkCompanyInQuestion(companyIdList);
        if (companyInQuestion != null && !companyInQuestion.isEmpty()) {
            return HttpResultEntry.error("企业【" + String.join(",", companyInQuestion) + "】已在题目中使用，不可禁用！");
        }*/
        wfCompanyService.changeCompanyStatus(companyIdList, "off");
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG);
    }

    /** 方法描述
     * @description 批量启用企业
     * @param companyIds
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     * @author Caicm
     * @date 2020/5/26 10:09
     */
    @RequiresPermission("company:batchActive")
    @PostMapping("/batchEnableCompany")
    public HttpResultEntry  batchEnableCompany(@RequestParam("companyIds")String companyIds){
        List<Long> companyIdList = JSONArray.parseArray(companyIds, Long.class);
        wfCompanyService.changeCompanyStatus(companyIdList, "on");
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG);
    }

    /** 方法描述
     * @description 批量禁用企业
     * @param companyIds
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     * @author Caicm
     * @date 2020/6/3 17:32
     */
    @RequiresPermission("company:batchForbidden")
    @PostMapping("/batchDisableCompany")
    public HttpResultEntry  batchDisableCompany(@RequestParam("companyIds")String companyIds){
        List<Long> companyIdList = JSONArray.parseArray(companyIds, Long.class);
        /*List<String> companyInQuestion = sysQuestionService.checkCompanyInQuestion(companyIdList);
        if (companyInQuestion != null && !companyInQuestion.isEmpty()) {
            return HttpResultEntry.error("企业【" + String.join(",", companyInQuestion) + "】已在题目中使用，不可禁用！");
        }*/
        wfCompanyService.changeCompanyStatus(companyIdList, "off");
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG);
    }

    /**  方法描述
     * @Description 查询不指定企业的试题的答案企业列表
     * @Author yuyb
     * @Date 10:41 2020/8/31
     * @param questionId
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @GetMapping("/ansList")
    public HttpResultEntry ansList(@RequestParam Long questionId){
        return HttpResultEntry.ok("操作成功", wfCompanyService.getAnsCompanyList(questionId));
    }

}

