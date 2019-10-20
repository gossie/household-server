package household.user;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private final UserService userService;

	@PostMapping
	public String createUser(@RequestBody MultiValueMap<String, String> data, Model model) {
		if (!Objects.equals(data.getFirst("password"), data.getFirst("passwordRepeat"))) {
			throw new BadRequestException();
		}

	    try {
            userService.createUser(new User(null, ((String) data.getFirst("email")).toLowerCase(), ((String) data.getFirst("password"))));
            model.addAttribute("successMessage", "Benutzer wurde angelegt");
            return "login";
        } catch(UserAlreadyExistsException e) {
            throw new ConflictException(e);
        }
    }
    
}
