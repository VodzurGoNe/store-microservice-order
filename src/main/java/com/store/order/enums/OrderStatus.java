package com.store.order.enums;

import org.springframework.lang.Nullable;

import java.util.Arrays;

/**
 * @author Vladislav Gruzdov
 */
public enum OrderStatus {

    CREATED("CREATED");

    private final String id;

    OrderStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static OrderStatus fromId(final String id) {
        return Arrays.stream(OrderStatus.values())
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseGet(null);
    }

}
