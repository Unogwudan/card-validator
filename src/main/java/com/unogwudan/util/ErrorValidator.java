package com.unogwudan.util;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
public class ErrorValidator {

    public static Map<String, Object> errors (MethodArgumentNotValidException methodArgumentNotValid){
        Map <String, Object> errors = new HashMap<>();
        for(FieldError fieldError : methodArgumentNotValid.getBindingResult().getFieldErrors()) {
            String fieldName = fieldError.getField();
            if(fieldError.getField().contains("data.")){
                fieldName = fieldName.replace("data.","");
            }
            errors.put(fieldName, CaseConverter.capitalize(fieldName)+" "+fieldError.getDefaultMessage());
        }
        return errors.keySet().isEmpty() ? null : errors;
    }
}
