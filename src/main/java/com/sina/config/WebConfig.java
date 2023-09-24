package com.sina.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.format.DateTimeFormatter;

/**
 * @author Sina Askarnejad
 */
@Configuration
public class WebConfig {

    @Bean
    @Order(1)
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return builder -> builder.serializationInclusion(JsonInclude.Include.NON_NULL)
                .serializers(new LocalDateSerializer(dateTimeFormatter));
    }
}
