package models;

import java.util.Date;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;
    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    public Customer getCustomer(){
        return this.customer;
    }
    public IRoom getRoom(){
        return this.room;
    }
    public Date getCheckInDate(){
        return this.checkInDate;
    }
    public Date getCheckOutDate(){
        return this.checkOutDate;
    }
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    public void setRoom(IRoom room){
        this.room = room;
    }
    public void setCheckInDate(Date checkInDate){
        this.checkInDate = checkInDate;
    }   
    public void setCheckOutDate(Date checkOutDate){
        this.checkOutDate = checkOutDate;
    }
    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Reservation reservation = (Reservation) obj;
        return reservation.getCustomer().equals(this.customer) && reservation.getRoom().equals(this.room) && reservation.getCheckInDate().equals(this.checkInDate) && reservation.getCheckOutDate().equals(this.checkOutDate);
    }
    @Override
    public int hashCode(){
        return this.customer.hashCode() + this.room.hashCode() + this.checkInDate.hashCode() + this.checkOutDate.hashCode();
    }
    
    @Override
    public String toString(){
        return this.customer + " " + this.room + " " + this.checkInDate + " " + this.checkOutDate;
    }
}
