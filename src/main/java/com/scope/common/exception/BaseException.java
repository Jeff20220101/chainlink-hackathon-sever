package com.scope.common.exception;


import com.scope.common.base.BaseResponseCode;

/**
 * Created with IntelliJ IDEA.
 * User: juvenile
 * Date: 17-12-12
 * Time: 下午4:40
 * Description:  统一异常
 */
public class BaseException extends RuntimeException {
    private BaseResponseCode baseResponseCode = BaseResponseCode.EXCEPTION;

    public BaseResponseCode getBaseResponseCode() {
        return baseResponseCode;
    }

    public void setBaseResponseCode(BaseResponseCode baseResponseCode) {
        this.baseResponseCode = baseResponseCode;
    }

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(BaseResponseCode baseResponseCode) {
        super(baseResponseCode.getMessage());
        this.baseResponseCode = baseResponseCode;
    }

    public BaseException(BaseResponseCode baseResponseCode, String message) {
        super(message);
        this.baseResponseCode = baseResponseCode;
    }

    public BaseException(BaseResponseCode baseResponseCode, Throwable cause) {
        super(baseResponseCode.getMessage(), cause);
        this.baseResponseCode = baseResponseCode;
    }


    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
