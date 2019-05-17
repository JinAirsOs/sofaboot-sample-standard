package com.alipay.sofa.common.util;

import java.io.Serializable;

public class ErrorContext implements Serializable {

    private static final long serialVersionUID = 5435384052419317346L;

    private String errorReason;

    private String errorLocation;

    private Integer errorCode;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    public String getErrorLocation() {
        return errorLocation;
    }

    public void setErrorLocation(String errorLocation) {
        this.errorLocation = errorLocation;
    }

}
