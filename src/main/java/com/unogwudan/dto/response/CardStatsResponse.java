package com.unogwudan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardStatsResponse {

    private boolean success = Boolean.TRUE;
    private int start;
    private int limit;
    private long size;
    private Map<String, Integer> payload;

    public CardStatsResponse(int start, int limit, long size, Map<String, Integer> payload) {
        this.start = start;
        this.limit = limit;
        this.size = size;
        this.payload = payload;
    }
}

