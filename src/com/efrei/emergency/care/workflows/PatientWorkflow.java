package com.efrei.emergency.care.workflows;

import com.efrei.emergency.care.services.EmergencyCare;

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
}
