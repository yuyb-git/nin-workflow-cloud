package cn.netinnet.cloudcommon.feign.fallback;

import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.feign.NinZuulFeign;
import org.springframework.stereotype.Component;

/**
 * @ClassName NinZuulFeignFallback
 * @Description
 * @Author yuyb
 * @Date 2021/1/14 16:06
 */
@Component
public class NinZuulFeignFallback implements NinZuulFeign {
    @Override
    public UserInfo getCurrUser() {
        return null;
    }
}
