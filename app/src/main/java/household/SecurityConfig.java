package household;

import java.io.IOException;

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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
                .logout()
            .and()
                .csrf().disable();
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
