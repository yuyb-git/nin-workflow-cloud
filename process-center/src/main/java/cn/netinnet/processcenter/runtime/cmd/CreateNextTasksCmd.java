package cn.netinnet.processcenter.runtime.cmd;

import cn.netinnet.processcenter.runtime.Command;
import cn.netinnet.processcenter.runtime.RuntimeManager;

/**
 * @ClassName CreateNextTasksCmd
 * @Description
 * @Author yuyb
 * @Date 2021/4/26 9:09
 */
public class CreateNextTasksCmd implements Command {

    /** 接收者对象，执行具体业务 */
    private RuntimeManager runtimeManager;

    public CreateNextTasksCmd(RuntimeManager runtimeManager) {
        this.runtimeManager = runtimeManager;
    }

    @Override
    public void execute() {
        runtimeManager.createNextTasks();
    }
}
