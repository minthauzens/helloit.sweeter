package lv.helloit.bootcamp.sweeter.security;

import lv.helloit.bootcamp.sweeter.user.User;
import lv.helloit.bootcamp.sweeter.user.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SweeterAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;
    private final PasswordService passwordService;

    public SweeterAuthenticationProvider(UserService userService, PasswordService passwordService) {
        this.userService = userService;
        this.passwordService = passwordService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException("No password was provided");
        }

        String emailFromLoginPAge = authentication.getName();

        Optional<User> possibleUser = userService.getUserByEmail(emailFromLoginPAge);

        if (possibleUser.isEmpty()) {
            throw new BadCredentialsException("User with " + emailFromLoginPAge + " not found!");
        }

        User user = possibleUser.get();
        String passwordFromLoginPage = authentication.getCredentials().toString();
        //hashedPassword.equals(user.getPasswordHash())
        if (passwordService.check(passwordFromLoginPage, user.getPasswordHash())) {
            return new UsernamePasswordAuthenticationToken(emailFromLoginPAge,
                    authentication.getCredentials(),
                    List.of(new SimpleGrantedAuthority("USER")));
        }

        throw new BadCredentialsException("Wrong credentials!");


    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
