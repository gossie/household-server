package household.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class NotAuthenticatedException extends RuntimeException {

    NotAuthenticatedException(Throwable cause) {
        super(cause);
    }
}
