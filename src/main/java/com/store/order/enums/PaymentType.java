package com.store.order.enums;

import org.springframework.lang.Nullable;

import java.util.Arrays;

/**
 * @author Vladislav Gruzdov
 */
public enum PaymentType {

    CARD("CARD");

    private final String id;

    PaymentType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static PaymentType fromId(final String id) {
        return Arrays.stream(PaymentType.values())
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseGet(null);
    }

}
