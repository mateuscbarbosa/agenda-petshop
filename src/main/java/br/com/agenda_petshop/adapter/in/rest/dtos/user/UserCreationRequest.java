package br.com.agenda_petshop.adapter.in.rest.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UserCreationRequest(
        @NotBlank String name,
        @NotBlank String email,
        @NotNull Set<ProfileRequest> profiles
        ) {
}
