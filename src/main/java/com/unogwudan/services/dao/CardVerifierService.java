package com.unogwudan.services.dao;

import com.unogwudan.constant.ExceptionKeyConstant;
import com.unogwudan.dto.VerifiedCardDetailsResponse;
import com.unogwudan.exception.CardValidatorException;
import com.unogwudan.interfaces.services.ICardStatisticService;
import com.unogwudan.interfaces.services.ICardVerifierService;
import com.unogwudan.kafka_producer.VerifiedCardProducer;
import com.unogwudan.model.VerifiedCardDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
@Slf4j
@Service
public class CardVerifierService implements ICardVerifierService {

    @Value("${binlist.endpoint}")
    private String binlistEndpoint;
    private final RestTemplate restTemplate;
    private final VerifiedCardProducer verifiedCardProducer;
    private final ICardStatisticService cardStatisticService;
    Map<String, Object> verifiedCards = new HashMap<>();

    public CardVerifierService(RestTemplate restTemplate, VerifiedCardProducer verifiedCardProducer, ICardStatisticService cardStatisticService) {
        this.restTemplate = restTemplate;
        this.verifiedCardProducer = verifiedCardProducer;
        this.cardStatisticService = cardStatisticService;
    }

    /**
     * Retrieve card details
     *
     * @param cardNumber
     * @return card details retrieved from API or from local cache
     */
    public VerifiedCardDetails verifyCard(String cardNumber) {

        VerifiedCardDetailsResponse cardDetails;

        // Call the API to retrieve card details and cache it in a map to reduce latency
        if (!verifiedCards.containsKey(cardNumber)) {
            cardDetails = callVerifyCardApi(cardNumber);
            verifiedCards.put(cardNumber, cardDetails);
        } else {
            cardDetails = (VerifiedCardDetailsResponse) verifiedCards.get(cardNumber);
        }

        VerifiedCardDetails verifiedCardDetails = new VerifiedCardDetails(cardDetails.getScheme(), cardDetails.getType());
        if (cardDetails.getBank() != null)
            verifiedCardDetails.setBank(cardDetails.getBank().getName());
        cardStatisticService.saveStatistic(cardNumber);
        verifiedCardProducer.publishVerifiedCardMessage(verifiedCardDetails);

        return verifiedCardDetails;
    }

    /**
     * Call the binlist api to retrieve the card details
     *
     * @param cardNumber
     * @return card details
     */
    private VerifiedCardDetailsResponse callVerifyCardApi(String cardNumber) {

        ResponseEntity<VerifiedCardDetailsResponse> response;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        try {
            log.info("Before Calling binlist api to retrieve the card details");
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(binlistEndpoint + cardNumber);

            HttpEntity<String> request = new HttpEntity<>(headers);
            response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, request, VerifiedCardDetailsResponse.class);
            log.info("Response After Calling Service to validate a Token: " + response.getBody());

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CardValidatorException(ExceptionKeyConstant.INTERNAL_SERVER_ERROR);
        }

        if (response.getStatusCode().equals(HttpStatus.OK)) return response.getBody();

        throw new CardValidatorException(ExceptionKeyConstant.NOT_FOUND);
    }
}
