package cn.netinnet.processcenter.runtime;

import cn.netinnet.processcenter.domain.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TransactionManager
 * @Description
 * @Author yuyb
 * @Date 2021/4/25 14:39
 */
public class CommandDataManager {


    private WfProcInst insertInst;

    private WfProcInst updateInst;

    /** 插入起始任务 */
    private WfRunTask insertStartTask;

    private WfRunTask updateTask;

    private List<WfRunTask> insertTaskList = new ArrayList<>();

    private List<WfRunTaskPartner> insertTaskPartnerList = new ArrayList<>();

    private List<WfMessage> insertMessageList = new ArrayList<>();

    private WfApproval wfApproval;

    public WfProcInst getInsertInst() {
        return insertInst;
    }

    public void setInsertInst(WfProcInst insertInst) {
        this.insertInst = insertInst;
    }

    public WfProcInst getUpdateInst() {
        return updateInst;
    }

    public void setUpdateInst(WfProcInst updateInst) {
        this.updateInst = updateInst;
    }

    public WfRunTask getInsertStartTask() {
        return insertStartTask;
    }

    public void setInsertStartTask(WfRunTask insertStartTask) {
        this.insertStartTask = insertStartTask;
    }

    public WfRunTask getUpdateTask() {
        return updateTask;
    }

    public void setUpdateTask(WfRunTask updateTask) {
        this.updateTask = updateTask;
    }

    public List<WfRunTask> getInsertTaskList() {
        return insertTaskList;
    }

    public void setInsertTaskList(List<WfRunTask> insertTaskList) {
        this.insertTaskList = insertTaskList;
    }

    public List<WfRunTaskPartner> getInsertTaskPartnerList() {
        return insertTaskPartnerList;
    }

    public void setInsertTaskPartnerList(List<WfRunTaskPartner> insertTaskPartnerList) {
        this.insertTaskPartnerList = insertTaskPartnerList;
    }

    public List<WfMessage> getInsertMessageList() {
        return insertMessageList;
    }

    public void setInsertMessageList(List<WfMessage> insertMessageList) {
        this.insertMessageList = insertMessageList;
    }

    public WfApproval getWfApproval() {
        return wfApproval;
    }

    public void setWfApproval(WfApproval wfApproval) {
        this.wfApproval = wfApproval;
    }
}
