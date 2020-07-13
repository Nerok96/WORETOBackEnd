package WORETO.business_controller.systems;

import WORETO.documents.User;
import WORETO.dtos.UserDto;
import WORETO.repositories.UserReactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Controller
public class UserValidator {

    private UserReactRepository userReactRepository;

    @Autowired
    public UserValidator(UserReactRepository userReactRepository){
        this.userReactRepository = userReactRepository;
    }
    public Mono<Boolean> validateUser(User user){
        return validateUser(new UserDto(user));
    }

    public Mono<Boolean> validateUser(UserDto userToValidate){
        if(Objects.nonNull(userToValidate.getEmail()) &&
                Objects.nonNull(userToValidate.getName()) &&
                Objects.nonNull(userToValidate.getSurname()) &&
                Objects.nonNull(userToValidate.getEnable()) &&
                Objects.nonNull(userToValidate.getPassword()) &&
                Objects.nonNull(userToValidate.getRoles())){
            return this.validateUser(userToValidate.getEmail());
        }else{
            return Mono.just(false);
        }
    }

    private Mono<Boolean> validateUser(String email){
        return this.userReactRepository.findByEmail(email).hasElement().map(aBoolean -> !aBoolean);
    }
}
