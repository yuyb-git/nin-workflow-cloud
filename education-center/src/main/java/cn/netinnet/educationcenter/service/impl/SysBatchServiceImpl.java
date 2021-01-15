package cn.netinnet.educationcenter.service.impl;

import cn.netinnet.cloudcommon.base.BaseService;
import cn.netinnet.cloudcommon.constant.UserConstant;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.exception.CustomException;
import cn.netinnet.cloudcommon.utils.DateUtil;
import cn.netinnet.cloudcommon.utils.StringUtil;
import cn.netinnet.cloudcommon.utils.UserUtil;
import cn.netinnet.educationcenter.constant.ParaConstant;
import cn.netinnet.educationcenter.dao.SysBatchMapper;
import cn.netinnet.educationcenter.dao.SysBatchStudentMapper;
import cn.netinnet.educationcenter.dao.SysExamSessionMapper;
import cn.netinnet.educationcenter.dao.SysUserMapper;
import cn.netinnet.educationcenter.domain.SysBatch;
import cn.netinnet.educationcenter.domain.SysUser;
import cn.netinnet.educationcenter.service.SysBatchService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


/**
 * @author yuyb
 * @date   2020-05-26
 */
@Service
public class SysBatchServiceImpl extends BaseService<SysBatch> implements SysBatchService {

    @Resource
    private SysBatchMapper sysBatchMapper;
    @Resource
    SysBatchStudentMapper sysBatchStudentMapper;
    @Resource
    SysExamSessionMapper sysExamSessionMapper;
    @Resource
    SysUserMapper sysUserMapper;

    @Override
    public int insertSelective(SysBatch sysBatch, long userId) {
        return sysBatchMapper.insertSelective(sysBatch);
    }
    @Override
    public Class getClazz(){
        return SysBatch.class;
    }

    /**  方法描述
     * @Description 查询批次列表
     * @Author yuyb
     * @Date 14:59 2020/5/26
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysBatch>
     **/
    @Override
    public List<SysBatch> queryList(Integer batchStatus, String batchName) {
        UserInfo userInfo = UserUtil.getUser();
        Long userId = userInfo.getUserId();
        Long schoolId = userInfo.getSchoolId();
        int userType = userInfo.getUserType();
        //学管查所有本校列表，教师查自己的，管理员可以查全部列表
        Long querySchoolId = userType == UserConstant.TEACHER || userType == UserConstant.MANAGER ? schoolId : null;
        Long queryUserId = userType == UserConstant.TEACHER ? userId : null;
        String batchNameLike = StringUtils.isBlank(batchName) ?
                null : "%" + batchName + "%";
        return sysBatchMapper.queryList(batchStatus, batchNameLike, querySchoolId, queryUserId);
    }

    /**  方法描述
     * @Description 新增批次
     * @Author yuyb
     * @Date 14:59 2020/5/26
     * @param sysBatch
     * @return void
     **/
    @Override
    public void addBatch(SysBatch sysBatch) {
        UserInfo userInfo = UserUtil.getUser();
        long userId = userInfo.getUserId();
        String userName = userInfo.getUserName();
        Long schoolId = userInfo.getSchoolId();
        if(schoolId == null){
            throw new CustomException("该用户没有归属的学校，请检查！");
        }
        Integer existBatch = sysBatchMapper.existBatch(null, userId, sysBatch.getBatchName());
        if(existBatch != null){
            throw new CustomException("该名称已存在！");
        }
        //新增批次
        SysBatch addBatch = new SysBatch();
        Long batchId = DateUtil.getUID();
        addBatch.setBatchId(batchId);
        addBatch.setBatchName(sysBatch.getBatchName());
        //创建人信息
        addBatch.setUserId(userId);
        addBatch.setUserName(userName);
        addBatch.setSchoolId(schoolId);
        //批次信息
        addBatch.setBeginDate(sysBatch.getBeginDate());
        addBatch.setEndDate(sysBatch.getEndDate());
        //批次状态-新建
        addBatch.setBatchStatus(ParaConstant.BATCH_STATUS_NEW);
        addBatch.setCreateUserId(userId);
        addBatch.setModifyUserId(userId);
        sysBatchMapper.insertSelective(addBatch);
    }

    /**  方法描述
     * @Description
     * @Author yuyb
     * @Date 14:59 2020/5/26
     * @param sysBatch
     * @return void
     **/
    @Override
    public void editBatch(SysBatch sysBatch) {
        UserInfo userInfo = UserUtil.getUser();
        long userId = userInfo.getUserId();
        Integer existBatch = sysBatchMapper.existBatch(sysBatch.getBatchId(), userId, sysBatch.getBatchName());
        if(existBatch != null){
            throw new CustomException("该名称已存在！");
        }
        //编辑批次
        SysBatch editBatch = new SysBatch();
        editBatch.setBatchId(sysBatch.getBatchId());
        editBatch.setBatchName(sysBatch.getBatchName());
        editBatch.setBeginDate(sysBatch.getBeginDate());
        editBatch.setEndDate(sysBatch.getEndDate());
        sysBatchMapper.updateByPrimaryKeySelective(editBatch);
    }

