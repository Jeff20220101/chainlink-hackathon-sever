package com.scope.common.base;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.scope.common.exception.BaseException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: juvenile
 * Date: 2018/11/2
 * Time: 11:36
 * Description: BaseResponse
 */
@Data
@ApiModel("")
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse implements Serializable {

    @ApiModelProperty(value = "")
    private int code;
    @ApiModelProperty(value = "")
    private String message;
    @ApiModelProperty(value = "")
    private Object data;
    @ApiModelProperty(value = "")
    private long uuid;
    @ApiModelProperty(value = "")
    private Long _t;


    public BaseResponse(Throwable throwable) {
        super();
        this.code = BaseResponseCode.NO.getCode();
        this.message = throwable.getMessage();
    }

    public BaseResponse(BaseResponseCode baseResponseCode) {
        super();
        this.code = baseResponseCode.getCode();
        this.message = baseResponseCode.getMessage();
    }

    /**
     */
    public BaseResponse() {
        this.code = BaseResponseCode.OK.getCode();
    }

    /**
     *
     */
    public BaseResponse(Object data) {
        this.data = data;
        this.code = BaseResponseCode.OK.getCode();
    }

    /**
     *
     */
    public BaseResponse(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static BaseResponse error() {
        return BaseResponse.builder()
                .code(BaseResponseCode.EXCEPTION.getCode())
                .build();
    }

    public static BaseResponse error(String message) {
        return BaseResponse.builder()
                .code(BaseResponseCode.EXCEPTION.getCode())
                .message(message)
                .build();
    }

    public static BaseResponse error(String message, long uuid) {
        return BaseResponse.builder()
                .code(BaseResponseCode.EXCEPTION.getCode())
                .uuid(uuid)
                .message(message)
                .build();
    }

    public static BaseResponse error(BaseException e) {
        return BaseResponse.builder()
                .code(e.getBaseResponseCode().getCode())
                .message(e.getMessage())
                .build();
    }

    public static BaseResponse errorZh(BaseException e) {
        String messageZh = e.getBaseResponseCode().getMessageZh();
        String message = e.getBaseResponseCode().getMessage();
        String message1 = e.getMessage();
        String s;
        if (message.equals(message1)) {
            s = messageZh;
        } else {
            s = message1;
        }
        /*String s = e.getMessage().replace(message, "");
        s = messageZh + s;*/
        return BaseResponse.builder()
                .code(e.getBaseResponseCode().getCode())
                .message(s)
                .build();
    }

    public static BaseResponse OK() {
        return BaseResponse.builder()
                .code(BaseResponseCode.OK.getCode())
                .build();
    }

    public static BaseResponse OK(Object o) {
        return BaseResponse.builder()
                .code(BaseResponseCode.OK.getCode())
                .data(o)
                .build();
    }

    @Override
    public String toString() {
        return JSONObject.toJSON(this).toString();
    }
}
