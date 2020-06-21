package com.unogwudan.interfaces.Exception;

import com.unogwudan.dto.response.ResponseWrapper;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
public interface IAppException {
    ResponseWrapper<Object> toResponseWrapper();
}
