package com.alipay.sofa.common.util;

public class Result<T> extends CommonResult {
    private static final long serialVersionUID = 579178995823150578L;
    private T data;

    public Result() {
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> failed(String errorMsg) {
        Result<T> result = new Result();
        ErrorContext errorContext = new ErrorContext();
        errorContext.setErrorReason(errorMsg);
        result.setErrorContext(errorContext);
        result.setSuccess(false);
        return result;
    }
    //e.getMessage()
    public static <T> Result<T> failed(Exception e) {
        Result<T> result = new Result();
        ErrorContext errorContext = new ErrorContext();
        errorContext.setErrorReason(e.toString());
        result.setErrorContext(errorContext);
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> failed(ErrorContext errorContext) {
        Result<T> result = new Result();
        result.setErrorContext(errorContext);
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> failed(ErrorContext errorContext,T data) {
        Result<T> result = new Result();
        result.setErrorContext(errorContext);
        result.setSuccess(false);
        result.setData(data);
        return result;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
