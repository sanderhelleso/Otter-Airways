package flg.flightreservationsystem.src;

public class Reservation extends Flight {

    private int id;
    private int seats;
    private int customerID;

    public Reservation(
            String name,
            String departure,
            String destination,
            int time,
            int capacity,
            double price,
            int reserved,
            int id,
            int seats,
            int customerID
    ) {
        super(name, departure, destination, time, capacity, price, reserved);
        this.id = id;
        this.seats = seats;
        this.customerID = customerID;
    }

    public int getId() {
        return id;
    }

    public int getSeats() {
        return seats;
    }

    public int getCustomerID() {
        return customerID;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", seats=" + seats +
                ", customerID=" + customerID +
                '}';
    }
}
