package cn.netinnet.processcenter.controller;

import cn.netinnet.cloudcommon.annotation.LogMark;
import cn.netinnet.cloudcommon.annotation.PreventRepeatSubmit;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.utils.UserUtil;
import cn.netinnet.common.base.BaseController;
import cn.netinnet.processcenter.domain.WfDept;
import cn.netinnet.processcenter.service.WfDeptService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author yuyb
 * @date   2020-04-09
 */
@RestController
@RequestMapping("/wfDept")
public class WfDeptController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(WfDeptController.class);

    @Resource
    private WfDeptService wfDeptService;

    /**  方法描述
     * @Description 查看部门组织-答案对比
     * @Author yuyb
     * @Date 13:34 2020/8/31
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @GetMapping("/getDeptTreeResult")
    public HttpResultEntry getDeptTreeResult(Long companyId, String companyName,
                                             Long sessionId, Long questionId, Long userId){
        //Long examId = sysExamUserMapper.getExamId(userId, sessionId);
        JSONObject result = new JSONObject();
        //Long stuCompanyId = wfCompanyMapper.getStuCompanyIdByAnswer(examId, questionId, companyName);
        /*Map<String, Object> ansMap = wfDeptService.getDeptTree(companyId, false);
        result.put("ansMap", ansMap);
        if(stuCompanyId == null){
            result.put("stuMap", null);
            return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, result);
        }*/
        /*Map<String, Object> stuMap = wfDeptService.getDeptTree(stuCompanyId, true);
        result.put("stuMap", stuMap);*/
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, result);
    }

    /** 方法描述
     * @description 编辑组织
     * @param wfDept
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     * @author Caicm
     * @date 2020/4/14 9:45
     */
    @LogMark("编辑部门")
    @PostMapping("/editDept")
    @PreventRepeatSubmit
    public HttpResultEntry editDept(@Valid WfDept wfDept, HttpSession session) {
        UserInfo userInfo = UserUtil.getUser();
        Long userId = userInfo.getUserId();
        wfDeptService.editDept(wfDept, userId);
        return HttpResultEntry.ok();
    }


    /** 方法描述
     * @description 删除部门
     * @param deptId
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     * @author Caicm
     * @date 2020/4/14 10:45
     */
    @LogMark("删除部门")
    @PostMapping("/deleteDept")
    @PreventRepeatSubmit
    public HttpResultEntry deleteDept(@RequestParam("deptId") Long deptId) {
        wfDeptService.deleteDept(deptId);
        return HttpResultEntry.ok();
    }

}


