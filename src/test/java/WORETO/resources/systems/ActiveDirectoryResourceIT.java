package WORETO.resources.systems;

import WORETO.documents.Role;
import WORETO.documents.User;
import WORETO.dtos.UserDto;
import WORETO.resources.ApiTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static WORETO.resources.systems.ActiveDirectoryResource.DISABLE;
import static WORETO.resources.systems.ActiveDirectoryResource.USERS;
import static WORETO.resources.systems.ActiveDirectoryResource.USER_EMAIL;
import static org.junit.jupiter.api.Assertions.*;

@ApiTestConfig
public class ActiveDirectoryResourceIT {

    @Value("${server.servlet.contextPath}")
    private String contextPath;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateMessage() {
        UserDto userDto = new UserDto(
                User.builder()
                        .email("email")
                        .name("name")
                        .surname("surname")
                        .enable(true)
                        .password("password")
                        .roles(Role.TIMERECORDER)
                        .build()
        );
        UserDto userDtoOutput = this.webTestClient
                .post().uri(contextPath + USERS)
                .body(BodyInserters.fromObject(userDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserDto.class)
                .value(Assertions::assertNotNull)
                .returnResult().getResponseBody();
        assertNotNull(userDtoOutput);
        assertEquals(userDto.getEmail(), userDtoOutput.getEmail());
        assertEquals(userDto.getName(), userDtoOutput.getName());
        assertEquals(userDto.getSurname(), userDtoOutput.getSurname());
        assertEquals(userDto.getEnable(), userDtoOutput.getEnable());
        assertEquals(userDto.getPassword(), userDtoOutput.getPassword());
        assertEquals(Role.TIMERECORDER, userDtoOutput.getRoles()[0]);

    }

    @Test
    void testCreateMessageFailMailAlreadyInDB() {
        UserDto userDto = new UserDto(
                User.builder()
                        .email("admin@admin.com")
                        .name("name")
                        .surname("surname")
                        .enable(true)
                        .password("password")
                        .roles(Role.TIMERECORDER)
                        .build()
        );
        this.webTestClient
                .post().uri(contextPath + USERS)
                .body(BodyInserters.fromObject(userDto))
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

    @Test
    void testCreateMessageFailMissingFields() {
        UserDto userDto = new UserDto(
                User.builder()
                        .email("new email")
                        .build()
        );
        this.webTestClient
                .post().uri(contextPath + USERS)
                .body(BodyInserters.fromObject(userDto))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(UserDto.class)
                .value(Assertions::assertNull);
    }

    @Test
    void testUpdateUserNonExist() {
        this.webTestClient
                .put().uri(contextPath + USERS)
                .body(BodyInserters.fromObject(
                        new UserDto(User.builder()
                                .email("email")
                                .build())
                )).exchange()
                .expectStatus()
                .is5xxServerError();
    }

    @Test
    void testUpdateUser() {
        String email = "partner@partner.com";
        String name = "name";
        String surname = "surname";
        Boolean enable = true;
        String password = "password";
        Role role = Role.TIMERECORDER;
        UserDto userDto = this.webTestClient
                .put().uri(contextPath + USERS)
                .body(BodyInserters.fromObject(
                        new UserDto(User.builder()
                                .email(email)
                                .name(name)
                                .surname(surname)
                                .enable(enable)
                                .password(password)
                                .roles(role)
                                .build())
                )).exchange().expectStatus().isOk().expectBody(UserDto.class)
                .value(Assertions::assertNotNull)
                .returnResult().getResponseBody();
        assertNotNull(userDto);
        assertEquals(email, userDto.getEmail());
        assertEquals(name, userDto.getName());
        assertEquals(surname, userDto.getSurname());
        assertEquals(enable, userDto.getEnable());
        assertEquals(password, userDto.getPassword());
        assertEquals(role, userDto.getRoles()[0]);
    }

    @Test
    void testDisableUser() {
        String email = "partner@partner.com";
        UserDto userDto = this.webTestClient
                .put().uri(contextPath + USERS + DISABLE + USER_EMAIL, email)
                .exchange().expectStatus()
                .isOk()
                .expectBody(UserDto.class)
                .value(Assertions::assertNotNull)
                .returnResult().getResponseBody();
        assertNotNull(userDto);
        assertEquals(email, userDto.getEmail());
        assertFalse(userDto.getEnable());
    }
}
