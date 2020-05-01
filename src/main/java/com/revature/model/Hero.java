package com.revature.model;

import java.util.UUID;

public class Hero {
    private String name;
    private String superPower;
    private UUID id;

    public Hero() {
    }

    public Hero(String name, String superPower) {
        this.name = name;
        this.superPower = superPower;
    }

    public Hero(String name, String superPower, UUID id) {
        this.name = name;
        this.superPower = superPower;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperPower() {
        return superPower;
    }

    public void setSuperPower(String superPower) {
        this.superPower = superPower;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
