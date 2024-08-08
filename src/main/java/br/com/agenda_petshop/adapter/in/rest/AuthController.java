package br.com.agenda_petshop.adapter.in.rest;

import br.com.agenda_petshop.adapter.in.rest.dtos.auth.AuthRequest;
import br.com.agenda_petshop.adapter.in.rest.dtos.auth.AuthResponse;
import br.com.agenda_petshop.application.usecases.auth.LoginUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthController {
    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @Operation(summary = "Authenticate am user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = { @Content(schema = @Schema(implementation = AuthResponse.class), mediaType = "application/json")})
    })
    @PostMapping
    public ResponseEntity<AuthResponse> authenticate(@RequestBody @Valid AuthRequest login){
        var token = loginUseCase.execute(login.email(), login.password());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
