import api.AdminResource;
import api.HotelResource;
import models.Customer;
import models.IRoom;
import models.Reservation;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class MainMenu {
    private final HotelResource hotelResource = HotelResource.getInstance();
    private final AdminResource adminResource = AdminResource.getInstance();
    private Date checkInDate, checkOutDate;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static final Scanner scanner = new Scanner(System.in);
    public static String choice;
    private boolean flag = true;
    private String firstName = null;
    private String lastName = null;
    private IRoom room;

    public MainMenu() {
    }


    public void mainMenu() {
        while (flag) {
            printMenu();
            //while choice is an integer
            try {
                choice = scanner.next();

                String intRegex = "^[0-5]*$";
                Pattern pattern = compile(intRegex);
                //if choice is not an integer
                if (!pattern.matcher(choice).matches()) {
                    System.out.println("Please enter a valid selection");
                } else {
                    int choiceInt = Integer.parseInt(choice);
                    switch (choiceInt) {
                        case 1 -> caseOne();
                        case 2 -> caseTwo();
                        case 3 -> caseThree();
                        case 4 -> {
                            AdminMenu adminMenu = new AdminMenu();
                            adminMenu.adminMenu();
                        }
                        case 5 -> {
                            flag = false;
                            System.exit(0);
                        }
                        default -> System.out.println("Please enter a valid choice");
                    }
                }
            }    catch (Exception exception) {
                System.out.println("Please enter a valid selection");
            }
        }
    }


    public void caseOne() {
        String dateRegex = "((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";

        Pattern datePattern = compile(dateRegex);

        try {
            System.out.println("Enter check in date please format as (YYYY-MM-DD):");

            String checkIn = scanner.next();
            assert checkIn != null;
            if (datePattern.matcher(checkIn).matches()) {

                checkInDate = sdf.parse(checkIn);

                try {
                    System.out.println("Enter check out date please format(YYYY-MM-DD):");


                    String checkOut = scanner.next();
                    assert checkOut != null;
                    if (datePattern.matcher(checkOut).matches()) {


                        checkOutDate = sdf.parse(checkOut);

                        assert checkInDate != null;
                        assert checkOutDate != null;
                        if (!checkInDate.after(checkOutDate)) {

                            if (!checkInDate.equals(checkOutDate)) {

                                Collection<IRoom> findRooms = hotelResource.findARoom(checkInDate, checkOutDate);
                                Collection<IRoom> allRooms = adminResource.allRooms();
                                if (allRooms != null) {

                                    if (findRooms != null) {
                                        for (IRoom room : findRooms) {
                                            System.out.println(room);
                                        }
                                        System.out.println("Enter customer email to book a room:");
                                        String email;
                                        String emailRegex = "^(.+)@(.+).com$";
                                        Pattern pattern = compile(emailRegex);
                                        try {
                                            email = scanner.next();
                                            assert email != null;
                                            if (pattern.matcher(email).matches()) {
                                                //System.out.println("Enter room number to book a room:");
                                                Collection<Customer> allCustomers = adminResource.getAllCustomers();
                                                //to do check if customer  exists
                                                Customer customer = adminResource.getCustomer(email);
                                                if (allCustomers.contains(customer)) {
                                                    System.out.println("Enter room number to book a room:");
                                                    String roomNumber;
                                                    try {
                                                        roomNumber = scanner.next();
                                                        String regex = "^[0-9]*$";
                                                        Pattern integerPattern = compile(regex);

                                                        if (integerPattern.matcher(roomNumber).matches()) {
                                                            allRooms = adminResource.allRooms();
                                                            room = hotelResource.getRoom(roomNumber);
                                                            if (allRooms.contains(room)) {

                                                                if (findRooms.contains(room)) {
                                                                    Reservation reservation = hotelResource.bookARoom(hotelResource.getCustomer(email), room, checkInDate, checkOutDate);
                                                                    System.out.println(reservation);
                                                                }
                                                            } else {
                                                                System.out.println("Room doesn't exist");
                                                            }
                                                        } else {
                                                            System.out.println("Please enter a valid room number");
                                                        }
                                                    } catch (Exception exception) {
                                                        System.out.println("Please enter a valid room number");
                                                    }
                                                } else {
                                                    System.out.println("Customer doesn't exist");
                                                }
                                            } else {
                                                System.out.println("Please enter a valid email");
                                            }
                                        } catch (Exception exception) {
                                            System.out.println("Please enter a valid email");
                                        }
                                    } else {
                                        displayDatesExtended(checkInDate, checkOutDate);
                                    }
                                } else {
                                    System.out.println("No rooms yet!");
                                }
                            } else {
                                System.out.println("Check in date cannot be the same as check out date");
                            }
                        } else {
                            System.out.println("Check in date cannot be after check out date");
                        }
                    } else {
                        System.out.println("Please enter a valid date");
                    }
                } catch (ParseException parseException) {
                    System.out.println("Please enter a valid date");
                }
            } else {
                System.out.println("Please enter a valid date");
            }
        }  catch (ParseException parseException) {
            System.out.println("Please enter a valid date");
        }
    }


    private void displayDatesExtended(Date checkInDate, Date checkOutDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(checkInDate);
        c.add(Calendar.DATE, 7);
        Date extendedCheckInDate = c.getTime();
        c.setTime(checkOutDate);
        c.add(Calendar.DATE, 7);
        Date extendedCheckOutDate = c.getTime();
        for (IRoom room: adminResource.allRooms()) {
            if(hotelResource.isRoomAvailable(room,extendedCheckInDate,extendedCheckOutDate)){
                System.out.println("Room " + room + " is available between the following dates:");
                System.out.println(extendedCheckInDate+"-"+extendedCheckOutDate);            }

        }
    }


    public void caseTwo() {
        System.out.println("Enter customer email to find reservations:");
        String emailOfCustomer = null;
        String emailRegex = "^(.+)@(.+).com$";
        Pattern pattern = compile(emailRegex);
        try {
            emailOfCustomer = scanner.next();
            if (!pattern.matcher(emailOfCustomer).matches()) {
                System.out.println("Enter a valid email");
            }

            Collection<Reservation> reservations = hotelResource.getCustomerReservations(emailOfCustomer);
            if (reservations == null || reservations.size() == 0) {
                System.out.println("No reservations found");
            }
            else {
                System.out.println(reservations);
            }


        } catch (Exception exception) {

            System.out.println("Please enter a valid email");
        }
    }

    public void caseThree() {
        System.out.println("Enter customer email to create an account:");
        String email;
        String emailRegex = "^(.+)@(.+).com$";
        Pattern pattern = compile(emailRegex);
        try {
            email = scanner.next();
            if (pattern.matcher(email).matches()) {

                Collection<Customer> customers = adminResource.getAllCustomers();
                if (customers.contains(hotelResource.getCustomer(email))) {
                    System.out.println("Account already exists");
                } else {

                    System.out.println("Enter customer first name to create an account:");

                    try {
                        firstName = scanner.next();

                        System.out.println("Enter customer last name to create an account:");

                        try {
                            lastName = scanner.next();
                            hotelResource.createACustomer(email, firstName, lastName);
                            System.out.println("Account created successfully");

                        } catch (Exception exception) {
                            System.out.println("Please enter a valid name");
                        }
                    } catch (Exception exception) {
                        System.out.println("Please enter a valid name");
                    }
                }
            } else {
                System.out.println("Please enter a valid email");
            }
        } catch (Exception exception) {
            System.out.println("Enter a valid email");
        }
    }




    public static void printMenu() {
        System.out.println("Main Menu");
        System.out.println("Select one of the following numerical options");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println();
    }
}






    



