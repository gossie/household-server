package household.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
class ConflictException extends RuntimeException {

    ConflictException(Throwable cause) {
        super(cause);
    }
}
