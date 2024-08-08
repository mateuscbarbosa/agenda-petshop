package br.com.agenda_petshop.adapter.in.rest.dtos.auth;

import jakarta.validation.constraints.NotEmpty;

public record AuthRequest(
        @NotEmpty String email,
        @NotEmpty String password
) {
}
