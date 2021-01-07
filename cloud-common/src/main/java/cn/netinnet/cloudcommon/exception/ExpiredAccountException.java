package cn.netinnet.cloudcommon.exception;


import org.apache.shiro.authc.AccountException;

/**
 \* @Author: Linjj
 \* @Date: 2019/8/20 14:44
 \* @Description: 失效登录异常
 \*/
public class ExpiredAccountException extends AccountException {

    public ExpiredAccountException() {
    }

    public ExpiredAccountException(String message) {
        super(message);
    }

    public ExpiredAccountException(Throwable cause) {
        super(cause);
    }

    public ExpiredAccountException(String message, Throwable cause) {
        super(message, cause);
    }

}