package com.efrei.emergency.care;

import com.efrei.emergency.care.entities.EmergencyCare;
import com.efrei.emergency.care.entities.Provider;
import com.efrei.emergency.care.entities.resources.Doctor;
import com.efrei.emergency.care.entities.resources.Room;

public class Main {

    public static void main(String[] args) {
        Provider mainProvider = new Provider("Main provider");
        EmergencyCare emergencyCareService = new EmergencyCare("HP EST");
        mainProvider.getEmergencyCareServices().put(emergencyCareService.getId(), emergencyCareService);

        Doctor doc1 = new Doctor("Gregory", "House");
        Doctor doc2 = new Doctor("Doctor", "Strange");
        Room room1 = new Room("Aries");
        Room room2 = new Room("Aurius");

        mainProvider.getDoctors().put(doc1.getId(),doc1);
        mainProvider.getRooms().put(room1.getId(), room1);

        emergencyCareService.getDoctors().put(doc2.getId(), doc2);
        emergencyCareService.getRooms().put(room2.getId(), room2);

        emergencyCareService.giveDoctor(mainProvider, doc2.getId());
        mainProvider.consumeTicket();
        emergencyCareService.getDoctor(mainProvider);
        mainProvider.consumeTicket();
    }
}
