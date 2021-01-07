package cn.netinnet.usercenter.controller;

import cn.netinnet.common.util.httpclient.ResultEntry;
import cn.netinnet.workflow.common.annotation.LogMark;
import cn.netinnet.workflow.common.annotation.PreventRepeatSubmit;
import cn.netinnet.workflow.common.base.BaseController;
import cn.netinnet.workflow.common.constant.ErrorMsgConstant;
import cn.netinnet.workflow.common.constant.GlobalConstant;
import cn.netinnet.workflow.common.constant.UserConstant;
import cn.netinnet.workflow.common.global.HttpResultEntry;
import cn.netinnet.workflow.common.global.ResultEnum;
import cn.netinnet.workflow.sys.dao.SysUserMapper;
import cn.netinnet.workflow.sys.domain.SysUser;
import cn.netinnet.workflow.sys.service.SysUserService;
import cn.netinnet.workflow.sys.util.UserUtil;
import cn.netinnet.workflow.util.ExcelUtils;
import cn.netinnet.workflow.util.FileUtil;
import cn.netinnet.workflow.util.PageUtils;
import cn.netinnet.workflow.util.StringUtilForFile;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* @Author: Linjj
 * \* @Date: 2019/7/5 17:29
 * \* @Description: 学生用户控制器
 * \
 */
@RestController
@RequestMapping("/student")
public class StudentController extends BaseController {
    private final static Logger LOG = LoggerFactory.getLogger(StudentController.class);

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * @Author Linjj
     * @Date 2019/7/8 16:53
     * @Description 新增学生
     */
    @RequiresPermissions("student:add")
    @LogMark("新增学生")
    @PostMapping("/addStudent")
    @PreventRepeatSubmit
    public HttpResultEntry addStudent(@Valid SysUser user) {
        // 学生新增特有校验-学院/系/班级的id和name不可为空
        if (user.getCollegeId() == null || StringUtils.isBlank(user.getCollegeName()) ||
                user.getMajorId() == null || StringUtils.isBlank(user.getMajorName()) ||
                user.getClassId() == null || StringUtils.isBlank(user.getClassName())){
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        // 新增账号不可为空
        if (StringUtils.isBlank(user.getUserLogin())) {
            return HttpResultEntry.error(ErrorMsgConstant.USER_LOGIN_NULL);
        }
        return sysUserService.add(user, UserConstant.STUDENT);
    }

    /**
     * @Author Linjj
     * @Date 2019/7/8 16:53
     * @Description 编辑学生
     */
    @RequiresPermissions("student:edit")
    @LogMark("编辑学生")
    @PostMapping("/editStudent")
    public HttpResultEntry editStudent(@Valid SysUser user) {
        // 用户id不可为空
        if (user.getUserId() == null) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        return sysUserService.edit(user, UserConstant.STUDENT);
    }

    /**
     * @Author Linjj
     * @Date 2019/7/8 17:11
     * @Description 查询学生详情
     */
    @GetMapping("/queryDetail")
    public HttpResultEntry queryDetail(Long userId) {
        if (userId == null) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        SysUser user = sysUserService.selectById(userId);
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, user);
    }

    /**
     * @Author Linjj
     * @Date 2019/7/8 17:30
     * @Description 查询学生列表
     */
    @RequiresPermissions("student:view")
    @GetMapping("/queryList")
    public ResultEntry queryList(SysUser sysUser, @RequestParam(defaultValue = "") String pageFun) {
        Long schoolId = UserUtil.getUser().getSchoolId();
        sysUser.setUserType(UserConstant.STUDENT);
        sysUser.setSchoolId(schoolId);
        if (StringUtils.isNotBlank(sysUser.getUserLogin())) {
            sysUser.setUserLogin("%" + sysUser.getUserLogin() + "%");
        }
        if (StringUtils.isNotBlank(sysUser.getUserName())) {
            sysUser.setUserName("%" + sysUser.getUserName() + "%");
        }
        PageInfo pageInfo = getPage(() -> sysUserMapper.queryList(sysUser));
        return PageUtils.returnPageInfo(pageFun, pageInfo);
    }

    /**
     * @Author Linjj
     * @Date 2019/7/8 17:38
     * @Description 删除学生
     */
    @RequiresPermissions("student:delete")
    @LogMark("删除学生")
    @PostMapping("/deleteStudent")
    public HttpResultEntry deleteStudent(Long userId) {
        if (userId == null) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        List<Long> userIdList = new ArrayList<>();
        userIdList.add(userId);
        return sysUserService.logicDeleteByIdList(userIdList);
    }

    /**
     * @Author Linjj
     * @Date 2019/7/8 17:51
     * @Description 批量删除学生
     */
    @RequiresPermissions("student:batchDelete")
    @LogMark("批量删除学生")
    @PostMapping("/batchDelete")
    public HttpResultEntry batchDelete(String userIds) {
        if (StringUtils.isBlank(userIds) || GlobalConstant.EMPTY_LIST.equals(userIds)) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        List<Long> userIdList = JSON.parseArray(userIds, Long.class);
        return sysUserService.logicDeleteByIdList(userIdList);
    }

    /**
     * @Author Linjj
     * @Date 2019/7/10 14:51
     * @Description 下载学生模板
     */
    @GetMapping("/downloadExcel")
    public void downloadExcel(HttpServletResponse response) {
        // 获取文件的路径
        String fileUri = "/cdn/static/template/template_class_student.xls";
        String fileUriFull = StringUtilForFile.getAbsolutePath(fileUri);
        // 下载Excel模板
        FileUtil.downloadFile(response, new File(fileUriFull));
    }

    /**
     * @Author Linjj
     * @Date 2019/7/10 15:36
     * @Description 学生导入
     */
    @RequiresPermissions("student:batchAdd")
    @LogMark("学生导入")
    @PostMapping("/importStudent")
    @PreventRepeatSubmit
    public HttpResultEntry importStudent(SysUser sysUser, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return HttpResultEntry.customize(ResultEnum.R_UPLOAD_NULL);
        }
        if (sysUser.getCollegeId() == null || sysUser.getCollegeName() == null || sysUser.getMajorId() == null ||
                sysUser.getMajorName() == null || sysUser.getClassId() == null || sysUser.getClassName() == null) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        String originalFilename = file.getOriginalFilename();
        String originalFilenametoLowerCase = originalFilename.toLowerCase();
        // 校验格式
        if (!(originalFilenametoLowerCase.endsWith(".xls") || originalFilenametoLowerCase.endsWith(".xlsx"))) {
            return HttpResultEntry.error(ErrorMsgConstant.EXCEL_FORMAT);
        }
        try {
            InputStream inputStream = file.getInputStream();
            Map<String, String> keyMap = new HashMap<>(8, 1);
            keyMap.put("序号", "seq");
            keyMap.put("姓名", "userName");
            keyMap.put("性别", "sex");
            keyMap.put("账号", "login");
            keyMap.put("密码", "password");
            JSONArray list = ExcelUtils.readExcel4Array(inputStream, originalFilename, keyMap);
            if (list == null || list.isEmpty()) {
                return HttpResultEntry.error(ErrorMsgConstant.EXCEL_TEMPLATE_ERR);
            }
            if (list.size() > 1000) {
                return HttpResultEntry.error(ErrorMsgConstant.EXCEL_OUT_SIZE);
            }
            return sysUserService.importStudent(sysUser, list);
        } catch (IOException e) {
            LOG.error("文件读取失败->", e);
            return HttpResultEntry.error(GlobalConstant.FAILURE, e.getMessage());
        }
    }
}