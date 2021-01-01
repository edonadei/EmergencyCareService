package com.efrei.emergency.care;

import com.efrei.emergency.care.entities.EmergencyCare;
import com.efrei.emergency.care.entities.Provider;

public class Main {

    public static void main(String[] args) {
        Provider mainProvider = new Provider("Main provider", 1, 1);
        EmergencyCare emergencyCareService = new EmergencyCare("HP EST", 1, 1, 10, mainProvider);
        mainProvider.getEmergencyCareServices().put(emergencyCareService.getId(), emergencyCareService);

        emergencyCareService.start();
        mainProvider.start();
    }
}
