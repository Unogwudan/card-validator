package com.unogwudan.controller;

import com.unogwudan.constant.CommonConstant;
import com.unogwudan.dto.response.BaseResponse;
import com.unogwudan.model.VerifiedCardDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
@Slf4j
@RestController
@RequestMapping(CommonConstant.API_VERSION + "card-scheme")
public class CardVerifierController {

    @GetMapping("/verify/{cardNumber}")
    public BaseResponse<?> verifyCard(@PathVariable String cardNumber) {
        log.info("Card Number: " + cardNumber);
        return new BaseResponse<>(new VerifiedCardDetails("visa", "debit", "UBS"));
    }

    @GetMapping("/stats/")
    public BaseResponse<?> getStats(@RequestParam("start") int start, @RequestParam("limit") int limit) {
        log.info("Start: " + start + " Limit: " + limit);
//        PageUtil.createPageRequest(start, limit);
        return new BaseResponse<>(new VerifiedCardDetails("visa", "debit", "UBS"));
    }
}
