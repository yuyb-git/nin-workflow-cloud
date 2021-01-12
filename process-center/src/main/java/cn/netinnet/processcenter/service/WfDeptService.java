package cn.netinnet.processcenter.service;

import cn.netinnet.common.base.Service;
import cn.netinnet.processcenter.domain.WfDept;

import java.util.Map;


/**
 * @author yuyb
 * @date   2020-04-09
 */
public interface WfDeptService extends Service<WfDept> {

    /** 编辑部门 **/
    void editDept(WfDept wfDept, Long userId);

    /** 删除部门 **/
    void deleteDept(Long deptId);

    /** 获取企业组织树（包含员工） **/
    Map<String, Object> getDeptTreeWithStaff(Long companyId, boolean useCache);
}
