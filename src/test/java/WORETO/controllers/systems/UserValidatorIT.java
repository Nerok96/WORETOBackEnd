package WORETO.controllers.systems;

import WORETO.TestConfig;
import WORETO.business_controller.systems.UserValidator;
import WORETO.documents.Role;
import WORETO.documents.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class UserValidatorIT {

    @Autowired
    private UserValidator userValidator;

    @Test
    void testValidateUserValidUser(){
        User user = User.builder()
                .email("mew email")
                .name("name")
                .surname("surname")
                .enable(true)
                .password("password")
                .roles(Role.TIMERECORDER)
                .build();
        assertTrue(userValidator.validateUser(user));
    }

    @Test
    void testValidateUserValidUserNoValidAttributes(){
        User user = User.builder()
                .email("new email")
                .build();
        user.setPassword(null);
        assertFalse(userValidator.validateUser(user));
    }
}
