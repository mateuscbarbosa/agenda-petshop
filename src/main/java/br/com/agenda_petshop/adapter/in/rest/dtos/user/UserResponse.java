package br.com.agenda_petshop.adapter.in.rest.dtos.user;

public record UserResponse(
    String id,
    String email,
    String name,
    UserStatusResponse userStatus,
    boolean isFirstPassword
) {
}
