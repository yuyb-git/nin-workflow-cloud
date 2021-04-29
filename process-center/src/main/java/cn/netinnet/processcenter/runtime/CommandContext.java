package cn.netinnet.processcenter.runtime;

import cn.netinnet.processcenter.domain.WfProcNode;
import cn.netinnet.processcenter.domain.WfRunTask;
import cn.netinnet.processcenter.dto.StaffInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CommandContext
 * @Description
 * @Author yuyb
 * @Date 2021/4/25 11:10
 */
public class CommandContext {

    private long userId;

    private int userType;

    private long examId;

    private long questionId;

    private long procDefId;

    private long instId;

    private WfRunTask currentRunTask;

    private WfProcNode nextProcNode;

    private Map<Long, Long> taskIdMap = new HashMap<>();

    private long formId;

    private StaffInfo staffInfo;
    /** 发起人id */
    private long startStaffId;

    private String formData;
    private Long businessId;

    private String approvalOpinion;

    public CommandContext() {
    }

    public CommandContext(String formData) {
        this.formData = formData;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(long procDefId) {
        this.procDefId = procDefId;
    }

    public long getInstId() {
        return instId;
    }

    public void setInstId(long instId) {
        this.instId = instId;
    }

    public WfRunTask getCurrentRunTask() {
        return currentRunTask;
    }

    public void setCurrentRunTask(WfRunTask currentRunTask) {
        this.currentRunTask = currentRunTask;
    }

    public WfProcNode getNextProcNode() {
        return nextProcNode;
    }

    public void setNextProcNode(WfProcNode nextProcNode) {
        this.nextProcNode = nextProcNode;
    }

    public Map<Long, Long> getTaskIdMap() {
        return taskIdMap;
    }

    public void setTaskIdMap(Map<Long, Long> taskIdMap) {
        this.taskIdMap = taskIdMap;
    }

    public long getFormId() {
        return formId;
    }

    public void setFormId(long formId) {
        this.formId = formId;
    }

    public StaffInfo getStaffInfo() {
        return staffInfo;
    }

    public void setStaffInfo(StaffInfo staffInfo) {
        this.staffInfo = staffInfo;
    }

    public long getStartStaffId() {
        return startStaffId;
    }

    public void setStartStaffId(long startStaffId) {
        this.startStaffId = startStaffId;
    }

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
    }

}
