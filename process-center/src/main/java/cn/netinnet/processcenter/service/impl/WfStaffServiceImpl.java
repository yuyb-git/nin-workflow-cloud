package cn.netinnet.processcenter.service.impl;

import cn.netinnet.cloudcommon.constant.CacheConstant;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.exception.CustomException;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.globol.ResultEnum;
import cn.netinnet.cloudcommon.utils.CommonUtil;
import cn.netinnet.cloudcommon.utils.RedisUtil;
import cn.netinnet.common.base.BaseService;
import cn.netinnet.common.util.DateUtil;
import cn.netinnet.common.util.StringUtil;
import cn.netinnet.processcenter.dao.WfDeptMapper;
import cn.netinnet.processcenter.dao.WfPositionMapper;
import cn.netinnet.processcenter.dao.WfStaffMapper;
import cn.netinnet.processcenter.domain.WfDept;
import cn.netinnet.processcenter.domain.WfPosition;
import cn.netinnet.processcenter.domain.WfStaff;
import cn.netinnet.processcenter.service.WfStaffService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * @author yuyb
 * @date 2020-04-09
 */
@Service
public class WfStaffServiceImpl extends BaseService<WfStaff> implements WfStaffService {

    @Resource
    WfStaffService wfStaffService;
    @Resource
    private WfStaffMapper wfStaffMapper;
    @Resource
    private WfPositionMapper wfPositionMapper;
    @Resource
    WfDeptMapper wfDeptMapper;

    @Override
    public int updateByPrimaryKeySelective(WfStaff wfStaff, long l) {
        return 0;
    }

    @Override
    public int insertSelective(WfStaff wfStaff, long userId) {
        return wfStaffMapper.insertSelective(wfStaff);
    }

    @Override
    public Class getClazz() {
        return WfStaff.class;
    }


    /** 方法描述
     * @description 员工列表
     * @param deptId
     * @param searchItem
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author Caicm
     * @date 2020/4/13 14:28
     */
    @Override
    public List<Map<String, Object>> queryStaffList(Long deptId, String searchItem) {
        if (!StringUtil.isBlankOrNull(searchItem)) {
            searchItem = "%" + searchItem + "%";
        }
        List<Map<String, Object>> staffList = new ArrayList<>();
        if (deptId != null) {
            staffList = wfStaffMapper.queryStaffList(deptId, searchItem);
        }
        return staffList;
    }

    /** 方法描述
     * @description 编辑员工
     * @param staff
     * @param userId
     * @return void
     * @author Caicm
     * @date 2020/4/13 14:28
     */
    @Override
    public void editStaff(WfStaff staff, Long userId){
        Long staffId = staff.getStaffId();
        Long companyId = staff.getCompanyId();
        staff.setModifyTime(new Date());
        staff.setModifyUserId(userId);
        Integer existStaff = wfStaffMapper.queryExistStaff(staff.getJobNumber(), companyId, staffId);
        if (existStaff != null && existStaff > 0 ) {
            throw new CustomException("存在相同工号的员工，请重新输入工号！", GlobalConstant.FAILURE);
        }
        if (staffId == null) {
            staffId = DateUtil.getUID();
            staff.setStaffId(staffId);
            staff.setCreateTime(new Date());
            staff.setCreateUserId(userId);
            staff.setEnable(1);
            wfStaffMapper.insertSelective(staff);
        } else {
            wfStaffMapper.updateByPrimaryKeySelective(staff);
        }
        //如果有缓存，就删除
        String key = CacheConstant.DEPT_TREE + companyId.toString();
        if(RedisUtil.hasKey(key)){
            RedisUtil.del(key);
        }
    }

    /** 方法描述
     * @description 员工操作（启用、禁用）
     * @param op
     * @param staffId
     * @return void
     * @author Caicm
     * @date 2020/4/13 14:28
     */
    @Override
    public void changeStaffEnable(String op, Long staffId){
        switch (op) {
            case "on":
                wfStaffMapper.changeStaffEnableStatus(1, staffId);
                break;
            case "off":
                wfStaffMapper.changeStaffEnableStatus(0, staffId);
                break;
            default:
                break;
        }
    }

