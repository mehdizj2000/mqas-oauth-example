package au.com.jaycar.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class MqasAuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	super.configure(security);
	security.checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	// @formatter:off
	clients
		.inMemory()
		.withClient("mehdi-dc")
		.secret(passwordEncoder.encode("123456"))
		.authorizedGrantTypes("authorization_code", "refresh_token")
		.scopes("read", "write")
		.accessTokenValiditySeconds(3600)
		.refreshTokenValiditySeconds(3600 * 24)
		.redirectUris("http://localhost:5859/client/proxy/addressInfo")
	;
	// @formatter:on
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

	// @formatter:off
 	endpoints
 		.tokenStore(tokenStore())
 		.authenticationManager(authenticationManager)
 		.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
 		.accessTokenConverter(accessTokenConverter())
 	;
	// @formatter:on
    }

    @Bean
    @Primary
    public DefaultTokenServices DefaultTokenServices() {
	DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
	defaultTokenServices.setTokenStore(tokenStore());
	defaultTokenServices.setSupportRefreshToken(true);
	return defaultTokenServices;
    }

    @Bean
    public TokenStore tokenStore() {
	return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
	JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
	jwtAccessTokenConverter.setSigningKey(
		"sdfgdsfgdkugws3ry94r8934r98437ru932um987m3hd19mjsdhfjsdhf82iube87b2387heyh87hy98edsyh8977y8edf8w7dbc8s7fc7shgcviushbdvjysgdysdfuygsdfuiygsdfygsudft786sdf8usygbdfiuygdfuytbcisriuseis7us76dft68");

	return jwtAccessTokenConverter;
    }

}
