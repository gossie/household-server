package household;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.json.JSONArray;
import org.json.JSONObject;
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
	
	@Test
	public void contextLoads() throws Exception {
		MockMvc mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.addFilters(filterChainProxy)
				.build();
		
		MockHttpServletResponse createUserResponse = mvc
				.perform(post("/api/users")
						.contentType("application/vnd.household.v1+json")
						.content("{\"email\":\"user1@email.com\", \"password\":\"12345678\"}"))
		        .andReturn()
		        .getResponse();

		assertThat(createUserResponse.getStatus()).isEqualTo(200);
		
		JSONObject json = new JSONObject(createUserResponse.getContentAsString());
		assertThat(json.getString("email")).isEqualTo("user1@email.com");
		
		createUserResponse = mvc
				.perform(post("/api/users")
						.contentType("application/vnd.household.v1+json")
						.content("{\"email\":\"user2@email.com\", \"password\":\"87654321\"}"))
		        .andReturn()
		        .getResponse();

		assertThat(createUserResponse.getStatus()).isEqualTo(200);
		
		json = new JSONObject(createUserResponse.getContentAsString());
		assertThat(json.getString("email")).isEqualTo("user2@email.com");
		
		MockHttpServletResponse createHouseholdResponse = mvc
				.perform(post("/api/households")
						.with(httpBasic("user1@email.com", "12345678"))
						.contentType("application/vnd.household.v1+json"))
		        .andReturn()
		        .getResponse();

		assertThat(createHouseholdResponse.getStatus()).isEqualTo(200);
		
		json = new JSONObject(createHouseholdResponse.getContentAsString());
		assertThat(determineLink(json, "shoppingList")).isNotNull();
		assertThat(determineLink(json, "cleaningPlan")).isNotNull();
		assertThat(determineLink(json, "foodPlan")).isNotNull();
		assertThat(determineLink(json, "cookbook")).isNotNull();
		
		
		MockHttpServletResponse invitationResponse = mvc
				.perform(post("/api/users/1/invitations")
						.with(httpBasic("user1@email.com", "12345678"))
						.contentType("application/vnd.household.v1+json")
						.content("{\"email\":\"user2@email.com\"}"))
				.andReturn()
				.getResponse();
		
		assertThat(invitationResponse.getStatus()).isEqualTo(200);
		
		
//		MockHttpServletResponse changeUser2Response = mvc
//				.perform(put("/api/users/user2@email.com")
//						.with(httpBasic("user2@email.com", "87654321"))
//						.content("1"))
//		        .andReturn()
//		        .getResponse();
//
//		assertThat(changeUser2Response.getStatus()).isEqualTo(200);
		
		
		MockHttpServletResponse getUser1Response = mvc
				.perform(get("/api/users/1")
						.with(httpBasic("user1@email.com", "12345678")))
		        .andReturn()
		        .getResponse();

		assertThat(getUser1Response.getStatus()).isEqualTo(200);
		
		json = new JSONObject(getUser1Response.getContentAsString());
		assertThat(determineLink(json, "household")).isNotNull();
		
		MockHttpServletResponse getUser2Response = mvc
				.perform(get("/api/users/2")
						.with(httpBasic("user2@email.com", "87654321")))
		        .andReturn()
		        .getResponse();

		assertThat(getUser2Response.getStatus()).isEqualTo(200);
		
		json = new JSONObject(getUser2Response.getContentAsString());
//		assertThat(determineLink(json, "household")).isNotNull();
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
