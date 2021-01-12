/*
 * SysLog.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-04-07 Created
 */
package cn.netinnet.logcenter.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2020-04-07
 **/
public class SysLog implements Serializable {
    /**
     * 记录id
     */
    private Long logId;

    /**
     * 用户类别,0-后台管理员;1-学校管理员;2-教师;3-学生
     */
    private Integer userType;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 操作名称
     */
    private String operationName;

    /**
     * 操作耗时
     */
    private Long spendTime;

    /**
     * 请求接口
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 请求ip
     */
    private String requestIp;

    /**
     * 记录生成时间
     */
    private Date createTime;

    private static final long serialVersionUID = 2729132703374077952L;

    /**
     * 获取记录id
     *
     * @return log_id - 记录id
     */
    public Long getLogId() {
        return logId;
    }

    /**
     * 设置记录id
     *
     * @param logId 记录id
     */
    public void setLogId(Long logId) {
        this.logId = logId;
    }

    /**
     * 获取用户类别,0-后台管理员;1-学校管理员;2-教师;3-学生
     *
     * @return user_type - 用户类别,0-后台管理员;1-学校管理员;2-教师;3-学生
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 设置用户类别,0-后台管理员;1-学校管理员;2-教师;3-学生
     *
     * @param userType 用户类别,0-后台管理员;1-学校管理员;2-教师;3-学生
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名称
     *
     * @return user_name - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取操作名称
     *
     * @return operation_name - 操作名称
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * 设置操作名称
     *
     * @param operationName 操作名称
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    /**
     * 获取操作耗时
     *
     * @return spend_time - 操作耗时
     */
    public Long getSpendTime() {
        return spendTime;
    }

    /**
     * 设置操作耗时
     *
     * @param spendTime 操作耗时
     */
    public void setSpendTime(Long spendTime) {
        this.spendTime = spendTime;
    }

    /**
     * 获取请求接口
     *
     * @return request_url - 请求接口
     */
    public String getRequestUrl() {
        return requestUrl;
    }

    /**
     * 设置请求接口
     *
     * @param requestUrl 请求接口
     */
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    /**
     * 获取请求参数
     *
     * @return request_params - 请求参数
     */
    public String getRequestParams() {
        return requestParams;
    }

    /**
     * 设置请求参数
     *
     * @param requestParams 请求参数
     */
    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    /**
     * 获取请求ip
     *
     * @return request_ip - 请求ip
     */
    public String getRequestIp() {
        return requestIp;
    }

    /**
     * 设置请求ip
     *
     * @param requestIp 请求ip
     */
    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    /**
     * 获取记录生成时间
     *
     * @return create_time - 记录生成时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置记录生成时间
     *
     * @param createTime 记录生成时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logId=").append(logId);
        sb.append(", userType=").append(userType);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", operationName=").append(operationName);
        sb.append(", spendTime=").append(spendTime);
        sb.append(", requestUrl=").append(requestUrl);
        sb.append(", requestParams=").append(requestParams);
        sb.append(", requestIp=").append(requestIp);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}