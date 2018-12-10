package flg.flightreservationsystem.src;

public class Reservation {

    private int id;
    private int seats;
    private String flightName;
    private int customerID;

    public Reservation(int id, int seats, String flightName, int customerID) {
        this.id = id;
        this.seats = seats;
        this.flightName = flightName;
        this.customerID = customerID;
    }

    public int getId() {
        return id;
    }

    public int getSeats() {
        return seats;
    }

    public String getFlightName() {
        return flightName;
    }

    public int getCustomerID() {
        return customerID;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", seats=" + seats +
                ", flightName='" + flightName + '\'' +
                ", customerID=" + customerID +
                '}';
    }
}
