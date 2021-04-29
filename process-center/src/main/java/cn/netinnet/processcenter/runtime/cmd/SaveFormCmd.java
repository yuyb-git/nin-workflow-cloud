package cn.netinnet.processcenter.runtime.cmd;

import cn.netinnet.processcenter.runtime.Command;
import cn.netinnet.processcenter.runtime.RuntimeManager;

/**
 * @ClassName 保存表单数据命令
 * @Description
 * @Author yuyb
 * @Date 2021/4/25 14:01
 */
public class SaveFormCmd implements Command {

    /** 接收者对象，执行具体业务 */
    private RuntimeManager runtimeManager;

    public SaveFormCmd(RuntimeManager runtimeManager) {
        this.runtimeManager = runtimeManager;
    }

    @Override
    public void execute() {
        runtimeManager.saveForm();
    }

}
