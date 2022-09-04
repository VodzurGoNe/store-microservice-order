package com.store.order.controllers;

import com.store.order.controllers.dto.Health;
import com.store.order.enums.HealthStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vladislav Gruzdov
 */
@RestController
@RequestMapping("/api/v1")
public class HealthResource {

    private static final Logger log = LoggerFactory.getLogger(HealthResource.class);

    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Health> getHealth() {
        log.debug("REST request to get the Health Status");

        var health = new Health();
        health.setStatus(HealthStatus.UP);

        return ResponseEntity.ok()
                .body(health);
    }

}
