package cn.netinnet.ninauthorization.utils;


import cn.netinnet.ninauthorization.domain.SysPermission;
import cn.netinnet.ninauthorization.dto.VueRouter;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author Linjj
 * @Date 2019/10/14 14:54
 * @Description 构建树形结构工具类
 */
public class TreeUtil {

    private final static long TOP_NODE_ID = 0L;

    /**
     * 构造前端路由
     *
     * @param routes routes
     * @return ArrayList<VueRouter>
     */
    public static List<VueRouter> buildRouterTree(List<SysPermission> routes) {
        if (routes == null) {
            return null;
        }
        // 转为vueRouter格式
        List<VueRouter> vueRouters = new ArrayList<>();
        for (SysPermission route : routes) {
            // 路由名称为exclude的排除（如只用于提供分类目录的公共模块）
            if ("exclude".equals(route.getRouterName())) {
                continue;
            }
            VueRouter vueRoute = new VueRouter();
            vueRoute.setId(route.getPermissionId());
            // 如果当前组件设置为顶层，将parentId置0，当作顶级路由
            if (route.getRouterTop().equals(1)) {
                vueRoute.setParentId(0L);
            } else {
                vueRoute.setParentId(route.getParentId());
            }
            vueRoute.setSeq(route.getPermissionSeq());
            vueRoute.setName(route.getRouterName());
            vueRoute.setPath(route.getRouterPath());
            vueRoute.setComponent(route.getRouterComponent());
            vueRoute.setMeta(route.getRouterMeta());
            vueRoute.setRedirect(route.getRouterRedirect());
            vueRoute.setSelfLayout(route.getSelfLayout());
            // 为1则表示隐藏
            vueRoute.setHidden(route.getRouterHidden() == 1);
            vueRouters.add(vueRoute);
        }
        // 将路由按parentId分组
        Map<Long, List<VueRouter>> vueRouterMap = vueRouters.stream()
                .collect(Collectors.groupingBy(VueRouter::getParentId));
        // 对各分组进行排序
        for (List<VueRouter> routerList : vueRouterMap.values()) {
            routerList.sort(Comparator.comparingInt(VueRouter::getSeq));
        }
        // 转换为路由树的格式, 先获取各自children
        for (VueRouter vueRouter : vueRouters) {
            long id = vueRouter.getId();
            List<VueRouter> children = vueRouterMap.get(id);
            if (!CollectionUtils.isEmpty(children)) {
                vueRouter.setChildren(children);
            }
        }
        // 提取路由数顶层，返回给前端
        List<VueRouter> topRoutes = vueRouterMap.getOrDefault(TOP_NODE_ID, new ArrayList<>());
        return topRoutes;
    }
}