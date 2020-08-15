package com.xiao5.twmall.product.exception;

import com.xiao5.twmall.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;

/**
 * 集中感知异常
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.xiao5.twmall.product.controller")
public class ProductExceptionControllerAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e){
        BindingResult result = e.getBindingResult();
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        result.getFieldErrors().forEach((item)->{
            map.put(item.getField(), item.getDefaultMessage());
        });
        return R.error().put("content", map);
    }

//    @ExceptionHandler(value = RuntimeException.class)
//    public R handleRunTimeExc(RuntimeException runtimeError){
//        return R.error().put("content", runtimeError.getMessage());
//    }
}
