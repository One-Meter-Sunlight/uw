package com.example.demo.exception.common;

import lombok.Getter;

@Getter
public class MvcException extends RuntimeException {

    private MvcExceptionFactor exceptionFactor = CommonMvcExceptionFactor.INTERNAL_ERROR;

    public MvcException() {
    }

    public MvcException(MvcExceptionFactor exceptionFactor) {
        this.exceptionFactor = exceptionFactor;
    }
}
