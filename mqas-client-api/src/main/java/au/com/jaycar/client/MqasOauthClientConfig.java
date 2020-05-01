package au.com.jaycar.client;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class MqasOauthClientConfig {

    @Bean
    public OAuth2ProtectedResourceDetails resourceDetails() {
	final AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
	details.setClientId("mehdi-dc");
	details.setClientSecret("123456");
	details.setAccessTokenUri("http://localhost:8369/authorization/oauth/token");
	details.setUserAuthorizationUri("http://localhost:8369/authorization/oauth/authorize");
	details.setScope(Arrays.asList("read", "write"));
	details.setGrantType("authorization_code");
	details.setUseCurrentUri(true);
	return details;
    }

    @Bean
    public OAuth2RestTemplate redditRestTemplate(final OAuth2ClientContext clientContext) {
	return new OAuth2RestTemplate(resourceDetails(), clientContext);
    }

}
