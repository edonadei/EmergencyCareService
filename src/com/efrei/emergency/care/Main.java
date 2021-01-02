package com.efrei.emergency.care;

import com.efrei.emergency.care.services.EmergencyCare;
import com.efrei.emergency.care.services.Provider;
import com.efrei.emergency.care.workflows.PatientWorkflow;

public class Main {

    public static void main(String[] args) {
        Provider mainProvider = new Provider("Main provider", 2, 2);
        EmergencyCare emergencyCareService = new EmergencyCare("HP EST", 2, 2, 5, mainProvider);
        mainProvider.getEmergencyCareServices().put(emergencyCareService.getId(), emergencyCareService);

        emergencyCareService.start();
        mainProvider.start();
    }
}
