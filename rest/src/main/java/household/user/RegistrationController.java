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
@RequestMapping("registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private final UserService userService;

	@PostMapping
	public String createUser(@RequestBody MultiValueMap<String, Object> data, Model model) {
        String nextPage = "registration";
		if (!Objects.equals(data.getFirst("password"), data.getFirst("passwordRepeat"))) {
			model.addAttribute("errorMessage", "Die Passwörter stimmen nicht überein.");
        } else  if (data.getFirst("dataProtection") == null) {
            model.addAttribute("errorMessage", "Bitte stimmen Sie den Datenschutz-Bestimmungen zu.");
        } else {
            try {
                userService.createUser(new User(null, ((String) data.getFirst("email")), ((String) data.getFirst("password"))));
                model.addAttribute("successMessage", "Benutzer wurde angelegt");
                nextPage = "login";
            } catch(UserAlreadyExistsException e) {
                model.addAttribute("errorMessage", "Die E-Mail Adresse ist schon vergeben.");
            }
        }

        return nextPage;
    }
    
}
