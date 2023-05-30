package models;

public class Room implements IRoom,Comparable<Room>{

    protected final String roomNumber;
    protected final Double price;
    protected final RoomType enumeration;

    public Room(String roomNumber, RoomType enumeration , Double price) {
        super();
        this.roomNumber = roomNumber;
        this.enumeration = enumeration;
        this.price = price;
    }



    @Override
    public String getRoomNumber() {
        return roomNumber;
    }


    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree(){ return false;}

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
       Room room = (Room) obj;
       return room.getRoomNumber().equals(this.roomNumber) && room.getRoomType().equals(this.enumeration) && room.getRoomPrice().equals(this.price);
   }
   @Override
    public String toString(){
        return this.roomNumber + " " + this.enumeration + " " + this.price;
   }
    @Override
    public int compareTo(Room o) {
        return this.roomNumber.compareTo(o.getRoomNumber());
    }

 }
