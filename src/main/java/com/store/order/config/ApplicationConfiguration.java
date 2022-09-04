package com.store.order.config;

import com.store.order.entitys.BaseUuidEntity;
import com.store.order.utils.UuidProvider;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import org.springframework.web.client.RestTemplate;

/**
 * @author Vladislav Gruzdov
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public BeforeConvertCallback<BaseUuidEntity> beforeSaveCallback() {
        return (entity, collection) -> {

            if (entity.getId() == null) {
                entity.setId(UuidProvider.createUuid());
            }

            return entity;
        };
    }

}
