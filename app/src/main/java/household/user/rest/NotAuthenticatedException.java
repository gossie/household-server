package household.user.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class NotAuthenticatedException extends RuntimeException {

    private static final long serialVersionUID = -7473939119792402603L;

    NotAuthenticatedException(Throwable cause) {
        super(cause);
    }
}
