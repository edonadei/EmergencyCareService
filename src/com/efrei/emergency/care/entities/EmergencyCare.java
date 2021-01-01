package com.efrei.emergency.care.entities;

import com.efrei.emergency.care.entities.tickets.Ticket;
import com.efrei.emergency.care.entities.tickets.TicketType;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Semaphore;

public class EmergencyCare implements Runnable {

    protected String id;
    protected String name;
    protected Semaphore rooms;
    protected Semaphore doctors;
    protected Semaphore patients;
    protected Provider provider;
    Thread thread;

    public void run() {
        System.out.println("Start: Emergency care service " + this.name + "  with ID: " + this.id);
        while (true) {
            checkForResources();
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newPatientsComing();
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, id);
            thread.start();
        }
    }

    public EmergencyCare(String name, int numberRooms, int numberDoctors, int numberPatients, Provider provider) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.rooms = new Semaphore(numberRooms);
        this.doctors = new Semaphore(numberDoctors);
        this.patients = new Semaphore(numberPatients);
        this.provider = provider;
    }

    public void newPatientsComing() {
        Random random = new Random();
        int numberOfNewPatients = random.nextInt(2);
        patients.release(numberOfNewPatients);
        System.out.println(this.name + ": " + numberOfNewPatients + " new patients are coming");
        System.out.println(this.name + ": " + this.patients.availablePermits() + " patients in total");
    }

    public void checkForResources() {
        if (doctors.availablePermits() == 0) {
            System.out.println(this.name + ": No more doctors, sending ticket to get one ");
            getDoctor();
        }

        if (rooms.availablePermits() == 0) {
            System.out.println(this.name + ": No more rooms, sending ticket to get one ");
            getRoom();
        }

        if (patients.availablePermits() == 0) {
            if (rooms.availablePermits() > 0) {
                System.out.println(this.name + ": No more patients and room available, sending ticket to give room ");
                giveRoom();
            }
            if (doctors.availablePermits() > 0) {
                System.out.println(this.name + ": No more patients and room available, sending ticket to get one ");
                giveDoctor();
            }
        }
    }

    public void getDoctor() {
        Ticket ticketGetResource = new Ticket(this.id, TicketType.NEED_DOCTOR);
        provider.getTickets().put(ticketGetResource.getId(), ticketGetResource);
    }

    public void giveDoctor() {
        Ticket ticketGiveResource = new Ticket(this.id, TicketType.GIVE_DOCTOR);
        provider.getTickets().put(ticketGiveResource.getId(), ticketGiveResource);
    }

    public void getRoom() {
        Ticket ticketGetRoom = new Ticket(this.id, TicketType.NEED_ROOM);
        provider.getTickets().put(ticketGetRoom.getId(), ticketGetRoom);
    }

    public void giveRoom() {
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
