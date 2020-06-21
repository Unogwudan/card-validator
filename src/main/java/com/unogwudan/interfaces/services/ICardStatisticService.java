package com.unogwudan.interfaces.services;

import com.unogwudan.model.CardStatistic;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
public interface ICardStatisticService {
    CardStatistic saveStatistic(String cardNumber);
    List<CardStatistic> getStatistics(int start, int limit);
}
