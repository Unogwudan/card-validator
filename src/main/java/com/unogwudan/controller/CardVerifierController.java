package com.unogwudan.controller;

import com.unogwudan.constant.CommonConstant;
import com.unogwudan.dto.response.BaseResponse;
import com.unogwudan.dto.response.CardStatsResponse;
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
        return new BaseResponse<>(cardVerifierService.verifyCard(cardNumber));
    }

    @GetMapping("/stats/")
    public CardStatsResponse getStats(@RequestParam("start") int start, @RequestParam("limit") int limit) {
        return cardStatisticService.getStatistics(start, limit);
    }
}
