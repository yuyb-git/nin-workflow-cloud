package cn.netinnet.processcenter.runtime;

/**
 * @ClassName RuntimeManager 抽象接收者对象,执行具体业务
 * @Description
 * @Author yuyb
 * @Date 2021/4/25 13:32
 */
public abstract class RuntimeManager {

    /** 开启新流程 */
    public abstract void startProcInst();

    /** 保存表单数据 */
    public abstract void saveForm();

    /** 完成当前任务 */
    public abstract void completeTask();

    /** 创建审批意见 */
    public abstract void createApproval();

    /** 创建下一步任务 */
    public abstract void createNextTasks();

    /** 创建消息 */
    public abstract void createMessages();

    /** 结束流程 */
    public abstract void endProcInst();

}
