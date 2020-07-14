package WORETO.business_controller.systems;

import WORETO.documents.User;
import WORETO.dtos.UserDto;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class ValidateUserController {

    public Boolean validateUser(User user) {
        return validateUser(new UserDto(user));
    }

    public Boolean validateUser(UserDto userToValidate) {
        return Objects.nonNull(userToValidate.getEmail()) &&
                Objects.nonNull(userToValidate.getName()) &&
                Objects.nonNull(userToValidate.getSurname()) &&
                Objects.nonNull(userToValidate.getEnable()) &&
                Objects.nonNull(userToValidate.getPassword()) &&
                Objects.nonNull(userToValidate.getRoles());
    }
}
