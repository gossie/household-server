package household;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import household.user.rest.JwtService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthFilter.class);

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var headerValue = request.getHeader("Authorization");
        if (headerValue == null || headerValue.isEmpty()) {
            LOGGER.info("No authenication information -> request is processed");
            filterChain.doFilter(request, response);
            return;
        }

        var token = headerValue.replace("Bearer", "").trim();
        try {
            var claims = jwtService.extractClaims(token);
            setSecurityContext(claims.getSubject());
            LOGGER.info("User is authenticated -> request is processed");
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            LOGGER.info("token is not valid -> 401 is returned", e);
            response.setStatus(401);
        }
    }

    private void setSecurityContext(String subject) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(subject, "", List.of());
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
