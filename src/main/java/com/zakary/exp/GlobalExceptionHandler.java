package com.zakary.exp;

import com.zakary.dao.JSONEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @ExceptionHandler
    @ResponseBody
    JSONEntity handleException(Exception e){
        logger.error(e.getMessage());
        e.printStackTrace();
        JSONEntity jsonEntity = new JSONEntity();
        jsonEntity.setSuccess(false);
        jsonEntity.setMsg("未知错误，操作失败");
        return jsonEntity;
    }
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    JSONEntity handleBusinessException(BusinessException e){
        logger.error(e.getMessage());
        JSONEntity jsonEntity = new JSONEntity();
        jsonEntity.setSuccess(false);
        jsonEntity.setMsg(e.getMessage());
        return jsonEntity;
    }
}
