package com.efrei.emergency.care;

import com.efrei.emergency.care.services.EmergencyCare;
import com.efrei.emergency.care.services.Provider;
import com.efrei.emergency.care.workflows.PatientWorkflow;

public class Main {

    public static void main(String[] args) {
        Provider mainProvider = new Provider("Main provider", 2, 2);
        EmergencyCare emergencyCareService1 = new EmergencyCare("HP EST", 2, 2, 2, mainProvider);
        mainProvider.getEmergencyCareServices().put(emergencyCareService1.getId(), emergencyCareService1);
        EmergencyCare emergencyCareService2 = new EmergencyCare("HP OUEST", 2, 2, 2, mainProvider);
        mainProvider.getEmergencyCareServices().put(emergencyCareService2.getId(), emergencyCareService2);
        EmergencyCare emergencyCareService3 = new EmergencyCare("HP SUD", 2, 2, 2, mainProvider);
        mainProvider.getEmergencyCareServices().put(emergencyCareService3.getId(), emergencyCareService3);

        emergencyCareService1.start();
        emergencyCareService2.start();
        emergencyCareService3.start();
        mainProvider.start();
    }
}
