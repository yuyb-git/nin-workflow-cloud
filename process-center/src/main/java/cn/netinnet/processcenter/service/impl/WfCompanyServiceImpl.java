package cn.netinnet.processcenter.service.impl;

import cn.netinnet.cloudcommon.constant.ParaConstant;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.exception.CustomException;
import cn.netinnet.cloudcommon.utils.UserUtil;
import cn.netinnet.common.base.BaseService;
import cn.netinnet.common.util.DateUtil;
import cn.netinnet.common.util.StringUtil;
import cn.netinnet.processcenter.dao.WfCompanyMapper;
import cn.netinnet.processcenter.dao.WfDeptMapper;
import cn.netinnet.processcenter.domain.WfCompany;
import cn.netinnet.processcenter.domain.WfDept;
import cn.netinnet.processcenter.service.WfCompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author yuyb
 * @date   2020-04-10
 */
@Service
public class WfCompanyServiceImpl extends BaseService<WfCompany> implements WfCompanyService {
    @Resource
    private WfCompanyMapper wfCompanyMapper;
    @Resource
    private WfDeptMapper wfDeptMapper;
    /*@Resource
    private SysQuestionMapper sysQuestionMapper;
    @Resource
    private SysQuestionCompanyMapper sysQuestionCompanyMapper;*/

    @Override
    public int updateByPrimaryKeySelective(WfCompany wfCompany, long l) {
        return 0;
    }

    @Override
    public int insertSelective(WfCompany wfCompany, long userId) {
        return wfCompanyMapper.insertSelective(wfCompany);
    }
    @Override
    public Class getClazz(){
        return WfCompany.class;
    }

