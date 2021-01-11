package cn.netinnet.educationcenter.controller;

import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.constant.UserConstant;
import cn.netinnet.cloudcommon.dto.ExamInfo;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.globol.ResultEnum;
import cn.netinnet.cloudcommon.utils.UserUtil;
import cn.netinnet.common.base.BaseController;
import cn.netinnet.common.util.httpclient.SessionUtil;
import cn.netinnet.educationcenter.constant.ParaConstant;
import cn.netinnet.educationcenter.domain.SysBatch;
import cn.netinnet.educationcenter.service.SysBatchService;
import cn.netinnet.educationcenter.service.SysUserService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yuyb
 * @date   2020-05-26
 */
@RestController
@RequestMapping("/sysBatch")
public class SysBatchController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SysBatchController.class);

    @Resource
    SysBatchService sysBatchService;
//    @Resource
//    SysExamSessionService sysExamSessionService;
    @Resource
    SysUserService sysUserService;

    /**
     * @Author wangyt
     * @Date 2020/4/10 10:20
     * @Description 查询批次列表
     */
    @RequiresPermissions("sysBatch:view")
    @GetMapping("/queryList")
    public HttpResultEntry queryList(@RequestParam(defaultValue = "") String pageFun,
                                     @RequestParam(required = false)Integer batchStatus,
                                     @RequestParam(required = false)String batchName) {
        PageInfo pageInfo = getPage(() -> sysBatchService.queryList(batchStatus, batchName));
        JSONObject result = new JSONObject();
        result.put("pageInfo", pageInfo);
        result.put("now", new Date());
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, result);
    }

    /**
     * @Author wangyt
     * @Date 2020/4/9 15:59
     * @Description 新增批次
     */
    @RequiresPermissions("sysBatch:add")
//    @LogMark("新增批次")
    @PostMapping("/addBatch")
//    @PreventRepeatSubmit
    public HttpResultEntry addBatch(SysBatch sysBatch) {
        //id不为空/参数为空, 参数异常
        if (sysBatch.getBatchId() != null || StringUtils.isBlank(sysBatch.getBatchName())) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        //时间比对
        if (sysBatch.getBeginDate() != null && sysBatch.getEndDate() != null
                && sysBatch.getBeginDate().after(sysBatch.getEndDate())) {
            return HttpResultEntry.error(GlobalConstant.FAILURE, "开始时间应早于结束时间");
        }
        sysBatchService.addBatch(sysBatch);
        return HttpResultEntry.ok();
    }

    @RequiresPermissions("sysBatch:edit")
//    @LogMark("修改批次")
    @PostMapping("/editBatch")
//    @PreventRepeatSubmit
    public HttpResultEntry editBatch(SysBatch sysBatch) {
        //批次id为空时, 参数异常
        if (sysBatch.getBatchId() == null) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        //时间比对
        if (sysBatch.getBeginDate() != null && sysBatch.getEndDate() != null
                && sysBatch.getBeginDate().after(sysBatch.getEndDate())) {
            return HttpResultEntry.error(GlobalConstant.FAILURE, "开始时间应早于结束时间");
        }
        sysBatchService.editBatch(sysBatch);
        return HttpResultEntry.ok();
    }

    @RequiresPermissions("sysBatch:delete")
//    @LogMark("删除批次")
    @PostMapping("/deleteBatch")
//    @PreventRepeatSubmit
    public HttpResultEntry deleteBatch(Long batchId) {
        //批次id为空时, 参数异常
        if (batchId == null) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        sysBatchService.deleteBatch(batchId);
        return HttpResultEntry.ok();
    }

    /**
     * @Author wangyt
     * @Date 2020/4/9 17:41
     * @Description 开始批次
     */
    @RequiresPermissions("sysBatch:status")
//    @LogMark("开始批次")
    @PostMapping("/startBatch")
//    @PreventRepeatSubmit
    public HttpResultEntry startBatch(Long batchId) {
        //批次id为空时, 参数异常
        if (batchId == null) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        sysBatchService.updateBatchStatus(batchId, ParaConstant.BATCH_STATUS_START);
        return HttpResultEntry.ok();
    }

    /**
     * @Author wangyt
     * @Date 2020/4/9 17:41
     * @Description 暂停批次
     */
    @RequiresPermissions("sysBatch:status")
//    @LogMark("暂停批次")
    @PostMapping("/pauseBatch")
//    @PreventRepeatSubmit
    public HttpResultEntry pauseBatch(Long batchId) {
        //批次id为空时, 参数异常
        if (batchId == null) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        sysBatchService.updateBatchStatus(batchId, ParaConstant.BATCH_STATUS_PAUSE);
        return HttpResultEntry.ok();
    }

    /**
     * @Author wangyt
     * @Date 2020/4/9 17:41
     * @Description 继续批次
     */
    @RequiresPermissions("sysBatch:status")
//    @LogMark("继续批次")
    @PostMapping("/continueBatch")
