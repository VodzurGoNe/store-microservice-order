package com.store.order.controllers.dto;

import com.store.order.enums.HealthStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vladislav Gruzdov
 */
@Data
@NoArgsConstructor
public class Health {

    private HealthStatus status;

}
