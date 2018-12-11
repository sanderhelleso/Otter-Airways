package flg.flightreservationsystem.src;

public class Reservation extends Flight {

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
        return "Reservation{" +
                "id=" + reservationID +
                ", seats=" + seats +
                ", customerID=" + customerID +
                '}';
    }
}
