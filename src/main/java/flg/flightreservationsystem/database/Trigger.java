package flg.flightreservationsystem.database;

public class Trigger {

    public String updateCustomer() {
        return  "CREATE TRIGGER new_customers AFTER INSERT ON " +
                "customers BEGIN INSERT INTO logs " +
                "(type, timestamp, user) " +
                "VALUES ('New Account', datetime('NOW'), NEW.username); " +
                "END;";

    }
}
