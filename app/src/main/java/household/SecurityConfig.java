package household;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

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
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors()
                .configurationSource(corsConfigurationSource())
            .and()
                .csrf().disable()
                //.csrfTokenRepository(csrfTokenRepository())
            //.and()
                //.addFilterAfter(this::csrfFilter, SecurityWebFiltersOrder.CSRF)
                //.requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/", "/index.html", "/*.js", "/*.css", "/*.png").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(HttpMethod.POST, "/api/registrations", "/api/auth/login").permitAll()
                .antMatchers(HttpMethod.GET, "/api/status").permitAll()
                .anyRequest().authenticated()
            .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
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
