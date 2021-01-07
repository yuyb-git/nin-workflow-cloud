package cn.netinnet.cloudcommon.globol;


import cn.netinnet.cloudcommon.constant.GlobalConstant;

import java.io.Serializable;

/**
 * @author chen.wb
 * @version V1.0
 * @Description: 统一的返回数据格式
 * <p>
 * ok方法：
 * ok(), ok(String msg), ok(String msg, Object data)
 * <p>
 * error方法：
 * error(String msg), error(int code, String msg), error(int code, String msg, Object data)
 * <p>
 * customize方法：
 * customize(ResultEnum resultEnum), customize(ResultEnum resultEnum, Object data)
 * @Date 2017-11-30
 **/
public class HttpResultEntry implements Serializable {

    // 编码
    private int code;
    // 信息
    private String msg;
    // 返回数据
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {

        if (this.msg == null) {
            return null;
        }
        return msg.length() > 350 ? msg.substring(0, 350) : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 请求成功
     *
     * @return
     */
    public static HttpResultEntry ok() {
        return new HttpResultEntry(GlobalConstant.SUCCESS, GlobalConstant.SUCCESS_MSG);
    }

    /**
     * 请求成功
     *
     * @param msg 返回的提示信息
     * @return
     */
    public static HttpResultEntry ok(String msg) {
        return new HttpResultEntry(GlobalConstant.SUCCESS, msg);
    }

    /**
     * 请求成功
     *
     * @param msg  返回的提示信息
     * @param data 返回的数据
     * @return
     */
    public static HttpResultEntry ok(String msg, Object data) {
        return new HttpResultEntry(GlobalConstant.SUCCESS, msg, data);
    }

    /**
     * 请求失败
     *
     * @param msg 返回的提示信息
     * @return
     */
    public static HttpResultEntry error(String msg) {
        return new HttpResultEntry(GlobalConstant.FAILURE, msg);
    }

    /**
     * 请求失败
     *
     * @param code 失败信息编码
     * @param msg  返回的提示信息
     * @return
     */
    public static HttpResultEntry error(int code, String msg) {

        return new HttpResultEntry(code, msg);
    }

    /**
     * 请求失败
     *
     * @param code 失败信息编码
     * @param msg  返回的提示信息
     * @param data 返回的数据
     * @return
     */
    public static HttpResultEntry error(int code, String msg, Object data) {
        return new HttpResultEntry(code, msg, data);
    }

    /**
     * 自定义http返回结果
     *
     * @param resultEnum 结果集枚举
     * @return
     */
    public static HttpResultEntry customize(ResultEnum resultEnum) {
        return new HttpResultEntry(resultEnum.getCode(), resultEnum.getMsg());
    }

    /**
     * 自定义http返回结果
     *
     * @param resultEnum 结果集枚举
     * @param data       返回的json串
     * @return
     */
    public static HttpResultEntry customize(ResultEnum resultEnum, Object data) {
        return new HttpResultEntry(resultEnum.getCode(), resultEnum.getMsg(), data);
    }

    public HttpResultEntry() {
        super();
    }

    private HttpResultEntry(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    private HttpResultEntry(int code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResultEntry{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
