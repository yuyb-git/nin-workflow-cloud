/*
 * SysPermission.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-09-14 Created
 */
package cn.netinnet.ninzuul.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2020-09-14
 **/
public class SysPermission implements Serializable {
    /**
     * 权限id
     */
    private Long permissionId;

    /**
     * 上级权限id
     */
    private Long parentId;

    /**
     * 权限类别,1-菜单;2-按钮
     */
    private Integer permissionType;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 排序
     */
    private Integer permissionSeq;

    /**
     * 路由名称
     */
    private String routerName;

    /**
     * 路由的url地址
     */
    private String routerPath;

    /**
     * 路由组件地址
     */
    private String routerComponent;

    /**
     * 路由meta属性，json串
     */
    private String routerMeta;

    /**
     * 路由重定向
     */
    private String routerRedirect;

    /**
     * 自定义组件
     */
    private String selfLayout;

    /**
     * 路由隐藏（0-显示，1-隐藏）
     */
    private Integer routerHidden;

    /**
     * 是否为顶层路由（0-否，1-是）
     */
    private Integer routerTop;

    /**
     * 1-教师端;2-管理后台端;3-学生端
     */
    private Integer projectType;

    /**
     * 记录创建人ID
     */
    private Long createUserId;

    /**
     * 记录生成时间
     */
    private Date createTime;

    /**
     * 记录修改人ID
     */
    private Long modifyUserId;

    /**
     * 记录修改时间
     */
    private Date modifyTime;

    private static final long serialVersionUID = 4544472943172488192L;

    /**
     * 获取权限id
     *
     * @return permission_id - 权限id
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * 设置权限id
     *
     * @param permissionId 权限id
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * 获取上级权限id
     *
     * @return parent_id - 上级权限id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置上级权限id
     *
     * @param parentId 上级权限id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取权限类别,1-菜单;2-按钮
     *
     * @return permission_type - 权限类别,1-菜单;2-按钮
     */
    public Integer getPermissionType() {
        return permissionType;
    }

