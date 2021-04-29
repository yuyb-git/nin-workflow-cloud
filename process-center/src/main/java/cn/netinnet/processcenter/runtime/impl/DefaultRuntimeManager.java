package cn.netinnet.processcenter.runtime.impl;

import cn.netinnet.processcenter.runtime.CommandContext;
import cn.netinnet.processcenter.runtime.CommandDataManager;
import cn.netinnet.processcenter.runtime.RuntimeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName RuntimeManager 接收者对象,执行具体业务
 * @Description
 * @Author yuyb
 * @Date 2021/4/25 13:40
 */
public class DefaultRuntimeManager extends RuntimeManager {

    private static final Logger logger = LoggerFactory.getLogger(DefaultRuntimeManager.class);

    private CommandContext commandContext;

    private CommandDataManager commandDataManager;

    DefaultRuntimeManager(CommandContext commandContext, CommandDataManager commandDataManager) {
        this.commandContext = commandContext;
        this.commandDataManager = commandDataManager;
    }


    @Override
    public void startProcInst() {

    }

    @Override
    public void saveForm() {

    }

    @Override
    public void completeTask() {

    }

    @Override
    public void createApproval() {

    }

    @Override
    public void createNextTasks() {

    }

    @Override
    public void createMessages() {

    }

    @Override
    public void endProcInst() {

    }
}
