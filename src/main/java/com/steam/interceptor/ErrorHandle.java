package com.steam.interceptor;

import com.steam.common.ErrorEnum;
import com.steam.common.SteamException;
import com.steam.model.vo.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : JOSE 2019/3/11 10:27 PM
 */
@ControllerAdvice
@Slf4j
public class ErrorHandle {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse errorHandle(HttpServletRequest request, Exception e) {
        log.error("业务异常:{}", e);
        int code = ErrorEnum.SYS_ERR.getCode();
        String message = ErrorEnum.SYS_ERR.getMessage();

        if(e instanceof SteamException) {
            SteamException error = (SteamException) e;
            code = error.getCode();
            message = error.getMessage();
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException error = (MethodArgumentNotValidException) e;
            code = ErrorEnum.REQUEST_PARAM_ERR.getCode();
            message = error.getMessage();
        }


        BaseResponse response = new BaseResponse();
        response.setMessage(message);
        response.setCode(code);
        return response;
    }
}
