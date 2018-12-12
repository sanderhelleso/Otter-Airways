package flg.flightreservationsystem.database;

public class Trigger {

    public String newCustomer() {
        return  "CREATE TRIGGER new_customers AFTER INSERT ON " +
                "customers BEGIN INSERT INTO logs " +
                "(type, timestamp, user) " +
                "VALUES ('New Account', datetime('NOW'), NEW.customer_id); " +
                "END;";

    }

    public String newReservation() {
        return  "CREATE TRIGGER new_reservation AFTER INSERT ON " +
                "reservations BEGIN INSERT INTO logs " +
                "(type, timestamp, user) " +
                "VALUES ('New Reservation', datetime('NOW'), NEW.customer_id); " +
                "END;";

    }

    public String newFlight() {
        return  "CREATE TRIGGER new_flight AFTER INSERT ON " +
                "flights BEGIN INSERT INTO logs " +
                "(type, timestamp, user) " +
                "VALUES ('New Flight', datetime('NOW'), 1); " +
                "END;";

    }

    public String cancelReservation() {
        return  "CREATE TRIGGER cancel_reservation AFTER DELETE ON " +
                "reservations BEGIN INSERT INTO logs " +
                "(type, timestamp, user) " +
                "VALUES ('Cancellation of Reservation', datetime('NOW'), OLD.customer_id); " +
                "END;";

    }
}
