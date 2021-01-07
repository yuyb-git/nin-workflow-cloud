package cn.netinnet.ninzuul.service.impl;

import cn.netinnet.ninzuul.dao.SysPermissionMapper;
import cn.netinnet.ninzuul.dao.SysRolePermissionMapper;
import cn.netinnet.ninzuul.domain.SysPermission;
import cn.netinnet.ninzuul.dto.VueRouter;
import cn.netinnet.ninzuul.service.SysPermissionService;
import cn.netinnet.ninzuul.utils.TreeUtil;
import cn.netinnet.common.util.DateUtil;
import cn.netinnet.cloudcommon.base.BaseService;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.utils.JsonValidator;
import cn.netinnet.cloudcommon.utils.UserUtil;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * @author yuyb
 * @date   2020-04-03
 */
@Service
public class SysPermissionServiceImpl extends BaseService<SysPermission>
        implements SysPermissionService {
    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    /** 方法描述
     * @description 获取用户权限路由
     * @return []
     * @author wanghy
     * @date 2020/4/8 13:43
     */
    @Override
    public String getRouters(String roleCode, Integer projectType) {
        //判断若是单独获取则无需传入角色参数
        if(StringUtils.isBlank(roleCode)) {
            UserInfo userInfo = UserUtil.getUser();
            // 目前用户与角色设计是一对一的，直接用roleCode查
            roleCode = userInfo.getRoleCode();
        }
        // 查询对应角色的路由表
        List<SysPermission> routerList = sysPermissionMapper.queryUserRouter(roleCode, projectType);
        // 转换成对应的路由表树
        List<VueRouter> routerTree = TreeUtil.buildRouterTree(routerList);
        //将路由树利用Base64进行编码
        Gson gson = new Gson();
        String str = gson.toJson(routerTree);
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

    /** 方法描述
     * @description 新增/编辑权限
     * @param sysPermission
     * @return [sysPermission]
     * @author wanghy
     * @date 2020/4/8 13:52
     */
    @Override
    public HttpResultEntry addEditPermission(SysPermission sysPermission) {
        Long permissionId = sysPermission.getPermissionId();
        Long userId = UserUtil.getUser().getUserId();
        String meta = sysPermission.getRouterMeta();
        String redirect = sysPermission.getRouterRedirect();
        // 校验meta格式
        if (StringUtils.isNotBlank(meta)) {
            if (!JsonValidator.getJsonValidatorInstance().validate(meta)) {
                return HttpResultEntry.error("路由属性的JSON格式错误");
            }
        }
        // 校验redirect格式(含有‘{’'}'表明为json串，则进行校验)
        if (StringUtils.isNotBlank(redirect) && (redirect.contains("{") || redirect.contains("}"))) {
            if (!JsonValidator.getJsonValidatorInstance().validate(redirect)) {
                return HttpResultEntry.error("路由重定向的JSON格式错误");
            }
        }
        if (permissionId == null || permissionId <= 0L) {
            // 新增权限
            permissionId = DateUtil.getUID();
            sysPermission.setPermissionId(permissionId);
            insertSelective(sysPermission, userId);
        } else {
            // 编辑权限
            updateByIdSelective(sysPermission);
            // 同步更新关联表中的权限编码
            String permissionCode = "";
            if (StringUtils.isNotBlank(sysPermission.getPermissionCode())) {
                permissionCode = sysPermission.getPermissionCode();
            }
            sysRolePermissionMapper.updatePermissionCodeById(permissionId, permissionCode);
        }
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, permissionId);
    }

    @Override
    public int insertSelective(SysPermission sysPermission, long userId) {
        sysPermission.setCreateUserId(userId);
        sysPermission.setModifyUserId(userId);
        return sysPermissionMapper.insertSelective(sysPermission);
    }

    /**
     * @Author Linjj
     * @Date 2019/10/15 9:34
     * @Description 删除权限（物理删除）
     */
    @Override
    public HttpResultEntry deletePermission(Long permissionId) {
        // 判断是否拥有子级权限，拥有则不允许删除
        int count = sysPermissionMapper.countPermissionByCondition(null, null, permissionId);
        if (count > 0) {
            return HttpResultEntry.error("拥有子级权限，不允许删除！");
        }
        // 进行当前权限删除
        sysPermissionMapper.deleteByPrimaryKey(permissionId);
        return HttpResultEntry.ok();
    }

    /**
     * @Author Linjj
     * @Date 2019/10/15 10:53
     * @Description 可选上级权限列表查询
     * @param permissionId 待过滤的id(自身及其子节点)
     */
    @Override
    public HttpResultEntry queryParentPermissions(Integer projectType,
                                                  @RequestParam(defaultValue = "0") Long permissionId) {
        List<SysPermission> parentPermissions = sysPermissionMapper.queryAllMenuPermission(projectType);
        // 映射成权限id - 权限信息的map，方便后续查找
        Map<Long, SysPermission> permissionMap = parentPermissions.stream()
                .collect(Collectors.toMap(SysPermission::getPermissionId, Function.identity()));
        // 备份一份原本的名称，以便拼接名称时，不会重复拼接
        Map<Long, String> nameMap = parentPermissions.stream()
                .collect(Collectors.toMap(SysPermission::getPermissionId, SysPermission::getPermissionName));
        // 备份一份原本的路径，以便拼接路径时，不会重复拼接
        Map<Long, String> pathMap = parentPermissions.stream()
                .collect(Collectors.toMap(SysPermission::getPermissionId, SysPermission::getRouterPath));
        // 最终返回的结果
        List<Map<String, Object>> result = new ArrayList<>();
        // 处理返回的权限名称（将子级名称拼上对应的祖父级名称）
        for (SysPermission permission : parentPermissions) {
            Map<String, Object> item = new HashMap<>();
            Long parentId = permission.getParentId();
            // 当前id
            Long currentId = permission.getPermissionId();
            // 最终当前级的名称（包含所有祖父级）
            String finalName = nameMap.get(currentId);
            // 最终当前级的路径（包含所有祖父级）
            String finalPath = pathMap.get(currentId);
            // 过滤节点标记（先过滤自身，后续再过滤其子节点）
            boolean filterFlag = currentId.equals(permissionId);
            // 父级路径的顶层标记（标记是否已经遍历到顶层）
            boolean topFlag = permission.getRouterTop() == 1;
            // 无父级id直接跳过
            while (parentId != null && parentId > 0L) {
                // 是否含有需要过滤的父节点（逆向搜索父节点，来判断）
                if (parentId.equals(permissionId)) {
                    filterFlag = true;
                }
                // 拼接祖父级名称
                String parentName = nameMap.get(parentId);
                finalName = parentName + "-" + finalName;
                // 拼接祖父级路径(未到顶层则一直获取父级path)
                if (!topFlag) {
                    String parenPath = pathMap.get(parentId);
                    if (!parenPath.endsWith("/")) {
                        parenPath = parenPath + "/";
                    }
                    finalPath = parenPath + finalPath;
                }
                // 当前节点不为顶层，则继续添加父级path
                topFlag = (topFlag) ? true : permissionMap.get(parentId).getRouterTop() == 1;
                // 获取新的父级id
                parentId = permissionMap.get(parentId).getParentId();
            }
            if (!finalPath.endsWith("/")) {
                finalPath = finalPath + "/";
            }
            // 需要过滤则不添加
            if (!filterFlag) {
                item.put("permissionId", currentId);
                item.put("permissionName", finalName);
                item.put("parentPath", finalPath);
                result.add(item);
            }
        }
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, result);
    }

    /**
     * @Author Linjj
     * @Date 2019/10/15 13:37
     * @Description 后台查询权限列表树
     */
    @Override
    public HttpResultEntry queryPermissionTree(Integer projectType) {
        List<SysPermission> allPermissions = sysPermissionMapper.queryAllPermission(Collections.singletonList(projectType));
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, allPermissions);
    }

    @Override
    public Class getClazz() {
        return null;
    }
}