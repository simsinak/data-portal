package com.sina;

import com.sina.config.FetchProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Sina Askarnejad
 */
@SpringBootApplication
@EnableConfigurationProperties(FetchProperties.class)
public class DataPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataPortalApplication.class, args);
	}
}