    /** 方法描述
     * @description 导出员工模板
     * @param companyId
     * @return org.apache.poi.hssf.usermodel.HSSFWorkbook
     * @author Caicm
     * @date 2020/4/15 9:22
     */
    @Override
    public HSSFWorkbook exportStaffTemplate(Long companyId) {
        //获取岗位名称
        List<String> positionNameList = wfPositionMapper.getPositionNameByCompanyId(companyId);
        //创建hssfworkbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表sheet
        HSSFSheet sheet = workbook.createSheet("导入员工模板");
        HSSFRow row = sheet.createRow(0);
        String[] positionNames = positionNameList.toArray(new String[positionNameList.size()]);
        //设置数据有效性
        DVConstraint constraint = DVConstraint.createExplicitListConstraint(positionNames);
        //设置第4列前500行都为下拉框（设置数据有效性加载在哪些单元格上）
        CellRangeAddressList region = new CellRangeAddressList(1, 500, 3, 3);
        //数据有效性对象
        HSSFDataValidation dataValidation = new HSSFDataValidation(region, constraint);
        sheet.addValidationData(dataValidation);
        HSSFCell cell;
        //设置单元格样式
        HSSFCellStyle cellStyle = setCellStyle(workbook, (short) 13, true, true);
        HSSFCellStyle rowStyle = setCellStyle(workbook, (short) 11, false, true);
        rowStyle.setWrapText(true);
        //首行标题
        String[] title = new String[]{"姓名", "工号", "部门", "岗位", "电话", "邮箱"};
        String[] rowTwoArr = new String[]{"张三", "000001", "总经办", "总经理", "", "", ""};
        String[] rowThreeArr = new String[]{"李四", "000001", "总经办#研发中心", "研发经理", "", "", ""};
        String[] rowFourArr = new String[]{"王五", "000001", "总经办#研发中心#设计分中心", "设计主管", "", "", ""};
        String[] rowFiveArr = new String[]{"陈六", "000001", "总经办#研发中心#设计分中心", "设计师", "", "", ""};
        int[] columnWidth = new int[]{3000, 3000, 10000, 3000, 3000, 3000};
        int titleLength = title.length;
        for (int i = 0; i < titleLength; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(title[i]);
            //设置单元格宽度
            sheet.setColumnWidth(i, columnWidth[i]);
        }
        String descr = "模板说明：\r\n1、表格中已有员工信息为示例，请在使用时删除；\r\n2、部门信息需要完整描述层次关系，层级间请用#号隔开，如：总经办#研发中心#设计分中心。";
        cell = row.createCell(6);
        cell.setCellStyle(rowStyle);
        cell.setCellValue(new HSSFRichTextString(descr));
        //设置单元格宽度
        sheet.setColumnWidth(6, 18000);
        HSSFRow rowTwo = sheet.createRow(1);
        for (int i = 0; i < rowTwoArr.length; i++) {
            HSSFCell rowTwoCell = rowTwo.createCell(i);
            rowTwoCell.setCellStyle(rowStyle);
            rowTwoCell.setCellValue(rowTwoArr[i]);
        }
        HSSFRow rowThree = sheet.createRow(2);
        for (int i = 0; i < rowThreeArr.length; i++) {
            HSSFCell rowTwoCell = rowThree.createCell(i);
            rowTwoCell.setCellStyle(rowStyle);
            rowTwoCell.setCellValue(rowThreeArr[i]);
        }
        HSSFRow rowFour = sheet.createRow(3);
        for (int i = 0; i < rowFourArr.length; i++) {
            HSSFCell rowTwoCell = rowFour.createCell(i);
            rowTwoCell.setCellStyle(rowStyle);
            rowTwoCell.setCellValue(rowFourArr[i]);
        }
        HSSFRow rowFive = sheet.createRow(4);
        for (int i = 0; i < rowFiveArr.length; i++) {
            HSSFCell rowTwoCell = rowFive.createCell(i);
            rowTwoCell.setCellStyle(rowStyle);
            rowTwoCell.setCellValue(rowFiveArr[i]);
        }
        //构建一行说明
        sheet.addMergedRegion(new CellRangeAddress(0, 4, 6, 6));

        return workbook;
    }

