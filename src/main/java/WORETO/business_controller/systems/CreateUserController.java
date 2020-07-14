package WORETO.business_controller.systems;

import WORETO.documents.User;
import WORETO.dtos.UserDto;
import WORETO.repositories.UserReactRepository;
import WORETO.services.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class CreateUserController {

    private UserReactRepository userReactRepository;
    private SequenceGenerator sequenceGenerator;
    private UserValidator userValidator;

    @Autowired
    public CreateUserController(UserReactRepository userReactRepository,
                                SequenceGenerator sequenceGenerator,
                                UserValidator userValidator) {
        this.userReactRepository = userReactRepository;
        this.sequenceGenerator = sequenceGenerator;
        this.userValidator = userValidator;
    }

    public Mono<UserDto> createUser(UserDto userDto) {
        User user = User.builder()
                .id(sequenceGenerator.getNextSequence(User.SEQUENCE_NAME))
                .email(userDto.getEmail())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .enable(userDto.getEnable())
                .password(userDto.getPassword())
                .roles(userDto.getRoles())
                .build();
        if (this.userValidator.validateUser(user)) {
            return this.userReactRepository.save(user).map(UserDto::new);
        } else {
            return null;
        }
    }
}
