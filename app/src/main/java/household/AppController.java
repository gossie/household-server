package household;

import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/")
public class AppController {

    @GetMapping
    public Mono<String> root() {
        return index();
    }

    @GetMapping(path = "index.html")
    public Mono<String> index() {
        return Mono.just("index");
    }

    @GetMapping(path = "login.html")
    public Mono<String> login(ServerWebExchange exchange, Model model) {
        return exchange.getAttributeOrDefault(CsrfToken.class.getName(), Mono.empty())
            .map(t -> {
                model.addAttribute("_csrf", t);
                return "login";
            });
    }

    @GetMapping(path = "registration.html")
    public Mono<String> registration(ServerWebExchange exchange, Model model) {
        return exchange.getAttributeOrDefault(CsrfToken.class.getName(), Mono.empty())
            .map(t -> {
                model.addAttribute("_csrf", t);
                return "registration";
            });
    }

    @GetMapping(path = "household.html")
    public Mono<String> household() {
        return app();
    }

    @GetMapping(path = "app.html")
    public Mono<String> app() {
        return Mono.just("app");
    }

}
