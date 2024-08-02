package br.com.agenda_petshop.adapter.out.persistence.mongo.entities;

public class ProfileMongo {
    private RoleMongo role;

    public ProfileMongo() {
    }

    public ProfileMongo(RoleMongo role) {
        this.role = role;
    }

    public RoleMongo getRole() {
        return role;
    }

    public ProfileMongo setRole(RoleMongo role) {
        this.role = role;
        return this;
    }
}
