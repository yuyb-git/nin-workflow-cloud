/*
 * SysLogMapper.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-04-07 Created
 */
package cn.netinnet.logcenter.dao;

import cn.netinnet.cloudcommon.base.BaseMapper;
import cn.netinnet.logcenter.domain.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface SysLogMapper extends BaseMapper<SysLog> {

    /** 方法描述
     * @description 根据beginDate, endDate, userName查询系统日志列表
     * @param param
     * @return [param]
     * @author wanghy
     * @date 2020/4/9 9:37
     */
    List<SysLog> queryList(Map<String, Object> param);

    /**  方法描述
     * @Description 删除endDate之前的系统日志
     * @Author yuyb
     * @Date 16:26 2020/6/4
     * @param endDate
     * @return int
     **/
    int delLogsByEndDate(@Param("endDate") String endDate);

    /**  方法描述
     * @Description 查询超过一定日期的系统日志数
     * @Author yuyb
     * @Date 15:49 2020/6/4
     * @param endDate
     * @return java.util.List<java.lang.Long>
     **/
    int getDelLogCount(@Param("endDate") String endDate);

    /**
    * 查询系统参数
    * @author ousp
    * @date 2020/8/13
    * @return java.lang.String
    */
    Map<String, Object> querySysParam(@Param("columns") String columns);
}