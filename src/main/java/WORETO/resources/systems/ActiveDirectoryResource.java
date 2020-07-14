package WORETO.resources.systems;

import WORETO.business_controller.systems.CreateUserController;
import WORETO.business_controller.systems.DisableUserController;
import WORETO.business_controller.systems.UpdateUserController;
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

    private CreateUserController createUserController;
    private UpdateUserController updateUserController;
    private DisableUserController disableUserController;

    @Autowired
    public ActiveDirectoryResource(CreateUserController createUser,
                                   UpdateUserController updateUserController,
                                   DisableUserController disableUserController) {
        this.createUserController = createUser;
        this.updateUserController = updateUserController;
        this.disableUserController = disableUserController;
    }

    @PostMapping()
    public Mono<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return this.createUserController.createUser(userDto);
    }

    @PutMapping()
    public Mono<UserDto> updateUser(@Valid @RequestBody UserDto userDto) {
        return this.updateUserController.updateUser(userDto);
    }

    @PutMapping(value = DISABLE + USER_EMAIL)
    public Mono<UserDto> disableUser(@PathVariable String email) {
        return this.disableUserController.disableUser(email);
    }
}