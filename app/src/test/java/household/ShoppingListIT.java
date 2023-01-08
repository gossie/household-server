package household;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import household.household.rest.HouseholdDTO;
import household.user.rest.LoginData;
import household.user.rest.Token;
import household.user.rest.UserDTO;
import household.user.rest.UserRegistrationData;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShoppingListIT {

    @Autowired
	private TestRestTemplate restTemplate;

    @Test
    void integrationTest() {
        createUser("shopping-list@user.de", "password");
        String token = login("shopping-list@user.de", "password");
        createHousehold(token);
    }

    private void createUser(String email, String password) {
        var headers = new HttpHeaders();
        headers.add("Accept", "application/vnd.household.v1+json");
        headers.add("Content-Type", "application/vnd.household.v1+json");
        var httpEntity = new HttpEntity<>(new UserRegistrationData(email, password, password), headers);
        var userCreationResponse = restTemplate.exchange(
            "/api/registrations",
            HttpMethod.POST,
            httpEntity, 
            UserDTO.class
        );
        assertThat(userCreationResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    private String login(String email, String password) {
        var headers = new HttpHeaders();
        headers.add("Accept", "application/vnd.household.v1+json");
        headers.add("Content-Type", "application/vnd.household.v1+json");
        var httpEntity = new HttpEntity<>(new LoginData(email, password), headers);
        var tokenResponse = restTemplate.exchange(
            "/api/auth/login",
            HttpMethod.POST,
            httpEntity, 
            Token.class
        );
        assertThat(tokenResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        return tokenResponse.getBody().token();
    }

    private HouseholdDTO createHousehold(String token) {
        var headers = new HttpHeaders();
        headers.add("Accept", "application/vnd.household.v1+json");
        headers.add("Content-Type", "application/vnd.household.v1+json");
        headers.add("Authorization", "Bearer " + token);
        var httpEntity = new HttpEntity<>(headers);
        var tokenResponse = restTemplate.exchange(
            "/api/households",
            HttpMethod.POST,
            httpEntity, 
            HouseholdDTO.class
        );
        assertThat(tokenResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        return tokenResponse.getBody();
    }
    
}
