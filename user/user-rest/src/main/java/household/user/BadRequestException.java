package household.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -9037684586723133316L;

    BadRequestException() {
        super();
    }
}
