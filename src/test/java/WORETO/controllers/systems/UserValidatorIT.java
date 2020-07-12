package WORETO.controllers.systems;

import WORETO.TestConfig;
import WORETO.business_controller.systems.UserValidator;
import WORETO.documents.Role;
import WORETO.documents.User;
import WORETO.repositories.UserReactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
import reactor.test.StepVerifier;

@TestConfig
public class UserValidatorIT {

    @Autowired
    private UserValidator userValidator;

    @Test
    void testValidateUserValidUser(){
        /*
        User user = User.builder()
                .email("admin@admin.com")
                .name("name")
                .surname("surname")
                .enable(true)
                .password("password")
                .roles(Role.TIMERECORDER)
                .build();
        assertTrue(userValidator.validateUser(user));

         */

        User user = User.builder()
                .email("admin@admin.com")
                .name("name")
                .surname("surname")
                .enable(true)
                .password("password")
                .roles(Role.TIMERECORDER)
                .build();
        StepVerifier
                .create(this.userValidator.validateUser(user))
                .expectNext(true)
                .expectComplete()
                .verify();
    }

    @Test
    void testValidateUserValidUserNoValidMail(){
        User user = User.builder()
                .email("NO MAIL")
                .name("name")
                .surname("surname")
                .enable(true)
                .password("password")
                .roles(Role.TIMERECORDER)
                .build();
        StepVerifier
                .create(this.userValidator.validateUser(user))
                .expectNext(false)
                .expectComplete()
                .verify();
    }

    @Test
    void testValidateUserValidUserNoValidAttributes(){
        User user = User.builder()
                .email("NO MAIL")
                .name("name")
                .surname("surname")
                .enable(true)
                .password("password")
                .roles(Role.TIMERECORDER)
                .build();
        user.setPassword(null);
        StepVerifier
                .create(this.userValidator.validateUser(user))
                .expectNext(false)
                .expectComplete()
                .verify();
    }
}
