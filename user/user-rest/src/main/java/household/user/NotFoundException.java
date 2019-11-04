package household.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -71803527526217597L;

    NotFoundException(Throwable cause) {
        super(cause);
    }
}
