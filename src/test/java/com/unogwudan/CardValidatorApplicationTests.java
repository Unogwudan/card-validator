package com.unogwudan;

import com.unogwudan.constant.CommonConstant;
import com.unogwudan.controller.CardVerifierController;
import com.unogwudan.dto.response.CardStatsResponse;
import com.unogwudan.interfaces.services.ICardStatisticService;
import com.unogwudan.interfaces.services.ICardVerifierService;
import com.unogwudan.model.CardStatistic;
import com.unogwudan.model.VerifiedCardDetails;
import com.unogwudan.repository.CardStatisticRepository;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest
@RunWith(SpringRunner.class)
class CardValidatorApplicationTests {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    ICardVerifierService cardVerifierService;
    @MockBean
    ICardStatisticService cardStatisticService;
    @InjectMocks
    CardVerifierController cardVerifierController;

    Map<String, Integer> populateStats() {
       return new HashMap<String, Integer>(){{
            put("45717360", 5);
            put("12345123", 2);
            put("57173607", 5);
            put("34512312", 2);
            put("23451235", 2);
        }};
    }

    @Before
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cardVerifierController).build();
    }

    @Test
    void testRetrieveCardDetails() throws Exception {
        when(cardVerifierService.verifyCard("45717360")).thenReturn(
                new VerifiedCardDetails("visa", "debit", "Jyske Bank")
        );
        mockMvc.perform(
                MockMvcRequestBuilders.get(CommonConstant.API_VERSION + "card-scheme/verify/45717360")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
         .andExpect(jsonPath("$.payload.scheme", Matchers.is("visa")))
         .andExpect(jsonPath("$.payload.type", Matchers.is("debit")))
         .andExpect(jsonPath("$.payload.bank", Matchers.is("Jyske Bank")));
    }

    @Test
    void testRetrieveCardStats() throws Exception {
        when(cardStatisticService.getStatistics(1, 3)).thenReturn(
                new CardStatsResponse(1, 3, 5l, populateStats())
        );
        mockMvc.perform(
                MockMvcRequestBuilders.get(CommonConstant.API_VERSION + "card-scheme/stats/?start=1&limit=3")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
         .andExpect(jsonPath("$.size", Matchers.equalTo(5)));
    }

}
