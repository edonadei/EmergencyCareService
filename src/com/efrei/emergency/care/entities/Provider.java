package com.efrei.emergency.care.entities;

import com.efrei.emergency.care.entities.resources.Doctor;
import com.efrei.emergency.care.entities.resources.Room;
import com.efrei.emergency.care.entities.tickets.Ticket;

import java.util.HashMap;

public class Provider {

    public void consumeTicket() {
        // do something
    }

    public void addEmergencyCareService() {

    }

    public HashMap<String, Room> getRooms() {
        return rooms;
    }

    public void setRooms(HashMap<String, Room> rooms) {
        this.rooms = rooms;
    }

    public HashMap<String, Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(HashMap<String, Doctor> doctors) {
        this.doctors = doctors;
    }

    public HashMap<String, Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(HashMap<String, Ticket> tickets) {
        this.tickets = tickets;
    }

    public HashMap<String, EmergencyCare> getEmergencyCareServices() {
        return emergencyCareServices;
    }

    public void setEmergencyCareServices(HashMap<String, EmergencyCare> emergencyCareServices) {
        this.emergencyCareServices = emergencyCareServices;
    }

    private HashMap<String, Room> rooms;
    private HashMap<String, Doctor> doctors;
    private HashMap<String, Ticket> tickets;
    private HashMap<String, EmergencyCare> emergencyCareServices;
}
