package br.com.agenda_petshop.adapter.in.rest.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record UserSelfUpdateRequest(
        @NotBlank String id,
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String password
) {
}
