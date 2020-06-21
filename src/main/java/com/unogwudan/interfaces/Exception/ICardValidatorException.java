package com.unogwudan.interfaces.Exception;

import com.unogwudan.dto.response.ResponseWrapper;

import java.util.Map;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
public interface ICardValidatorException {
    String getMessage();
    String getCode();
    Map<String, Object> getValidation();

    default ResponseWrapper<Object> tvpResponseWrapper() {
        ResponseWrapper<Object> response = new ResponseWrapper<>();
        response.setMessage(this.getMessage());
        response.setValidation(this.getValidation());
        response.setStatusCode(Integer.valueOf(this.getCode()));
        return response;
    }
}
