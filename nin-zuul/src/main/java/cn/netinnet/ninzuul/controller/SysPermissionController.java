package cn.netinnet.ninzuul.controller;

import cn.netinnet.ninzuul.dao.SysPermissionMapper;
import cn.netinnet.ninzuul.dao.SysRolePermissionMapper;
import cn.netinnet.ninzuul.domain.SysPermission;
import cn.netinnet.ninzuul.service.SysPermissionService;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.globol.ResultEnum;
import cn.netinnet.cloudcommon.utils.UserUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author CodeGenerator
 * @date 2019-10-11
 */
@RestController
@RequestMapping("/sysPermission")
public class SysPermissionController{
    private final static Logger LOG = LoggerFactory.getLogger(SysPermissionController.class);

    @Resource
    private SysPermissionService sysPermissionService;
    @Resource
    private SysPermissionMapper sysPermissionMapper;
    @Resource
    SysRolePermissionMapper sysRolePermissionMapper;

    /**
     * @Author Linjj
     * @Date 2019/10/11 9:59
     * @Description 获取用户的路由权限表
     */
    @GetMapping("/getRouters")
    public HttpResultEntry getRouters(Integer projectType) {
        //获取该用户的路由列表
        String routers =sysPermissionService.getRouters(null, projectType);
        //获取该用户的持有权限编码列表
        UserInfo userInfo = UserUtil.getUser();
        // 目前用户与角色设计是一对一的，直接用roleCode查
        String roleCode = userInfo.getRoleCode();
        if(StringUtils.isBlank(roleCode)){
            return HttpResultEntry.error("该用户角色不存在");
        }
        List<String> rolePermission = sysRolePermissionMapper.queryPermissionByRoleCode(roleCode);
        Set<String> permissionSet = rolePermission.stream()
                // 过滤空字符的权限
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toSet());
        Map<String, Object> result = new HashMap<>(4);
        result.put("routers",routers);
        result.put("permissionSet",permissionSet);
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, result);
    }

    /**
     * @Author Linjj
     * @Date 2019/10/15 9:34
     * @Description 新增权限
     */
    @RequiresPermissions("permission:add")
    @PostMapping("/addPermission")
    public HttpResultEntry addPermission(@Valid SysPermission permission) {
        String permissionCode = permission.getPermissionCode();
        // id需要为空
        if (permission.getPermissionId() != null) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        // 权限code非空时，校验权限code是否已存在
        if (StringUtils.isNotBlank(permissionCode)) {
            int num = sysPermissionMapper.countPermissionByCondition(permissionCode, null, null);
            if (num > 0) {
                return HttpResultEntry.error("已存在该权限编码，请重新填写");
            }
        }
        return sysPermissionService.addEditPermission(permission);
    }

    /**
     * @Author Linjj
     * @Date 2019/10/15 9:34
     * @Description 编辑权限
     */
    @RequiresPermissions("permission:edit")
    @PostMapping("/editPermission")
    public HttpResultEntry editPermission(@Valid SysPermission permission) {
        Long permissionId = permission.getPermissionId();
        String permissionCode = permission.getPermissionCode();
        // id不可为空
        if (permissionId == null || permissionId <= 0L) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        // 权限code非空时，校验权限code是否已存在(排除自身)
        if (StringUtils.isNotBlank(permissionCode)) {
            int num = sysPermissionMapper.countPermissionByCondition(permissionCode, permissionId, null);
            if (num > 0) {
                return HttpResultEntry.error("已存在该权限编码，请重新填写");
            }
        }
        return sysPermissionService.addEditPermission(permission);
    }

    /**
     * @Author Linjj
     * @Date 2019/10/15 9:34
     * @Description 删除权限(逻辑删除)
     */
    @RequiresPermissions("permission:delete")
    @PostMapping("/deletePermission")
    public HttpResultEntry deletePermission(Long permissionId) {
        // id不可为空
        if (permissionId == null) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        return sysPermissionService.deletePermission(permissionId);
    }

    /**
     * @Author Linjj
     * @Date 2019/10/15 10:53
     * @Description 可选上级权限列表查询
     */
    @GetMapping("/queryParentPermissions")
    public HttpResultEntry queryParentPermissions(Integer projectType, Long permissionId) {
        return sysPermissionService.queryParentPermissions(projectType, permissionId);
    }

    /**
     * @Author Linjj
     * @Date 2019/10/15 13:37
     * @Description 后台查询权限列表树
     */
    @RequiresPermissions("permission:view")
    @GetMapping("/queryPermissionTree")
    public HttpResultEntry queryPermissionTree(Integer projectType) {
        return sysPermissionService.queryPermissionTree(projectType);
    }

    @GetMapping("/getPermissionByRoleCode")
    public List<String> getPermissionByRoleCode(@RequestParam("roleCode") String roleCode){
        return sysPermissionService.getPermissionByRoleCode(roleCode);
    }
}


