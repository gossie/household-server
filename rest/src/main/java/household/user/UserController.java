package household.user;

import static household.Constants.DEFAULT_MEDIA_TYPE;

import java.util.Map;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	private final UserDTOMapper userMapper;
	private final UserResourceProcessor userResourceProcessor;

	@PostMapping(consumes=DEFAULT_MEDIA_TYPE)
	@ResponseStatus(HttpStatus.OK)
	public HttpEntity<Resource<UserDTO>> createUser(@RequestBody Map<String, String> data) {
		User createUser = userService.createUser(new User(null, data.get("email"), data.get("password")));
		return ResponseEntity.ok(createResource(createUser));
	}
	
	private Resource<UserDTO> createResource(User user) {
		Resource<UserDTO> resource = new Resource<UserDTO>(userMapper.map(user));
		return userResourceProcessor.process(resource);
	}
	
}
