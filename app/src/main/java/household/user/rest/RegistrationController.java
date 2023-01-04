package household.user.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/registrations")
@CrossOrigin
@RequiredArgsConstructor
public class RegistrationController {

	private final UserService userService;
    private final UserDTOMapper userMapper;

	@PostMapping(consumes = {"application/vnd.household.v1+json"}, produces = {"application/vnd.household.v1+json"})
	public UserDTO createUser(@RequestBody UserRegistrationData registrationData) {
	    return userMapper.map(
            userService.createUser(new User(null, registrationData.email(), registrationData.password()))
        );
    }

}
