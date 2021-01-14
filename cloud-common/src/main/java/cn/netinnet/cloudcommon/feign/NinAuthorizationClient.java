package cn.netinnet.cloudcommon.feign;

import cn.netinnet.cloudcommon.feign.fallback.NinAuthorizationClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName NinAuthorizationClient
 * @Description
 * @Author yuyb
 * @Date 2021/1/14 14:08
 */
@FeignClient(value = "nin-authorization", fallback = NinAuthorizationClientFallback.class)
public interface NinAuthorizationClient {

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
