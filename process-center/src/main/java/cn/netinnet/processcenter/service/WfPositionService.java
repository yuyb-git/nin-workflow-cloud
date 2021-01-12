package cn.netinnet.processcenter.service;

import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.common.base.Service;
import cn.netinnet.processcenter.domain.WfPosition;
import com.alibaba.fastjson.JSONArray;


/**
 * @author yuyb
 * @date   2020-04-09
 */
public interface WfPositionService extends Service<WfPosition> {

    /**  新增编辑岗位 **/
    void editPosition(WfPosition wfPosition, Long userId);

    /** 获取岗位信息详情 **/
    WfPosition positionDetail(Long positionId);

    /** 删除岗位 **/
    void deletePosition(Long positionId);

    /** 导入岗位 **/
    HttpResultEntry importPosition(JSONArray jsonArray, Long companyId, Long userId);

}
