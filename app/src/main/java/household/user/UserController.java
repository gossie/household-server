package household.user;

import static household.Constants.DEFAULT_MEDIA_TYPE;

import java.util.Map;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@ExposesResourceFor(UserDTO.class)
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;

	@PostMapping(consumes=DEFAULT_MEDIA_TYPE)
	@ResponseStatus(HttpStatus.OK)
	public void createUser(@RequestBody Map<String, String> data) {
		User createUser = userService.createUser(new User(null, data.get("email"), data.get("password")));
		System.out.println(createUser.getId());
		System.out.println(createUser.getEmail());
		System.out.println(createUser.getPassword());
	}
}
