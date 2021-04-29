package cn.netinnet.processcenter.runtime.cmd;

import cn.netinnet.processcenter.runtime.Command;
import cn.netinnet.processcenter.runtime.RuntimeManager;

/**
 * @ClassName CreateApprovalCmd
 * @Description
 * @Author yuyb
 * @Date 2021/4/26 9:08
 */
public class CreateApprovalCmd implements Command {

    /** 接收者对象，执行具体业务 */
    private RuntimeManager runtimeManager;

    public CreateApprovalCmd(RuntimeManager runtimeManager) {
        this.runtimeManager = runtimeManager;
    }

    @Override
    public void execute() {
        runtimeManager.createApproval();
    }
}
