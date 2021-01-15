package cn.netinnet.processcenter.service;

import cn.netinnet.cloudcommon.base.Service;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.processcenter.domain.WfDept;
import cn.netinnet.processcenter.domain.WfStaff;
import com.alibaba.fastjson.JSONArray;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author yuyb
 * @date   2020-04-09
 */
public interface WfStaffService extends Service<WfStaff> {

    /**  分页查询员工列表 **/
    List<Map<String,Object>> queryStaffList(Long deptId, String searchItem);

    /** 编辑员工 **/
    void editStaff(WfStaff staff, Long userId);

    /** 员工操作（启用、禁用） **/
    void changeStaffEnable(String op, Long staffId);

    /**  删除员工 **/
    String deleteStaff(Long companyId, List<Long> staffIdList);

    /** 导出员工模板 **/
    HSSFWorkbook  exportStaffTemplate(Long companyId);

    /** 批量导入员工 **/
    HttpResultEntry importStaff(JSONArray jsonArray, Long companyId, Long userId);

    /** 查询员工姓名 **/
    Map<Long, WfStaff> queryNameAndJobNumMap(Set<Long> staffIds);

    /** 导入部门员工事务 **/
    void importStaffTransaction(List<Long> staffIds, List<Long> deptIds, List<WfDept> depts, List<WfStaff> wfStaffs);

    /** 查询企业下所有员工 **/
    List<WfStaff> getAllStaffByCompanyId(long companyId);
}
