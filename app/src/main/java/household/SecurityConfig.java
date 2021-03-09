package household;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.WebSessionServerCsrfTokenRepository;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String CSRF_COOKIE_NAME = "XSRF-TOKEN";

    private final ReactiveAuthenticationManager authenticationManager;

    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        return http
            .authenticationManager(authenticationManager)
                .cors()
            .and()
                .csrf()
                .csrfTokenRepository(csrfTokenRepository())
            .and()
                .addFilterAfter(this::csrfFilter, SecurityWebFiltersOrder.CSRF)
                .authorizeExchange()
                .pathMatchers("/simple.js").permitAll()
                .pathMatchers("/logo.png").permitAll()
                .pathMatchers("/", "/index.html", "/registration.html", "/login.html").permitAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers(HttpMethod.POST, "/registrations").permitAll()
                .pathMatchers(HttpMethod.GET, "/api/status").permitAll()
                .anyExchange().authenticated()
            .and()
                .formLogin()
                .loginPage("/login.html")
                .authenticationSuccessHandler(successHandler())
            .and()
                .logout()
            .and()
                .build();
            // TODO: .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
    }

    private ServerCsrfTokenRepository csrfTokenRepository() {
        WebSessionServerCsrfTokenRepository repository = new WebSessionServerCsrfTokenRepository();
        repository.setHeaderName(CSRF_COOKIE_NAME);
        return repository;
    }

    private Mono<Void> csrfFilter(ServerWebExchange exchange, WebFilterChain filterChain) {
        Mono<CsrfToken> csrfToken = exchange.getAttribute(CsrfToken.class.getName());
        if (csrfToken != null) {
            return csrfToken
                .map(csrf -> {
                    if (csrf != null) {

                        HttpCookie cookie = exchange.getRequest().getCookies().getFirst(CSRF_COOKIE_NAME);
                        String token = csrf.getToken();

                        if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                            exchange.getResponse().addCookie(ResponseCookie.from(CSRF_COOKIE_NAME, token)
                                .path("/")
                                .httpOnly(false)
                                .build());
                        }
                    }
                    return csrf;
                })
                .flatMap(csrf -> filterChain.filter(exchange));
        } else {
            return filterChain.filter(exchange);
        }
    }

    private ServerAuthenticationSuccessHandler successHandler() {
        RedirectServerAuthenticationSuccessHandler successHandler = new RedirectServerAuthenticationSuccessHandler();
        successHandler.setLocation(URI.create("/household.html"));
        return successHandler;
    }
}
