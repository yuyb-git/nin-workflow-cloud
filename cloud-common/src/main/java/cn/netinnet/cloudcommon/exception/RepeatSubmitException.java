package cn.netinnet.cloudcommon.exception;


import cn.netinnet.cloudcommon.globol.ResultEnum;

/**
 \* @Author: Linjj
 \* @Date: 2019/6/25 15:39
 \* @Description: 
 \*/
public class RepeatSubmitException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;
    private int errorCode;

    public RepeatSubmitException(){
        super(ResultEnum.R_REPEAT_SUBMIT.getMsg());
        this.message = ResultEnum.R_REPEAT_SUBMIT.getMsg();
        this.errorCode = ResultEnum.R_REPEAT_SUBMIT.getCode();
    }

    public RepeatSubmitException(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    public RepeatSubmitException(Throwable cause) {
        super(cause);
    }

    public RepeatSubmitException(String message, int errorCode, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}