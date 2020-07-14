package WORETO.business_controller.systems;

import WORETO.documents.User;
import WORETO.dtos.UserDto;
import WORETO.repositories.UserReactRepository;
import WORETO.services.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class UpdateUserController {

    private UserReactRepository userReactRepository;
    private SequenceGenerator sequenceGenerator;
    private ValidateUserController validateUserController;

    @Autowired
    public UpdateUserController(UserReactRepository userReactRepository,
                                SequenceGenerator sequenceGenerator,
                                ValidateUserController validateUserController) {
        this.userReactRepository = userReactRepository;
        this.sequenceGenerator = sequenceGenerator;
        this.validateUserController = validateUserController;
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