    /**  方法描述
     * @Description 删除批次
     * @Author yuyb
     * @Date 14:59 2020/5/26
     * @param batchId
     * @return void
     **/
    @Override
    public void deleteBatch(Long batchId) {
        SysBatch sysBatch = new SysBatch();
        sysBatch.setBatchId(batchId);
        sysBatch.setDelFlag(1);
        sysBatchMapper.updateByPrimaryKeySelective(sysBatch);
    }

    /**  方法描述
     * @Description 改变批次状态(0 - 新建, 1 - 已开始, 2 - 暂停中)
     * @Author yuyb
     * @Date 15:00 2020/5/26
     * @param batchId
     * @param status
     * @return void
     **/
    @Override
    public void updateBatchStatus(Long batchId, Integer status) {
        SysBatch sysBatch = new SysBatch();
        sysBatch.setBatchId(batchId);
        sysBatch.setBatchStatus(status);
        sysBatchMapper.updateByPrimaryKeySelective(sysBatch);
    }

    /**  方法描述
     * @Description 根据学校ID查询批次列表
     * @Author yuyb
     * @Date 15:00 2020/5/26
     * @param schoolId
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysBatch>
     **/
    @Override
    public List<SysBatch> queryListBySchoolId(Long schoolId) {
        return sysBatchMapper.queryListBySchoolId(schoolId);
    }

    /**  方法描述
     * @Description 根据学生用户id和学校ID查询所在批次列表
     * @Author yuyb
     * @Date 11:45 2020/6/12
     * @param userId
     * @param schoolId
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @Override
    public List<Map<String, Object>> queryBatchByUserIdAndSchoolId(Long userId, Long schoolId, Integer batchStatus) {
        Date now = new Date();
        return sysBatchMapper.queryBatchByUserIdAndSchoolId(Arrays.asList(userId), schoolId, now, batchStatus);
    }

    /***
    * 查询批次相关数据信息
    * @param batchId  批次id
    * @author ousp
    * @date 2020/8/26
    * @return java.util.Map<java.lang.String,java.lang.Object>
    */
    @Override
    public Map<String, Object> batchDataDetail(long batchId) {
        SysBatch sysBatch = sysBatchMapper.selectByPrimaryKey(batchId);
        if (sysBatch == null) {
            throw new CustomException("批次不存在！");
        }
        Map<String, Object> detail = new HashMap<>(8);
        detail.put("batchName", sysBatch.getBatchName());
        detail.put("beginDate", sysBatch.getBeginDate());
        detail.put("endDate", sysBatch.getEndDate());
        List<Map<String, Object>> count = sysExamSessionMapper.countByType(batchId);
        detail.put("trainNum", 0);
        detail.put("taskNum", 0);
        detail.put("examNum", 0);
        count.forEach(m -> {
            if (Integer.parseInt(m.get("session_type").toString()) == ParaConstant.SESSION_TYPE_TRAIN) {
                detail.put("trainNum", m.get("num"));
            } else if (Integer.parseInt(m.get("session_type").toString()) == ParaConstant.SESSION_TYPE_TASK) {
                detail.put("taskNum", m.get("num"));
            } else if (Integer.parseInt(m.get("session_type").toString()) == ParaConstant.SESSION_TYPE_EXAM) {
                detail.put("examNum", m.get("num"));
            }
        });
        // 学生数
        detail.put("stuNum", sysBatchStudentMapper.countByBatchId(batchId));
        return detail;
    }

    /***
    * dif用户查询批次列表
    * @author ousp
    * @date 2020/10/22
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    */
    @Override
    public List<Map<String,Object>> difStudentBatch(long schoolId, long individualityId, Integer batchStatus) {
        // 查询用户
        List<SysUser> users = sysUserMapper.queryRelByIndividualityKey(null,null,Arrays.asList(individualityId));
        if (!users.isEmpty()) {
            List<Long> userIds = new ArrayList<>();
            Map<Long, String> cuMap = new HashMap<>();
            users.forEach(u -> {
                userIds.add(u.getUserId());
                cuMap.put(u.getUserId(), u.getClassName());
            });
            Date now = new Date();
            // 查询用户所在批次
            List<Map<String,Object>>  batchList = sysBatchMapper.queryBatchByUserIdAndSchoolId(userIds, schoolId, now, batchStatus);
            // 统计批次信息
            if (!batchList.isEmpty()) {
                List<Long> batchIds = new ArrayList<>();
                batchList.forEach(m -> batchIds.add((Long)m.get("batchId")));
                Map<Long, Long> countMap = new HashMap<>();
                sysBatchStudentMapper.countByBatchIds(batchIds).forEach(m -> countMap.put((Long)m.get("batchId"), (Long) m.get("num")));
                batchList.forEach(m -> {
                    m.put("num", countMap.get((Long)m.get("batchId")));
                    m.put("className", cuMap.get((Long)m.get("userId")));
                    m.put("batchState", String.valueOf(m.get("batchState")));
                });
                return batchList;
            }
        }
        return Collections.emptyList();
    }

    /**  方法描述
     * @Description 查询用户的批次
     * @Author yuyb
     * @Date 16:05 2020/10/22
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @Override
    public List<Map<String, Object>> batchList(Long schoolId, Long userId, Integer batchStatus, String batchName) {
        Date now = new Date();
        if(!StringUtil.isBlankOrNull(batchName)){
            batchName = "%" + batchName + "%";
        }
        return sysBatchMapper.batchList(schoolId, userId, batchStatus, batchName, now);
    }
}
