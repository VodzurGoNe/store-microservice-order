package com.store.order.services;

import com.store.order.entitys.Order;
import com.store.order.repositorys.OrderRepository;
import com.store.order.workers.OrderWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Vladislav Gruzdov
 */
@Service(OrderService.NAME)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderWorker orderWorker;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderWorker orderWorker) {
        this.orderRepository = orderRepository;
        this.orderWorker = orderWorker;
    }

    @Override
    public Optional<Order> createOrder(Order order) {
        if (orderWorker.createOrder(order)) {
            return Optional.of(orderRepository.save(order));
        }

        return Optional.empty();
    }

}
