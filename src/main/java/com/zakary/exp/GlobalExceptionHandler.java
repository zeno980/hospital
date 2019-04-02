package com.zakary.exp;

import com.zakary.dao.JsonResultDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @ExceptionHandler
    @ResponseBody
    JsonResultDao handleException(Exception e){
        logger.error(e.getMessage());
        e.printStackTrace();
        return new JsonResultDao(null,1,"未知错误，操作失败");
    }
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    JsonResultDao handleBusinessException(BusinessException e){
        logger.error(e.getMessage());
        return new JsonResultDao(null,1,e.getMessage());
    }
}
