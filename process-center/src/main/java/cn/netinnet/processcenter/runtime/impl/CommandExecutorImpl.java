package cn.netinnet.processcenter.runtime.impl;

import cn.netinnet.processcenter.runtime.Command;
import cn.netinnet.processcenter.runtime.CommandExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CommandExecutorImpl 命令调用者
 * @Description
 * @Author yuyb
 * @Date 2021/4/25 10:11
 */
public class CommandExecutorImpl implements CommandExecutor {

    private List<Command> commandList = new ArrayList<>();

    @Override
    public void addCommand(Command command){
        commandList.add(command);
    }

    @Override
    public void executeCommand() {
        for(Command command : commandList){
            command.execute();
        }
    }
}
