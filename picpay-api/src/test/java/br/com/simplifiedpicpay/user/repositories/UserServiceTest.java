package br.com.simplifiedpicpay.user.repositories;

import br.com.simplifiedpicpay.user.exception.UserNotFoundException;
import br.com.simplifiedpicpay.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("Should throw UserNotFoundException when user id is null")
    void shouldThrowExceptionWhenUserIdIsNull() {
        Exception exceptionResponse = Assertions.assertThrows(UserNotFoundException.class, () -> {
           this.userService.findUserById(null);
        });

        Assertions.assertEquals("User not found by id.", exceptionResponse.getMessage());
    }
}