    /**  方法描述
     * @Description 新增企业
     * @Author yuyb
     * @Date 14:57 2020/5/14
     * @param companyName
     * @param companyCategory
     * @param remarks
     * @param templateId
     * @return void
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCompany(String companyName, Integer companyCategory, String remarks, Long templateId, long examId, Long questionId) {
        if(templateId == null){
            throw new CustomException("表单模板id不能为空！");
        }
        UserInfo userInfo = UserUtil.getUser();
        Long userId = userInfo.getUserId();
        if (this.checkExist(null, companyCategory, companyName, examId, userId, questionId)) {
            if(!questionId.equals(0L)){
                throw new CustomException("做题时只能创建一个企业！");
            }else{
                throw new CustomException("已存在相同的企业名！");
            }
        }
        WfCompany wfCompany = new WfCompany();
        Long companyId = DateUtil.getUID();
        wfCompany.setCompanyId(companyId);
        //企业归类（0：系统，1：自建企业-学生，2：自建企业-答案，3：教师企业）
        wfCompany.setCompanyCategory(companyCategory);
        wfCompany.setCompanyName(companyName);
        wfCompany.setRemarks(remarks);
        wfCompany.setCreateUserId(userId);
        wfCompany.setCompanyStatus(0);
        wfCompany.setTemplateId(templateId);
        wfCompany.setExamId(examId);
        wfCompanyMapper.insertSelective(wfCompany);
        //构建第一级部门实体
        WfDept wfDept = new WfDept();
        wfDept.setDeptId(DateUtil.getUID());
        wfDept.setCompanyId(companyId);
        wfDept.setDeptName(companyName);
        wfDept.setLevel(1);
        wfDept.setParent(0L);
        wfDept.setUserId(userId);
        //新建第一级部门
        wfDeptMapper.insertSelective(wfDept);

        /*if(questionId!=0L){
            //构建问题企业实体
            SysQuestionCompany questionCompany = new SysQuestionCompany();
            questionCompany.setId(DateUtil.getUID());
            questionCompany.setCompanyId(companyId);
            questionCompany.setCompanyCategory(companyCategory);
            questionCompany.setExamId(examId);
            questionCompany.setQuestionId(questionId);
            questionCompany.setCreateUserId(userId);
            //新增问题企业
            sysQuestionCompanyMapper.insertSelective(questionCompany);
            //删除缓存
            RedisUtil.del(CacheConstant.ANS_COMPANY + questionId);
        }*/
    }

    /** 方法描述
     * @description 编辑企业
     * @param companyId
     * @param companyName
     * @param remarks
     * @param templateId
     * @return void
     * @author Caicm
     * @date 2020/6/3 17:10
     */
    @Override
    public void editCompany(long examId, Long companyId, String companyName, String remarks, Long templateId) {
        if(templateId == null){
            throw new CustomException("表单模板id不能为空！");
        }
        WfCompany company = wfCompanyMapper.selectColumnsById(companyId, "company_category");
        if(company == null){
            throw new CustomException("未找到该企业！");
        }
        UserInfo userInfo = UserUtil.getUser();
        Long userId = userInfo.getUserId();
        if (this.checkExist(companyId, company.getCompanyCategory(), companyName, examId, userId,0L)) {
            throw new CustomException("已存在相同的企业名！");
        }
        WfCompany wfCompany = new WfCompany();
        wfCompany.setCompanyId(companyId);
        wfCompany.setCompanyName(companyName);
        wfCompany.setTemplateId(templateId);
        wfCompany.setRemarks(remarks);
        wfCompanyMapper.updateByPrimaryKeySelective(wfCompany);
    }

    /**  方法描述
     * @Description 查询答案企业列表-不指定企业
     * @Author yuyb
     * @Date 17:41 2020/9/15
     * @param questionId
     * @return java.util.List<cn.netinnet.processcenter.domain.WfCompany>
     **/
    @Override
    public List<WfCompany> getAnsCompanyList(long questionId) {
        //Long companyId = sysQuestionMapper.queryCompanyId(questionId);
        Long companyId = 0L;
        if(!companyId.equals(0L)){
            WfCompany company = wfCompanyMapper.selectColumnsById(companyId, "company_id,company_name");
            return Collections.singletonList(company);
        }
        return wfCompanyMapper.getAnswerCompanys(questionId);
    }

    /**  方法描述
     * @Description 查询我的企业列表
     * @Author osp
     * @Date 14:38 2020/5/14
     * @return java.util.List<cn.netinnet.processcenter.domain.WfCompany>
     **/
    @Override
    public Map<String, Object> getMyCompanyList(long questionId, long examId) {
        Map<String, Object> result = new HashMap<>(8);
        if(questionId != 0) {
            //Long companyId = sysQuestionMapper.queryCompanyId(questionId);
            Long companyId = 0L;
            if (companyId == null) {
                throw new CustomException("题目不存在！");
            }
            if (companyId != 0L) {
                //说明指定企业，只有一个企业
                WfCompany company = wfCompanyMapper.selectColumnsById(companyId, "company_id,company_name");
                if (company == null) {
                    throw new CustomException("企业不存在！");
                }
                result.put("companies", Collections.singletonList(company));
                result.put("flag", "0");
            } else {
                //说明未指定企业，从sys_question_company查询
                List<WfCompany> companies = seachCompanyListInQuestion(examId, questionId, null);
                result.put("companies", companies);
                result.put("flag", "1");
            }
        } else {
            List<WfCompany> companies = wfCompanyMapper.getMyCompanyList(examId);
            result.put("companies", companies);
            result.put("flag", "1");
        }
        return result;
    }

    /** 方法描述
     * @description 查询企业列表 -后台获取企业列表
     * @param companyCategory   企业类型
     * @param companyName       企业名称
     * @param enable            启用状态
     * @param examId            用户id为空时，表示后台查询
     * @return java.util.List<cn.netinnet.processcenter.domain.WfCompany>
     * @author ousp
     * @date 2020/4/14 9:45
     */
    @Override
    public List<WfCompany> seachCompanyList(Integer companyCategory, String companyName, Integer enable, long examId) {
        if (!StringUtil.isBlankOrNull(companyName)) {
            companyName = "%" + companyName + "%";
        }
        if(companyCategory == ParaConstant.COMPANY_TEACHER){
            UserInfo userInfo = UserUtil.getUser();
            Long userId = userInfo.getUserId();
            return wfCompanyMapper.seachTeacherCompanyList(userId, enable, companyName);
        }
        return wfCompanyMapper.seachCompanyList(examId, companyCategory, enable, companyName);
    }

    /**
    * @description  检查企业是否存在
    * @param  company
    * @param  examId    examId为空或0表示检查的是系统创建的
    * @author ousp
    * @date 2020/4/14
    * @return boolean
    */
    @Override
    public boolean checkExist(Long companyId, Integer companyCategory, String company, Long examId, Long userId, Long questionId) {
        Integer result;
        if (companyCategory == ParaConstant.COMPANY_ANSWER) {
            //答案自建企业只能建一个
            result = wfCompanyMapper.checkSingleQuestionExist(questionId, ParaConstant.COMPANY_ANSWER);
        }else if(companyCategory == ParaConstant.COMPANY_SYSTEM){
            result = wfCompanyMapper.checkExist(companyId, companyCategory, company, 0L);
        }else if(companyCategory == ParaConstant.COMPANY_TEACHER){
            result = wfCompanyMapper.checkTeacherCompanyExist(companyId, companyCategory, company, userId);
        }else{
            //学生自荐企业,companyCategory == ParaConstant.COMPANY_STUDENT
            if(questionId != null && !questionId.equals(0L)){
                //试题id！=0，说明是学生做题自荐企业 自建企业只能建一个
                result = wfCompanyMapper.checkUserQuesCompExist(examId, questionId, ParaConstant.COMPANY_STUDENT);
            }else{
                //试题id==0，说明是学生自由练习建企业
                result = wfCompanyMapper.checkExist(companyId, companyCategory, company, examId);
            }
        }
        return result != null && result != 0;
    }

    /** 方法描述
     * @description 获取企业下拉框数据
     * @return java.util.List<cn.netinnet.processcenter.domain.WfCompany>
     * @author Caicm
     * @date 2020/5/14 10:20
     */
    @Override
    public List<WfCompany> queryCompanyOptions(Integer questionCategory, Long userId) {
        return wfCompanyMapper.queryCompanyOptions(questionCategory, userId);
    }

    /**
    * 删除企业数据
    * @Param companyIds      企业id
    * @author ousp
    * @date 2020/5/19
    * @return void
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCompanyData(List<Long> companyIds) {
        wfCompanyMapper.logicalDeleteByIdList(companyIds);
    }


    /** 方法描述
     * @description 启用（禁用）企业
     * @param companyIds
     * @param op
     * @return void
     * @author Caicm
     * @date 2020/5/26 10:04
     */
    @Override
    public void changeCompanyStatus(List<Long> companyIds, String op) {
        Integer status = "on".equals(op) ? 0 : 1;
        wfCompanyMapper.changeCompanyStatus(companyIds, status);
    }

    /**
    * 查询题目下的企业
    * @author ousp
    * @date 2020/6/10
    * @return java.util.List<cn.netinnet.processcenter.domain.WfCompany>
    */
    @Override
    public List<WfCompany> seachCompanyListInQuestion(long examId, long questionId, String companyName) {
        if (!StringUtil.isBlankOrNull(companyName)) {
            companyName = "%" + companyName + "%";
        }
        return wfCompanyMapper.seachCompanyListInQuestion(questionId, companyName, examId);
    }


}