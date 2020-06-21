package com.unogwudan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
@Data
@Entity
@Table(name = "card_stats")
@AllArgsConstructor
public class CardStatistic extends BaseModel<CardStatistic> {
    private String cardNumber;
    private int count;
}
