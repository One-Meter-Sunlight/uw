package com.example.demo.utility;


import com.example.demo.constants.ResponseCodeConstants;
import com.example.demo.model.CustomResponseBody;
import com.example.demo.model.PageResult;

public class ResponseWrapper {

    public static Object wrap(Object body) {
        if (body == null) {
            // when return type of controller method is void
            CustomResponseBody<Object> responseBody = new CustomResponseBody<>();
            responseBody.setResultCode(ResponseCodeConstants.OK);
            return responseBody;
        } else if (body instanceof CustomResponseBody) {
            // already processed by ExceptionHandler
            if (!((CustomResponseBody) body).getResultCode().equalsIgnoreCase(ResponseCodeConstants.OK)) {
                return body;
            }
        } else if (body instanceof PageResult) {
            PageResult page = (PageResult) body;
            CustomResponseBody<Object> responseBody = new CustomResponseBody<>();
            responseBody.setContents(page.getRecords());
            responseBody.setTotalRecords(page.getTotalElements());
            responseBody.setTotalPages(page.getTotalPages());
            responseBody.setCurrentPage(page.getCurrentPage());
            responseBody.setResultCode(ResponseCodeConstants.OK);
            return responseBody;
        } else {
            CustomResponseBody<Object> responseBody = new CustomResponseBody<>();
            responseBody.setResultCode(ResponseCodeConstants.OK);
            responseBody.setContents(body);
            return responseBody;
        }

        return body;
    }

}
