package flg.flightreservationsystem.src;

import java.text.DecimalFormat;

import flg.flightreservationsystem.helpers.Util;

public class Reservation extends Flight {

    // instantiate new util
    private final Util UTIL = new Util();

    // instantiate new decimal formater to ensure number contains two decimals
    private final DecimalFormat df = new DecimalFormat("#.00");

    private int reservationID;
    private int seats;
    private int customerID;
    private String username;

    public Reservation(
            String flightName,
            String departure,
            String destination,
            int time,
            int capacity,
            double price,
            int reserved,
            int id,
            int seats,
            int customerID,
            String username
    ) {
        super(flightName, departure, destination, time, capacity, price, reserved);
        this.reservationID = id;
        this.seats = seats;
        this.customerID = customerID;
        this.username = username;
    }

    public int getReservationID() {
        return reservationID;
    }

    public int getSeats() {
        return seats;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {

        // get total price
        Double totalPrice = Double.parseDouble(String.valueOf(
                getPrice() * seats)
        );

        return "\n\nUsername: " + username + "\n" +
                "Flight Number: " + getFlightName() + "\n" +
                "Departure: " + getDeparture() + "\n" +
                "Destination: " + getDestination() + "\n" +
                "Departure Time: " + UTIL.getDateMarkerFromHourMin(getTime()) + "\n" +
                "Price per ticket: " + df.format(getPrice()) + "$\n" +
                "Amount of tickets: " + getSeats() + "\n\n\n" +
                "Total Price: $" + df.format(totalPrice) + "\n";
    }

    public String logMessage() {
        return  "Flight Number: " + getFlightName() + "_" +
                "Departure: " + getDeparture() + "_" +
                "Destination: " + getDestination() + "_" +
                "Departure Time: " + UTIL.getDateMarkerFromHourMin(getTime()) + "_" +
                "Number of Tickets: " + getSeats() + "_" +
                "Reservation Number: " + getReservationID() + "_";
    }
}
