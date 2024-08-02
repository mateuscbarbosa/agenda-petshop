package br.com.agenda_petshop.model.user;

import org.springframework.security.core.GrantedAuthority;

public class Profile implements GrantedAuthority {

    private Role role;

    public Profile() {
    }

    public Profile(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role.name();
    }

    public Role getRole() {
        return role;
    }

    public Profile setRole(Role role) {
        this.role = role;
        return this;
    }
}
