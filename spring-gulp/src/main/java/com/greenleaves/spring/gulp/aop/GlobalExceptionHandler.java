package com.greenleaves.spring.gulp.aop;

import com.greenleaves.spring.gulp.model.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sgdn001 on 9/27/2016.
 */
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ErrorDTO commonExceptionHandler(Exception exception) {
        logger.error(exception.getMessage());
        exception.printStackTrace();
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrCode("500");
        errorDTO.setErrMessage(exception.getMessage());
        return errorDTO;
    }

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseStatus(value= HttpStatus.UNAUTHORIZED)
    public ErrorDTO authenticationExceptionHandler(Exception exception) {
        logger.error(exception.getMessage());
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrCode("401");
        errorDTO.setErrMessage(exception.getMessage());
        return errorDTO;
    }
}
