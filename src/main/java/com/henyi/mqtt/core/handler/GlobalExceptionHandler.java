package com.henyi.mqtt.core.handler;

import com.henyi.mqtt.core.domain.R;
import com.henyi.mqtt.core.utils.ValidateTipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 全局异常处理器
 *
 * @author henyi
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<?> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                    HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestUri, e.getMethod(), e);
        return R.fail(e.getMessage());
    }


    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public R<?> handleException(Exception e, HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常,原因为{}", requestUri, e.getMessage(), e);
        return R.fail(e.getMessage());
    }


    /**
     * 验证Dto参数异常
     */
    @ExceptionHandler(BindException.class)
    public R<?> bindExceptionException(BindException e, HttpServletRequest request) {
        String validateElStart = "${";
        String validateElEnd = "}";
        String requestUri = request.getRequestURI();
        String field = Objects.requireNonNull(e.getBindingResult().getFieldError()).getField();
        Object[] arguments = Objects.requireNonNull(e.getBindingResult().getFieldError()).getArguments();
        String typeName = Objects.requireNonNull(e.getBindingResult().getFieldError()).getCode();
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        assert message != null;
        if (message.startsWith(validateElStart) && message.lastIndexOf(validateElEnd) == message.length() - 1) {
            message = message.substring(2, message.length() - 1);
            assert typeName != null;
            message = ValidateTipUtil.validate(typeName, message, arguments);
        }
        log.error("请求地址'{}',验证Dto参数异常,原因为:{}", requestUri, message, e);
        return R.fail(message, field);
    }


}