    /** 方法描述
     * @description 设置单元格style
     * @param workbook
     * @param fontSize
     * @param isBlod
     * @param isAlign
     * @return org.apache.poi.hssf.usermodel.HSSFCellStyle
     * @author Caicm
     * @date 2020/5/9 17:38
     */
    private HSSFCellStyle setCellStyle(HSSFWorkbook workbook, short fontSize, boolean isBlod, boolean isAlign) {
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        //是否水平居中
        if (isAlign) {
            //设置水平居中
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        }
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont font = workbook.createFont();
        if (isBlod) {
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        }
        font.setFontHeightInPoints(fontSize);
        cellStyle.setFont(font);
        return cellStyle;
    }



    /** 方法描述
     * @description 删除员工
     * @param staffIdList
     * @return java.lang.String
     * @author Caicm
     * @date 2020/4/13 14:31
     */
    @Override
    public String deleteStaff(Long companyId, List<Long> staffIdList) {
        int totalCount = staffIdList.size();
        //没用被使用的员工存在时，才删除员工。
        if (!staffIdList.isEmpty()) {
            wfStaffMapper.batchDeleteByList(staffIdList);
        }
        int successCount = staffIdList.size();
        int failCount = totalCount - successCount;
        StringBuilder msgSb = new StringBuilder("成功删除").append(successCount).append("个员工");
        if(failCount > 0){
            msgSb.append(",").append("失败").append(failCount).append("个（").append("员工已在流程中使用）！");
        }
        return msgSb.toString();
    }

    /** 方法描述
     * @description 检验员工导入信息
     * @param list
     * @param positionMap
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     * @author Caicm
     * @date 2020/4/15 14:37
     */
    private List<String> checkStaffInfo(JSONArray list, Map<String,Long> positionMap, List<String> emptyRows) {
        List<String> errList = new ArrayList<>();
        //工号map,检验是否重复
        Map<String, String> jobNumberMap = new HashMap<>(16);
        //工号重复信息
        List<String> jobNumberRepeat = new ArrayList<>();
        StringBuilder err;
        JSONObject staff;
        String rows, jobNumber, staffName, dept, poisition, tel, email;
        String telReg = "^1[3|4|5|7|8]\\d{9}$";
        String emailReg = "^[A-Za-z0-9]+([_\\.][A-Za-z0-9]+)*@([A-Za-z0-9\\-]+\\.)+[A-Za-z]{2,6}$";
        for (int i = 0; i < list.size(); i++) {
            err = new StringBuilder();
            staff = list.getJSONObject(i);
            rows = staff.getString("rows");
            jobNumber = staff.getString("jobNumber");
            staffName = staff.getString("staffName");
            dept = staff.getString("dept");
            poisition = staff.getString("position");
            tel = staff.getString("tel");
            email = staff.getString("email");
            boolean isRowEmpty = StringUtil.isBlankOrNull(staffName) && StringUtil.isBlankOrNull(jobNumber) &&
                    StringUtil.isBlankOrNull(dept) && StringUtil.isBlankOrNull(poisition);
            if(isRowEmpty){
                emptyRows.add(rows);
                continue;
            }
            //判断员工姓名是否为空
            if (StringUtil.isBlankOrNull(staffName)) {
                err.append("第").append(rows).append("行 第A列").append("信息为空；");
            }
            //判断员工姓名长度
            if (staffName.length() > 20) {
                err.append("第").append(rows).append("行 第A列").append("姓名长度不能大于20；");
            }
            //判断工号是否为空
            if(StringUtil.isBlankOrNull(jobNumber)){
                err.append("第").append(rows).append("行 第B列").append("信息为空；");
            }
            //判断工号长度
            if (jobNumber.length() > 6) {
                err.append("第").append(rows).append("行 第B列").append("工号长度不能大于6位；");
            }
            if (StringUtil.isBlankOrNull(dept)) {
                err.append("第").append(rows).append("行 第C列").append("部门信息为空；");
            }
            //判断岗位是否为空
            if (StringUtil.isBlankOrNull(poisition)) {
                err.append("第").append(rows).append("行 第D列").append("岗位信息为空；");
            }
            if (!positionMap.containsKey(poisition)){
                err.append("第").append(rows).append("行 第D列").append("岗位不存在于该企业；");
            }
            if(!StringUtil.isBlankOrNull(tel)){
                if(!Pattern.matches(telReg, tel)){
                    err.append("第").append(rows).append("行 第E列").append("电话格式错误；");
                }
            }
            if(!StringUtil.isBlankOrNull(email)){
                if(!Pattern.matches(emailReg, email)){
                    err.append("第").append(rows).append("行 第F列").append("邮箱格式错误；");
                }
            }
            // 添加错误信息
            if (err.length() > 0) {
                errList.add(err.toString());
            }
            CommonUtil.judgeLoginRepeat(jobNumberMap, null, jobNumberRepeat, jobNumber, rows);
        }
        //如果存在重复用户名，则添加重复信息
        if (!jobNumberRepeat.isEmpty()) {
            CommonUtil.genErrInfo(jobNumberMap, jobNumberRepeat, errList, "A", "信息重复");
        }
        return errList;
    }

