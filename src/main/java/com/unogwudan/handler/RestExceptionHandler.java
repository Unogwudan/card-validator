package com.unogwudan.handler;

import com.unogwudan.constant.ExceptionKeyConstant;
import com.unogwudan.dto.response.ResponseWrapper;
import com.unogwudan.exception.CardValidatorException;
import com.unogwudan.exception.UnAuthorizedException;
import com.unogwudan.util.ErrorValidator;
import com.unogwudan.util.ExceptionCodeUtil;
import com.unogwudan.util.KeyValue;
import com.unogwudan.util.PropertyUtil;
import javassist.NotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.persistence.EntityNotFoundException;
import java.util.Map;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(CardValidatorException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(CardValidatorException e) {
        return errorResponse(e.toResponseWrapper());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(Exception exception) {
        return errorResponse(defaultExceptionResponseBuilder(exception));
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(TransactionSystemException e) {
        return errorResponse(defaultExceptionResponseBuilder(e));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(MethodArgumentNotValidException e) {
        return errorResponse(validationExceptionTransformer(e));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(EntityNotFoundException e) {
        return errorResponse(notFoundExceptionResponseBuilder(e));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(NoHandlerFoundException e) {
        return errorResponse(notFoundExceptionResponseBuilder(e));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(NotFoundException e) {
        return errorResponse(notFoundExceptionResponseBuilder(e));
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<String> handleException(UnAuthorizedException exception) {
        return errorResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(DataIntegrityViolationException exception) {
        return errorResponse(databaseExceptionResponseBuilder(exception));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(ConstraintViolationException exception) {
        return errorResponse(databaseExceptionResponseBuilder(exception));
    }

    private static ResponseEntity<String> errorResponse(String message, HttpStatus httpStatus) {
        return new ResponseEntity<>('"'+message+'"',httpStatus);
    }

    private static ResponseWrapper<Object> validationExceptionTransformer(MethodArgumentNotValidException e){
        KeyValue keyValue = ExceptionCodeUtil.data(ExceptionKeyConstant.VALIDATION_FAILURE);
        return errorResponseBuilder(keyValue.getCode(), keyValue.getMessage(), ErrorValidator.errors(e));
    }

    //Handler Helpers
    private static ResponseEntity<ResponseWrapper<Object>> errorResponse(ResponseWrapper<Object> responseWrapper) {
        return new ResponseEntity<>(responseWrapper,responseWrapper.getHttpStatus());
    }

    private static ResponseWrapper<Object> notFoundExceptionResponseBuilder(Exception e) {
        KeyValue keyValue = ExceptionCodeUtil.data(ExceptionKeyConstant.NOT_FOUND);
        return errorResponseBuilder(keyValue.getCode(), keyValue.getMessage(), null);
    }

    private static ResponseWrapper<Object> databaseExceptionResponseBuilder(Exception e){
        e.printStackTrace();
        KeyValue data ;
        data = ExceptionCodeUtil.data(ExceptionKeyConstant.CONSTRAINT_VIOLATION);
        return errorResponseBuilder(data.getCode(),data.getMessage(),null);
    }

    private static ResponseWrapper<Object> defaultExceptionResponseBuilder(Exception e){
        e.printStackTrace();
        String code;
        String msg;
        if (e instanceof ConstraintViolationException) {
            KeyValue keyValue = ExceptionCodeUtil.data(ExceptionKeyConstant.CONSTRAINT_VIOLATION);
            code = keyValue.getCode();
            msg = keyValue.getMessage();
        }
        else{
            code = ExceptionCodeUtil.code(ExceptionKeyConstant.VALIDATION_FAILURE);
            if(e.getMessage() != null) {
                msg = e.getMessage();
            }else {
               msg = PropertyUtil.msg("validation_failure");
            }

        }
        return errorResponseBuilder(code, msg,null);
    }

    private static ResponseWrapper<Object> errorResponseBuilder(String code,  String message, Map<String,Object> validation) {
        ResponseWrapper<Object> response = new ResponseWrapper<>();
        response.setSuccess(false);
        response.setStatusCode(Integer.valueOf(code));
        response.setMessage(message);
        response.setValidation(validation);
        return response;
    }


}
