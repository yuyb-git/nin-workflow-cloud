package cn.netinnet.usercenter.feign.fallback;

import cn.netinnet.usercenter.feign.NinZuulClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName NinZuulClientFallback
 * @Description
 * @Author yuyb
 * @Date 2021/1/8 15:10
 */
@Component
public class NinZuulClientFallback implements NinZuulClient {

    @Override
    public List<String> getPermissionByRoleCode(String roleCode) {
        return null;
    }

}
