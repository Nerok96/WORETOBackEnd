package WORETO.business_controller.systems;


import WORETO.documents.User;
import WORETO.dtos.UserDto;
import WORETO.repositories.UserReactRepository;
import WORETO.services.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class UserController {

    private UserReactRepository userReactRepository;
    private SequenceGenerator sequenceGenerator;
    private UserValidator userValidator;

    @Autowired
    public UserController(UserReactRepository userReactRepository,
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

    public Mono<UserDto> updateUser(UserDto userDto) {
        Mono<User> user = this.userReactRepository.findByEmail(userDto.getEmail())
                .switchIfEmpty(Mono.error(new RuntimeException("No user for user:" + userDto.getEmail())))
                .map(user1 -> {
                    user1.setEmail(userDto.getEmail());
                    user1.setName(userDto.getName());
                    user1.setSurname(userDto.getSurname());
                    user1.setEnable(userDto.getEnable());
                    user1.setPassword(userDto.getPassword());
                    user1.setRoles(userDto.getRoles());
                    return user1;
                });
        return Mono.when(user)
                .then(this.userReactRepository.saveAll(user).next()).map(UserDto::new);
    }
}
