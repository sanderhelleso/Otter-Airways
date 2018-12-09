package flg.flightreservationsystem.src;

import android.os.Parcel;

import java.io.Serializable;

public class ReserveSeatSearch implements Serializable {

    private int customerID;
    private int amountOfTickets;

    public ReserveSeatSearch(int customerID, int amountOfTickets) {
        this.customerID = customerID;
        this.amountOfTickets = amountOfTickets;
    }

    protected ReserveSeatSearch(Parcel in) {
        customerID = in.readInt();
        amountOfTickets = in.readInt();
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getAmountOfTickets() {
        return amountOfTickets;
    }

    @Override
    public String toString() {
        return "ReserveSeatSearch{" +
                "customerID=" + customerID +
                ", amountOfTickets=" + amountOfTickets +
                '}';
    }
}
