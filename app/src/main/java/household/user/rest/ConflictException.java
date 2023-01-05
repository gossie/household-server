package household.user.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
class ConflictException extends RuntimeException {

    private static final long serialVersionUID = -5705649684064893781L;

    ConflictException(Throwable cause) {
        super(cause);
    }
}
