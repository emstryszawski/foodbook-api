package pl.edu.pjatk.foodbook.foodbookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.edu.pjatk.foodbook.foodbookservice.swagger.recipe.api.RecipeControllerApiClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories
@EnableFeignClients(
    clients = RecipeControllerApiClient.class
)
public class FoodbookServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodbookServiceApplication.class, args);
    }
}
