package cn.netinnet.processcenter.service.impl;

import cn.netinnet.cloudcommon.base.BaseService;
import cn.netinnet.cloudcommon.constant.CacheConstant;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.exception.CustomException;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.globol.ResultEnum;
import cn.netinnet.cloudcommon.utils.CommonUtil;
import cn.netinnet.cloudcommon.utils.DateUtil;
import cn.netinnet.cloudcommon.utils.RedisUtil;
import cn.netinnet.cloudcommon.utils.StringUtil;
import cn.netinnet.processcenter.dao.WfPositionMapper;
import cn.netinnet.processcenter.dao.WfStaffMapper;
import cn.netinnet.processcenter.domain.WfPosition;
import cn.netinnet.processcenter.service.WfPositionService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


/**
 * @author yuyb
 * @date   2020-04-09
 */
@Service
public class WfPositionServiceImpl extends BaseService<WfPosition> implements WfPositionService {
    @Resource
    private WfPositionMapper wfPositionMapper;
    @Resource
    private WfStaffMapper  wfStaffMapper;
    /*@Resource
    private WfNodeAssigneeMapper wfNodeAssigneeMapper;
    @Resource
    WfErrorPointMapper wfErrorPointMapper;
    @Resource
    WfProcDefMapper wfProcDefMapper;*/

    @Override
    public int insertSelective(WfPosition wfPosition, long userId) {
        return wfPositionMapper.insertSelective(wfPosition);
    }
    @Override
    public Class getClazz(){
        return WfPosition.class;
    }


    /** 方法描述
     * @description 新增编辑岗位
     * @param wfPosition
     * @param userId
     * @return void
     * @author Caicm
     * @date 2020/4/20 14:51
     */
    @Override
    public void editPosition(WfPosition wfPosition, Long userId){
        Integer level = wfPosition.getLevel();
        Long parent = wfPosition.getParent();
        Long positionId = wfPosition.getPositionId();
        Long companyId = wfPosition.getCompanyId();
        String positionCode = wfPosition.getPositionCode();
        if(positionId == null) {
            //新增一级岗位时进行判断
            if (level == 1 && parent == 0L) {
                Integer firstPositionExist = wfPositionMapper.countFirstPositionExist(companyId);
                if (firstPositionExist > 0) {
                    throw new CustomException("该企业已经存在一级岗位了！", GlobalConstant.FAILURE);
                }
            }
        }
        String positionName = wfPosition.getPositionName().trim();
        Integer positionNameExist = wfPositionMapper.countPositionNameExist(companyId, positionName, positionId);
        //验证该企业是否存在同名岗位
        if (positionNameExist != null) {
            throw new CustomException("该企业已经存在同名的岗位！", GlobalConstant.FAILURE);
        }
        Integer positionCodeExist = wfPositionMapper.countPositionCodeExist(companyId, positionCode, positionId);
        // //验证该企业是否存在相同岗位编码岗位
        if(positionCodeExist != null){
            throw new CustomException("该企业已经存在岗位编码相同的岗位！", GlobalConstant.FAILURE);
        }
        wfPosition.setUserId(userId);
        wfPosition.setModifyTime(new Date());
        if (positionId == null) {
            positionId = DateUtil.getUID();
            wfPosition.setPositionId(positionId);
            wfPositionMapper.insertSelective(wfPosition);
        } else {
            wfPositionMapper.updateByPrimaryKeySelective(wfPosition);
        }
        //如果有缓存，就删除
        String key = CacheConstant.DEPT_TREE + companyId.toString();
        if(RedisUtil.hasKey(key)){
            RedisUtil.del(key);
        }
    }


    /** 方法描述
     * @description 获取岗位信息详情
     * @param positionId
     * @return cn.netinnet.processcenter.domain.WfPosition
     * @author Caicm
     * @date 2020/4/20 14:51
     */
    @Override
    public WfPosition positionDetail(Long positionId) {
        return wfPositionMapper.selectByPrimaryKey(positionId);
    }

