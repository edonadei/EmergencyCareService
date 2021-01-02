package com.efrei.emergency.care.workflows;

import com.efrei.emergency.care.services.EmergencyCare;

import java.util.Random;
import java.util.UUID;

public class PatientWorkflow implements Runnable {
    String id;
    Thread thread;
    EmergencyCare emergencyCare;

    public PatientWorkflow(EmergencyCare emergencyCare) {
        this.emergencyCare = emergencyCare;
        this.id = UUID.randomUUID().toString();
    }

    public void run() {
        printEmergencyCare("Start: Patient Workflow with ID: " + this.id);
        boolean tooMuchWaitingTime = this.emergencyCare.checkForResources();
        if (tooMuchWaitingTime) {
            printEmergencyCare("Patient exit emergency room because there is too much waiting time");
        } else {
            printEmergencyCare("Patient need to fill paperwork");
            fillPaperWork();
            seeingDoctor();
            checkOut();
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, id);
            thread.start();
        }
    }

    public void printEmergencyCare (String text) {
        System.out.println(this.emergencyCare.getName() + ": " + text);
    }

    public void fillPaperWork() {
        printEmergencyCare("Patient (" + this.id + ") fill the paperwork");
        emergencyCare.setWaitingTime(emergencyCare.getWaitingTime() + 10);
        randomWaitingTime();
    }

    public void seeingDoctor() {
        emergencyCare.getDoctors().acquireUninterruptibly(1);
        emergencyCare.getRooms().acquireUninterruptibly(1);
        printEmergencyCare("Patient (" + this.id + ") enter the room and start examining");
        randomWaitingTime();
    }

    public void checkOut() {
        emergencyCare.getDoctors().release(1);
        emergencyCare.getRooms().release(1);
        printEmergencyCare("Patient (" + this.id + ") left the room");
        emergencyCare.setWaitingTime(emergencyCare.getWaitingTime() - 10);
        randomWaitingTime();
        this.emergencyCare.getPatients().acquireUninterruptibly(1);
    }

    public void randomWaitingTime() {
        try {
            Random rn = new Random();
            Thread.sleep((rn.nextInt((5 - 2) + 1) + 2) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
