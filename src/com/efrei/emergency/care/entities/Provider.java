package com.efrei.emergency.care.entities;

import com.efrei.emergency.care.entities.resources.Doctor;
import com.efrei.emergency.care.entities.resources.Room;
import com.efrei.emergency.care.entities.tickets.Ticket;
import com.efrei.emergency.care.entities.tickets.TicketType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Provider {

    public void consumeTicket() {
        List<Ticket> valuesList = new ArrayList<Ticket>(this.tickets.values());
        int randomIndex = new Random().nextInt(valuesList.size());
        Ticket randomTicket = valuesList.get(randomIndex);

        if (randomTicket.getType() == TicketType.GIVE_DOCTOR){

        }
        if (randomTicket.getType() == TicketType.GIVE_ROOM){

        }
        if (randomTicket.getType() == TicketType.NEED_DOCTOR){

        }
        if (randomTicket.getType() == TicketType.NEED_ROOM){

        }

        this.tickets.remove(randomTicket.getId());
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

    private HashMap<String, Room> rooms;
    private HashMap<String, Doctor> doctors;
    private HashMap<String, Ticket> tickets;
}
