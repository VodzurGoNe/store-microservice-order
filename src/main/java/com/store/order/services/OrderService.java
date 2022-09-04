package com.store.order.services;

import com.store.order.entitys.Order;

import java.util.Optional;

/**
 * @author Vladislav Gruzdov
 */
public interface OrderService {

    String NAME = "order_OrderService";

    Optional<Order> createOrder(Order order);

}
