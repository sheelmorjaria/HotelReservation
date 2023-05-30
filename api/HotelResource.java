package api;

import models.Customer;
import models.IRoom;
import models.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static final HotelResource reference = new HotelResource();
    private HotelResource(){
    }
    public static HotelResource getInstance(){
        return reference;
    }
    public Customer getCustomer(String email){
        return CustomerService.getInstance().getCustomer(email);

    }
    public void createACustomer(String email, String firstName, String lastName){
        CustomerService.getInstance().addCustomer(email,firstName,lastName);
    }

    public IRoom getRoom(String roomNumber){
        return ReservationService.getInstance().getARoom(roomNumber);

    }
    public boolean isRoomAvailable(IRoom room, Date checkInDate, Date checkOutDate){
        return ReservationService.getInstance().isFree2(room,checkInDate,checkOutDate);
    }
    public Reservation bookARoom(Customer customer,IRoom room, Date checkInDate, Date checkOutDate){
        return ReservationService.getInstance().reserveARoom(customer,room,checkInDate,checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(String email){
        return ReservationService.getInstance().getCustomerReservation(getCustomer(email));
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return ReservationService.getInstance().findRooms(checkIn,checkOut);
    }
}
