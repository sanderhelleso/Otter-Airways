package flg.flightreservationsystem.database;

public class Actions {

    // default error messages
    public static final String SQL_ERROR =          "\nCustom SQL detected. Abort query";
    public static final String DEFAULT_ERROR =      "\nHmm..Something went wrong.\nThe following error occured: \n\n";
    public static final String CONTACT_ADMIN =      "\n\nPlease contact the administrator";
    public static final String ACCOUNT_CREATED =    "\nAccount Successfully Created!\n\nYou can now log into your new account to reserve and cancel seats for your next flight";
    public static final String LOGIN_SUCCESS =      "\nLogin Successful";
    public static final String LOGIN_FAILED =       "\nInvalid username or password.\n";
    public static final String FLIGHTS_NOT_FOUND =  "\nSorry. No available flights with the given destination / departure.\n\n" + "To solve this you can try  the following:\n" + "\n-Different departure\n-Different destination" + "\n\nIf none of the above works and you are sure flights should be available, " + Actions.CONTACT_ADMIN;
    public static final String LOGS_FOUND =         "\nSuccessfully fetched system logs";
    public static final String RESERVATIONS_FOUND = "\nReservations found";
    public static final String NEW_FLIGHT_ADDED =   "\nSuccessfully created new flight record!\n\nCustomers can now browse and reserve seats for this flight";
    public static final String INVALID_SEATS =      "\nReservation cant be made due to the systems restriction.\n\nA maximum of 7 seats per reservation is allowed.";

    // base SQL actions
    public static final String CREATE =             "CREATE TABLE ";
    public static final String DELETE =             "DELETE ";
    public static final String DELETE_CASCADE =     "ON DELETE CASCADE ";
    public static final String INSERT_INTO =        "INSERT INTO ";
    public static final String SELECT_ALL =         "SELECT * ";
    public static final String SELECT =             "SELECT ";
    public static final String UPDATE =             "UPDATE ";
    public static final String INNER_JOIN =         "INNER JOIN ";

    // base database table names
    public static final String LOGS_TABLE =         "logs";
    public static final String CUSTOMERS_TABLE =    "customers";
    public static final String FLIGHTS_TABLE =      "flights";
    public static final String RESERVATIONS_TABLE = "reservations";

    // base database colums
    public static final String CUSTOMER_COLUMNS =       "(username, password, admin) ";
    public static final String FLIGHTS_COLUMNS =        "(name, departure, destination, time, capacity, price, reserved) ";
    public static final String RESERVATIONS_COLUMNS =   "(seats, flight_name, customer_id) ";
    public static final String LOGS_COLUMNS =           "(type, timestamp, user, message) ";


}