    /**
     * 设置权限类别,1-菜单;2-按钮
     *
     * @param permissionType 权限类别,1-菜单;2-按钮
     */
    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }

    /**
     * 获取权限编码
     *
     * @return permission_code - 权限编码
     */
    public String getPermissionCode() {
        return permissionCode;
    }

    /**
     * 设置权限编码
     *
     * @param permissionCode 权限编码
     */
    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    /**
     * 获取权限名称
     *
     * @return permission_name - 权限名称
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * 设置权限名称
     *
     * @param permissionName 权限名称
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    /**
     * 获取排序
     *
     * @return permission_seq - 排序
     */
    public Integer getPermissionSeq() {
        return permissionSeq;
    }

    /**
     * 设置排序
     *
     * @param permissionSeq 排序
     */
    public void setPermissionSeq(Integer permissionSeq) {
        this.permissionSeq = permissionSeq;
    }

    /**
     * 获取路由名称
     *
     * @return router_name - 路由名称
     */
    public String getRouterName() {
        return routerName;
    }

    /**
     * 设置路由名称
     *
     * @param routerName 路由名称
     */
    public void setRouterName(String routerName) {
        this.routerName = routerName;
    }

    /**
     * 获取路由的url地址
     *
     * @return router_path - 路由的url地址
     */
    public String getRouterPath() {
        return routerPath;
    }

    /**
     * 设置路由的url地址
     *
     * @param routerPath 路由的url地址
     */
    public void setRouterPath(String routerPath) {
        this.routerPath = routerPath;
    }

    /**
     * 获取路由组件地址
     *
     * @return router_component - 路由组件地址
     */
    public String getRouterComponent() {
        return routerComponent;
    }

    /**
     * 设置路由组件地址
     *
     * @param routerComponent 路由组件地址
     */
    public void setRouterComponent(String routerComponent) {
        this.routerComponent = routerComponent;
    }

    /**
     * 获取路由meta属性，json串
     *
     * @return router_meta - 路由meta属性，json串
     */
    public String getRouterMeta() {
        return routerMeta;
    }

    /**
     * 设置路由meta属性，json串
     *
     * @param routerMeta 路由meta属性，json串
     */
    public void setRouterMeta(String routerMeta) {
        this.routerMeta = routerMeta;
    }

    /**
     * 获取路由重定向
     *
     * @return router_redirect - 路由重定向
     */
    public String getRouterRedirect() {
        return routerRedirect;
    }

    /**
     * 设置路由重定向
     *
     * @param routerRedirect 路由重定向
     */
    public void setRouterRedirect(String routerRedirect) {
        this.routerRedirect = routerRedirect;
    }

    /**
     * 获取自定义组件
     *
     * @return self_layout - 自定义组件
     */
    public String getSelfLayout() {
        return selfLayout;
    }

    /**
     * 设置自定义组件
     *
     * @param selfLayout 自定义组件
     */
    public void setSelfLayout(String selfLayout) {
        this.selfLayout = selfLayout;
    }

    /**
     * 获取路由隐藏（0-显示，1-隐藏）
     *
     * @return router_hidden - 路由隐藏（0-显示，1-隐藏）
     */
    public Integer getRouterHidden() {
        return routerHidden;
    }

    /**
     * 设置路由隐藏（0-显示，1-隐藏）
     *
     * @param routerHidden 路由隐藏（0-显示，1-隐藏）
     */
    public void setRouterHidden(Integer routerHidden) {
        this.routerHidden = routerHidden;
    }

    /**
     * 获取是否为顶层路由（0-否，1-是）
     *
     * @return router_top - 是否为顶层路由（0-否，1-是）
     */
    public Integer getRouterTop() {
        return routerTop;
    }

    /**
     * 设置是否为顶层路由（0-否，1-是）
     *
     * @param routerTop 是否为顶层路由（0-否，1-是）
     */
    public void setRouterTop(Integer routerTop) {
        this.routerTop = routerTop;
    }

    /**
     * 获取1-教师端;2-管理后台端;3-学生端
     *
     * @return project_type - 1-教师端;2-管理后台端;3-学生端
     */
    public Integer getProjectType() {
        return projectType;
    }

    /**
     * 设置1-教师端;2-管理后台端;3-学生端
     *
     * @param projectType 1-教师端;2-管理后台端;3-学生端
     */
    public void setProjectType(Integer projectType) {
        this.projectType = projectType;
    }

    /**
     * 获取记录创建人ID
     *
     * @return create_user_id - 记录创建人ID
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置记录创建人ID
     *
     * @param createUserId 记录创建人ID
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取记录生成时间
     *
     * @return create_time - 记录生成时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置记录生成时间
     *
     * @param createTime 记录生成时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取记录修改人ID
     *
     * @return modify_user_id - 记录修改人ID
     */
    public Long getModifyUserId() {
        return modifyUserId;
    }

    /**
     * 设置记录修改人ID
     *
     * @param modifyUserId 记录修改人ID
     */
    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    /**
     * 获取记录修改时间
     *
     * @return modify_time - 记录修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置记录修改时间
     *
     * @param modifyTime 记录修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", permissionId=").append(permissionId);
        sb.append(", parentId=").append(parentId);
        sb.append(", permissionType=").append(permissionType);
        sb.append(", permissionCode=").append(permissionCode);
        sb.append(", permissionName=").append(permissionName);
        sb.append(", permissionSeq=").append(permissionSeq);
        sb.append(", routerName=").append(routerName);
        sb.append(", routerPath=").append(routerPath);
        sb.append(", routerComponent=").append(routerComponent);
        sb.append(", routerMeta=").append(routerMeta);
        sb.append(", routerRedirect=").append(routerRedirect);
        sb.append(", selfLayout=").append(selfLayout);
        sb.append(", routerHidden=").append(routerHidden);
        sb.append(", routerTop=").append(routerTop);
        sb.append(", projectType=").append(projectType);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}