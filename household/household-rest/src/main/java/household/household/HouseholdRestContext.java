package household.household;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class HouseholdRestContext {

    @Bean
    public WebClient webClient() {
        return WebClient.create("http://app:8080");
    }

}
