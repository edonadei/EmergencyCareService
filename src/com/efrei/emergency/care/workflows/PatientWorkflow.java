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

    public void run(){
        System.out.println("Start: Patient Workflow with ID: " + this.id);
        //while (true) {
            this.emergencyCare.getPatients().acquireUninterruptibly(1);
            this.emergencyCare.checkForResources();
            System.out.println("Patient need to fill paperwork");
            fillPaperWork();
            seeingDoctor();
            checkOut();
            this.emergencyCare.getPatients().release(1);
        //}
        // It's the workflow

        // Checking ressources
        // Filling paperwork
        // Seeing the doctor
        // Checking out
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, id);
            thread.start();
        }
    }

    public void fillPaperWork () {
        System.out.println("Patient (" + this.id + ") fill the paperwork");
        /*try {
            Thread.sleep(360 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public void seeingDoctor () {
        System.out.println("Patient (" + this.id + ") enter the room and start examining");
        emergencyCare.getDoctors().acquireUninterruptibly(1);
        emergencyCare.getRooms().acquireUninterruptibly(1);
        /*try {
            Random rn = new Random();
            Thread.sleep((rn.nextInt((1500 - 0) + 1) + 0) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public void checkOut () {
        System.out.println("Patient (" + this.id + ") left the room");
        emergencyCare.getDoctors().release(1);
        emergencyCare.getRooms().release(1);
        /*try {
            Thread.sleep(360 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
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
