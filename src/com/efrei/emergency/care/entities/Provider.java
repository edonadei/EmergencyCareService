package com.efrei.emergency.care.entities;

import com.efrei.emergency.care.entities.resources.Doctor;
import com.efrei.emergency.care.entities.resources.Room;
import com.efrei.emergency.care.entities.tickets.Ticket;
import com.efrei.emergency.care.entities.tickets.TicketType;

import java.util.*;

public class Provider {

    private String id;
    private String name;
    private HashMap<String, Room> rooms;
    protected HashMap<String, Doctor> doctors;
    protected HashMap<String, Ticket> tickets;
    protected HashMap<String, EmergencyCare> emergencyCareServices;

    public Provider(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
        this.tickets = new HashMap<String, Ticket>();
        this.doctors = new HashMap<String, Doctor>();
        this.emergencyCareServices = new HashMap<String, EmergencyCare>();
        this.rooms = new HashMap<String, Room>();
    }

    public void consumeTicket() {
        List<Ticket> ticketsList = new ArrayList<>(this.tickets.values());
        int randomIndex = new Random().nextInt(ticketsList.size());
        Ticket ticket = ticketsList.get(randomIndex);

        if (ticket.getType() == TicketType.GIVE_DOCTOR){
            EmergencyCare emergencyCare = this.emergencyCareServices.get(ticket.getEmergencyCareID());
            Doctor doctorToShare = emergencyCare.getDoctors().get(ticket.getResourceID());
            this.doctors.put(doctorToShare.getId(), doctorToShare);
            emergencyCare.getDoctors().remove(ticket.getResourceID());
        }
        if (ticket.getType() == TicketType.GIVE_ROOM){
            EmergencyCare emergencyCare = this.emergencyCareServices.get(ticket.getEmergencyCareID());
            Room roomToShare = emergencyCare.getRooms().get(ticket.getResourceID());
            this.rooms.put(roomToShare.getId(), roomToShare);
            emergencyCare.getRooms().remove(ticket.getResourceID());
        }
        if (ticket.getType() == TicketType.NEED_DOCTOR){
            List<Doctor> valuesDoctors = new ArrayList<>(this.doctors.values());
            if (valuesDoctors.size() > 0) {
                int randomIndexDoctor = new Random().nextInt(valuesDoctors.size());
                Doctor doctorToGive = valuesDoctors.get(randomIndexDoctor);
                EmergencyCare emergencyCare = this.emergencyCareServices.get(ticket.getEmergencyCareID());
                emergencyCare.getDoctors().put(doctorToGive.getId(), doctorToGive);
                this.doctors.remove(doctorToGive.getId());
            }
        }
        if (ticket.getType() == TicketType.NEED_ROOM){
            List<Room> valuesRooms = new ArrayList<>(this.rooms.values());
            if (valuesRooms.size() > 0) {
                int randomIndexRoom = new Random().nextInt(valuesRooms.size());
                Room roomToGive = valuesRooms.get(randomIndexRoom);
                EmergencyCare emergencyCare = this.emergencyCareServices.get(ticket.getEmergencyCareID());
                emergencyCare.getRooms().put(roomToGive.getId(), roomToGive);
                this.rooms.remove(roomToGive.getId());
            }
        }

        this.tickets.remove(ticket.getId());
    }

    public HashMap<String, Room> getRooms() {
        return rooms;
    }

    public void setRooms(HashMap<String, Room> rooms) {
        this.rooms = rooms;
    }

    public HashMap<String, Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(HashMap<String, Doctor> doctors) {
        this.doctors = doctors;
    }

    public HashMap<String, Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(HashMap<String, Ticket> tickets) {
        this.tickets = tickets;
    }

    public HashMap<String, EmergencyCare> getEmergencyCareServices() {
        return emergencyCareServices;
    }

    public void setEmergencyCareServices(HashMap<String, EmergencyCare> emergencyCareServices) {
        this.emergencyCareServices = emergencyCareServices;
    }


}
