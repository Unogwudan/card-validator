package com.unogwudan.repository;

import com.unogwudan.model.CardStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
public interface CardStatisticRepository extends JpaRepository<CardStatistic, Long> {
    CardStatistic findByCardNumber(String cardNumber);
}
