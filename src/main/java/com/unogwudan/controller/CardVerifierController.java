package com.unogwudan.controller;

import com.unogwudan.constant.CommonConstant;
import com.unogwudan.dto.response.BaseResponse;
import com.unogwudan.interfaces.services.ICardStatisticService;
import com.unogwudan.interfaces.services.ICardVerifierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
@Slf4j
@RestController
@RequestMapping(CommonConstant.API_VERSION + "card-scheme")
public class CardVerifierController {

    private final ICardVerifierService cardVerifierService;
    private final ICardStatisticService cardStatisticService;

    public CardVerifierController(ICardVerifierService cardVerifierService, ICardStatisticService cardStatisticService) {
        this.cardVerifierService = cardVerifierService;
        this.cardStatisticService = cardStatisticService;
    }

    @GetMapping("/verify/{cardNumber}")
    public BaseResponse<?> verifyCard(@PathVariable String cardNumber) {
        log.info("Card Number: " + cardNumber);
        return new BaseResponse<>(cardVerifierService.verifyCard(cardNumber));
    }

    @GetMapping("/stats/")
    public BaseResponse<?> getStats(@RequestParam("start") int start, @RequestParam("limit") int limit) {
        return new BaseResponse<>(cardStatisticService.getStatistics(start, limit));
    }
}
