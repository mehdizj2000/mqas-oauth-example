package au.com.jaycar.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MqasSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void globalConfig(AuthenticationManagerBuilder auth) throws Exception {
	// @formatter:off
 	auth
 		.inMemoryAuthentication()
 		.withUser("mehdi")
 		.password(passwordEncoder().encode("123456"))
 		.roles("USER")
 	;
	// @formatter:on
    }

    /*    @Override
    public void configure(HttpSecurity http) throws Exception {
	// @formatter:off
	http
		.authorizeRequests()
		.antMatchers("/oauth/token", "/oauth/check_token", "/oauth/authorize").permitAll()
		.anyRequest().authenticated()
	.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	.and()
		.csrf()
		.disable()
	;
	// @formatter:on
    }*/

    @Override
    protected void configure(final HttpSecurity http) throws Exception { // @formatter:off
        http.authorizeRequests()
            .anyRequest().authenticated()
            .and().formLogin().permitAll()
            .and().csrf().disable()
            ;
    } // @formatter:on

    @Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
    }

}
