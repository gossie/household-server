package household;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import lombok.RequiredArgsConstructor;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // private static final String CSRF_COOKIE_NAME = "XSRF-TOKEN";

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                //.csrfTokenRepository(csrfTokenRepository())
            //.and()
                //.addFilterAfter(this::csrfFilter, SecurityWebFiltersOrder.CSRF)
                //.requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
                .authorizeHttpRequests(configurer -> configurer
                    .requestMatchers(HttpMethod.GET, "/", "/index.html", "/error", "/*.js", "/*.css", "/*.png").permitAll()
                    .requestMatchers(HttpMethod.OPTIONS).permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/registrations", "/api/auth/login").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/status").permitAll()
                    .anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern(CorsConfiguration.ALL);
        configuration.setAllowedMethods(List.of(CorsConfiguration.ALL));
        configuration.setAllowedHeaders(List.of(CorsConfiguration.ALL));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // private ServerCsrfTokenRepository csrfTokenRepository() {
    //     WebSessionServerCsrfTokenRepository repository = new WebSessionServerCsrfTokenRepository();
    //     repository.setHeaderName(CSRF_COOKIE_NAME);
    //     return repository;
    // }

    // private Mono<Void> csrfFilter(ServerWebExchange exchange, WebFilterChain filterChain) {
    //     Mono<CsrfToken> csrfToken = exchange.getAttribute(CsrfToken.class.getName());
    //     if (csrfToken != null) {
    //         return csrfToken
    //             .map(csrf -> {
    //                 if (csrf != null) {

    //                     HttpCookie cookie = exchange.getRequest().getCookies().getFirst(CSRF_COOKIE_NAME);
    //                     String token = csrf.getToken();

    //                     if (cookie == null || token != null && !token.equals(cookie.getValue())) {
    //                         exchange.getResponse().addCookie(ResponseCookie.from(CSRF_COOKIE_NAME, token)
    //                             .path("/")
    //                             .httpOnly(false)
    //                             .build());
    //                     }
    //                 }
    //                 return csrf;
    //             })
    //             .flatMap(csrf -> filterChain.filter(exchange));
    //     } else {
    //         return filterChain.filter(exchange);
    //     }
    // }
}
