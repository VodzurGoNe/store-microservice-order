package com.store.order.utils;

import java.util.UUID;

/**
 * @author Vladislav Gruzdov
 */
public final class UuidProvider {

    private UuidProvider() {
    }

    public static UUID createUuid() {
        return UUID.randomUUID();
    }

}
