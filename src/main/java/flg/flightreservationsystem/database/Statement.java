package flg.flightreservationsystem.database;

import java.util.ArrayList;

public class Statement {

    // base SQL actions
    private final String CREATE = "CREATE TABLE ";
    private final String DELETE_CASCADE = "ON DELETE CASCADE ";
    private final String INSERT_INTO = "INSERT INTO ";

    // base database table names
    private final String LOGS_TABLE =   "logs ";
    final String CUSTOMERS_TABLE =      "customers ";
    final String FLIGHTS_TABLE =        "flights ";
    final String RESERVATIONS_TABLE =   "reservations ";

    /**
     * Schema and create statement for "logs" table
     */
    public String createLogsTable() {

        // table columns
        final String ID =           "entry_id INT NOT NULL PRIMARY KEY AUTOINCREMENT, ";
        final String TIMESTAMP =    "timestamp DATETIME NOT NULL, ";
        final String USER =         "user VARCHAR(20) NOT NULL, ";
        final String DESCRIPTION =  "description VARCHAR(40) NULL";

        // create log table and return statement
        return CREATE + LOGS_TABLE +
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

        // table columns
        final String ID =           "customer_id INT NOT NULL PRIMARY KEY AUTOINCREMENT, ";
        final String USER_NAME =    "username UNIQUE VARCHAR(20) NOT NULL, ";
        final String PASSWORD =     "password VARCHAR(255) NOT NULL, ";
        final String ADMIN =        "admin BOOLEAN NOT NULL";

        // create customers table and return statement
        return CREATE + CUSTOMERS_TABLE +
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

        // table columns
        final String NAME =         "name VARCHAR(255) NOT NULL UNIQUE PRIMARY KEY, ";
        final String DEPARTURE =    "departure VARCHAR(255) NOT NULL, ";
        final String DESTINATION =  "destination VARCHAR(255) NOT NULL, ";
        final String TIME =         "time INTEGER NULL, ";
        final String CAPACITY =     "capacity INTEGER NOT NULL, ";
        final String RESERVED =     "reserved INTEGER NOT NULL, ";
        final String PRICE =        "price DECIMAL NOT NULL";

        // create flights table and return statement
        return CREATE + FLIGHTS_TABLE +
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

        // table columns
        final String ID =       "reservation_id INTEGER PRIMARY KEY AUTOINCREMENT,";
        final String SEATS =    "seats INTEGER NOT NULL,";

        // foreign key references
        final String REFERENCE_FLIGHT =     "FOREIGN KEY (flight_name) REFERENCES flights (name) " + DELETE_CASCADE;
        final String REFERENCE_CUSTOMER =   "FOREIGN KEY (customers_id) REFERENCES customers (reservation_id) " + DELETE_CASCADE;

        // create reservations table and return statement
        return CREATE + RESERVATIONS_TABLE +
                "(" +
                    ID +
                    SEATS +
                    REFERENCE_FLIGHT +
                    REFERENCE_CUSTOMER +
                ");";
    }

    /**
     * insert default "customers" and admin statements
     */
    public ArrayList<String> insertDefaultCustomers() {

        // declare a new arraylist to hold statements
        final ArrayList<String> STATEMENTS = new ArrayList<>();

        // table columns
        final String COLUMNS = "(username, password, admin) ";

        // admin statement
        String insertCustomer = INSERT_INTO + CUSTOMERS_TABLE + COLUMNS +
                " VALUES " +
                "(" +
                    "\"!admiM2\", " +
                    "\"!admiM2\", " +
                    "true" +
                ");";

        // add statement to list
        STATEMENTS.add(insertCustomer);

        // customer 1 statement
        insertCustomer = INSERT_INTO + CUSTOMERS_TABLE + COLUMNS +
                " VALUES " +
                "(" +
                    "\"A@lice5\", " +
                    "\"@cSit100\", " +
                    "false" +
                ");";

        // add statement to list
        STATEMENTS.add(insertCustomer);

        // customer 2 statement
        insertCustomer = INSERT_INTO + CUSTOMERS_TABLE + COLUMNS +
                " VALUES " +
                "(" +
                    "\"$BriAn7\", " +
                    "\"123aBc##\", " +
                    "false" +
                ");";

        // add statement to list
        STATEMENTS.add(insertCustomer);

        // customer 3 statement
        insertCustomer = INSERT_INTO + CUSTOMERS_TABLE + COLUMNS +
                " VALUES " +
                "(" +
                    "\"!chriS12\", " +
                    "\"CHrIS12\", " +
                    "false" +
                ");";

        // add statement to list
        STATEMENTS.add(insertCustomer);

        // return list with statements
        return STATEMENTS;

    }
}
