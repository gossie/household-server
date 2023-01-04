package household.user.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestContext {

    @Bean
    JwtService jwtService(@Value("${app.jwt.secret}") String secret) {
        return new JwtService(secret);
    }

}
