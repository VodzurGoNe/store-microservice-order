package com.store.order.workers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.order.entitys.Order;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Vladislav Gruzdov
 */
@Component(OrderWorker.NAME)
public class OrderWorkerImpl implements OrderWorker {

    private static final Logger log = LoggerFactory.getLogger(OrderWorkerImpl.class);

    private static final String CUSTOMER_ORDER_URL = "customerOrders/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${spring.application.microservice-customer.url}")
    private String customerBaseUrl;

    @Override
    public boolean createOrder(Order order) {
        var url = customerBaseUrl + CUSTOMER_ORDER_URL + order.getCustomerId();
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        log.info("Order Request URL: {}", url);
        try {
            var request = new HttpEntity<>(order, headers);
            var responseEntity = restTemplate.postForEntity(url, request, Order.class);
            if (responseEntity.getStatusCode().isError()) {
                log.error("For Order ID: {}, error response: {} is received to create Order in Customer Microservice",
                        order.getId(),
                        responseEntity.getStatusCode().getReasonPhrase()
                );

                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        String.format("For Order UUID: %s, Customer Microservice Message: %s",
                                order.getId(),
                                responseEntity.getStatusCodeValue())
                );

            }

            if (responseEntity.hasBody()) {
                Order body = responseEntity.getBody();
                log.error("Order From Response: {}", (body == null ? "body is null" : body.getId()));
            }

        } catch (Exception e) {
            String rootCauseMessage = ExceptionUtils.getRootCauseMessage(e);
            log.error("For Order ID: {}, cannot create Order in Customer Microservice for reason: {}", order.getId(), rootCauseMessage);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("For Order UUID: %s, Customer Microservice Response: %s", order.getId(), rootCauseMessage));
        }

        return true;
    }

}
