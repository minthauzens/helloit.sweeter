package lv.helloit.bootcamp.sweeter.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserDontExistException extends Exception {
    public UserDontExistException(String message) {
        super(message);
    }
}
