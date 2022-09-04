package com.store.order.entitys;

import com.store.order.enums.OrderStatus;
import com.store.order.enums.PaymentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * @author Vladislav Gruzdov
 */
@Document(collection = "order")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Order extends BaseUuidEntity {

    private static final long serialVersionUID = 2271255497931152196L;

    @NotNull
    @Field("CUSTOMER_ID")
    private UUID customerId;

    @Field("CREATED_AT")
    @CreatedDate
    private Instant createdAt;

    @Field("UPDATED_AT")
    @LastModifiedDate
    private Instant updatedAt;

    @Version
    public Integer version;

    @Field("STATUS")
    private String status = OrderStatus.CREATED.getId();

    @Field("PAYMENT_STATUS")
    private Boolean paymentStatus = Boolean.FALSE;

    @NotNull
    @Field("PAYMENT_METHOD")
    private PaymentType paymentMethod;

    @NotNull
    @Field("PAYMENT_DETAILS")
    private String paymentDetails;

    @Field("SHIPPING_ADDRESS")
    private Address shippingAddress;

    @ToString.Exclude
    @Field("PRODUCTS")
    @NotEmpty
    private Set<@Valid Product> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        return Objects.equals(id, order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
