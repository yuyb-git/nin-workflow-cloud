package cn.netinnet.educationcenter.service;

import cn.netinnet.cloudcommon.base.Service;
import cn.netinnet.educationcenter.domain.SysBatch;

import java.util.List;
import java.util.Map;


/**
 * @author yuyb
 * @date   2020-05-26
 */
public interface SysBatchService extends Service<SysBatch> {

    /** 查询批次列表 **/
    List<SysBatch> queryList(Integer batchStatus, String batchName);

    /** 新增批次 **/
    void addBatch(SysBatch sysBatch);

    /** 修改批次 **/
    void editBatch(SysBatch sysBatch);

    /** 删除批次 **/
    void deleteBatch(Long batchId);

    /** 改变批次状态 **/
    void updateBatchStatus(Long batchId, Integer status);

    /** 根据学校ID查询批次列表 **/
    List<SysBatch> queryListBySchoolId(Long schoolId);

    /** 根据学生用户id和学校ID查询所在批次列表 **/
    List<Map<String,Object>> queryBatchByUserIdAndSchoolId(Long userId, Long schoolId, Integer batchStatus);

    /** 查询批次相关数据信息 **/
    Map<String, Object> batchDataDetail(long batchId);

    /** dif用户查询批次列表 **/
    List<Map<String,Object>> difStudentBatch(long schoolId, long individualityId, Integer batchStatus);

    /** 查询用户的批次 **/
    List<Map<String,Object>> batchList(Long schoolId, Long userId, Integer batchStatus, String batchName);
}
