package household.user;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@ExposesResourceFor(UserDTO.class)
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private final UserService userService;
	private final UserDTOMapper userMapper;
	private final UserResourceProcessor userResourceProcessor;

	@PostMapping(consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.v1+json"})
	public HttpEntity<Resource<UserDTO>> createUser(@RequestBody Map<String, String> data) {
	    try {
            User createUser = userService.createUser(new User(null, data.get("email").toLowerCase(), data.get("password")));
            return ResponseEntity.ok(createResource(createUser));
        } catch(UserAlreadyExistsException e) {
            throw new ConflictException(e);
        }
	}

	@PutMapping(path="/{userId}",  consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.v1+json"})
    public HttpEntity<Resource<UserDTO>> changePassword(@PathVariable Long userId, @RequestBody Map<String, String> data) {
	    User user = userService.changePassword(userId, data.get("currentPassword"), data.get("newPassword"));
        return ResponseEntity.ok(createResource(user));
    }

	@GetMapping(path="/{userId}", produces={"application/vnd.household.v1+json"})
	public HttpEntity<Resource<UserDTO>> getUser(@PathVariable Long userId) {
		User user = userService.determineUser(userId);
		return ResponseEntity.ok(createResource(user));
	}

	@PostMapping(path="/login", produces={"application/vnd.household.v1+json"})
	public HttpEntity<Resource<UserDTO>> login() {
	    try {
            User user = userService.determineCurrentUser();
            return ResponseEntity.ok(createResource(user));
        } catch(IllegalStateException e) {
            throw new NotAuthenticatedException(e);
        }
	}

	@PostMapping(path="/{userId}/invitations", consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.v1+json"})
	@ResponseStatus(value = HttpStatus.OK)
	public HttpEntity<Resource<UserDTO>> invite(@PathVariable Long userId, @RequestBody InvitationRequestDTO invitation) {
		User invitingUser = userService.determineUser(userId);
		try {
            userService.invite(invitation.getEmail().toLowerCase(), invitingUser);
            return ResponseEntity.ok(createResource(invitingUser));
        } catch(IllegalStateException e) {
		    throw new NotFoundException(e);
        }
	}

    @PostMapping(path="/{userId}/invitations/{invitationId}", produces={"application/vnd.household.v1+json"})
    @ResponseStatus(value = HttpStatus.OK)
    public HttpEntity<Resource<UserDTO>> acceptInvitation(@PathVariable Long userId, @PathVariable Long invitationId) {
        userService.acceptInvitation(userId, invitationId);
        return ResponseEntity.ok(createResource(userService.determineUser(userId)));
    }

	@DeleteMapping(path="/{userId}/invitations/{invitationId}", produces={"application/vnd.household.v1+json"})
	@ResponseStatus(value = HttpStatus.OK)
	public HttpEntity<Resource<UserDTO>> rejectInvitation(@PathVariable Long userId, @PathVariable Long invitationId) {
		userService.rejectInvitation(userId, invitationId);
		return ResponseEntity.ok(createResource(userService.determineUser(userId)));
	}

	private Resource<UserDTO> createResource(User user) {
		Resource<UserDTO> resource = new Resource<>(userMapper.map(user));
		return userResourceProcessor.process(resource);
	}
}
