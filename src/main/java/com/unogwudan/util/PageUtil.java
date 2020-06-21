package com.unogwudan.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
public class PageUtil {
    public static Pageable createPageRequest(int pageNumber, Sort sort) {
        return createPageRequest(pageNumber, 10, sort);
    }

    public static Pageable createPageRequest(int pageNumber, int size, Sort sort) {
        return PageRequest.of(pageNumber, size, sort);
    }
}
