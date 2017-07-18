package household;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanApplicationIT {
	
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private FilterChainProxy filterChainProxy;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.addFilters(filterChainProxy)
				.build();
	}
	
	@Test
	public void contextLoads() throws Exception {
		JSONObject user1 = createUser("user1@email.com", "12345678");
		assertThat(user1.getString("email")).isEqualTo("user1@email.com");
		
		JSONObject user2 = createUser("user2@email.com", "87654321");
		assertThat(user2.getString("email")).isEqualTo("user2@email.com");
		
		JSONObject household = createHousehold("user1@email.com", "12345678");
		assertThat(determineLink(household, "shoppingList")).isNotNull();
		assertThat(determineLink(household, "cleaningPlan")).isNotNull();
		assertThat(determineLink(household, "foodPlan")).isNotNull();
		assertThat(determineLink(household, "cookbook")).isNotNull();
		
		invite();
		
		user1 = getUser(1L, "user1@email.com", "12345678");
		assertThat(determineLink(user1, "household")).isNotNull();
		
		user2 = getUser(2L, "user2@email.com", "87654321");
		assertThat(determineLink(user2, "household")).isNull();
		assertThat(user2.getJSONArray("invitations").length()).isEqualTo(1);
		
		rejectInvitation(2L, 1L, "user2@email.com", "87654321");
		
		user2 = getUser(2L, "user2@email.com", "87654321");
		assertThat(user2.getJSONArray("invitations").length()).isEqualTo(0);
		
		invite();
		
		user2 = getUser(2L, "user2@email.com", "87654321");
		assertThat(user2.getJSONArray("invitations").length()).isEqualTo(1);
		
		acceptInvitation(2L, 1L, "user2@email.com", "87654321");
		
		user2 = getUser(2L, "user2@email.com", "87654321");
        assertThat(determineLink(user1, "household")).isNotNull();
        assertThat(user2.getJSONArray("invitations").length()).isEqualTo(1);
	}

	private JSONObject createUser(String email, String password) throws Exception {
		MockHttpServletResponse response = mvc
				.perform(post("/api/users")
						.contentType("application/vnd.household.v2+json")
						.content("{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}"))
		        .andReturn()
		        .getResponse();

		assertThat(response.getStatus()).isEqualTo(200);
		
		return new JSONObject(response.getContentAsString());
	}
	
	private JSONObject createHousehold(String email, String password) throws Exception {
		MockHttpServletResponse response = mvc
				.perform(post("/api/households")
						.with(httpBasic(email, password))
						.contentType("application/vnd.household.v2+json"))
		        .andReturn()
		        .getResponse();

		assertThat(response.getStatus()).isEqualTo(200);
		
		return new JSONObject(response.getContentAsString());
	}
	
	private void invite() throws Exception {
		MockHttpServletResponse response = mvc
				.perform(post("/api/users/1/invitations")
						.with(httpBasic("user1@email.com", "12345678"))
						.contentType("application/vnd.household.v2+json")
						.content("{\"email\":\"user2@email.com\"}"))
				.andReturn()
				.getResponse();
		
		assertThat(response.getStatus()).isEqualTo(200);
	}
	
	private JSONObject getUser(Long id, String email, String password) throws Exception {
		MockHttpServletResponse response = mvc
				.perform(get("/api/users/" + id)
						.with(httpBasic(email, password)))
		        .andReturn()
		        .getResponse();

		assertThat(response.getStatus()).isEqualTo(200);
		
		return new JSONObject(response.getContentAsString());
	}
	
	private void rejectInvitation(long userId, long invitationId, String email, String password) throws Exception {
		MockHttpServletResponse response = mvc
				.perform(delete("/api/users/" + userId + "/invitations/" + invitationId)
						.with(httpBasic(email, password)))
		        .andReturn()
		        .getResponse();

		assertThat(response.getStatus()).isEqualTo(200);
	}
	
	private void acceptInvitation(long userId, long invitationId, String email, String password) throws Exception {
		MockHttpServletResponse response = mvc
				.perform(post("/api/users/" + userId + "/invitations/" + invitationId)
						.with(httpBasic(email, password)))
		        .andReturn()
		        .getResponse();

		assertThat(response.getStatus()).isEqualTo(200);
	}
	
	private String determineLink(JSONObject json, String rel) throws Exception {
		JSONArray jsonArray = json.getJSONArray("links");
		for(int i=0; i<jsonArray.length(); i++) {
			JSONObject link = jsonArray.getJSONObject(i);
			if(rel.equals(link.getString("rel"))) {
				return link.getString("href");
			}
		}
		return null;
	}
}
