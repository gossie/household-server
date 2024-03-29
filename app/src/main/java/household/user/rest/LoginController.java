package household.user.rest;

import java.security.Principal;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping(path = "/login", consumes = {"application/vnd.household.v1+json"}, produces = {"application/vnd.household.v1+json"})
    public ResponseEntity<Token> login(@RequestBody LoginData loginData) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginData.email(), loginData.password()));
            return ResponseEntity.ok(new Token(jwtService.createToken(new HashMap<>(), loginData.email())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping(path = "/refresh", produces = {"application/vnd.household.v1+json"})
    public ResponseEntity<Token> refreshToken(Principal principal) {
        return ResponseEntity.ok(new Token(jwtService.createToken(new HashMap<>(), principal.getName())));
    }

}
