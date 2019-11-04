package household;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.util.WebUtils;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String CSRF_COOKIE_NAME = "XSRF-TOKEN";

	private final UserDetailsService userDetailsService;
	private final AuthenticationProvider authenticationProvider;

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requiresChannel()
            .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
            .requiresSecure()
            .and()
                .cors()
            .and()
                .csrf()
                .csrfTokenRepository(csrfTokenRepository())
            .and()
            .addFilterAfter(this::csrfFilter, CsrfFilter.class)
                .authorizeRequests()
                .antMatchers("/registration.html").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/").permitAll()
                .mvcMatchers(HttpMethod.OPTIONS).permitAll()
                .mvcMatchers(HttpMethod.POST, "/registrations").permitAll()
                .mvcMatchers(HttpMethod.GET, "/api/status").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login.html")
                .successHandler(this::successHandler)
                .permitAll()
            .and()
                .logout();
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName(CSRF_COOKIE_NAME);
        return repository;
    }

    private void csrfFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        if (csrf != null) {

            Cookie cookie = WebUtils.getCookie((HttpServletRequest) request, CSRF_COOKIE_NAME);
            String token = csrf.getToken();

            if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                cookie = new Cookie(CSRF_COOKIE_NAME, token);
                cookie.setPath("/");
                cookie.setHttpOnly(false);
                ((HttpServletResponse) response).addCookie(cookie);
            }
        }

        try {
            filterChain.doFilter(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void successHandler(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        try {
            redirectStrategy.sendRedirect(request, response, "/household.html");
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
