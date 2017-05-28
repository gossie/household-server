package household;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//		    .jdbcAuthentication()
//		    .dataSource(dataSource)
//		    .withUser("user").password("Zb&VWN-~;PWh").roles("USER")
//            .and()
//            .withUser("admin").password("#3AF6!87}Ry%").roles("ADMIN");
        auth.inMemoryAuthentication()
                .withUser("user").password("Zb&VWN-~;PWh").roles("USER")
                .and()
                .withUser("admin").password("#3AF6!87}Ry%").roles("ADMIN");
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().fullyAuthenticated();
        http.httpBasic();
        http.csrf().disable();
    }
}
