package models;

public class FreeRoom extends Room implements Comparable<Room>{
    private final Double price;
    public FreeRoom(String roomNumber,RoomType enumeration, Double price) {
        super(roomNumber,enumeration, price);
        this.price = 0.0;
    }
    @Override
    public boolean isFree(){return true;}

    @Override
    public String toString(){
        return this.roomNumber + " " + this.enumeration + " " + this.price;
    }

}
