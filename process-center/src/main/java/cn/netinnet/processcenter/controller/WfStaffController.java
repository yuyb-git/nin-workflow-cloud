package cn.netinnet.processcenter.controller;

import cn.netinnet.cloudcommon.annotation.LogMark;
import cn.netinnet.cloudcommon.annotation.PreventRepeatSubmit;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.globol.ResultEnum;
import cn.netinnet.cloudcommon.utils.ExcelUtils;
import cn.netinnet.cloudcommon.utils.UserUtil;
import cn.netinnet.common.base.BaseController;
import cn.netinnet.processcenter.domain.WfStaff;
import cn.netinnet.processcenter.service.WfStaffService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuyb
 * @date   2020-04-09
 */
@RestController
@RequestMapping("/wfStaff")
public class WfStaffController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(WfStaffController.class);

    @Resource
    WfStaffService wfStaffService;


    /** 方法描述
     * @description 分页查询员工
     * @param deptId
     * @param searchItem
     * @param page
     * @param limit
     * @return cn.netinnet.common.util.httpclient.ResultEntry
     * @author Caicm
     * @date 2020/4/14 16:30
     */
    @GetMapping("/queryStaffList")
    public HttpResultEntry queryStaffList(@RequestParam(value = "deptId") Long deptId,
                                          @RequestParam(value = "searchItem", required = false) String searchItem,
                                          @RequestParam(value = "page", defaultValue = "1") String page,
                                          @RequestParam(value = "limit", defaultValue = "10") String limit) {

        PageInfo pageInfo = getPage(() -> wfStaffService.queryStaffList(deptId, searchItem));
        String pageFun = "";
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, pageInfo);
    }


    /** 方法描述
     * @description 删除员工
     * @param staffIds
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     * @author Caicm
     * @date 2020/4/20 10:47
     */
    @LogMark("删除员工")
    @PostMapping("/deleteStaff")
    @PreventRepeatSubmit
    public HttpResultEntry deleteStaff(@RequestParam(value = "companyId") Long companyId,
                                       @RequestParam("staffIds") String staffIds) {
        List<Long> staffIdList = JSONArray.parseArray(staffIds, Long.class);
        String msg = wfStaffService.deleteStaff(companyId, staffIdList);
        return HttpResultEntry.ok(msg);
    }

    /** 方法描述
     * @description 编辑员工
     * @param wfStaff
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     * @author Caicm
     * @date 2020/4/14 16:30
     */
    @LogMark("新增编辑员工")
    @PostMapping("/editStaff")
    @PreventRepeatSubmit
    public HttpResultEntry editStaff(@Valid WfStaff wfStaff) {
        UserInfo userInfo = UserUtil.getUser();
        Long userId = userInfo.getUserId();
        wfStaffService.editStaff(wfStaff, userId);
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG);
    }

    /** 方法描述
     * @description 后台管理 - 新增、编辑员工
     * @param wfStaff
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     * @author Caicm
     * @date 2020/4/14 16:30
     */
    @PostMapping("/editSystemStaff")
    @PreventRepeatSubmit
    public HttpResultEntry editSystemStaff(@Valid WfStaff wfStaff) {
        UserInfo userInfo = UserUtil.getUser();
        Long userId = userInfo.getUserId();
        wfStaffService.editStaff(wfStaff, userId);
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG);

    }


    /** 方法描述
     * @description 员工操作（启用、禁用）
     * @param op
     * @param staffId
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     * @author Caicm
     * @date 2020/4/14 16:31
     */
    @PostMapping("/changeStaffEnable")
    public HttpResultEntry changeStaffEnable(@RequestParam("op") String op,
                                             @RequestParam("staffId")Long staffId) {
        wfStaffService.changeStaffEnable(op, staffId);
        return HttpResultEntry.ok();
    }

   /** 方法描述
    * @description 导出员工模板
    * @param companyId
    * @param response
    * @return void
    * @author Caicm
    * @date 2020/4/15 9:24
    */
    @GetMapping("/exportStaffTemplate")
    @PreventRepeatSubmit
    public void exportStaffTemplate(Long companyId, HttpServletResponse response) {
        try {
            response.setHeader("Content-Disposition", "attachment; filename=staffTemplate.xls");
            ServletOutputStream outputStream = response.getOutputStream();
            HSSFWorkbook hssfWorkbook = wfStaffService.exportStaffTemplate(companyId);
            hssfWorkbook.write(outputStream);
            hssfWorkbook.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            LOGGER.error("导出员工模板失败！", e);
        }
    }

    /** 方法描述
     * @description 导入员工
     * @param file
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     * @author Caicm
     * @date 2020/4/15 15:51
     */
    @PostMapping("/importStaff")
    @PreventRepeatSubmit
    public HttpResultEntry importStaff(@RequestParam("file") MultipartFile file, Long companyId) {
        //文件为空时，返回为空提示
        if (file.isEmpty()) {
            return HttpResultEntry.customize(ResultEnum.R_UPLOAD_NULL);
        }
        String originalFileName = file.getOriginalFilename();
        String originalFileNameLowCase = originalFileName.toLowerCase();
        JSONObject data = new JSONObject();
        data.put("fileName", originalFileName);
        if (!(originalFileNameLowCase.endsWith(".xls") || originalFileNameLowCase.endsWith(".xlsx"))) {
            return HttpResultEntry.customize(ResultEnum.R_IS_NOT_XLS, data);
        }
        try {
            InputStream inputStream = file.getInputStream();
            Map<String, String> keyMap = new HashMap<>(8);
            keyMap.put("姓名", "staffName");
            keyMap.put("工号", "jobNumber");
            keyMap.put("部门", "dept");
            keyMap.put("岗位", "position");
            keyMap.put("电话", "tel");
            keyMap.put("邮箱", "email");
            JSONArray jsonArray = ExcelUtils.readExcel4Array(inputStream, originalFileName, keyMap);
            if (jsonArray == null || jsonArray.size() == 0) {
                return HttpResultEntry.customize(ResultEnum.R_FILE_ERR, data);
            }
            UserInfo userInfo = UserUtil.getUser();
            Long userId = userInfo.getUserId();
            return wfStaffService.importStaff(jsonArray, companyId, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultEntry.error(GlobalConstant.FAILURE, e.getMessage());
        }
    }

    /***
    * 查询企业下所有员工
    * @author ousp
    * @date 2020/12/7
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    */
    @GetMapping("/getAllStaffs")
    public HttpResultEntry getAllStaffs(@RequestParam("companyId") long companyId) {
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, wfStaffService.getAllStaffByCompanyId(companyId));
    }
}


