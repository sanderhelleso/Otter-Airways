package flg.flightreservationsystem.database;

public class Query {

    // base SQL actions
    private final String CREATE = "CREATE TABLE ";
    private final String DELETE_CASCADE = "ON DELETE CASCADE ";

    /**
     * Schema and create statement for "logs" table
     */
    public String createLogsTable() {

        // table name
        final String TABLE_NAME = "logs ";

        // table columns
        final String ID =           "entry_id INT NOT NULL PRIMARY KEY AUTOINCREMENT, ";
        final String TIMESTAMP =    "timestamp DATETIME NOT NULL, ";
        final String USER =         "user VARCHAR(20) NOT NULL, ";
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
     * Schema and statement for "customers" table
     */
    public String createCustomersTable() {

        // table name
        final String TABLE_NAME = "customers ";

        // table columns
        final String ID =           "customer_id INT NOT NULL PRIMARY KEY AUTOINCREMENT, ";
        final String USER_NAME =    "username UNIQUE VARCHAR(20) NOT NULL, ";
        final String PASSWORD =     "password VARCHAR(255) NOT NULL, ";
        final String ADMIN =        "admin BOOLEAN NOT NULL";

        // create customers table and return statement
        return CREATE + TABLE_NAME +
                "(" +
                    ID +
                    USER_NAME +
                    PASSWORD +
                    ADMIN +
                ");";

    }

    /**
     * Schema and statements for "flights" table
     */
    public String createFlightsTable() {

        // table name
        final String TABLE_NAME = "flights ";

        // table columns
        final String NAME =         "name VARCHAR(255) NOT NULL UNIQUE PRIMARY KEY, ";
        final String DEPARTURE =    "departure VARCHAR(255) NOT NULL, ";
        final String DESTINATION =  "destination VARCHAR(255) NOT NULL, ";
        final String TIME =         "time INTEGER NULL, ";
        final String CAPACITY =     "capacity INTEGER NOT NULL, ";
        final String RESERVED =     "reserved INTEGER NOT NULL, ";
        final String PRICE =        "price DECIMAL NOT NULL";

        // create flights table and return statement
        return CREATE + TABLE_NAME +
                "(" +
                    NAME +
                    DEPARTURE +
                    DESTINATION +
                    TIME +
                    CAPACITY +
                    RESERVED +
                    PRICE +
                ");";
    }

    /**
     * Schema and statement for "reservations" table
     */
    public String createReservationsTable() {

        // table name
        final String TABLE_NAME = "reservations ";

        // table columns
        final String ID =       "reservation_id INTEGER PRIMARY KEY AUTOINCREMENT,";
        final String SEATS =    "seats INTEGER NOT NULL,";

        // foreign key references
        final String REFERENCE_FLIGHT =     "FOREIGN KEY (flight_name) REFERENCES flights (name) " + DELETE_CASCADE;
        final String REFERENCE_CUSTOMER =   "FOREIGN KEY (customers_id) REFERENCES customers (reservation_id) " + DELETE_CASCADE;

        // create reservations table and return statement
        return CREATE + TABLE_NAME +
                "(" +
                    ID +
                    SEATS +
                    REFERENCE_FLIGHT +
                    REFERENCE_CUSTOMER +
                ");";
    }
}
