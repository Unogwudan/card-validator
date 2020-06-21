package com.unogwudan.services.dao;

import com.unogwudan.dto.response.CardStatsResponse;
import com.unogwudan.interfaces.services.ICardStatisticService;
import com.unogwudan.model.CardStatistic;
import com.unogwudan.repository.CardStatisticRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
@Slf4j
@Service
public class CardStatisticService implements ICardStatisticService {

    private final CardStatisticRepository statisticRepository;

    public CardStatisticService(CardStatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Override
    public CardStatistic saveStatistic(String cardNumber) {
        CardStatistic stat = statisticRepository.findByCardNumber(cardNumber);
        if (stat != null) {
            stat.setCount(stat.getCount() +1);
            stat = statisticRepository.saveAndFlush(stat);
        } else {
            stat = statisticRepository.saveAndFlush(new CardStatistic(cardNumber, 1));
        }

        return stat;
    }

    @Override
    public CardStatsResponse getStatistics(int start, int limit) {
        PageRequest pageRequest = PageRequest.of(0, limit, Sort.by("createdAt").descending());
        Page<CardStatistic> data = statisticRepository.findAll(pageRequest);

        Map<String, Integer> payload = data.getContent().parallelStream().collect(
                Collectors.toMap(CardStatistic :: getCardNumber, CardStatistic :: getCount));
        return new CardStatsResponse(start, limit, statisticRepository.count(), payload);
    }
}
