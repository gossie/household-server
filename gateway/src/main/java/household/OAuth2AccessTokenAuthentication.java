package household;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

class OAuth2AccessTokenAuthentication extends AbstractAuthenticationToken {

    private final OAuth2AccessToken oAuth2AccessToken;
    private final Authentication userAuthentication;

    OAuth2AccessTokenAuthentication(OAuth2AccessToken oAuth2AccessToken, Authentication userAuthentication) {
        super(null);
        this.oAuth2AccessToken = oAuth2AccessToken;
        this.userAuthentication = userAuthentication;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();

        if (userAuthentication instanceof CredentialsContainer) {
            ((CredentialsContainer) userAuthentication).eraseCredentials();
        }
    }

    @Override
    public Object getCredentials() {
        return oAuth2AccessToken;
    }

    @Override
    public Object getPrincipal() {
        return userAuthentication.getPrincipal();
    }

    public OAuth2AccessToken getoAuth2AccessToken() {
        return oAuth2AccessToken;
    }
}
