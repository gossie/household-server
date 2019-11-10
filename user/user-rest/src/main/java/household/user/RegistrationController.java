package household.user;

import java.util.Objects;

import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("registrations")
@RequiredArgsConstructor
public class RegistrationController {

	private final UserService userService;

	@PostMapping
	public Mono<String> createUser(ServerWebExchange exchange, Model model) {
	    return exchange.getAttributeOrDefault(CsrfToken.class.getName(), Mono.empty())
            .flatMap(csrf -> determinePage(csrf, exchange, model));
    }

    private Mono<String> determinePage(Object csrf, ServerWebExchange exchange, Model model) {
	    return exchange.getFormData()
            .map(data -> {
                model.addAttribute("_csrf", csrf);
                if (!Objects.equals(data.getFirst("password"), data.getFirst("passwordRepeat"))) {
                    model.addAttribute("errorMessage", "Die Passwörter stimmen nicht überein.");
                    return "registration";
                } else  if (data.getFirst("dataProtection") == null) {
                    model.addAttribute("errorMessage", "Bitte stimmen Sie den Datenschutz-Bestimmungen zu.");
                    return "registration";
                } else {
                    try {
                        userService.createUser(new User(null, ((String) data.getFirst("email")), ((String) data.getFirst("password"))));
                        model.addAttribute("successMessage", "Benutzer wurde angelegt");
                        return "login";
                    } catch(UserAlreadyExistsException e) {
                        model.addAttribute("errorMessage", "Die E-Mail Adresse ist schon vergeben.");
                        return "registration";
                    }
                }
            });
    }
}
