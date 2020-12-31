package com.efrei.emergency.care.entities;

import com.efrei.emergency.care.entities.resources.Doctor;
import com.efrei.emergency.care.entities.resources.Room;
import com.efrei.emergency.care.entities.tickets.Ticket;
import com.efrei.emergency.care.entities.tickets.TicketType;

import java.util.HashMap;
import java.util.UUID;

public class EmergencyCare {

    private String id;
    private String name;
    private HashMap<String, Room> rooms;
    private HashMap<String, Doctor> doctors;
    private HashMap<String, Patient> patients;

    public EmergencyCare(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.rooms = new HashMap<String, Room>();
        this.doctors = new HashMap<String, Doctor>();
        this.patients = new HashMap<String, Patient>();
    }

    public void getDoctor(Provider provider) {
        Ticket ticketGetResource = new Ticket(this.id, TicketType.NEED_DOCTOR);
        provider.getTickets().put(ticketGetResource.getId(), ticketGetResource);
    }

    public void giveDoctor(Provider provider, String doctorID) {
        Ticket ticketGiveResource = new Ticket(this.id, TicketType.GIVE_DOCTOR, doctorID);
        provider.getTickets().put(ticketGiveResource.getId(), ticketGiveResource);
    }

    public void getRoom(Provider provider) {
        Ticket ticketGetRoom = new Ticket(this.id, TicketType.NEED_ROOM);
        provider.getTickets().put(ticketGetRoom.getId(), ticketGetRoom);
    }

    public void getDoctor(Provider provider, String roomID) {
        Ticket ticketGetDoctor = new Ticket(this.id, TicketType.GIVE_DOCTOR, roomID);
        provider.getTickets().put(ticketGetDoctor.getId(), ticketGetDoctor);
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

    public HashMap<String, Patient> getPatients() {
        return patients;
    }

    public void setPatients(HashMap<String, Patient> patients) {
        this.patients = patients;
    }
}
