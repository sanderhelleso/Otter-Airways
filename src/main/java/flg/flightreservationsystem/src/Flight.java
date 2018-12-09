package flg.flightreservationsystem.src;

public class Flight {

    private String name;
    private String departure;
    private String arrival;
    private int time;
    private int capacity;
    private double price;
    private int reserved;

    public Flight(String name, String departure, String arrival, int time, int capacity, double price, int reserved) {
        this.name = name;
        this.departure = departure;
        this.arrival = arrival;
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

    public String getArrival() {
        return arrival;
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
        return "Flight{" +
                "name='" + name + '\'' +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", time='" + time + '\'' +
                ", capacity='" + capacity + '\'' +
                ", price='" + price + '\'' +
                ", reserved='" + reserved + '\'' +
                '}';
    }
}
