package se.stock.config;


import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import se.stock.vo.Response;

import javax.validation.ConstraintViolationException;

/**
 * @author jh
 * @date 2020/3/4
 */
@RestControllerAdvice
public class MyExceptionHandler {
    public static final String UNKNOWN_EXCEPTION = "未知错误";

    /**
     * validation get参数异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Response handleConstraintViolationException(ConstraintViolationException ex) {
        String msg = ex.getMessage();
        if (msg.indexOf(",") != -1) {
            msg = msg.substring(0, msg.indexOf(","));
        }
        ex.printStackTrace();
        return Response.buildFailure(msg.substring(msg.indexOf(":") + 2));
    }

    /**
     * validation post参数异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        // ex.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，则调用ex.getAllErrors()
        ex.printStackTrace();
        final FieldError error = ex.getBindingResult().getFieldError();
        String errorMessage;
        if (error != null) {
            errorMessage = error.getDefaultMessage();
        } else {
            errorMessage = ex.getMessage();
        }
        String msg = errorMessage.substring(errorMessage.indexOf(":") + 1);
        if (msg.indexOf(",") != -1) {
            msg = msg.substring(0, msg.indexOf(","));
        }
        return Response.buildFailure(msg);
    }

    /**
     * 全局未知错误
     */
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception ex) {
        ex.printStackTrace();
        return Response.buildFailure(ex.getMessage());
    }

}
