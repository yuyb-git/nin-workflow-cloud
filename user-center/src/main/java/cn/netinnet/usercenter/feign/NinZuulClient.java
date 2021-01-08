package cn.netinnet.usercenter.feign;

import cn.netinnet.usercenter.feign.fallback.NinZuulClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName NinZuulClient
 * @Description
 * @Author yuyb
 * @Date 2021/1/7 17:28
 */
@FeignClient(value = "nin-zuul", fallback = NinZuulClientFallback.class)
public interface NinZuulClient {

    /**  方法描述
     * @Description 根据角色编码获取权限
     * @Author yuyb
     * @Date 17:30 2021/1/7
     * @param roleCode
     * @return java.util.List<java.lang.String>
     **/
    @GetMapping("/sysPermission/getPermissionByRoleCode")
    List<String> getPermissionByRoleCode(@RequestParam("roleCode") String roleCode);

}
