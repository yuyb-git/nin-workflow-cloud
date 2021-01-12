package cn.netinnet.processcenter.service.impl;

import cn.netinnet.cloudcommon.constant.CacheConstant;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.exception.CustomException;
import cn.netinnet.cloudcommon.utils.RedisUtil;
import cn.netinnet.common.base.BaseService;
import cn.netinnet.common.util.DateUtil;
import cn.netinnet.processcenter.dao.WfDeptMapper;
import cn.netinnet.processcenter.dao.WfPositionMapper;
import cn.netinnet.processcenter.dao.WfStaffMapper;
import cn.netinnet.processcenter.domain.WfDept;
import cn.netinnet.processcenter.domain.WfPosition;
import cn.netinnet.processcenter.service.WfDeptService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author yuyb
 * @date   2020-04-09
 */
@Service
public class WfDeptServiceImpl extends BaseService<WfDept> implements WfDeptService {

    @Resource
    private WfDeptMapper wfDeptMapper;
    @Resource
    private WfStaffMapper wfStaffMapper;
    @Resource
    private WfPositionMapper wfPositionMapper;

    @Override
    public int updateByPrimaryKeySelective(WfDept wfDept, long l) {
        return 0;
    }

    @Override
    public int insertSelective(WfDept wfDept, long userId) {
        return wfDeptMapper.insertSelective(wfDept);
    }
    @Override
    public Class getClazz(){
        return WfDept.class;
    }

    /** 方法描述
     * @description 编辑部门
     * @param wfDept
     * @param userId
     * @return void
     * @author Caicm
     * @date 2020/4/13 14:31
     */
    @Override
    public void editDept(WfDept wfDept, Long userId) {
        wfDept.setModifyTime(new Date());
        Long deptId = wfDept.getDeptId();
        Long companyId = wfDept.getCompanyId();
        Long parent = wfDept.getParent();
        String deptName = wfDept.getDeptName().trim();
        //验证同级同名部门是否存在
        Integer countDeptExist = wfDeptMapper.countDeptExist(companyId, deptId, deptName, parent);
        if(countDeptExist != null){
            throw new CustomException("该企业已经存在同级重名部门了！", GlobalConstant.FAILURE);
        }
        if (deptId == null) {
            wfDept.setDeptId(DateUtil.getUID());
            wfDept.setUserId(userId);
            wfDeptMapper.insertSelective(wfDept);
        } else {
            //更新时设置level为null,防止传level，出现错误level情况
            wfDept.setLevel(null);
            wfDeptMapper.updateByPrimaryKeySelective(wfDept);
        }
        //如果有缓存，就删除
        String key = CacheConstant.DEPT_TREE + wfDept.getCompanyId().toString();
        if(RedisUtil.hasKey(key)){
            RedisUtil.del(key);
        }
    }

    /** 方法描述
     * @description 删除部门
     * @param deptId
     * @return void
     * @author Caicm
     * @date 2020/4/13 14:31
     */
    @Override
    public void deleteDept(Long deptId) {
       List<WfDept> wfDepts = wfDeptMapper.getChildrenDept(deptId);
       //判断是否存在子部门,存在则提示请先删除掉子部门
       if(!wfDepts.isEmpty()){
           throw new CustomException("该部门存在下级部门，请先删除掉下级部门！", GlobalConstant.FAILURE);
       }
       List<Long> staffIds = wfStaffMapper.queryStaffIdsByDeptId(deptId);
       if(!staffIds.isEmpty()){
           throw new CustomException("该部门存在员工，请先将员工移出部门！", GlobalConstant.FAILURE);
       }
       wfDeptMapper.deleteByPrimaryKey(deptId);
    }

    /** 方法描述
     * @description 获取企业组织树（包含员工）
     * @param companyId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Caicm
     * @date 2020/5/8 15:48
     */
    @Override
    @Cacheable(value = CacheConstant.DEPT_TREE,  key = "#root.target.getDepatTreeCacheKey() + #companyId ", condition = "#useCache == true")
    public Map<String, Object> getDeptTreeWithStaff(Long companyId, boolean useCache) {
        List<Map<String, Object>> deptMap = wfDeptMapper.getAllDeptByCompanyIdUseAlias(companyId);
        if (deptMap.isEmpty()) {
            return Collections.EMPTY_MAP;
        }

        Map<Long, List<Map<String, Object>>>  staffMap =  wfStaffMapper.getAllStaffByCompanyIdUseAlias(companyId).stream()
                .collect(Collectors.groupingBy(e -> Long.valueOf(String.valueOf(e.get("deptId")))));

        Map<Long, List<Map<String, Object>>> treeMap = deptMap.stream()
                .collect(Collectors.groupingBy(e -> Long.valueOf(String.valueOf(e.get("parentId")))));

        Map<Long, String> positionNameMap = wfPositionMapper.getAllPositionByCompanyId(companyId)
                .stream().collect(Collectors.toMap(WfPosition::getPositionId, WfPosition::getPositionName));

        Long itemId;
        List<Map<String, Object>> tempList1, tempList2;
        int staffNum;
        Map<String, Object> dept;
        for (int i = deptMap.size() - 1; i >= 0; i --) {
            dept = deptMap.get(i);
            itemId = Long.valueOf(String.valueOf(dept.get("itemId")));
            dept.put("itemType", 1);
            // 员工
            tempList1 = staffMap.get(itemId);
            if (tempList1 != null && !tempList1.isEmpty()) {
                dept.put("staffNum", "" + tempList1.size() + "");
                dept.put("list", tempList1);
                for (Map<String, Object> tempMap : tempList1) {
                    tempMap.put("position", positionNameMap.get(Long.valueOf(String.valueOf(tempMap.get("position")))));
                    tempMap.put("deptName", dept.get("itemName"));
                    tempMap.put("itemType", 2);
                }
            } else {
                dept.put("staffNum", "0");
            }
            // 岗位
            tempList1 = treeMap.get(itemId);
            if (tempList1 != null && !tempList1.isEmpty()) {
                tempList2 = (List<Map<String, Object>>) dept.computeIfAbsent("list", k -> new ArrayList<Map<String, Object>>());
                tempList2.addAll(tempList1);
                // 加上子部门的员工个数
                staffNum = Integer.parseInt((String) dept.get("staffNum"));
                for (Map<String, Object> tempMap : tempList1) {
                    staffNum += Integer.parseInt(tempMap.get("staffNum").toString());
                }
                dept.put("staffNum", staffNum);
            }
        }
        return treeMap.get(0L).get(0);
    }

    public String getDepatTreeCacheKey() {
        return CacheConstant.DEPT_TREE;
    }

    /** 方法描述
     * @description 获取组织架构数据子树
     * @param depts
     * @param deptMap
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author Caicm
     * @date 2020/5/9 17:07
     */
    private List<Map<String,Object>> getDeptChildrenTree(List<WfDept> depts, Map<Long,List<WfDept>> deptMap){
        List<Map<String,Object>> childTree = new ArrayList<>();
        for(WfDept dept: depts){
            Map<String,Object> map = new HashMap<>(8);
            map.put("deptId", dept.getDeptId());
            map.put("deptName", dept.getDeptName());
            map.put("parentId",dept.getParent());
            map.put("level", dept.getLevel());
            map.put("judgeResult", dept.getJudgeResult());
            List<WfDept> childList = deptMap.get(dept.getDeptId());
            if(childList!=null) {
                map.put("list", getDeptChildrenTree(childList,deptMap));
            }else{
                map.put("list", null);
            }
            childTree.add(map);
        }
        return childTree;
    }
}