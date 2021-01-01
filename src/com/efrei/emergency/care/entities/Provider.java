package com.efrei.emergency.care.entities;

import com.efrei.emergency.care.entities.tickets.Ticket;
import com.efrei.emergency.care.entities.tickets.TicketType;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Provider implements Runnable {

    private String id;
    private String name;
    private Semaphore rooms;
    protected Semaphore doctors;
    protected HashMap<String, Ticket> tickets;
    protected HashMap<String, EmergencyCare> emergencyCareServices;


    public Provider(String name, int numberDoctors, int numberRooms) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
        this.tickets = new HashMap<String, Ticket>();
        this.doctors = new Semaphore(numberDoctors);
        this.rooms = new Semaphore(numberRooms);
        this.emergencyCareServices = new HashMap<String, EmergencyCare>();
    }

    public void run() {
        System.out.println("Start: Provider " + this.name + "  with ID: " + this.id);
        while (true) {
            consumeTicket();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consumeTicket() {
        if (!this.tickets.isEmpty()) {
            List<Ticket> ticketsList = new ArrayList<>(this.tickets.values());
            int randomIndex = new Random().nextInt(ticketsList.size());

            Ticket ticket = ticketsList.get(randomIndex);

            EmergencyCare emergencyCare = this.emergencyCareServices.get(ticket.getEmergencyCareID());
            System.out.println("Ticket found asked by " + emergencyCare.getName());

            if (ticket.getType() == TicketType.GIVE_DOCTOR) {
                emergencyCare.getDoctors().acquireUninterruptibly(1);
                this.doctors.release(1);
            }
            if (ticket.getType() == TicketType.GIVE_ROOM) {
                emergencyCare.getRooms().acquireUninterruptibly(1);
                this.rooms.release(1);
            }
            if (ticket.getType() == TicketType.NEED_DOCTOR) {
                this.doctors.acquireUninterruptibly(1);
                emergencyCare.getDoctors().release(1);
            }
            if (ticket.getType() == TicketType.NEED_ROOM) {
                this.rooms.acquireUninterruptibly(1);
                emergencyCare.getRooms().release(1);
            }

            this.tickets.remove(ticket.getId());
        } else {
            System.out.println("No ticket found");
        }
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
}
