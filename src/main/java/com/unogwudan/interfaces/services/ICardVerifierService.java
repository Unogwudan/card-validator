package com.unogwudan.interfaces.services;

import com.unogwudan.model.VerifiedCardDetails;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
public interface ICardVerifierService {
    VerifiedCardDetails verifyCard(String cardNumber);
}
