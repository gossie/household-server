package household;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RequiredArgsConstructor
public class OAuth2PasswordAuthenticationManager implements ReactiveAuthenticationManager {

    private final WebClient restTemplate;

    @Value("${oauth.url}")
    private String url;

    @Value("${oauth.clientId}")
    private String clientId;

    @Value("${oauth.secret}")
    private String secret;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {

        if (!(authentication instanceof UsernamePasswordAuthenticationToken)) {
            return null;
        }

        final UsernamePasswordAuthenticationToken usernamePassword = (UsernamePasswordAuthenticationToken) authentication;

        final String username = (String) usernamePassword.getPrincipal();
        final String password = (String) usernamePassword.getCredentials();

        final MultiValueMap<String, String> formFields = new LinkedMultiValueMap<>();
        formFields.add("grant_type", "password");
        formFields.add("username", username);
        formFields.add("password", password);

        final byte[] clientCredentialsBytes = (clientId + ":" + secret).getBytes(StandardCharsets.US_ASCII);
        final String clientCredentialsEncoded = Base64.getEncoder().encodeToString(clientCredentialsBytes);

        return restTemplate.post()
            .uri(url)
            .header(HttpHeaders.AUTHORIZATION, "Basic " + clientCredentialsEncoded)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .bodyValue(formFields)
            .retrieve()
            .bodyToMono(OAuth2AccessToken.class)
            .map(oAuth2AccessToken -> new OAuth2AccessTokenAuthentication(oAuth2AccessToken, authentication));

        // TODO: error handling
        /*
        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            throw new InsufficientAuthenticationException("Authorization failed");
        } else if (response.getStatusCode() != HttpStatus.OK) {
            throw new AuthenticationServiceException("Authorization failed");
        }
        */
    }
}
