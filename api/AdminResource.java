package api;

import models.Customer;
import models.IRoom;
import models.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static final AdminResource reference = new AdminResource();
    private AdminResource(){
    }
    public static AdminResource getInstance(){
        return reference;
    }

    public Customer getCustomer(String email){
       return CustomerService.getInstance().getCustomer(email);

    }
    public void addRoom(List<IRoom> rooms){
        for (IRoom room : rooms) {
            ReservationService.getInstance().addRoom(room);
        }        
    }

    public Collection<IRoom> allRooms(){
        return ReservationService.getInstance().allRooms();
    }

    public Collection<Reservation> allReservations(){
        return  ReservationService.getInstance().allReservations();
    }


    public Collection<Customer> getAllCustomers(){
        return CustomerService.getInstance().getAllCustomers();
    }
    public void displayAllReservations(){
        ReservationService.getInstance().printAllReservation();
        }
        
}

