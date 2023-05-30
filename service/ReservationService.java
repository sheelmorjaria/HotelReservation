package service;

import models.Customer;
import models.IRoom;
import models.Reservation;

import java.util.*;

public class ReservationService {

    private static final ReservationService reference = new ReservationService();
    private final Collection<Reservation> reservations; // collection of reservations
    private final Collection<IRoom> rooms; // collection of rooms

    private Collection<Reservation> customerReservations;

    private ReservationService() {
        //private constructor
        this.reservations = new LinkedHashSet<>();
        this.rooms = new HashSet<>();
    }

    public static ReservationService getInstance() {
        return reference;
    }

    public void addRoom(IRoom room) {
        //add room to collection
        if (room != null) {
            rooms.add(room);
        }
    }

    public IRoom getARoom(String roomId) {
        for (IRoom room : rooms) {
            if (room.getRoomNumber().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        //reserve a room if not already reserved
        if (customer != null && room != null && checkInDate != null && checkOutDate != null) {
            Reservation reservation = null;

            reservation = new Reservation(customer, room, checkInDate, checkOutDate);


            if (!reservations.contains(reservation)) {
                reservations.add(reservation);
            }
            if (reservations.size() > 0) {
                return reservation;
            }
        }
        return null;
    }

    public boolean isFree2(IRoom room, Date checkInDate, Date checkOutDate){
        if(room != null && checkInDate != null && checkOutDate != null){
            for (Reservation reservation : reservations) {
                //check if room is free
                if (reservation.getRoom().equals(room)) {
                    if (checkInDate.before(reservation.getCheckInDate()) && checkOutDate.before(reservation.getCheckInDate())) {
                        return true;
                    } else if (checkInDate.after(reservation.getCheckOutDate()) && checkOutDate.after(reservation.getCheckOutDate())) {
                        return true;
                    }else if (checkInDate.equals(reservation.getCheckInDate()) && checkOutDate.equals(reservation.getCheckOutDate())) {
                        return false;
                    }else if (checkInDate.equals(reservation.getCheckInDate()) && checkOutDate.before(reservation.getCheckOutDate())) {
                        return false;
                    }else if (checkInDate.after(reservation.getCheckInDate()) && checkOutDate.equals(reservation.getCheckOutDate())) {
                        return false;
                    }else if (checkInDate.after(reservation.getCheckInDate()) && checkOutDate.before(reservation.getCheckOutDate())) {
                        return false;
                    }else if (checkInDate.before(reservation.getCheckInDate()) && checkOutDate.after(reservation.getCheckOutDate())) {
                        return false;
                    }else if (checkInDate.before(reservation.getCheckInDate()) && checkOutDate.equals(reservation.getCheckOutDate())) {
                        return false;
                    }else if (checkInDate.before(reservation.getCheckInDate()) && checkOutDate.after(reservation.getCheckInDate())) {
                        return false;
                    }else if (checkInDate.before(reservation.getCheckOutDate()) && checkOutDate.after(reservation.getCheckOutDate())) {
                        return false;
                    }else if (checkInDate.equals(reservation.getCheckInDate()) && checkOutDate.after(reservation.getCheckOutDate())){
                        return false;
                    }else{
                        return false;
                    }   
               }
            }
            return true;
        }
        return false;
    }


    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {//find available rooms
        if(checkInDate != null && checkOutDate != null){
            Collection<IRoom> availableRooms= new HashSet<>();
            for (IRoom room : rooms) {

                    //if room is free
                    if (isFree2(room, checkInDate, checkOutDate)) {
                        availableRooms.add(room);
                    }

            }
            if (availableRooms.size() > 0) {
                return availableRooms;
            }
        }
        return null;
    }




    //find rooms that are free
    public Collection<IRoom> findFreeAvailableRooms(Date checkInDate, Date checkOutDate) {
        if (checkInDate != null && checkOutDate != null) {
            Collection<IRoom> freeRooms = new HashSet<>();
            for (IRoom room : rooms) {
                if (room.getRoomPrice() == 0.0) {
                    if (isFree2(room, checkInDate, checkOutDate)) {
                        freeRooms.add(room);
                    }
                }
            }
            if (freeRooms.size() > 0) {
                return freeRooms;
            }
        }
        return null;
    }

    public Collection<IRoom> findPaidAvailableRooms(Date checkInDate, Date checkOutDate) {
        if (checkInDate != null && checkOutDate != null) {
            Collection<IRoom> paidRooms = new HashSet<>();
            for (IRoom room : rooms) {
                if (room.getRoomPrice() > 0.0) {
                    if (isFree2(room, checkInDate, checkOutDate)) {
                        paidRooms.add(room);
                    }
                }
            }
            if (paidRooms.size() > 0) {
                return paidRooms;
            }
        }
        return null;
    }

    //helper method
    public boolean isFree(IRoom room, Date checkInDate, Date checkOutDate) {
        if (room != null && checkInDate != null && checkOutDate != null) {
            for (Reservation reservation : reservations) {
                if (room.equals(reservation.getRoom())) {
                    if (checkInDate.before(reservation.getCheckInDate()) && checkOutDate.before(reservation.getCheckInDate())) {
                        return true;
                    } else if (checkInDate.after(reservation.getCheckOutDate()) && checkOutDate.after(reservation.getCheckOutDate())) {
                        return true;
                    }
                    else{
                        return false;
                    }   
                }
                else{
                    return false;
                }
            }   
        }
        return true;
    }




    public Collection<Reservation> getCustomerReservation(Customer customer){
        customerReservations = new HashSet<>();
        if (customer != null) {
            //get customer reservations
            for (Reservation reservation : reservations) {
                if(reservation.getCustomer().getEmail().equals(customer.getEmail())){
                    customerReservations.add(reservation);
                }
            }
            return customerReservations;
        }
        return null;
    }
    public void printAllReservation() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }

    }
    public Collection<Reservation> allReservations(){
        return reservations;
    }
    public Collection<IRoom> allRooms(){
        return rooms;
    }

    @Override
    public String toString () {
        return rooms.toString() + reservations.toString();
    }
}


