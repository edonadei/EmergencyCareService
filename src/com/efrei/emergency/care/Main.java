package com.efrei.emergency.care;

import com.efrei.emergency.care.services.EmergencyCare;
import com.efrei.emergency.care.services.Provider;
import com.efrei.emergency.care.workflows.PatientWorkflow;

public class Main {

    public static void main(String[] args) {
        Provider mainProvider = new Provider("Main provider", 1, 1);
        EmergencyCare emergencyCareService = new EmergencyCare("HP EST", 1, 1, 10, mainProvider);
        mainProvider.getEmergencyCareServices().put(emergencyCareService.getId(), emergencyCareService);

        emergencyCareService.start();
        mainProvider.start();
        int i = 15;
        while(i > 0) {
        System.out.println("test : " + (emergencyCareService.getPatients().availablePermits()));
            if (emergencyCareService.getPatients().availablePermits() > 0) {
                PatientWorkflow patientWorkflow = new PatientWorkflow(emergencyCareService);
                patientWorkflow.start();
                i--;
            }
        }
    }
}
