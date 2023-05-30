import api.AdminResource;
import api.HotelResource;
import models.Customer;
import models.IRoom;
import models.Room;
import models.RoomType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static models.RoomType.DOUBLE;
import static models.RoomType.SINGLE;

public class AdminMenu{
    public void adminMenu(){
        AdminResource adminResource = AdminResource.getInstance();
        HotelResource hotelResource = HotelResource.getInstance();
        MainMenu.choice = null;
        Scanner scanner = new Scanner(System.in);
        printAdminMenu();
        try {
            MainMenu.choice = scanner.next();

            String intRegex = "^[0-5]*$";
            Pattern pattern = Pattern.compile(intRegex);
            //if choice is not an integer
            assert MainMenu.choice != null;
            if (!pattern.matcher(MainMenu.choice).matches()) {
                System.out.println("Please enter a valid selection");
            } else {
                int choiceInt = Integer.parseInt(MainMenu.choice);

                switch (choiceInt) {
                    case 1 -> {
                        Collection<Customer> customers = adminResource.getAllCustomers();
                        for (Customer customer : customers) {
                            System.out.println(customer);
                        }
                    }
                    case 2 -> {
                        Collection<IRoom> rooms = adminResource.allRooms();
                        for (IRoom room : rooms) {
                            System.out.println(room);
                        }
                    }
                    case 3 -> adminResource.displayAllReservations();
                    case 4 -> {
                        System.out.println("Enter room number:");
                        Integer roomNumber;

                        try {
                            roomNumber = scanner.nextInt();

                        } catch (Exception exception) {
                            System.out.println("Please enter a valid room number");
                            break;
                        }
                        Collection<IRoom> rooms = adminResource.allRooms();
                        //if room number exists
                        IRoom room = hotelResource.getRoom(roomNumber.toString());
                        if (rooms.contains(room)) {
                            System.out.println("Room number already exists");
                            break;
                        }

                        System.out.println("Enter room price:");
                        double roomPrice;
                        try {
                            roomPrice = Double.parseDouble(scanner.next());
                        } catch (Exception exception) {
                            System.out.println("Please enter a valid room price");
                            break;
                        }
                        System.out.println("Enter room type SINGLE or DOUBLE in uppercase");
                        RoomType type;
                        String roomType = null;
                        try {
                            roomType = scanner.next();
                        } catch (Exception exception) {
                            System.out.println("Enter a valid room type");
                        }
                        if (roomType != null && roomType.equals("SINGLE")) {
                            type = SINGLE;
                        } else if (roomType != null && roomType.equals("DOUBLE")) {
                            type = DOUBLE;
                        } else {
                            System.out.println("Enter a valid room type");
                            break;
                        }
                        IRoom newRoom = new Room(roomNumber.toString(), type, roomPrice);
                        List<IRoom> roomList = new ArrayList<>();
                        roomList.add(newRoom);
                        adminResource.addRoom(roomList);
                        System.out.println("Room added!");
                    }
                    case 5 -> {
                        MainMenu menuMain = new MainMenu();
                        menuMain.mainMenu();
                    }
                    default -> System.out.println("Invalid choice");
                }
            }
        } catch (Exception exception) {
            System.out.println("Enter a valid selection");
        }
    }

        public void printAdminMenu(){
        System.out.println("Welcome to Admin Menu");
        System.out.println("1. See all customers");
        System.out.println("2. See all rooms");
        System.out.println("3. See all reservations");
        System.out.println("4. Add a room");
        System.out.println("5. Back to main menu");
        System.out.println();

    }
}