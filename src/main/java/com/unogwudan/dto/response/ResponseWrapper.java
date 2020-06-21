package com.unogwudan.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unogwudan.constant.ExceptionKeyConstant;
import com.unogwudan.util.PropertyUtil;
import com.unogwudan.util.RequestUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.unogwudan.constant.ModelConstant.MESSAGE;


/**
 * Created by Daniel Unogwu on 21/06/20.
 */
@Getter
@Setter
@ToString
public class ResponseWrapper<T> {

    public boolean success = false;
    @JsonIgnore
    private int statusCode;
    @JsonIgnore
    private String message;

    {
        HttpServletRequest servletRequest = RequestUtil.getRequest();
        if (servletRequest != null) {
            if (StringUtils.isEmpty(servletRequest.getAttribute(MESSAGE))) {
                message = PropertyUtil.msg(ExceptionKeyConstant.SUCCESS);
            } else {
               Object messageObj = servletRequest.getAttribute(MESSAGE);
               if (messageObj != null) {
                   message = messageObj.toString();
               }
            }
        }
    }


    private T payload;

    @JsonIgnore
    private Map<String, Object> validation;

    @JsonIgnore
    private HttpStatus httpStatus = HttpStatus.OK;

    public ResponseWrapper() {
    }

    public ResponseWrapper(T body) {
        setSuccessParams(body);
    }

    private void setSuccessParams(T body, String message, HttpStatus status){
        setSuccess(status.is2xxSuccessful());
        setMessage(message);
        setStatusCode(status.value());
        setPayload(body);
        setValidation(null);
    }

    private void setSuccessParams(T body, String message){
        setSuccessParams(body, message, HttpStatus.OK);
    }

    private void setSuccessParams(T body){
        HttpServletRequest httpServletRequest = RequestUtil.getRequest();
        if (httpServletRequest != null) {
            httpServletRequest.setAttribute(MESSAGE, null);
        }
        if (body instanceof String) {
            message = (String) body;
        }
        setSuccessParams(body, message);
    }

    public ResponseWrapper(T body, String message) {
        setSuccessParams(body, message);
    }


    public ResponseWrapper(T body, HttpStatus status) {
        setSuccessParams(status.is2xxSuccessful() ? body : null, (String) body, status);
    }

    public ResponseWrapper(T body, BindingResult bindingResult, String message) {
        this(message);
        validation = new HashMap<>();
        List<FieldError> errors = bindingResult.getFieldErrors();
        errors.forEach(fieldError -> {
            validation.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        setPayload(body);
        setStatusCode(HttpStatus.BAD_REQUEST.value());
        setValidation(validation);
    }
  
    public ResponseWrapper(String message) {
        setSuccess(false);
        setMessage(message);
    }
}
