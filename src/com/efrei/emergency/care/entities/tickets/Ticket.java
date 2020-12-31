package com.efrei.emergency.care.entities.tickets;

import java.util.UUID;

public class Ticket {
    private String id;
    private String resourceID;
    private String emergencyCareID;
    private TicketType type;

    public Ticket(String resourceID, String emergencyCareID, TicketType type) {
        this.id = UUID.randomUUID().toString();
        this.resourceID = resourceID;
        this.emergencyCareID = emergencyCareID;
        this.type = type;
    }

    public Ticket( String emergencyCareID, TicketType type) {
        this.id = UUID.randomUUID().toString();
        this.emergencyCareID = emergencyCareID;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public String getEmergencyCareID() {
        return emergencyCareID;
    }

    public void setEmergencyCareID(String emergencyCareID) {
        this.emergencyCareID = emergencyCareID;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }
}
