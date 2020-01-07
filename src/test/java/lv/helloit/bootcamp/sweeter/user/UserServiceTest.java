package lv.helloit.bootcamp.sweeter.user;

import com.sparkpost.exception.SparkPostException;
import lv.helloit.bootcamp.sweeter.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserDAO userDAO;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserService victim;

    @Test
    void shouldSendEmailWhenUserRegisters() throws SparkPostException {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setEmail("test@mail.com");
        createUserDto.setPassword("password");

        User createdUser = victim.registerUser(createUserDto);

        assertEquals(createdUser.getEmail(), "test@mail.com");
        assertNotNull(createdUser.getId());

        verify(emailService).sendNewUserEmail("test@mail.com");
    }
}