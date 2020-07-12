package WORETO.documents;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserTest {

    @Test
    void testUserBuilder(){
        LocalDateTime ldt = LocalDateTime.now();
        String id = "1";
        String email = "email@email.com";
        String name ="Name";
        String surname = "Surname";
        Boolean enable = true;
        String password = "password";
        Role[] roles = new Role[]{ Role.PARTNER};

        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setEnable(enable);
        user.setPassword(password);
        user.setRoles(roles);

        User userBuilder = User.builder()
                .id(id)
                .email(email)
                .name(name)
                .surname(surname)
                .enable(enable)
                .password(password)
                .roles(roles)
                .build();

        assertEquals(user.getId(), userBuilder.getId());
        assertEquals(user.getEmail(), userBuilder.getEmail());
        assertEquals(user.getName(), userBuilder.getName());
        assertEquals(user.getSurname(), userBuilder.getSurname());
        assertEquals(user.getEnable(), userBuilder.getEnable());
        assertEquals(user.getPassword(), userBuilder.getPassword());
        assertEquals(user.getRoles(), userBuilder.getRoles());

    }
}
