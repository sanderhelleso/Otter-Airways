package flg.flightreservationsystem.src;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Flight implements Serializable {

    private String flightName;
    private String departure;
    private String destination;
    private int time;
    private int capacity;
    private double price;
    private int reserved;

    public Flight(String flightName, String departure, String destination, int time, int capacity, double price, int reserved) {
        this.flightName = flightName;
        this.departure = departure;
        this.destination = destination;
        this.time = time;
        this.capacity = capacity;
        this.price = price;
        this.reserved = reserved;
    }

    public String getFlightName() {
        return flightName;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public int getTime() {
        return time;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getPrice() {
        return price;
    }

    public int getReserved() {
        return reserved;
    }

    @Override
    public String toString() {
        return  "\n\nFlight Name: " + flightName + "\n" +
                "Departure: " + departure + "\n" +
                "Destination: " + destination + "\n" +
                "Departure Time: " + time + "\n";
    }
}