    /** 方法描述
     * @description 批量导入员工
     * @param jsonArray
     * @param userId
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     * @author Caicm
     * @date 2020/4/15 15:52
     */
    @Override
    public HttpResultEntry importStaff(JSONArray jsonArray, Long companyId, Long userId){
        /*Integer existEmployedProc = wfProcDefMapper.existDeployedProcByCompany(companyId);
        if(existEmployedProc != null){
            return HttpResultEntry.error("企业下有已设计的流程，不能再导入部门员工数据！");
        }*/
        List<WfPosition> wfPositions = wfPositionMapper.getAllPositionByCompanyId(companyId);
        Map<String, Long> positionMap = wfPositions.stream().collect(Collectors.toMap(WfPosition::getPositionName, WfPosition::getPositionId));
        List<String> emptyRows = new ArrayList<>();
        List<String> errList = checkStaffInfo(jsonArray, positionMap, emptyRows);
        if(!errList.isEmpty()){
            return HttpResultEntry.customize(ResultEnum.R_IMPORT_CHECK, errList);
        }
        List<Long> deptIds = wfDeptMapper.queryDeptIdsByCompanyId(companyId);
        List<Long> staffIds = wfStaffMapper.queryStaffIdsByCompanyId(companyId);
        Long firstDeptId = deptIds.get(0);
        deptIds.remove(firstDeptId);
        List<WfStaff> wfStaffs = new ArrayList<>();
        List<WfDept> depts = new ArrayList<>();
        Map<String, Long> deptMap = new HashMap<>(16);
        WfStaff wfStaff;
        Date rightNow = new Date();
        JSONObject jsonObject;
        String deptString;
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            String rows = jsonObject.getString("rows");
            if(emptyRows.contains(rows)){
                continue;
            }
            deptString = jsonObject.getString("dept").trim();
            String[] deptArr = deptString.split("#");
            long staffDeptId = genImportDeptData(companyId, firstDeptId, userId, deptArr, depts, deptMap);
            wfStaff = new WfStaff();
            wfStaff.setStaffId(DateUtil.getUID());
            wfStaff.setJobNumber(jsonObject.getString("jobNumber").trim());
            wfStaff.setStaffName(jsonObject.getString("staffName").trim());
            wfStaff.setTel(jsonObject.getString("tel"));
            wfStaff.setEmail(jsonObject.getString("email"));
            wfStaff.setCompanyId(companyId);
            wfStaff.setDeptId(staffDeptId);
            wfStaff.setPositionId(positionMap.get(jsonObject.getString("position").trim()));
            wfStaff.setEnable(1);
            wfStaff.setCreateUserId(userId);
            wfStaff.setModifyUserId(userId);
            wfStaff.setCreateTime(rightNow);
            wfStaff.setModifyTime(rightNow);
            wfStaffs.add(wfStaff);
        }
        wfStaffService.importStaffTransaction(staffIds, deptIds, depts, wfStaffs);
        return HttpResultEntry.ok("导入成功！");
    }

    /**  方法描述
     * @Description 导入部门员工事务
     * @Author yuyb
     * @Date 10:14 2020/9/10
     * @return void
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importStaffTransaction(List<Long> staffIds, List<Long> deptIds, List<WfDept> depts, List<WfStaff> wfStaffs){
        if(!staffIds.isEmpty()){
            wfStaffMapper.batchDeleteByList(staffIds);
        }
        if(!deptIds.isEmpty()){
            wfDeptMapper.batchDeleteByList(deptIds);
        }
        wfDeptMapper.batchInsertSelective(depts);
        wfStaffMapper.batchInsertSelective(wfStaffs);
    }

    /**  方法描述
     * @Description 构造导入的部门
     * @Author yuyb
     * @Date 10:14 2020/9/10
     * @return long
     **/
    private long genImportDeptData(long companyId, Long firstDeptId, long userId, String[] deptArr,
                                   List<WfDept> depts, Map<String, Long> deptMap){
        int deptLength = deptArr.length;
        long lastDeptId = 0;
        for(int i = 0; i < deptLength; i ++){
            String deptName = deptArr[i];
            if(StringUtil.isBlankOrNull(deptName)){
                continue;
            }
            String key = deptName + "|" + i;
            if(deptMap.containsKey(key)){
                lastDeptId = deptMap.get(key);
                continue;
            }
            WfDept dept = new WfDept();
            Long deptId = DateUtil.getUID();
            if(i == deptLength - 1){
                lastDeptId = deptId;
            }
            deptMap.put(key, deptId);
            dept.setDeptId(deptId);
            dept.setDeptName(deptName);
            dept.setCompanyId(companyId);
            dept.setLevel(i+2);
            dept.setParent(i == 0 ? firstDeptId : deptMap.get(deptArr[i-1] + "|" + (i-1)));
            dept.setUserId(userId);
            depts.add(dept);
        }
        return lastDeptId;
    }

    /**
     *  查询员工姓名
     * @param staffIds  员工id
     * @return java.util.List<cn.netinnet.processcenter.domain.WfStaff>
     * @author ousp
     * @date 2020/4/24
     */
    @Override
    public Map<Long, WfStaff> queryNameAndJobNumMap(Set<Long> staffIds) {
        List<WfStaff> wfStaffs = wfStaffMapper.queryStaffNameAndJobNum(staffIds);
        if (wfStaffs == null || wfStaffs.isEmpty()) {
            return new HashMap<>(0);
        }
        Map<Long, WfStaff> nameMap = new HashMap<>(16);
        wfStaffs.forEach(wfStaff -> nameMap.put(wfStaff.getStaffId(), wfStaff));
        return nameMap;
    }

    /***
    * 查询企业下所有员工
    * @author ousp
    * @date 2020/12/7
    * @return java.util.List<cn.netinnet.processcenter.domain.WfStaff>
    */
    @Override
    public List<WfStaff> getAllStaffByCompanyId(long companyId) {
        return wfStaffMapper.getAllStaffByCompanyId(companyId);
    }
}
