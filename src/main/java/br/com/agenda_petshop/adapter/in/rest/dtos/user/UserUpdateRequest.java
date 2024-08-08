package br.com.agenda_petshop.adapter.in.rest.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UserUpdateRequest(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String password,
        @NotNull Set<ProfileRequest> profiles
) {
}
