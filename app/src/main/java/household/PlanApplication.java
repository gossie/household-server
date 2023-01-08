package household;

import com.google.common.eventbus.EventBus;

import household.user.rest.JwtService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PlanApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanApplication.class, args);
	}

	@Bean
    EventBus eventBus() {
	    return new EventBus();
    }

	@Bean
	JwtAuthFilter jwtAuthFilter(JwtService jwtService) {
		return new JwtAuthFilter(jwtService);
	}

}
