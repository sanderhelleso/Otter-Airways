package flg.flightreservationsystem.database;

import android.animation.TypeEvaluator;

import java.util.ArrayList;

public class Statement {

    /**
     * Schema and create statement for "logs" table
     */
    public String createLogsTable() {

        // table columns
        final String ID =           "entry_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ";
        final String TYPE =         "type VARCHAR(40) NOT NULL, ";
        final String TIMESTAMP =    "timestamp DATETIME NOT NULL, ";
        final String USER =         "user INTEGER NOT NULL, ";
        final String MESSAGE =      "message VARCHAR(255) NULL";

        // create log table and return statement
        return Actions.CREATE + Actions.LOGS_TABLE +
                "(" +
                    ID +
                    TYPE +
                    TIMESTAMP +
                    USER +
                        MESSAGE +
                ");";

    }

    /**
     * Schema and statement for "customers" table
     */
    public String createCustomersTable() {

        // table columns
        final String ID =           "customer_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ";
        final String USER_NAME =    "username VARCHAR(20) NOT NULL UNIQUE, ";
        final String PASSWORD =     "password VARCHAR(255) NOT NULL, ";
        final String ADMIN =        "admin BOOLEAN NOT NULL";

        // create customers table and return statement
        return Actions.CREATE + Actions.CUSTOMERS_TABLE +
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
        final String PRICE =        "price DECIMAL NOT NULL, ";
        final String RESERVED =     "reserved INTEGER NOT NULL";

        // create flights table and return statement
        return Actions.CREATE + Actions.FLIGHTS_TABLE +
                "(" +
                    NAME +
                    DEPARTURE +
                    DESTINATION +
                    TIME +
                    CAPACITY +
                    PRICE +
                    RESERVED +
                ");";
    }

    /**
     * Schema and statement for "reservations" table
     */
    public String createReservationsTable() {

        // table columns
        final String ID =       "reservation_id INTEGER PRIMARY KEY AUTOINCREMENT,";
        final String SEATS =    "seats INTEGER NOT NULL,";
        final String NAME =     "flight_name VARCHAR(255) NOT NULL, ";
        final String CUSTOMER = "customer_id INTEGER NOT NULL, ";

        // foreign key references
        final String REFERENCE_FLIGHT =     "FOREIGN KEY (flight_name) REFERENCES flights (name) " + Actions.DELETE_CASCADE + ", ";
        final String REFERENCE_CUSTOMER =   "FOREIGN KEY (customer_id) REFERENCES customers (reservation_id) " + Actions.DELETE_CASCADE;

        // create reservations table and return statement
        return Actions.CREATE + Actions.RESERVATIONS_TABLE +
                "(" +
                    ID +
                    SEATS +
                    NAME +
                    CUSTOMER +
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

        // admin statement
        String insertCustomer = Actions.INSERT_INTO + Actions.CUSTOMERS_TABLE + Actions.CUSTOMER_COLUMNS +
                " VALUES (\"!admiM2\", \"!admiM2\", \"true\");";

        // add statement to list
        STATEMENTS.add(insertCustomer);

        // customer 1 statement
        insertCustomer = Actions.INSERT_INTO + Actions.CUSTOMERS_TABLE + Actions.CUSTOMER_COLUMNS +
                " VALUES (\"A@lice5\", \"@cSit100\", \"false\");";

        // add statement to list
        STATEMENTS.add(insertCustomer);

        // customer 2 statement
        insertCustomer = Actions.INSERT_INTO + Actions.CUSTOMERS_TABLE + Actions.CUSTOMER_COLUMNS +
                " VALUES (\"$BriAn7\", \"123aBc##\", \"false\");";

        // add statement to list
        STATEMENTS.add(insertCustomer);

        // customer 3 statement
        insertCustomer = Actions.INSERT_INTO + Actions.CUSTOMERS_TABLE + Actions.CUSTOMER_COLUMNS +
                " VALUES (\"!chriS12\", \"CHrIS12\", \"false\");";

        // add statement to list
        STATEMENTS.add(insertCustomer);

        // return list with statements
        return STATEMENTS;
    }

    /**
     * insert default "flights"
     */
    public ArrayList<String> insertDefaultFlights() {

        // declare a new arraylist to hold statements
        final ArrayList<String> STATEMENTS = new ArrayList<>();

        // flight 1 statement
        String insertCustomer = Actions.INSERT_INTO + Actions.FLIGHTS_TABLE + Actions.FLIGHTS_COLUMNS +
                " VALUES (\"Otter101\", \"Monterey\", \"Los Angeles\", 1030, 10, 150.00, 0);";

        // add statement to list
        STATEMENTS.add(insertCustomer);

        // flight 2 statement
        insertCustomer = Actions.INSERT_INTO + Actions.FLIGHTS_TABLE + Actions.FLIGHTS_COLUMNS +
                " VALUES (\"Otter102\", \"Los Angeles\", \"Monterey\", 1300, 10, 150.00, 0);";

        // add statement to list
        STATEMENTS.add(insertCustomer);

        // flight 3 statement
        insertCustomer = Actions.INSERT_INTO + Actions.FLIGHTS_TABLE + Actions.FLIGHTS_COLUMNS +
                " VALUES (\"Otter201\", \"Monterey\", \"Seattle\", 1100, 5, 200.50, 0);";

        // add statement to list
        STATEMENTS.add(insertCustomer);

        // flight 4 statement
        insertCustomer = Actions.INSERT_INTO + Actions.FLIGHTS_TABLE + Actions.FLIGHTS_COLUMNS +
                " VALUES (\"Otter205\", \"Monterey\", \"Seattle\", 1545, 15, 150.00, 0);";

        // add statement to list
        STATEMENTS.add(insertCustomer);

        // flight 5 statement
        insertCustomer = Actions.INSERT_INTO + Actions.FLIGHTS_TABLE + Actions.FLIGHTS_COLUMNS +
                " VALUES (\"Otter202\", \"Seattle\", \"Monterey\", 1410, 5, 200.00, 0);";

        // add statement to list
        STATEMENTS.add(insertCustomer);

        // return list with statements
        return STATEMENTS;
    }
}
