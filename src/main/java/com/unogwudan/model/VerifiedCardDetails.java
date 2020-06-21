package com.unogwudan.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
@Data
@AllArgsConstructor
public class VerifiedCardDetails {
    private String scheme;
    private String type;
    private String bank;
}
