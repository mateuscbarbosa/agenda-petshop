package br.com.agenda_petshop.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class User implements UserDetails {

    private String id;
    private String email;
    private String password;
    private String name;
    private Set<Profile> profiles;
    private UserStatus userStatus;
    private Boolean isFirstPassword;

    public User() {
        this.profiles = new HashSet<>();
    }

    public User(String id, String email, String password, String name, Set<Profile> profiles, UserStatus userStatus, Boolean isFirstPassword) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.profiles = profiles;
        this.userStatus = userStatus;
        this.isFirstPassword = isFirstPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.profiles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isEnabled() {
        if(this.userStatus.ordinal() == 0)
            return true;
        return false;
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User generatePassword(int length){
        this.password = new PasswordGenerator().generatePassword(length);
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Profile> getProfiles() {
        return profiles;
    }

    public User setProfiles(Set<Profile> profiles) {
        this.profiles = profiles;
        return this;
    }

    public void addProfile(Profile profile){
        this.profiles.add(profile);
    }

    public void removeProfile(Profile profile){
        this.profiles.remove(profile);
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public User setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public Boolean isFirstPassword() {
        return isFirstPassword;
    }

    public User setFirstPassword(Boolean firstPassword) {
        isFirstPassword = firstPassword;
        return this;
    }
}