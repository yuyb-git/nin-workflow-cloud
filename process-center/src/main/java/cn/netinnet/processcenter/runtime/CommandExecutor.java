package cn.netinnet.processcenter.runtime;

/**
 * @ClassName CommandExecutor 命令调用者
 * @Description
 * @Author yuyb
 * @Date 2021/4/25 10:10
 */
public interface CommandExecutor {

    void addCommand(Command command);

    void executeCommand();

}
