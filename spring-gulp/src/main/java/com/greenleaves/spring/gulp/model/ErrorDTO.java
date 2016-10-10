package com.greenleaves.spring.gulp.model;

/**
 * Created by sgdn001 on 9/28/2016.
 */
public class ErrorDTO {
    private String errCode;
    private String errMessage;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