//    @PreventRepeatSubmit
    public HttpResultEntry continueBatch(Long batchId) {
        //批次id为空时, 参数异常
        if (batchId == null) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        sysBatchService.updateBatchStatus(batchId, ParaConstant.BATCH_STATUS_START);
        return HttpResultEntry.ok();
    }

    /**  方法描述
     * @Description 学生登录获取所在批次
     * @Author yuyb
     * @Date 11:51 2020/6/12
     * @param
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @GetMapping("/studentBatch")
    public HttpResultEntry queryStudentBatch(@RequestParam(required = false)Integer batchStatus){
        UserInfo userInfo = UserUtil.getUser();
        long userId = userInfo.getUserId();
        long schoolId = userInfo.getSchoolId();
        List<Map<String,Object>> batchList = sysBatchService.queryBatchByUserIdAndSchoolId(userId, schoolId, batchStatus);
        if(batchList.isEmpty()){
            return HttpResultEntry.error("您没有进入批次，请联系教师！");
        }
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, batchList);
    }

    /**  方法描述
     * @Description 选择批次
     * @Author yuyb
     * @Date 15:14 2020/6/12
     * @param batchId
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @PostMapping("/chooseBatch")
    public HttpResultEntry chooseBatch(@RequestParam Long batchId){
        SysBatch sysBatch = sysBatchService.selectByPrimaryKey(batchId);
        if(sysBatch.getBeginDate() == null && sysBatch.getBatchStatus() != 1){
            return HttpResultEntry.error("该批次未开始或已被暂停，请联系管理员！");
        }
        Date now = new Date();
        if(sysBatch.getBeginDate() != null && now.before(sysBatch.getBeginDate())){
            return HttpResultEntry.error("该批次尚未开始！");
        }
        if(sysBatch.getEndDate() != null && now.after(sysBatch.getEndDate())){
            return HttpResultEntry.error("该批次已结束！");
        }
        SessionUtil.setSession(GlobalConstant.SESSION_EXAM, new ExamInfo());

        return HttpResultEntry.ok();
    }

    /***
    * 查询批次相关数据信息
    * @param batchId    批次id
    * @author ousp
    * @date 2020/8/26
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
    @GetMapping("/batchDataDetail")
    public HttpResultEntry batchDataDetail(long batchId) {
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, sysBatchService.batchDataDetail(batchId));
    }

    /***
    * 查询用户场次列表信息
    * @param sessionType    场次类型
    * @param batchId        批次id
    * @param userId         用户ID
    * @author ousp @date 2020/8/26
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
    @GetMapping("/queryUserSessionList")
    public HttpResultEntry queryUserSessionList(@RequestParam("sessionType") int sessionType,
                                                @RequestParam("batchId") long batchId,
                                                @RequestParam("userId") long userId,
                                                @RequestParam(value = "sessionStatus", required = false) Integer sessionStatus,
                                                @RequestParam(value = "sessionName", required = false) String sessionName) {
//        todo
//        PageInfo pageInfo = getPage(() -> sysExamSessionService.queryUserSessionList(sessionType, batchId, userId, sessionStatus, sessionName));
        JSONObject result = new JSONObject();
//        result.put("pageInfo", pageInfo);
        result.put("now", new Date());
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, result);
    }

    /**  方法描述
     * @Description 查询教师批次dif
     * @Author yuyb
     * @Date 16:07 2020/10/22
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @GetMapping("/batchList")
    public HttpResultEntry getBatchList(@RequestParam(required = false) String page,
                                        @RequestParam(required = false) String size,
                                        @RequestParam("schoolId") long schoolId,
                                        @RequestParam("userId") long userId,
                                        @RequestParam(required = false)Integer batchStatus,
                                        @RequestParam(required = false)String batchName) {
        UserInfo userInfo = UserUtil.getUser();
        PageInfo pageInfo;
        if (UserConstant.MANAGER == userInfo.getUserType()) {
            // 高校管理员查询该学校下所有
            pageInfo = getPage(() -> sysBatchService.batchList(schoolId, null, batchStatus, batchName));
        } else {
            pageInfo = getPage(() -> sysBatchService.batchList(schoolId, userId, batchStatus, batchName));
        }

        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, pageInfo);
    }


    /***
    * dif用户查询批次列表
    * @author ousp
    * @date 2020/10/22
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
    // todo
//    @GetMapping("/difStudentBatch")
//    public HttpResultEntry difStudentBatch(@RequestParam(required = false)Integer batchStatus) {
//        UserInfo userInfo = UserUtil.getUser();
//        long individualityId = userInfo.getIndividualityId();
//        long schoolId = userInfo.getSchoolId();
//        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG,sysBatchService.difStudentBatch(schoolId, individualityId, batchStatus));
//    }

    /***
    * dif切换批次
    * @author ousp
    * @date 2020/10/22
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
//    todo
//    @PostMapping("/difChooseBatch")
//    public HttpResultEntry difChooseBatch(@RequestParam("batchId") Long batchId, @RequestParam("userId") Long userId){
//        SysBatch sysBatch = sysBatchService.selectByPrimaryKey(batchId);
//        if(sysBatch.getBeginDate() == null && sysBatch.getBatchStatus() != 1){
//            return HttpResultEntry.error("该批次未开始或已被暂停，请联系管理员！");
//        }
//        Date now = new Date();
//        if(sysBatch.getBeginDate() != null && now.before(sysBatch.getBeginDate())){
//            return HttpResultEntry.error("该批次尚未开始！");
//        }
//        if(sysBatch.getEndDate() != null && now.after(sysBatch.getEndDate())){
//            return HttpResultEntry.error("该批次已结束！");
//        }
//        SessionUtil.setSession(GlobalConstant.SESSION_EXAM, new ExamInfo());
//        UserInfo userInfo = UserUtil.getUser();
//        userInfo.setUserId(userId);
//        // 重新设置token
//        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, sysUserService.userInfoSet(userInfo, false));
//    }
}


