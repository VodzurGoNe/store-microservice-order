package com.store.order.entitys;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.UUID;

public abstract class BaseUuidEntity implements Serializable {

    private static final long serialVersionUID = -1;

    @Id
    protected UUID id;

    public void setId(UUID id) {
        if (this.id != null) {
            throw new UnsupportedOperationException("ID is already defined");
        }

        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
