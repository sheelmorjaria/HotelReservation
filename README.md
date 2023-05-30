HOTEL RESERVATION

USER SCENARIOS
The application provides four user scenarios:

Creating a customer account. The user needs to first create a customer account before they can create a reservation.

Searching for rooms. The app should allow the user to search for available rooms based on provided checkin and checkout dates. 
If the application has available rooms for the specified date range, a list of the corresponding rooms will be displayed to the user for choosing.

Booking a room. Once the user has chosen a room, the app will allow them to book the room and create a reservation.

Viewing reservations. After booking a room, the app allows customers to view a list of all their reservations.

ADMIN SCENARIOS 
The application provides four administrative scenarios:

Displaying all customers accounts.
Viewing all of the rooms in the hotel.
Viewing all of the hotel reservations.
Adding a room to the hotel application.

RESERVING A ROOM - REQUIREMENTS
The application allows customers to reserve a room. Here are the specifics:

Avoid conflicting reservations. A single room may only be reserved by a single customer per check-in and check-out date range.
Search for recommended rooms. If there are no available rooms for the customer's date range, a search will be performed that displays recommended rooms on alternative dates. 
The recommended room search will add seven days to the original check-in and check-out dates to see if the hotel has any availabilities and then display the recommended rooms/dates to the customer.
Example: If the customers date range search is 1/1/2020 – 1/5/2020 and all rooms are booked, the system will search again for recommended rooms using the date range 1/8/2020 - 1/12/2020. 
If there are no recommended rooms, the system will not return any rooms.


ROOM REQUIREMENTS
Room cost. Rooms will contain a price per night. When displaying rooms, paid rooms will display the price per night and free rooms will display "Free" or have a $0 price.
Unique room numbers. Each room will have a unique room number, meaning that no two rooms can have the same room number.
Room type. Rooms can be either single occupant or double occupant (Enumeration: SINGLE, DOUBLE).
Customer Requirements
The application will have customer accounts. Each account has:

A unique email for the customer. RegEx is used to check that the email is in the correct format (i.e., name@domain.com).
A first name and last name.

ERROR REQUIREMENTS
The hotel reservation application handles all exceptions gracefully (user inputs included), meaning:

No crashing. The application does not crash based on user input.
No unhandled exceptions. The app has try and catch blocks that are used to capture exceptions and provide useful information to the user. 
There are no unhandled exceptions.


PROJECT SPECIFICATION

Object-Oriented Programming:

Create and use Java Interface classes to support polymorphism: The hotel reservation application contains the IRoom interface, which is implemented by the Room class.
Subclass a parent class to support polymorphism: The FreeRoom class extends the Room class.
Define class variables as private and provide accessor and mutator methods to get and manipulate class variables: There is at least one example of the model classes (Room, Customer, Reservation) using data encapsulation.
Use variable access modifiers (such as public, private and final) to modify access: The application contains at least one example of using each of the following access modifiers: ‘public’, ‘private’, and ‘final’.
You should use the final for the data model classes variables and public methods.
There is at least one example of the model classes (Room, Customer, Reservation) overriding the toString method.

Processing and Storing Data:

Collections are used to store data for:

Room
Customer
Reservation
The collection type chosen for rooms ensures that two rooms cannot be booked at the same time.

All of the service classes use static references to create singleton objects.

Core Java Concepts

Use Regular Expressions to validate String input.
The Customer class should contain at least one example of validating a String to ensure that it has valid email address syntax.
Use Java types to store and process data: The application uses different Java types (String, Double, and Dates) to store data on objects.
Use Date and Calendar objects to store and process dates: The Reservation class uses Date objects for check-in and check-out dates.
Create Enumeration classes: The application contains the enumeration class RoomType.
The application contains at least one example of using Exceptions to validate input and try and catch blocks to handle error flow without crashing the application.
The application UI uses a switch statement to handle the user input flow.
