package com.store.order.workers;

import com.store.order.entitys.Order;

/**
 * @author Vladislav Gruzdov
 */
public interface OrderWorker {

    String NAME = "order_OrderWorker";

    boolean createOrder(Order order);

}
