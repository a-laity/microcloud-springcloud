package com.imooc.exception;


import com.imooc.grace.result.GraceJSONResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xujunchen
 * @date 2023/5/18 23:11
 * @describe todo
 */
@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public GraceJSONResult handlerException(CustomException e) {
        e.printStackTrace();
        return GraceJSONResult.exception(e.getResponseStatusEnum());
    }
}
