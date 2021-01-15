package cn.netinnet.educationcenter.controller;

import cn.netinnet.cloudcommon.annotation.LogMark;
import cn.netinnet.cloudcommon.annotation.PreventRepeatSubmit;
import cn.netinnet.cloudcommon.annotation.RequiresPermission;
import cn.netinnet.cloudcommon.constant.ErrorMsgConstant;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.constant.ParaConstant;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.globol.ResultEnum;
import cn.netinnet.cloudcommon.utils.UserUtil;
import cn.netinnet.common.base.BaseController;
import cn.netinnet.educationcenter.dao.SysBatchStudentMapper;
import cn.netinnet.educationcenter.domain.SysBatchStudent;
import cn.netinnet.educationcenter.service.SysBatchStudentService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yuyb
 * @date   2020-05-26
 */
@RestController
@RequestMapping("/sysBatchStudent")
public class SysBatchStudentController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SysBatchStudentController.class);

    @Resource
    SysBatchStudentService sysBatchStudentService;
    @Resource
    SysBatchStudentMapper sysBatchStudentMapper;

    /** @Author xiangra
     * @Date 2020/4/7/007 9:09
     * @Description 批次学生班级下拉框
     */
    @GetMapping("/queryBatchClass")
    public HttpResultEntry queryBatchStudentClass(Long batchId) {
        if (batchId == null) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, sysBatchStudentMapper.queryStudentClassByBatch(batchId));
    }

    /** @Author xiangra
     * @Date 2020/4/2/002 15:25
     * @Description 获取批次学生列表
     */
    @RequiresPermission("sysBatchStudent:view")
    @GetMapping("/queryList")
    public HttpResultEntry list(Long batchId, Long classId, String userName, String userLogin,
                                @RequestParam(defaultValue = "") String pageFun) {
        //批次id不能为空
        if(null==batchId){
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        if(StringUtils.isNoneBlank(userName)){
            userName="%" + userName + "%";
        }
        if(StringUtils.isNoneBlank(userLogin)){
            userLogin="%" + userLogin + "%";
        }
        String userNameLike = userName;
        String userLoginLike=userLogin;
        PageInfo pageInfo = getPage(() -> sysBatchStudentMapper.queryBatchStudentList(batchId,classId, userNameLike,userLoginLike));
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, pageInfo);
    }

    /** @Author xiangra
     * @Date 2020/4/7/007 10:08
     * @Description删除批次学生
     */
    @RequiresPermission("sysBatchStudent:delete")
    @LogMark("删除批次学生")
    @PostMapping("/deleteBatchStudent")
    @PreventRepeatSubmit
    public HttpResultEntry deleteBatchStudent(Long batchStudentId) {
        if (batchStudentId == null) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        List<Long> batchStudentList = new ArrayList<>();
        batchStudentList.add(batchStudentId);
        sysBatchStudentService.deleteBatchStudentByIdList(batchStudentList);
        return HttpResultEntry.ok();
    }

    /** @Author xiangra
     * @Date 2020/4/7/007 10:11
     * @Description
     */
    @RequiresPermission("sysBatchStudent:batchDelete")
    @LogMark("批量删除批次学生")
    @PostMapping("/batchDelete")
    @PreventRepeatSubmit
    public HttpResultEntry batchDeleteBatchStudent(String batchStudentIds) {
        if (StringUtils.isBlank(batchStudentIds) || GlobalConstant.EMPTY_LIST.equals(batchStudentIds)) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        List<Long> batchStudentIdList = JSONObject.parseArray(batchStudentIds, Long.class);
        sysBatchStudentService.deleteBatchStudentByIdList(batchStudentIdList);
        return HttpResultEntry.ok();
    }

    /** @Author xiangra
     * @Date 2020/4/7/007 10:23
     * @Description 新增批次学生时：查询对应班级及其学生
     */
    @GetMapping("/queryClassStudent")
    public HttpResultEntry queryAddBatchStudentList(Long batchId, String className,
              @RequestParam(required = false, defaultValue = ParaConstant.PROJECT_CODE_STANDARD)String projectCode) {
        if (batchId == null) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        //获取产品编码
        UserInfo userInfo = UserUtil.getUser();
        List<Map<String, Object>> data = sysBatchStudentService.queryAddBatchStudentList(projectCode, batchId, className, userInfo.getUserId());
        return HttpResultEntry.ok("操作成功！", data);
    }

    /** @Author xiangra
     * @Date 2020/4/7/007 13:25
     * @Description 新增批次学生
     */
    @RequiresPermission("sysBatchStudent:add")
    @LogMark("新增批次学生")
    @PostMapping("/addBatchStudent")
    @PreventRepeatSubmit
    public HttpResultEntry addBatchStudent(Long batchId,String userInfos) {
        if (batchId == null || StringUtils.isBlank(userInfos) || GlobalConstant.EMPTY_LIST.equals(userInfos)) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        try {
            List<SysBatchStudent> userInfoList= JSONObject.parseArray(userInfos, SysBatchStudent.class);
            sysBatchStudentService.addBatchStudent(batchId, userInfoList);
            return HttpResultEntry.ok();
        } catch (Exception e) {
            LOGGER.error("JSON解析失败", e);
            return HttpResultEntry.error(ErrorMsgConstant.JSON_ERR);
        }
    }

}


