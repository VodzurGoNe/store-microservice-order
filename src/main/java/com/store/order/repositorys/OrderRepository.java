package com.store.order.repositorys;

import com.store.order.entitys.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Vladislav Gruzdov
 */
@Repository
public interface OrderRepository extends MongoRepository<Order, UUID> {
}
