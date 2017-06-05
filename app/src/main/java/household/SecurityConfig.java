package household;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	
	
	
     
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//      http.authorizeRequests()
//        .antMatchers("/", "/home").permitAll()
//        .antMatchers("/admin/**","/newuser").access("hasRole('ADMIN')")
//        .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
//        .and().formLogin().loginPage("/login")
//        .usernameParameter("ssoId").passwordParameter("password")
//        .and().csrf()
//        .and().exceptionHandling().accessDeniedPage("/Access_Denied");
//    }
	

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//		    .jdbcAuthentication()
//		    .dataSource(dataSource)
//		    .withUser("user").password("Zb&VWN-~;PWh").roles("USER")
//            .and()
//            .withUser("admin").password("#3AF6!87}Ry%").roles("ADMIN");
		
//        auth.inMemoryAuthentication()
//                .withUser("user").password("Zb&VWN-~;PWh").roles("USER")
//                .and()
//                .withUser("admin").password("#3AF6!87}Ry%").roles("ADMIN");
		
		auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider);
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().fullyAuthenticated();
        http.httpBasic();
        http.csrf().disable();
    }
}
