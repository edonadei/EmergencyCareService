package com.efrei.emergency.care.entities.resources;

public class Resource {

    protected String id;
    protected Boolean busy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getBusy() {
        return busy;
    }

    public void setBusy(Boolean busy) {
        this.busy = busy;
    }
}
