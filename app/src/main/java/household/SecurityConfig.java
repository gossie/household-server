package household;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

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
                .defaultSuccessUrl("/household.html")
                .loginPage("/login.html")
                .permitAll()
            .and()
                .logout()
            .and()
                .csrf().disable();
    }
}
