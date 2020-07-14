package WORETO.business_controller.systems;

import WORETO.documents.User;
import WORETO.dtos.UserDto;
import WORETO.repositories.UserReactRepository;
import WORETO.services.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class DisableUserController {

    private UserReactRepository userReactRepository;
    private SequenceGenerator sequenceGenerator;
    private ValidateUserController validateUserController;

    @Autowired
    public DisableUserController(UserReactRepository userReactRepository,
                                 SequenceGenerator sequenceGenerator,
                                 ValidateUserController validateUserController) {
        this.userReactRepository = userReactRepository;
        this.sequenceGenerator = sequenceGenerator;
        this.validateUserController = validateUserController;
    }


    public Mono<UserDto> disableUser(String email) {
        Mono<User> user = this.userReactRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new RuntimeException("No user for user:" + email)))
                .map(user1 -> {
                    user1.setEnable(false);
                    return user1;
                });
        return Mono.when(user)
                .then(this.userReactRepository.saveAll(user).next()).map(UserDto::new);
    }
}
