package cn.netinnet.processcenter.controller;

import cn.netinnet.cloudcommon.annotation.LogMark;
import cn.netinnet.cloudcommon.annotation.PreventRepeatSubmit;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.globol.ResultEnum;
import cn.netinnet.cloudcommon.utils.ExcelUtils;
import cn.netinnet.cloudcommon.utils.UserUtil;
import cn.netinnet.common.base.BaseController;
import cn.netinnet.processcenter.domain.WfPosition;
import cn.netinnet.processcenter.service.WfPositionService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuyb
 * @date   2020-04-09
 */
@RestController
@RequestMapping("/wfPosition")
public class WfPositionController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(WfPositionController.class);

    @Resource
    WfPositionService wfPositionService;

   /** 方法描述
    * @description 新增编辑岗位
    * @param wfPosition
    * @return cn.netinnet.workflow.common.global.HttpResultEntry
    * @author Caicm
    * @date 2020/4/20 14:52
    */
    @LogMark("新增编辑岗位")
    @PostMapping("/editPosition")
    @PreventRepeatSubmit
    public HttpResultEntry editPosition(@RequestBody WfPosition wfPosition) {
        UserInfo userInfo = UserUtil.getUser();
        long userId = userInfo.getUserId();
        wfPositionService.editPosition(wfPosition, userId);
        return HttpResultEntry.ok();
    }

    /** 方法描述
     * @description 岗位详情
     * @param positionId
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     * @author Caicm
     * @date 2020/4/20 14:52
     */
    @GetMapping("/positionDetail")
    public HttpResultEntry positionDetail(@RequestParam("positionId") Long positionId) {
        WfPosition wfPosition = wfPositionService.positionDetail(positionId);
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, wfPosition);
    }

    /** 方法描述
     * @description 删除岗位
     * @param positionId
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     * @author Caicm
     * @date 2020/4/20 15:04
     */
    @LogMark("删除岗位")
    @PostMapping("/deletePosition")
    @PreventRepeatSubmit
    public HttpResultEntry deletePosttion(@RequestParam("positionId") Long positionId) {
        wfPositionService.deletePosition(positionId);
        return HttpResultEntry.ok();
    }


    /**  方法描述
     * @Description 导入岗位
     * @Author yuyb
     * @Date 10:55 2020/9/14
     * @return cn.netinnet.workflow.common.global.HttpResultEntry
     **/
    @PostMapping("/importPosition")
    @PreventRepeatSubmit
    public HttpResultEntry importPosition(@RequestParam("file") MultipartFile file, Long companyId) {
        //文件为空时，返回为空提示
        if (file.isEmpty()) {
            return HttpResultEntry.customize(ResultEnum.R_UPLOAD_NULL);
        }
        String originalFileName = file.getOriginalFilename();
        String originalFileNameLowCase = originalFileName.toLowerCase();
        JSONObject data = new JSONObject();
        data.put("fileName", originalFileName);
        if (!(originalFileNameLowCase.endsWith(".xls") || originalFileNameLowCase.endsWith(".xlsx"))) {
            return HttpResultEntry.customize(ResultEnum.R_IS_NOT_XLS, data);
        }
        try {
            InputStream inputStream = file.getInputStream();
            Map<String, String> keyMap = new HashMap<>(8);
            keyMap.put("岗位名称", "positionName");
            keyMap.put("岗位编码", "positionCode");
            keyMap.put("上级岗位", "parent");

            JSONArray jsonArray = ExcelUtils.readExcel4Array(inputStream, originalFileName, keyMap);
            if (jsonArray == null || jsonArray.size() == 0) {
                return HttpResultEntry.customize(ResultEnum.R_FILE_ERR, data);
            }
            UserInfo userInfo = UserUtil.getUser();
            Long userId = userInfo.getUserId();
            return wfPositionService.importPosition(jsonArray, companyId, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultEntry.error(GlobalConstant.FAILURE, e.getMessage());
        }
    }

}


