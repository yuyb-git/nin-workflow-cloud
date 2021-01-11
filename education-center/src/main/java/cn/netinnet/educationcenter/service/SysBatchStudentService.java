package cn.netinnet.educationcenter.service;


import cn.netinnet.common.base.Service;
import cn.netinnet.educationcenter.domain.SysBatchStudent;

import java.util.List;
import java.util.Map;


/**
 * @author yuyb
 * @date   2020-05-26
 */
public interface SysBatchStudentService extends Service<SysBatchStudent> {

    /** 删除批次学生 **/
    void deleteBatchStudentByIdList(List<Long> idList);

    /** 新增批次学生时：查询对应班级及其学生 **/
    List<Map<String, Object>> queryAddBatchStudentList(String projectCode, Long batchId, String className, Long userId);

    /** 新增批次学生 **/
    void addBatchStudent(Long batchId, List<SysBatchStudent> userInfoList);

}
