package com.store.order.entitys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.Objects;

/**
 * @author Vladislav Gruzdov
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product extends BaseUuidEntity {

    private static final long serialVersionUID = 3271255497931152196L;

    @Field("CREATED_AT")
    @CreatedDate
    private Instant createdAt;

    @Field("UPDATED_AT")
    @LastModifiedDate
    private Instant updatedAt;

    @Version
    public Integer version;

    @Field("NAME")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;

        return Objects.equals(id, product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
