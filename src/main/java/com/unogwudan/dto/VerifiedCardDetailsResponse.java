package com.unogwudan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerifiedCardDetailsResponse {

    private String scheme;
    private String type;
    private Bank bank;
}

