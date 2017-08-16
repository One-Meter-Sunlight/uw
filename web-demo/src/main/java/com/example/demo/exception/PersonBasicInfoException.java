package com.example.demo.exception;

import com.example.demo.exception.common.MvcException;
import com.example.demo.exception.common.MvcExceptionFactor;
import com.example.demo.exception.common.MvcExceptionModel;

/**
 * person异常
 */
public class PersonBasicInfoException extends MvcException {

    public enum BasicInfoExceptionFactor implements MvcExceptionFactor {
        NO_PHONE_FAILURE(10001, "没有输入电话号码"),
        NO_PERSON_FAILURE(20001, "查找的用户不存在"),;

        private int index;
        private String reason;
        private String detailMsg;

        BasicInfoExceptionFactor(int index, String reason) {
            this.index = index;
            this.reason = reason;
            this.detailMsg = reason;
        }

        public MvcExceptionModel getExModel() {
            MvcExceptionModel model = new MvcExceptionModel();
            model.setHttpCode(404);
            model.setErrorCode(index);
            model.setErrorMsg(reason);
            model.setDetailMsg(detailMsg);
            return model;
        }
    }

    public PersonBasicInfoException(BasicInfoExceptionFactor error) {
        super(error);
    }
}
