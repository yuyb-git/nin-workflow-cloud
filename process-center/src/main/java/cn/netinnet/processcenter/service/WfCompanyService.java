package cn.netinnet.processcenter.service;

import cn.netinnet.cloudcommon.base.Service;
import cn.netinnet.processcenter.domain.WfCompany;

import java.util.List;
import java.util.Map;


/**
 * @author yuyb
 * @date   2020-04-10
 */
public interface WfCompanyService extends Service<WfCompany> {

    /** 新增企业 **/
    void addCompany(String companyName, Integer companyCategory, String remarks, Long templateId, long examId, Long questionId);

   /** 编辑企业 **/
   void editCompany(long examId, Long companyId, String companyName, String remarks, Long templateId);

    /** 查询答案企业列表-不指定企业 **/
    List<WfCompany> getAnsCompanyList(long questionId);

    /** 查询我的企业列表 **/
    Map<String, Object> getMyCompanyList(long questionId, long examId);

    /** 查询企业列表 **/
    List<WfCompany> seachCompanyList(Integer companyCategory, String companyName, Integer enable, long examId);

    /** 检查企业是否存在 **/
    boolean checkExist(Long companyId, Integer companyCategory, String company, Long examId, Long userId, Long questionId);

    /** 获取企业下拉框数据 **/
    List<WfCompany> queryCompanyOptions(Integer questionCategory, Long userId);

    /** 删除企业数据 **/
    void deleteCompanyData(List<Long> companyId);

    /** 启用(禁用)企业 **/
    void changeCompanyStatus(List<Long> companyIds, String op);

    /** 查询题目下的企业 **/
    List<WfCompany> seachCompanyListInQuestion(long examId, long questionId, String companyName);

}
