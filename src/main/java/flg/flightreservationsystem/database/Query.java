package flg.flightreservationsystem.database;

public class Query {

    // base SQL actions
    private final String CREATE = "CREATE TABLE ";

    /**
     * creates the "log" table
     */
    public String createLogTable() {

        // table name
        final String TABLE_NAME = "log";

        // table rows
        final String ID =           "entry_id INT NOT NULL PRIMARY KEY AUTOINCREMENT,\n";
        final String TIMESTAMP =    "timestamp DATETIME NOT NULL,\n";
        final String USER =         "user VARCHAR(20) NOT NULL,\n";
        final String DESCRIPTION =  "description VARCHAR(40) NULL";

        // create log table and return statement
        return CREATE + TABLE_NAME +
                "(" +
                    ID +
                    TIMESTAMP +
                    USER +
                    DESCRIPTION +
                ");";

    }

    /**
     * creates the "customers" table
     */
    public String createCustomersTable() {

        // table name
        final String TABLE_NAME = "customers ";

        // table rows
        final String ID =         "customer_id INT NOT NULL PRIMARY KEY AUTOINCREMENT,\n";
        final String USER_NAME = "username UNIQUE VARCHAR(20) NOT NULL,\n";
        final String PASSWORD =  "password VARCHAR(255) NOT NULL,\n";
        final String ADMIN =     "admin BOOLEAN NOT NULL";

        // create customers table and return statement
        return CREATE + TABLE_NAME +
                "(" +
                    ID +
                    USER_NAME +
                    PASSWORD +
                    ADMIN +
                ");";

    }
}
