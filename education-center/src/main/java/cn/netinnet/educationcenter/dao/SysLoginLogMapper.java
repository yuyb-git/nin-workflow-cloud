/*
 * SysLoginLogMapper.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-06-19 Created
 */
package cn.netinnet.educationcenter.dao;

import cn.netinnet.common.base.BaseMapper;
import cn.netinnet.educationcenter.domain.SysLoginLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {

    /**  方法描述
     * @Description 删除endDate之前的用户登录日志
     * @Author yuyb
     * @Date 9:32 2020/6/22
     * @param endDate
     * @return int
     **/
    int delLogsByEndDate(@Param("endDate") String endDate);

    /***
    * 查询登录日志
    * @author ousp
    * @date 2020/9/27
    * @return java.util.List<cn.netinnet.workflow.sys.domain.SysLoginLog>
    */
    List<SysLoginLog> queryByDate(@Param("beginDate") String beginDate, @Param("endDate") String endDate, @Param("userName") String userName);
}
