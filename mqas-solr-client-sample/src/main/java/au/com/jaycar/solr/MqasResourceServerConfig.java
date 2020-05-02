package au.com.jaycar.solr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableWebSecurity
@EnableResourceServer
public class MqasResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
	// @formatter:off
 	http
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) 	
 	.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/getAddress/**").access("#oauth2.hasScope('read')")
		.antMatchers(HttpMethod.POST, "/api/getAddress/**").access("#oauth2.hasScope('write')")
		.antMatchers(HttpMethod.DELETE, "/api/getAddress/**").access("#oauth2.hasScope('write')")
 	;
 	// @formatter:on

	}

	@Bean
	public ResourceServerTokenServices tokenServices() {
		RemoteTokenServices tokenServices = new RemoteTokenServices();
		tokenServices.setClientId("mehdi-dc");
		tokenServices.setClientSecret("123456");
		tokenServices.setCheckTokenEndpointUrl("http://127.0.0.1:8369/authorization/oauth/check_token");
		return tokenServices;
	}

}
