package cn.netinnet.ninzuul.controller;

import cn.netinnet.ninzuul.dao.SysRoleMapper;
import cn.netinnet.ninzuul.domain.SysRole;
import cn.netinnet.ninzuul.service.SysRoleService;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.globol.ResultEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author CodeGenerator
 * @date   2019-08-08
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {
    private final static Logger LOG = LoggerFactory.getLogger(SysRoleController.class);

    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysRoleMapper sysRoleMapper;

    /**
     * @Author Linjj
     * @Date 2019/8/8 16:01
     * @Description 角色列表
     */
    @RequiresPermissions("sysRole:view")
    @GetMapping("/queryList")
    public HttpResultEntry queryList(SysRole sysRole, @RequestParam(defaultValue = "") String pageFun) {
        if (StringUtils.isNotBlank(sysRole.getRoleName())) {
            sysRole.setRoleName("%" + sysRole.getRoleName() + "%");
        }
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, sysRoleMapper.queryList(sysRole));
    }

    /**
     * @Author Linjj
     * @Date 2019/8/8 17:02
     * @Description 新增角色
     */
    @RequiresPermissions("sysRole:add")
    @PostMapping("/addRole")
    public HttpResultEntry addRole(@Valid SysRole role, String rolePermissionJson) {
        // 校验code是否已存在
        int num = sysRoleMapper.countRoleCodeAndId(role.getRoleCode(), null);
        if (num > 0) {
            return HttpResultEntry.error("已存在该角色编码，请重新填写");
        }
        return sysRoleService.addRole(role, rolePermissionJson);
    }

    /**
     * @Author Linjj
     * @Date 2019/8/8 17:16
     * @Description 编辑角色
     */
    @RequiresPermissions("sysRole:edit")
    @PostMapping("/editRole")
    public HttpResultEntry editRole(@Valid SysRole role, String rolePermissionJson) {
        Long roleId = role.getRoleId();
        // id不可为空
        if (roleId == null || roleId <= 0L) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        // 校验code是否已存在(排除自身那条)
        int num = sysRoleMapper.countRoleCodeAndId(role.getRoleCode(), roleId);
        if (num > 0) {
            return HttpResultEntry.error("已存在该角色编码，请重新填写");
        }
        return sysRoleService.editRole(role, rolePermissionJson);
    }

    /**
     * @Author Linjj
     * @Date 2019/8/8 17:21
     * @Description 删除角色(逻辑删除)
     */
    @RequiresPermissions("sysAdmin:delete")
    @PostMapping("/deleteRole")
    public HttpResultEntry deleteRole(Long roleId) {
        // id不可为空
        if (roleId == null){
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        return sysRoleService.deleteRole(roleId);
    }

    /**
     * @Author Linjj
     * @Date 2019/8/8 17:29
     * @Description 批量删除角色(逻辑删除)
     */
    @RequiresPermissions("sysRole:batchDelete")
    @PostMapping("/batchDeleteRole")
    public HttpResultEntry batchDeleteRole(String roleIds) {
        // ids不可为空
        if (StringUtils.isBlank(roleIds) || GlobalConstant.EMPTY_LIST.equals(roleIds)){
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        List<Long> roleIdList = JSON.parseArray(roleIds, Long.class);
        return sysRoleService.batchDeleteRole(roleIdList);
    }

    /**
     * @Author Linjj
     * @Date 2019/8/9 15:34
     * @Description 查询所有角色code和name
     */
    @GetMapping("queryCodeName")
    public HttpResultEntry queryCodeName() {
        List<SysRole> allRoleList = sysRoleMapper.queryAllCodeName();
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, allRoleList);
    }

    /**
     * @Author Linjj
     * @Date 2019/10/21 17:24
     * @Description 查询对应角色的权限列表（包含权限的选择状态）
     */
    @GetMapping("queryPermission")
    public HttpResultEntry queryPermission(Long roleId, String projectType) {
        if (roleId == null || projectType == null) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        List<Integer> projectTypes = JSONArray.parseArray(projectType, Integer.class);
        return sysRoleService.queryPermission(roleId, projectTypes);
    }

}


