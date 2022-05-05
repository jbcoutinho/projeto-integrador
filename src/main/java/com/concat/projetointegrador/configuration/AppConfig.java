package com.concat.projetointegrador.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("app")
public class AppConfig {

		private String viaCepBaseURL;
		private String viaCepReturnType;

}
