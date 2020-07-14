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
    private ValidateUserController validateUserController;

    @Autowired
    public CreateUserController(UserReactRepository userReactRepository,
                                SequenceGenerator sequenceGenerator,
                                ValidateUserController validateUserController) {
        this.userReactRepository = userReactRepository;
        this.sequenceGenerator = sequenceGenerator;
        this.validateUserController = validateUserController;
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
        if (this.validateUserController.validateUser(user)) {
            return this.userReactRepository.save(user).map(UserDto::new);
        } else {
            return null;
        }
    }
}
