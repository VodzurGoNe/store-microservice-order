package com.store.order.controllers;

import com.store.order.entitys.Order;
import com.store.order.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * @author Vladislav Gruzdov
 */
public class OrderResource {

    private static final Logger log = LoggerFactory.getLogger(OrderResource.class);

    private static final String ENTITY_NAME = "order";

    @Value("${spring.application.name}")
    private String applicationName;

    private final OrderService orderService;

    @Autowired
    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) throws URISyntaxException {
        log.debug("REST request to save Order : {}", order);
        if (order.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A new order cannot already have an ID");
        }

        Optional<Order> optionalOrder =  orderService.createOrder(order);
        if (optionalOrder.isEmpty()) {
            return ResponseEntity.internalServerError()
                    .build();
        }

        var result = optionalOrder.get();

        String message =
                String.format("A new '%s' is created with identifier '%s'", ENTITY_NAME, result.getId().toString());

        var headers = new HttpHeaders();
        headers.add("X-" + applicationName + "-alert", message);
        headers.add("X-" + applicationName + "-params", result.getId().toString());

        return ResponseEntity.created(new URI("/api/orders/" + result.getId()))
                .headers(headers)
                .body(result);
    }

}
