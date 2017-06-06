package household;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import household.user.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanApplicationIT {
	
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void contextLoads() throws Exception {
		
		MockMvc mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.build();
		
		//.with(httpBasic("user", "Zb&VWN-~;PWh")
		//.content("{\"email\":\"a@bc.de\", \"password\":\"12345asdf\"}")
		
		MockHttpServletResponse postResponse = mvc.perform(post("/api/households").contentType("application/vnd.household.v1+json"))
		        .andReturn()
		        .getResponse();

		assertThat(postResponse.getStatus()).isEqualTo(200);
		
		String postContent = postResponse.getContentAsString();
		
		MockHttpServletResponse getResponse = mvc.perform(get("/api/households/1").accept("application/vnd.household.v1+json"))
				.andReturn()
				.getResponse();
		
		assertThat(getResponse.getStatus()).isEqualTo(200);
		
		String getContent = getResponse.getContentAsString();
		
		assertThat(postContent).isEqualTo(getContent);
		
//		User existingUser = userRepository.determineUser("a@bc.de");
		
//		assertThat(existingUser).isNotNull();
//		assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() -> userRepository.determineUser("b@bc.de"));
	}
}