    /** 方法描述
     * @description 删除岗位
     * @param positionId
     * @return void
     * @author Caicm
     * @date 2020/5/9 17:27
     */
    @Override
    public void deletePosition(Long positionId){
        List<Long> positionIds = wfPositionMapper.getPositionIdByParent(positionId);
        List<Long> positionList = new ArrayList<>();
        positionList.add(positionId);
        List<Long> staffIds = wfStaffMapper.getStaffIdListByPositionId(positionList, null);
        /*Integer existProcDef = wfNodeAssigneeMapper.countAssigneeExistProcNode(positionId);
        if(existProcDef != null ){
            throw  new CustomException("此岗位已经被流程使用,无法删除！",GlobalConstant.FAILURE);
        }*/
        if (positionIds != null && positionIds.size() > 0) {
            throw new CustomException("此岗位存在下级岗位，请先删除下级岗位！", GlobalConstant.FAILURE);
        }
        if (staffIds != null && staffIds.size() > 0) {
            throw new CustomException("存在任职此岗位的员工，请先修改员工岗位！", GlobalConstant.FAILURE);
        }
        wfPositionMapper.deleteByPrimaryKey(positionId);
    }

    /** 方法描述
     * @description 获取下级岗位级联信息
     * @param positions
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author Caicm
     * @date 2020/4/20 11:14
     */
    private List<Map<String, Object>> getPositionChildrenTree(List<WfPosition> positions, Map<Long, List<WfPosition>> positionMap) {
        List<Map<String, Object>> childTree = new ArrayList<>();
        for (WfPosition position : positions) {
            Map<String, Object> map = new HashMap<>(16);
            map.put("positionId", position.getPositionId());
            map.put("positionName", position.getPositionName());
            map.put("parentId", position.getParent());
            map.put("level", position.getLevel());
            map.put("positionCode", position.getPositionCode());
            map.put("judgeResult", position.getJudgeResult());
            List<WfPosition> childList = positionMap.get(position.getPositionId());
            if (childList != null) {
                map.put("list", getPositionChildrenTree(childList, positionMap));
            } else {
                map.put("list", null);
            }
            childTree.add(map);
        }
        return childTree;
    }

    /**  方法描述
     * @Description 导入岗位
     * @Author yuyb
     * @Date 10:20 2020/9/14
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResultEntry importPosition(JSONArray jsonArray, Long companyId, Long userId) {
        List<Long> positionIds = wfPositionMapper.queryPositionIdsByCompanyId(companyId);
        //岗位被使用，进行提示
        if (positionIds != null && positionIds.size() > 0) {
            List<Long> staffIds = wfStaffMapper.getStaffIdListByPositionId(positionIds, null);
            if (staffIds != null && staffIds.size() > 0) {
                return HttpResultEntry.error("现在岗位已被使用，替换失败！请在【员工管理】中删除员工后重试。");
            }
        }

        /*Integer existEmployedProc = wfProcDefMapper.existDeployedProcByCompany(companyId);
        if(existEmployedProc != null){
            return HttpResultEntry.error("企业下有已设计的流程，不能再导入岗位数据！");
        }*/
        Map<String, Long> positionIdMap = new HashMap<>(16);
        Map<String, String> parentPosition  = new HashMap<>(16);
        List<String> errList = checkPosition(jsonArray, parentPosition, positionIdMap);
        if(!errList.isEmpty()){
            return HttpResultEntry.customize(ResultEnum.R_IMPORT_CHECK, errList);
        }

