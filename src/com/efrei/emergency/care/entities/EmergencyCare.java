package com.efrei.emergency.care.entities;

import com.efrei.emergency.care.entities.tickets.Ticket;
import com.efrei.emergency.care.entities.tickets.TicketType;

import java.util.UUID;
import java.util.concurrent.Semaphore;

public class EmergencyCare implements Runnable {

    protected String id;
    protected String name;
    protected Semaphore rooms;
    protected Semaphore doctors;
    protected Semaphore patients;

    public void run() {

    }

    public EmergencyCare(String name, int numberRooms, int numberDoctors, int numberPatients) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.rooms = new Semaphore(numberRooms);
        this.doctors = new Semaphore(numberDoctors);
        this.patients = new Semaphore(numberPatients);
    }

    public void getDoctor(Provider provider) {
        Ticket ticketGetResource = new Ticket(this.id, TicketType.NEED_DOCTOR);
        provider.getTickets().put(ticketGetResource.getId(), ticketGetResource);
    }

    public void giveDoctor(Provider provider) {
        Ticket ticketGiveResource = new Ticket(this.id, TicketType.GIVE_DOCTOR);
        provider.getTickets().put(ticketGiveResource.getId(), ticketGiveResource);
    }

    public void getRoom(Provider provider) {
        Ticket ticketGetRoom = new Ticket(this.id, TicketType.NEED_ROOM);
        provider.getTickets().put(ticketGetRoom.getId(), ticketGetRoom);
    }

    public void giveRoom(Provider provider) {
        Ticket ticketGetDoctor = new Ticket(this.id, TicketType.GIVE_DOCTOR);
        provider.getTickets().put(ticketGetDoctor.getId(), ticketGetDoctor);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Semaphore getRooms() {
        return rooms;
    }

    public void setRooms(Semaphore rooms) {
        this.rooms = rooms;
    }

    public Semaphore getDoctors() {
        return doctors;
    }

    public void setDoctors(Semaphore doctors) {
        this.doctors = doctors;
    }

    public Semaphore getPatients() {
        return patients;
    }

    public void setPatients(Semaphore patients) {
        this.patients = patients;
    }


}
