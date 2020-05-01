package au.com.jaycar.solr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

    /*    public void configure2(HttpSecurity http) throws Exception {
	// @formatter:off
	http
		.authorizeRequests()
		.anyRequest()
		.authenticated()
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
    public void configure(HttpSecurity http) throws Exception {
	// @formatter:off
 	http
		.requestMatchers().antMatchers("/api/getAddress/**") 	
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
	tokenServices.setAccessTokenConverter(accessTokenConverter());
	return tokenServices;
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
	JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
	jwtAccessTokenConverter.setSigningKey(
		"sdfgdsfgdkugws3ry94r8934r98437ru932um987m3hd19mjsdhfjsdhf82iube87b2387heyh87hy98edsyh8977y8edf8w7dbc8s7fc7shgcviushbdvjysgdysdfuygsdfuiygsdfygsudft786sdf8usygbdfiuygdfuytbcisriuseis7us76dft68");
	return jwtAccessTokenConverter;
    }

}
