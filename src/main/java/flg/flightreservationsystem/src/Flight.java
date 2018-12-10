package flg.flightreservationsystem.src;

import java.io.Serializable;

public class Flight implements Serializable {

    private String name;
    private String departure;
    private String destination;
    private int time;
    private int capacity;
    private double price;
    private int reserved;

    public Flight(String name, String departure, String destination, int time, int capacity, double price, int reserved) {
        this.name = name;
        this.departure = departure;
        this.destination = destination;
        this.time = time;
        this.capacity = capacity;
        this.price = price;
        this.reserved = reserved;
    }

    public String getName() {
        return name;
    }

    public String getDeparture() {
        return departure;
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
        return  "\n\nFlight Name: " + name + "\n" +
                "Departure: " + departure + "\n" +
                "Destination: " + destination + "\n" +
                "Departure Time: " + time + "\n";
    }
}
