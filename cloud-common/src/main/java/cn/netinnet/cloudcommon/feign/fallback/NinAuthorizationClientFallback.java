package cn.netinnet.cloudcommon.feign.fallback;

import cn.netinnet.cloudcommon.feign.NinAuthorizationClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName NinAuthorizationClientFallback
 * @Description
 * @Author yuyb
 * @Date 2021/1/14 14:08
 */
@Component
public class NinAuthorizationClientFallback implements NinAuthorizationClient {

    @Override
    public List<String> getPermissionByRoleCode(String roleCode) {
        return null;
    }

}
