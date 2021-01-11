package cn.netinnet.ninzuul.service.impl;

import cn.netinnet.cloudcommon.constant.CacheConstant;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.utils.JsonValidator;
import cn.netinnet.cloudcommon.utils.RedisUtil;
import cn.netinnet.cloudcommon.utils.UserUtil;
import cn.netinnet.common.base.BaseService;
import cn.netinnet.common.util.DateUtil;
import cn.netinnet.ninzuul.dao.SysPermissionMapper;
import cn.netinnet.ninzuul.dao.SysRoleMapper;
import cn.netinnet.ninzuul.dao.SysRolePermissionMapper;
import cn.netinnet.ninzuul.domain.SysPermission;
import cn.netinnet.ninzuul.domain.SysRole;
import cn.netinnet.ninzuul.domain.SysRolePermission;
import cn.netinnet.ninzuul.service.SysRoleService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author yuyb
 * @date   2020-04-03
 */
@Service
public class SysRoleServiceImpl extends BaseService<SysRole> implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysPermissionMapper sysPermissionMapper;
    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;
    @Resource
    private SysRoleService sysRoleService;

    /** 方法描述
     * @description 查询对应角色的权限列表（包含权限的选择状态）
     * @param roleId
     * @param projectTypes
     * @return [roleId, projectType]
     * @author wanghy
     * @date 2020/4/7 16:07
     */
    @Override
    public HttpResultEntry queryPermission(Long roleId, List<Integer> projectTypes) {
        // 查询对应项目类型的所有权限列表
        List<SysPermission> allPermissions = sysPermissionMapper.queryAllPermission(projectTypes);
        Map<Integer, List<SysPermission>> permissionsMap = allPermissions.stream()
                .collect(Collectors.groupingBy(SysPermission :: getProjectType));
        // 查询当前角色拥有的权限id列表
        List<Long> rolePermIds = sysRolePermissionMapper.queryPermissionIdsByRoleId(roleId);
        Set<Long> rolePermIdSet = new HashSet<>(rolePermIds);
        // 拼接最终返回的结果
        Map<Integer, Object> result = new HashMap<>(4);
        Map<String, Object> item;
        for(Integer ptojectType : permissionsMap.keySet()){
            List<SysPermission> permissions = permissionsMap.get(ptojectType);
            if(permissions.isEmpty()){
                continue;
            }
            List<Map<String, Object>> itemList = new ArrayList<>();
            result.put(ptojectType, itemList);
            for (SysPermission permission : permissions) {
                Long permissionId = permission.getPermissionId();
                int ifActive = (rolePermIdSet.contains(permissionId)) ? 1 : 0;
                item = new HashMap<>(16);
                item.put("permissionId", permissionId);
                item.put("parentId", permission.getParentId());
                item.put("permissionType", permission.getPermissionType());
                item.put("permissionCode", permission.getPermissionCode());
                item.put("permissionName", permission.getPermissionName());
                item.put("ifActive", ifActive);
                itemList.add(item);
            }
        }
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, result);
    }

    /** 方法描述
     * @description 批量删除角色
     * @param roleIds
     * @return [roleIds]
     * @author wanghy
     * @date 2020/4/7 16:22
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResultEntry batchDeleteRole(List<Long> roleIds) {
        Date now = new Date();
        Long userId = UserUtil.getUser().getUserId();
        // 批量逻辑删除的角色
        List<SysRole> logicDeleteRoles = new ArrayList<>();
        for (Long roleId : roleIds) {
            SysRole logicDeleteRole = new SysRole();
            logicDeleteRole.setRoleId(roleId);
            logicDeleteRole.setDelFlag(1);
            logicDeleteRole.setModifyUserId(userId);
            logicDeleteRole.setModifyTime(now);
            logicDeleteRoles.add(logicDeleteRole);
        }
        sysRoleService.batchUpdateByPrimaryKey(logicDeleteRoles);
        // 逻辑删除对应的角色权限
        sysRolePermissionMapper.logicDeleteByRoleIds(roleIds, userId);
        return HttpResultEntry.ok();
    }

    /** 方法描述
     * @description 删除角色
     * @param roleId
     * @return [roleId]
     * @author wanghy
     * @date 2020/4/7 16:25
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResultEntry deleteRole(Long roleId) {
        long userId = UserUtil.getUser().getUserId();
        // 逻辑删除角色
        SysRole logicDeleteRole = new SysRole();
        logicDeleteRole.setRoleId(roleId);
        logicDeleteRole.setDelFlag(1);
        sysRoleService.updateByPrimaryKeySelective(logicDeleteRole, userId);
        // 逻辑删除角色权限
        sysRolePermissionMapper.logicDeleteByRoleId(roleId, userId);
        return HttpResultEntry.ok();
    }


    /** 方法描述
     * @description 编辑角色
     * @param sysRole
     * @param rolePermissionJson
     * @return [sysRole, rolePermissionJson]
     * @author wanghy
     * @date 2020/4/7 16:38
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResultEntry editRole(SysRole sysRole, String rolePermissionJson) {
        long userId = UserUtil.getUser().getUserId();
        Date now = new Date();
        long roleId = sysRole.getRoleId();
        String roleCode = sysRole.getRoleCode();
        // 编辑的角色数据
        SysRole updateRole = new SysRole();
        updateRole.setRoleId(roleId);
        updateRole.setRoleCode(roleCode);
        updateRole.setRoleName(sysRole.getRoleName());
        updateRole.setRoleDescr(sysRole.getRoleDescr());
        // 校验json串内容，非正常则置为"[]"
        if (StringUtils.isBlank(rolePermissionJson)
                || !JsonValidator.getJsonValidatorInstance().validate(rolePermissionJson)) {
            rolePermissionJson = "[]";
        }
        // 生成对应的角色权限数据
        List<SysRolePermission> rolePermissions = JSON.parseArray(rolePermissionJson, SysRolePermission.class);
        for (SysRolePermission rolePermission : rolePermissions) {
            rolePermission.setRolePermissionId(DateUtil.getUID());
            rolePermission.setRoleId(roleId);
            rolePermission.setRoleCode(roleCode);
            rolePermission.setCreateUserId(userId);
            rolePermission.setCreateTime(now);
            rolePermission.setModifyUserId(userId);
            rolePermission.setModifyTime(now);
        }
        // 查出对应角色的用户，清除shiro中的缓存权限，避免缓存中为旧数据
        List<Long> shiroUserIdList = sysRoleMapper.queryUserIdsByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(shiroUserIdList)) {
            List<String> shiroUserKeys = shiroUserIdList.stream()
                    .map(item -> RedisUtil.getKey(CacheConstant.R_SHIRO_CACHE, item))
                    .collect(Collectors.toList());
            RedisUtil.delete(shiroUserKeys);
        }
        // 删除原有的角色权限
        sysRolePermissionMapper.deletePermissionByRoleId(roleId);
        // 修改角色并新增对应对应的角色权限数据
        sysRoleService.updateByPrimaryKeySelective(updateRole, userId);
        sysRolePermissionMapper.batchInsertSelective(rolePermissions);
        return HttpResultEntry.ok();
    }

    /** 方法描述
     * @description 新增角色
     * @param sysRole
     * @param rolePermissionJson
     * @return [sysRole, rolePermissionJson]
     * @author wanghy
     * @date 2020/4/7 16:44
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResultEntry addRole(SysRole sysRole, String rolePermissionJson) {
        long userId = UserUtil.getUser().getUserId();
        Date now = new Date();
        // 生成的角色id
        long roleId = DateUtil.getUID();
        sysRole.setRoleId(roleId);
        // 校验json串内容，非正常则置为"[]"
        if (StringUtils.isBlank(rolePermissionJson)
                || !JsonValidator.getJsonValidatorInstance().validate(rolePermissionJson)) {
            rolePermissionJson = "[]";
        }
        // 对应生成的角色权限数据
        List<SysRolePermission> rolePermissions = JSON.parseArray(rolePermissionJson, SysRolePermission.class);
        for (SysRolePermission rolePermission : rolePermissions) {
            rolePermission.setRolePermissionId(DateUtil.getUID());
            rolePermission.setRoleId(roleId);
            rolePermission.setRoleCode(sysRole.getRoleCode());
            rolePermission.setCreateUserId(userId);
            rolePermission.setCreateTime(now);
            rolePermission.setModifyUserId(userId);
            rolePermission.setModifyTime(now);
        }
        sysRoleService.insertSelective(sysRole, userId);
        sysRolePermissionMapper.batchInsertSelective(rolePermissions);
        return HttpResultEntry.ok();
    }

    @Override
    public int updateByPrimaryKeySelective(SysRole sysRole, long l) {
        return 0;
    }

    @Override
    public Class getClazz() {
        return null;
    }

}