package br.com.agenda_petshop.adapter.in.rest;

import br.com.agenda_petshop.adapter.in.rest.dtos.user.UserCreationRequest;
import br.com.agenda_petshop.adapter.in.rest.dtos.user.UserResponse;
import br.com.agenda_petshop.adapter.in.rest.dtos.user.UserSelfUpdateRequest;
import br.com.agenda_petshop.adapter.in.rest.dtos.user.UserUpdateRequest;
import br.com.agenda_petshop.adapter.in.rest.mapper.UserDTOMapper;
import br.com.agenda_petshop.application.usecases.user.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final ListUsersUseCase listUsersUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final InactiveUserUseCase inactiveUserUseCase;
    private final ChangeFirstPasswordUserUseCase changeFirstPasswordUserUseCase;
    private final SelfUpdateUserUseCase selfUpdateUserUseCase;
    private final ResetUserPasswordUseCase resetUserPasswordUseCase;

    public UserController(CreateUserUseCase createUserUseCase, ListUsersUseCase listUsersUseCase,
                          UpdateUserUseCase updateUserUseCase, InactiveUserUseCase inactiveUserUseCase,
                          ChangeFirstPasswordUserUseCase changeFirstPasswordUserUseCase,
                          SelfUpdateUserUseCase selfUpdateUserUseCase, ResetUserPasswordUseCase resetUserPasswordUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.listUsersUseCase = listUsersUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.inactiveUserUseCase = inactiveUserUseCase;
        this.changeFirstPasswordUserUseCase = changeFirstPasswordUserUseCase;
        this.selfUpdateUserUseCase = selfUpdateUserUseCase;
        this.resetUserPasswordUseCase = resetUserPasswordUseCase;
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserResponse>> listAllUsers(){
        final var users = listUsersUseCase.execute();
        final var usersResponse = users.stream()
                .map(UserDTOMapper.INSTANCE::mapToResponse)
                .toList();

        return ResponseEntity.ok().body(usersResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        final var newUser = UserDTOMapper.INSTANCE.mapToEntity(request);
        final var user = createUserUseCase.execute(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserDTOMapper.INSTANCE.mapToResponse(user));
    }

    @PutMapping("/selfUpdate")
    public ResponseEntity<UserResponse> selfUpdate(@RequestBody @Valid UserSelfUpdateRequest request){
        final var updateUser = UserDTOMapper.INSTANCE.mapToEntity(request);
        final var user = selfUpdateUserUseCase.execute(updateUser);

        return ResponseEntity.ok().body(UserDTOMapper.INSTANCE.mapToResponse(user));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") @NotBlank String id,
                                                   @RequestBody @Valid UserUpdateRequest request){
        final var updateUser = UserDTOMapper.INSTANCE.mapToEntity(request);
        final var user = updateUserUseCase.execute(id, updateUser);

        return ResponseEntity.ok().body(UserDTOMapper.INSTANCE.mapToResponse(user));
    }

    @PatchMapping("/{id}/resetpassword")
    public ResponseEntity<UserResponse> resetSelectedUserPassword(@PathVariable("id") @NotBlank String id){
        final var user = resetUserPasswordUseCase.execute(id);

        return ResponseEntity.ok().body(UserDTOMapper.INSTANCE.mapToResponse(user));
    }

    @DeleteMapping("/{id}/inactivate")
    public ResponseEntity<UserResponse> inactivateUser(@PathVariable("id") @NotBlank String id){
        inactiveUserUseCase.execute(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/firstpassword")
    public ResponseEntity<UserResponse> changeFirstPassword(@RequestBody @Valid UserUpdateRequest request){
        final var firstUser = UserDTOMapper.INSTANCE.mapToEntity(request);
        final var user = changeFirstPasswordUserUseCase.execute(firstUser);

        return ResponseEntity.ok().body(UserDTOMapper.INSTANCE.mapToResponse(user));
    }

}
