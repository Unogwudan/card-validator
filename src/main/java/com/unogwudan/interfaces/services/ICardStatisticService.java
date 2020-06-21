package com.unogwudan.interfaces.services;

import com.unogwudan.dto.response.CardStatsResponse;
import com.unogwudan.model.CardStatistic;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
public interface ICardStatisticService {
    CardStatistic saveStatistic(String cardNumber);
    CardStatsResponse getStatistics(int start, int limit);
}
