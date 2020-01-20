package lv.helloit.bootcamp.sweeter.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(5));
    }

    public boolean check(String passwordToCheck, String hashToCompareTo) {
        return BCrypt.checkpw(passwordToCheck, hashToCompareTo);
    }
}
