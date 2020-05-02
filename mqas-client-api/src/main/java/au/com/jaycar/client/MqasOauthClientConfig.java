package au.com.jaycar.client;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class MqasOauthClientConfig {
    
    @Bean
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
	ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setAccessTokenUri("http://localhost:8369/authorization/oauth/token");
        resourceDetails.setClientId("mehdi-dc");
        resourceDetails.setClientSecret("123456");
        resourceDetails.setGrantType("client_credentials");
        resourceDetails.setScope(Arrays.asList("read", "write"));
        return resourceDetails;
    }

//    @Bean
    public OAuth2ProtectedResourceDetails resourceDetails() {
	final AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
	details.setClientId("mehdi-dc");
	details.setClientSecret("123456");
	details.setAccessTokenUri("http://localhost:8369/authorization/oauth/token");
	details.setUserAuthorizationUri("http://localhost:8369/authorization/oauth/authorize");
	details.setScope(Arrays.asList("read", "write"));
	details.setGrantType("client_credentials");
	details.setUseCurrentUri(true);
	return details;
    }

    @Bean
    public OAuth2RestTemplate redditRestTemplate(final OAuth2ClientContext clientContext) {
	return new OAuth2RestTemplate(clientCredentialsResourceDetails(), clientContext);
    }

}
