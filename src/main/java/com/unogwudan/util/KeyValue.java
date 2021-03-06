package com.unogwudan.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class KeyValue {
    private String key;
    private String value;

    public KeyValue(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getCode(){
        return this.key;
    }

    public String getMessage(){
        return this.value;
    }
}
