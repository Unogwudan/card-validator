package com.unogwudan.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
@Getter
@Setter
@ToString
public class BaseResponse<T> extends ResponseEntity<ResponseWrapper<T>> {
    public BaseResponse(T body) {
        super(new ResponseWrapper<>(body), HttpStatus.OK);
    }

    public BaseResponse(HttpStatus status) {
        super(status);
    }

    public BaseResponse(T body, HttpStatus status) {
        super(new ResponseWrapper<>(body, status), status);
    }

    public BaseResponse(T body, String message, HttpStatus status) {
        super(new ResponseWrapper<>(body, message), status);
    }


    public BaseResponse(T body, String message) {
        super(new ResponseWrapper<>(body, message), HttpStatus.OK);
    }


    public BaseResponse(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

    public BaseResponse(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(new ResponseWrapper<>(body), headers, status);
    }

    public BaseResponse(T body, BindingResult bindingResult, String message) {
        super(new ResponseWrapper<>(body, bindingResult, message), HttpStatus.BAD_REQUEST);
    }


}
