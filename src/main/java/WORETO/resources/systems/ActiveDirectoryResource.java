package WORETO.resources.systems;

import WORETO.business_controller.systems.CreateUserController;
import WORETO.business_controller.systems.UserController;
import WORETO.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(ActiveDirectoryResource.USERS)
public class ActiveDirectoryResource {

    public static final String USERS = "/users";
    public static final String DISABLE = "/disable";
    public static final String USER_EMAIL = "/{email}";

    private UserController userController;
    private CreateUserController createUserController;

    @Autowired
    public ActiveDirectoryResource(UserController userController,
                                   CreateUserController createUser) {
        this.userController = userController;
        this.createUserController = createUser;
    }

    @PostMapping()
    public Mono<UserDto> creatUser(@Valid @RequestBody UserDto userDto) {
        return this.createUserController.createUser(userDto);
    }

    @PutMapping()
    public Mono<UserDto> updateUser(@Valid @RequestBody UserDto userDto) {
        return this.userController.updateUser(userDto);
    }

    @PutMapping(value = DISABLE + USER_EMAIL)
    public Mono<UserDto> disableUser(@PathVariable String email) {
        return this.userController.disableUser(email);
    }
}