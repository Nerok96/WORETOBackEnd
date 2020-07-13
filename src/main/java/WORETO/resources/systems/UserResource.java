package WORETO.resources.systems;

import WORETO.business_controller.systems.UserController;
import WORETO.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {

    public static final String USERS = "/users";

    private UserController userController;

    @Autowired
    public UserResource(UserController userController) {
        this.userController = userController;
    }

    @PostMapping()
    public Mono<UserDto> testGet(@Valid @RequestBody UserDto userDto) {
        return this.userController.createUser(userDto);
    }
}