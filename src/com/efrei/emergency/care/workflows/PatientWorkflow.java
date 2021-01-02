package com.efrei.emergency.care.workflows;

import com.efrei.emergency.care.services.EmergencyCare;

import java.sql.SQLOutput;
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
        System.out.println("Start: Patient Workflow with ID: " + this.id);
        boolean tooMuchWaitingTime = this.emergencyCare.checkForResources();
        if (tooMuchWaitingTime) {
            System.out.println("Patient exit emergency room because there is too much waiting time");
        } else {
            System.out.println("Patient need to fill paperwork");
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

    public void fillPaperWork() {
        System.out.println("Patient (" + this.id + ") fill the paperwork");
        emergencyCare.setWaitingTime(emergencyCare.getWaitingTime() + 10);
        randomWaitingTime();
    }

    public void seeingDoctor() {
        System.out.println("Patient (" + this.id + ") enter the room and start examining");
        emergencyCare.getDoctors().acquireUninterruptibly(1);
        emergencyCare.getRooms().acquireUninterruptibly(1);
        randomWaitingTime();
    }

    public void checkOut() {
        System.out.println("Patient (" + this.id + ") left the room");
        emergencyCare.getDoctors().release(1);
        emergencyCare.getRooms().release(1);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public EmergencyCare getEmergencyCare() {
        return emergencyCare;
    }

    public void setEmergencyCare(EmergencyCare emergencyCare) {
        this.emergencyCare = emergencyCare;
    }
}
