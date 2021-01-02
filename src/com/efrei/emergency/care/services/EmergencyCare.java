package com.efrei.emergency.care.services;

import com.efrei.emergency.care.entities.tickets.Ticket;
import com.efrei.emergency.care.entities.tickets.TicketType;
import com.efrei.emergency.care.workflows.PatientWorkflow;

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
    protected int waitingTime;
    Thread thread;

    public void run() {
        System.out.println("Start: Emergency care service " + this.name + "  with ID: " + this.id);
        if (this.getPatients().availablePermits() == 0) {
            checkForResources();
        }
        takeCareOfPatients(this.getPatients().availablePermits());
        while (true) {
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
        this.waitingTime = 0;
    }

    public void newPatientsComing() {
        Random random = new Random();
        int numberOfNewPatients = random.nextInt((10 - 1) + 1) + 1;
        patients.release(numberOfNewPatients);
        printEmergencyCare(numberOfNewPatients + " new patients are coming");
        printEmergencyCare(this.patients.availablePermits() + " patients in total");
        takeCareOfPatients(numberOfNewPatients);
    }

    public void printEmergencyCare (String text) {
        System.out.println(this.name + ": " + text);
    }

    public boolean checkForResources() {
        if (doctors.availablePermits() == 0) {
            printEmergencyCare("No more doctors, sending ticket to get one ");
            getDoctor();
        }

        if (rooms.availablePermits() == 0) {
            printEmergencyCare("No more rooms, sending ticket to get one ");
            getRoom();
        }

        if (patients.availablePermits() == 0) {
            if (rooms.availablePermits() > 0) {
                printEmergencyCare("No more patients and room available, sending ticket to give room ");
                giveRoom();
            }
            if (doctors.availablePermits() > 0) {
                printEmergencyCare("No more patients and doctor available, sending ticket to give doctor");
                giveDoctor();
            }
        }
        printEmergencyCare("Waiting time : " + this.waitingTime);
        return this.waitingTime > 50;
    }

    public void takeCareOfPatients(int numberOfPatients) {
        for (int i = 0; i < numberOfPatients; i++) {
            PatientWorkflow patientWorkflow = new PatientWorkflow(this);
            patientWorkflow.start();
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
        Ticket ticketGiveRoom = new Ticket(this.id, TicketType.GIVE_ROOM);
        provider.getTickets().put(ticketGiveRoom.getId(), ticketGiveRoom);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Semaphore getRooms() {
        return rooms;
    }

    public Semaphore getDoctors() {
        return doctors;
    }

    public Semaphore getPatients() {
        return patients;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }


}
