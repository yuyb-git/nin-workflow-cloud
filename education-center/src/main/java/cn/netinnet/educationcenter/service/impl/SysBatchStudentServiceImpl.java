package cn.netinnet.educationcenter.service.impl;

import cn.netinnet.cloudcommon.exception.CustomException;
import cn.netinnet.cloudcommon.utils.UserUtil;
import cn.netinnet.common.base.BaseService;
import cn.netinnet.common.util.DateUtil;
import cn.netinnet.educationcenter.constant.ParaConstant;
import cn.netinnet.educationcenter.dao.SysBatchStudentMapper;
import cn.netinnet.educationcenter.dao.SysUserMapper;
import cn.netinnet.educationcenter.domain.SysBatchStudent;
import cn.netinnet.educationcenter.service.SysBatchStudentService;
import cn.netinnet.educationcenter.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author yuyb
 * @date   2020-05-26
 */
@Service
public class SysBatchStudentServiceImpl extends BaseService<SysBatchStudent> implements SysBatchStudentService {

    @Resource
    private SysBatchStudentMapper sysBatchStudentMapper;
    @Resource
    SysUserService sysUserService;
    @Resource
    SysUserMapper sysUserMapper;

    @Override
    public int updateByPrimaryKeySelective(SysBatchStudent sysBatchStudent, long l) {
        return 0;
    }

    @Override
    public int insertSelective(SysBatchStudent sysBatchStudent, long userId) {
        return sysBatchStudentMapper.insertSelective(sysBatchStudent);
    }
    @Override
    public Class getClazz(){
        return SysBatchStudent.class;
    }

    /**
     * @Author xiangra
     * @Date 2020/4/7/007 10:11
     * @Description删除批次学生
     */
    @Override
    public void deleteBatchStudentByIdList(List<Long> idList) {
        long modifyUserId = UserUtil.getUser().getUserId();
        Date now = new Date();
        //todo:后面删除批次学生时得添加限制，删除学生时，查询是否有开始做任务，根据学生端的相关数据表的数据进行判断
        List<SysBatchStudent> updateList = new ArrayList<>();
        for (Long batchStudentId : idList) {
            SysBatchStudent batchStudent = new SysBatchStudent();
            batchStudent.setBatchStudentId(batchStudentId);
            batchStudent.setDelFlag(1);
            batchStudent.setModifyUserId(modifyUserId);
            batchStudent.setModifyTime(now);
            updateList.add(batchStudent);
        }
        // 批量逻辑删除
        sysBatchStudentMapper.batchUpdateByPrimaryKey(updateList);
    }

    /**
     * @Author xiangra
     * @Date 2020/4/7/007 10:24
     * @Description 新增批次学生时：查询对应班级及其学生
     */
    @Override
    public List<Map<String, Object>> queryAddBatchStudentList(String projectCode, Long batchId, String className, Long userId) {
        Long schoolId = UserUtil.getUser().getSchoolId();
        //查询该批次下已存在的批次学生
        List<Long> studentIdList = new ArrayList<>();
        if(ParaConstant.PROJECT_CODE_DIF.equals(projectCode)){
            //dif产品查询不一样
            List<Long> individualIds = sysBatchStudentMapper.queryIndividualIdByBatchIdDif(batchId);
            if(!individualIds.isEmpty()){
                studentIdList = sysUserMapper.queryUserIdByIndividualityIds(individualIds);
            }
        }else{
            studentIdList = sysBatchStudentMapper.queryUserIdByBatchId(batchId);
        }
        return sysUserService.getUserGroupByClass(schoolId, className, studentIdList, projectCode, userId);
    }

    /**
     * @Author xiangra
     * @Date 2020/4/7/007 13:28
     * @Description 新增批次学生
     */
    @Override
    public void addBatchStudent(Long batchId, List<SysBatchStudent> userInfoList) {
        Long createUserId = UserUtil.getUser().getUserId();
        Date currentTime = new Date();
        //查询该批次下已存在的批次学生
        List<Long> studentIdList = sysBatchStudentMapper.queryUserIdByBatchId(batchId);
        //判断是否有重复新增学生
        List<Long> addUserIdList = userInfoList.stream().map(SysBatchStudent :: getUserId).collect(Collectors.toList());
        //重复idList
        List<Long> existUserIdList = studentIdList.stream().filter(addUserIdList::contains).collect(Collectors.toList());
        if (existUserIdList.size() > 0) {
            throw new CustomException("存在重复添加的学生，请检查！");
        }
        List<SysBatchStudent> finalList = new ArrayList<>();
        if (userInfoList.size() > 0) {
            for (SysBatchStudent addStudent : userInfoList) {
                addStudent.setBatchStudentId(DateUtil.getUID());
                addStudent.setBatchId(batchId);
                addStudent.setCreateUserId(createUserId);
                addStudent.setCreateTime(currentTime);
                addStudent.setModifyUserId(createUserId);
                addStudent.setModifyTime(currentTime);
                finalList.add(addStudent);
            }
        }
        // 删除之前删除的记录，避免删除后重新加入后，被定时器清理
        sysBatchStudentMapper.delLogicDeleteData(batchId, addUserIdList);
        sysBatchStudentMapper.batchInsertSelective(finalList);
    }

}
