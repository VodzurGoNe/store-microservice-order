package com.store.order.enums;

import org.springframework.lang.Nullable;

import java.util.Arrays;

/**
 * @author Vladislav Gruzdov
 */
public enum HealthStatus {

    UP("UP");

    private final String id;

    HealthStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static HealthStatus fromId(final String id) {
        return Arrays.stream(HealthStatus.values())
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseGet(null);
    }

}
