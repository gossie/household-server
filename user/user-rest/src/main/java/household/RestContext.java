package household;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import household.user.JwtService;

@Configuration
public class RestContext {

    @Bean
    JwtService jwtService(@Value("${app.jwt.secret}") String secret) {
        return new JwtService(secret);
    }

}
