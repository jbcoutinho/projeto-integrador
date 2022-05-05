package com.concat.projetointegrador;

import com.concat.projetointegrador.configuration.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class ProjetoIntegradorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoIntegradorApplication.class, args);
    }

}
