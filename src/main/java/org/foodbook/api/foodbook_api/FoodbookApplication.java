package org.foodbook.api.foodbook_api;

import org.foodbook.api.swagger.recipe.api.RecipeControllerApiClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories
@EnableFeignClients(
    clients = RecipeControllerApiClient.class
)
public class FoodbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodbookApplication.class, args);
    }
}
