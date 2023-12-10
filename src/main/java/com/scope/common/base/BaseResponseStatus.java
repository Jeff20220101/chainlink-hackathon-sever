package com.scope.common.base;

/**
 * Created with IntelliJ IDEA.
 * User: juvenile
 * Date: 17-12-13
 * Time: 下午3:30
 * Description:  响应状态
 */
public enum BaseResponseStatus {
    NO(0, "请求失败"),
    OK(1, "请求成功");

    private final int code;
    private final String message;

    BaseResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
