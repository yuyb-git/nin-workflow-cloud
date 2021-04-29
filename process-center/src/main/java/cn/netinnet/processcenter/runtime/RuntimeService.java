package cn.netinnet.processcenter.runtime;

import cn.netinnet.cloudcommon.dto.ExamInfo;
import cn.netinnet.processcenter.domain.WfRunTask;
import cn.netinnet.processcenter.dto.StaffInfo;

/**
 * @ClassName RuntimeService
 * @Description
 * @Author yuyb
 * @Date 2021/4/25 9:58
 */
public interface RuntimeService {

    void startProcInstById(ExamInfo examInfo, StaffInfo staffInfo, Long procDefId, String formData);

    void approvalAccess(ExamInfo examInfo, StaffInfo staffInfo, WfRunTask currentRunTask, String approvalOpinion);

    void doTransaction(CommandDataManager commandDataManager);

}
