package cn.netinnet.processcenter.runtime.impl;

import cn.netinnet.cloudcommon.dto.ExamInfo;
import cn.netinnet.processcenter.domain.WfRunTask;
import cn.netinnet.processcenter.dto.StaffInfo;
import cn.netinnet.processcenter.runtime.CommandDataManager;
import cn.netinnet.processcenter.runtime.RuntimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName RuntimeServiceImpl
 * @Description
 * @Author yuyb
 * @Date 2021/4/25 10:09
 */
@Service
public class RuntimeServiceImpl implements RuntimeService {

    @Resource
    RuntimeService runtimeService;


    @Override
    public void startProcInstById(ExamInfo examInfo, StaffInfo staffInfo, Long procDefId, String formData) {

    }

    @Override
    public void approvalAccess(ExamInfo examInfo, StaffInfo staffInfo, WfRunTask currentRunTask, String approvalOpinion) {

    }

    @Override
    public void doTransaction(CommandDataManager commandDataManager) {

    }
}