        Date rightNow = new Date();
        JSONObject jsonObject;
        WfPosition position;
        String positionName, parent;
        List<WfPosition> positionList = new ArrayList<>();
        long positionId;
        for (int i = 0; i < jsonArray.size(); i++){
            jsonObject = jsonArray.getJSONObject(i);
            positionName = jsonObject.getString("positionName").trim();
            parent = jsonObject.getString("parent").trim();
            if(!positionIdMap.containsKey(parent) && !"0".equals(parent)){
                return HttpResultEntry.error("上级岗位"+parent+"不存在！");
            }
            int level = genImportPositionData(positionName, parentPosition);
            positionId = positionIdMap.get(positionName);
            position = new WfPosition();
            position.setPositionId(positionId);
            position.setPositionName(positionName);
            position.setPositionCode(jsonObject.getString("positionCode").trim());
            position.setCompanyId(companyId);
            position.setLevel(level);
            position.setParent("0".equals(parent)?0L:positionIdMap.get(parent));
            position.setUserId(userId);
            position.setModifyTime(rightNow);
            positionList.add(position);
        }
        if(!positionIds.isEmpty()){
            wfPositionMapper.batchDeleteByList(positionIds);
        }
        wfPositionMapper.batchInsertSelective(positionList);
        return HttpResultEntry.ok("导入成功！");
    }

    /**  方法描述
     * @Description 计算岗位level
     * @Author yuyb
     * @Date 10:58 2020/9/14
     * @return int
     **/
    private int genImportPositionData(String positionName, Map<String, String> parentPosition){
        int i = 1;
        String firstParent = parentPosition.get(positionName);
        boolean hasParent = !"0".equals(firstParent);
        while (hasParent){
            i++;
            String nextParent = parentPosition.get(firstParent);
            hasParent = !"0".equals(nextParent);
            firstParent = nextParent;
        }
        return i;
    }

    /**  方法描述
     * @Description 校验岗位数据
     * @Author yuyb
     * @Date 10:58 2020/9/14
     * @return java.util.List<java.lang.String>
     **/
    private List<String> checkPosition(JSONArray list, Map<String, String> parentPosition, Map<String, Long> positionIdMap) {
        List<String> errList = new ArrayList<>();
        //工号map,检验是否重复
        Map<String, String> repeatMap = new HashMap<>(16);
        //工号重复信息
        List<String> positionNameRepeat = new ArrayList<>();
        List<String> positionCodeRepeat = new ArrayList<>();
        StringBuilder err;
        JSONObject position;
        String rows, positionName, positionCode, parent;
        int rootPositionCount = 0;
        for (int i = 0; i < list.size(); i++) {
            err = new StringBuilder();
            position = list.getJSONObject(i);
            rows = position.getString("rows");
            positionName = position.getString("positionName");
            positionCode = position.getString("positionCode");
            parent = position.getString("parent");
            if("0".equals(parent)){
                rootPositionCount += 1;
            }
            parentPosition.put(positionName, parent);
            positionIdMap.put(positionName, DateUtil.getUID());
            //判断员工姓名是否为空
            if (StringUtil.isBlankOrNull(positionCode)) {
                err.append("第").append(rows).append("行 第A列").append("岗位编码为空；");
            }
            //判断员工姓名长度
            if (positionCode.length() > 20) {
                err.append("第").append(rows).append("行 第A列").append("岗位编码长度不能大于20；");
            }
            //判断工号是否为空
            if(StringUtil.isBlankOrNull(positionName)){
                err.append("第").append(rows).append("行 第B列").append("岗位名称信息为空；");
            }
            //判断工号长度
            if (positionName.length() > 6) {
                err.append("第").append(rows).append("行 第B列").append("岗位名称长度不能大于20位；");
            }
            if (StringUtil.isBlankOrNull(parent)) {
                err.append("第").append(rows).append("行 第C列").append("上级岗位信息为空；");
            }
            // 添加错误信息
            if (err.length() > 0) {
                errList.add(err.toString());
            }
            CommonUtil.judgeLoginRepeat(repeatMap, null, positionNameRepeat, positionName, rows);
            CommonUtil.judgeLoginRepeat(repeatMap, null, positionCodeRepeat, positionCode, rows);
        }
        if(rootPositionCount  == 0){
            errList.add("没有一级岗位；");
        }
        if(rootPositionCount > 1){
            errList.add("一级岗位只能有一个；");
        }
        if (!positionNameRepeat.isEmpty()) {
            CommonUtil.genErrInfo(repeatMap, positionNameRepeat, errList, "A", "信息重复");
        }if (!positionCodeRepeat.isEmpty()) {
            CommonUtil.genErrInfo(repeatMap, positionCodeRepeat, errList, "B", "信息重复");
        }
        return errList;
    }

}