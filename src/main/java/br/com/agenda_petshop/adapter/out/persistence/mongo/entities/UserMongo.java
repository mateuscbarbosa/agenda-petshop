package br.com.agenda_petshop.adapter.out.persistence.mongo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document(collation = "users")
public class UserMongo {

    @Id
    private String id;
    private String email;
    private String password;
    private String name;
    private Set<ProfileMongo> profiles;
    private UserStatusMongo userStatus;
    private Boolean isFirstPassword;

    public UserMongo() {
        this.profiles = new HashSet<>();
    }

    public UserMongo(String id, String email, String password, String name, Set<ProfileMongo> profiles, UserStatusMongo userStatus, Boolean isFirstPassword) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.profiles = profiles;
        this.userStatus = userStatus;
        this.isFirstPassword = isFirstPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMongo userMongo = (UserMongo) o;
        return Objects.equals(id, userMongo.id) && Objects.equals(email, userMongo.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    public String getId() {
        return id;
    }

    public UserMongo setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserMongo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserMongo setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserMongo setName(String name) {
        this.name = name;
        return this;
    }

    public Set<ProfileMongo> getProfiles() {
        return profiles;
    }

    public UserMongo setProfiles(Set<ProfileMongo> profiles) {
        this.profiles = profiles;
        return this;
    }

    public UserStatusMongo getUserStatus() {
        return userStatus;
    }

    public UserMongo setUserStatus(UserStatusMongo userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public Boolean getFirstPassword() {
        return isFirstPassword;
    }

    public UserMongo setFirstPassword(Boolean firstPassword) {
        isFirstPassword = firstPassword;
        return this;
    }
}
