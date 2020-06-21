package com.unogwudan.exception;

import com.unogwudan.constant.ExceptionKeyConstant;
import com.unogwudan.dto.response.ResponseWrapper;
import com.unogwudan.interfaces.Exception.IAppException;
import com.unogwudan.interfaces.Exception.ICardValidatorException;
import com.unogwudan.util.ExceptionCodeUtil;
import com.unogwudan.util.KeyValue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
@Getter
@Setter
@ToString
public class CardValidatorException extends RuntimeException implements ICardValidatorException, IAppException {
    private String code = ExceptionKeyConstant.UNKNOWN;

    private String message;

    private String statusCode;

    private Map<String, String> errors = new LinkedHashMap<>();

    private Map<String, Object> validation = null;

    public CardValidatorException(String code) {
        super(ExceptionCodeUtil.msg(code));
        KeyValue keyValue = ExceptionCodeUtil.data(code);
        setCode(keyValue.getCode());
        setMessage(keyValue.getMessage());
    }

    public CardValidatorException(String code, Map<String, Object> errors) {
        super(ExceptionCodeUtil.msg(code));
        KeyValue keyValue = ExceptionCodeUtil.data(code);
        setCode(keyValue.getCode());
        setMessage(keyValue.getMessage());
        setValidation(errors);
    }

    public CardValidatorException(String code, String customLanguageKey) {
        super(ExceptionCodeUtil.msg(code));
        setCode(ExceptionCodeUtil.code(code));
        setMessage(ExceptionCodeUtil.msg(customLanguageKey));
    }

    public CardValidatorException(String code, Map<String, Object> validation, String customLanguageKey) {
        super(customLanguageKey == null || StringUtils.isEmpty(customLanguageKey) ? ExceptionCodeUtil.msg(code) : ExceptionCodeUtil.msg(customLanguageKey));
        setCode(ExceptionCodeUtil.code(code));
        setMessage(ExceptionCodeUtil.msg(customLanguageKey));
        setValidation(validation);
    }

    public CardValidatorException(HttpStatus code, String customLanguageKey) {
        setCode(String.valueOf(code.value()));
        if(customLanguageKey.contains(" ")) setMessage(customLanguageKey);
        else setMessage(ExceptionCodeUtil.msg(customLanguageKey));
    }

    @Override
    public ResponseWrapper<Object> toResponseWrapper() {
        return tvpResponseWrapper();
    }


    public boolean hasErrors()
    {
        return !errors.isEmpty();
    }

    /**
     * @return string
     */
    public String getFirstError()
    {
        return errors.values().stream().findFirst().orElse(null);
    }

    public String getBestError()
    {
        return (this.hasErrors()) ? getFirstError() : this.getMessage();
    }


}
