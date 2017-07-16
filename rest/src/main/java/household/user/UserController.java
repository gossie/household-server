package household.user;

import static household.Constants.DEFAULT_MEDIA_TYPE;

import java.util.Map;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@PostMapping(produces=DEFAULT_MEDIA_TYPE)
	public HttpEntity<Resource<UserDTO>> createUser(@RequestBody Map<String, String> data) {
		User createUser = userService.createUser(new User(null, data.get("email"), data.get("password")));
		return ResponseEntity.ok(createResource(createUser));
	}
	
	@GetMapping(path="/{userId}", produces=DEFAULT_MEDIA_TYPE)
	public HttpEntity<Resource<UserDTO>> getUser(@PathVariable Long userId) {
		User user = userService.determineUser(userId);
		return ResponseEntity.ok(createResource(user));
	}
	
	@PostMapping(path="/login", produces=DEFAULT_MEDIA_TYPE)
	public HttpEntity<Resource<UserDTO>> login() {
		User user = userService.determineCurrentUser();
		return ResponseEntity.ok(createResource(user));
	}
	
	@PostMapping(path="/{userId}/invitations", consumes=DEFAULT_MEDIA_TYPE)
	@ResponseStatus(value = HttpStatus.OK)
	public HttpEntity<Resource<UserDTO>> invite(@PathVariable Long userId, @RequestBody InvitationRequestDTO invitation) {
		User invitingUser = userService.determineUser(userId);
		userService.invite(invitation.getEmail(), invitingUser.getHouseholdId());
		return ResponseEntity.ok(createResource(invitingUser));
	}
	
	@DeleteMapping(path="/{userId}/invitations/{invitationId}")
	@ResponseStatus(value = HttpStatus.OK)
	public HttpEntity<Resource<UserDTO>> rejectInvitation(@PathVariable Long userId, @PathVariable Long invitationId) {
		userService.rejectInvitation(userId, invitationId);
		return ResponseEntity.ok(createResource(userService.determineUser(userId)));
	}
	
	private Resource<UserDTO> createResource(User user) {
		Resource<UserDTO> resource = new Resource<UserDTO>(userMapper.map(user));
		return userResourceProcessor.process(resource);
	}
	
}
