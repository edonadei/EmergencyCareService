package com.efrei.emergency.care.entities.resources;

import com.efrei.emergency.care.entities.resources.Resource;

import java.util.UUID;

public class Room extends Resource {

    public Room(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
