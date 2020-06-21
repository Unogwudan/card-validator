package com.unogwudan.model;

import lombok.*;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerifiedCardDetails {
    private String scheme;
    private String type;
    private String bank;

    public VerifiedCardDetails(String scheme, String type) {
        this.scheme = scheme;
        this.type = type;
    }
}
