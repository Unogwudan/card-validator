package com.unogwudan.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
@Getter
@Setter
@Entity
@Table(name = "card_stats")
@AllArgsConstructor
@NoArgsConstructor
public class CardStatistic extends BaseModel<CardStatistic> {
    private String cardNumber;
    private int count;
}
