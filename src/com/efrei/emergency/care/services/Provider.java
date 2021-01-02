package com.efrei.emergency.care.services;

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
    Thread thread;


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

    public void start() {
        if (thread == null) {
            thread = new Thread(this, id);
            thread.start();
        }
    }

    public void printProvider (String text) {
        System.out.println(this.name + ": " + text);
    }

    public void consumeTicket() {
        if (!this.tickets.isEmpty()) {
            List<Ticket> ticketsList = new ArrayList<>(this.tickets.values());
            int randomIndex = new Random().nextInt(ticketsList.size());

            Ticket ticket = ticketsList.get(randomIndex);

            EmergencyCare emergencyCare = this.emergencyCareServices.get(ticket.getEmergencyCareID());
            printProvider("Ticket found asked by " + emergencyCare.getName());

            if (ticket.getType() == TicketType.GIVE_DOCTOR) {
                printProvider(emergencyCare.getName() + " give a doctor");
                emergencyCare.getDoctors().acquireUninterruptibly(1);
                this.doctors.release(1);
                printProvider("- provider doctors: " + this.doctors.availablePermits());
                printProvider("- emergency care doctors: " + emergencyCare.getDoctors().availablePermits());
            }
            if (ticket.getType() == TicketType.GIVE_ROOM) {
                printProvider(emergencyCare.getName() + " give a room");
                emergencyCare.getRooms().acquireUninterruptibly(1);
                this.rooms.release(1);
                printProvider("- provider rooms: " + this.rooms.availablePermits());
                printProvider("- emergency care rooms: " + emergencyCare.getRooms().availablePermits());
            }
            if (ticket.getType() == TicketType.NEED_DOCTOR) {
                printProvider(emergencyCare.getName() + " need a doctor");
                this.doctors.acquireUninterruptibly(1);
                emergencyCare.getDoctors().release(1);
                printProvider("- provider doctors: " + this.doctors.availablePermits());
                printProvider("- emergency care doctors: " + emergencyCare.getDoctors().availablePermits());
            }
            if (ticket.getType() == TicketType.NEED_ROOM) {
                printProvider(emergencyCare.getName() + " need a room");
                this.rooms.acquireUninterruptibly(1);
                emergencyCare.getRooms().release(1);
                printProvider("- provider rooms: " + this.rooms.availablePermits());
                printProvider("- emergency care rooms: " + emergencyCare.getRooms().availablePermits());
            }

            this.tickets.remove(ticket.getId());
        } else {
            printProvider("No ticket found");
        }
    }

    public HashMap<String, Ticket> getTickets() {
        return tickets;
    }

    public HashMap<String, EmergencyCare> getEmergencyCareServices() {
        return emergencyCareServices;
    }

}
