package com.scope.common.exception.handler;

import com.scope.common.base.BaseResponseCode;
import com.scope.common.exception.BadRequestException;
import com.scope.common.exception.BaseException;
import com.scope.common.exception.EntityExistException;
import com.scope.common.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import javax.annotation.Resource;
import java.util.Objects;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity handleException(Throwable e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return buildResponseEntity(ApiError.error("system exception"));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity handleMethodNotSupportException(Throwable e) {
//        log.error(ExceptionUtils.getStackTrace(e));
        return buildResponseEntity(ApiError.error("HttpRequestMethodNotSupported"));
    }

    /**
     */
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ApiError> BaseException(BaseException e) {
        // 打印堆栈信息
        if (BaseResponseCode.REQUEST_TOO_FREQUENT != e.getBaseResponseCode()) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return buildResponseEntity(ApiError.error(e.getMessage()));
    }

    /**
     */
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiError> badRequestException(BadRequestException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getStatus(), e.getMessage()));
    }

    /**
     */
    @ExceptionHandler(value = EntityExistException.class)
    public ResponseEntity<ApiError> entityExistException(EntityExistException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getMessage()));
    }

    /**
     */
   /* @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ApiError> AccessDeniedException(AccessDeniedException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return buildResponseEntity(ApiError.error(""));
    }*/

    /**
     * EntityNotFound
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ApiError> entityNotFoundException(EntityNotFoundException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return buildResponseEntity(ApiError.error(NOT_FOUND.value(), e.getMessage()));
    }

    /**
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        log.error(ExceptionUtils.getStackTrace(e));
        String[] str = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getCodes())[1].split("\\.");
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        if ("不能为空".equals(message)) {
            message = str[1] + ":" + message;
        }
        return buildResponseEntity(ApiError.error(message));
    }

    /**
     * @param ex 
     */
    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public void handleAsyncRequestTimeout(AsyncRequestTimeoutException ex) {
        /*try {
            emitter.send(SseEmitter.event().name("error").data(ex.getMessage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        emitter.completeWithError(ex);*/

        /*String stackTrace = ExceptionUtils.getStackTrace(ex);
        if (stackTrace.length() > 500) {
            stackTrace = stackTrace.substring(0, 500);
        }
        webhookUtils.feiShuWebhook("url:/api/chat/v2,inParam:,ErrorInfo:" + stackTrace);*/
    }

    /**
     */
    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }
}
