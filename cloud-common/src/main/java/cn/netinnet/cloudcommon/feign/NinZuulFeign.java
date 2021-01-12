package cn.netinnet.cloudcommon.feign;

import cn.netinnet.cloudcommon.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName NinZuulClient
 * @Description
 * @Author yuyb
 * @Date 2021/1/7 17:28
 */
@FeignClient(value = "nin-zuul")
public interface NinZuulFeign {

    @GetMapping("/server/getCurrUser")
    UserInfo getCurrUser();
}
