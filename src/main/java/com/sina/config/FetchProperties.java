package com.sina.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Sina Askarnejad
 */
@ConfigurationProperties("fetch")
@Getter
@Setter
public class FetchProperties {

    private int pageSize = 30;
}
