package flg.flightreservationsystem.database;

public class Actions {

    // default error messages
    public static final String SQL_ERROR =          "Custom SQL detected. Abort query";
    public static final String DEFAULT_ERROR =      "Hmm..Something went wrong.\nThe following error occured: \n\n";
    public static final String CONTACT_ADMIN =      "\n\nPlease contact the administrator";
    public static final String ACCOUNT_CREATED =    "Successfully Created Account!\n\nYou can now log into your account to reserve and cancel seats for your next flights";
    public static final String LOGIN_SUCCESS =      "Successfully login!";
    public static final String LOGIN_FAILED =       "Invalid username or password.\n";
    public static final String FLIGHTS_AVAILABLE =  "Found available flights";

    public static final String FLIGHTS_NOT_FOUND =  "Sorry. No available seats matching your criterias were found.\n\n" +
            "To solve this you can try  the following:\n" +
            "\n-Different departure\n-Different destination" +
            "\n-Change the amount of tickets" +
            "\n\nIf none of the above works and you are sure flights should be available, " +
            "please contact the administrator.";

    public static final String RESERVATIONS_FOUND =     "Reservations found";
    public static final String RESERVATIONS_NOT_FOUND = "Reservations not found";

    // base SQL actions
    public static final String CREATE =         "CREATE TABLE ";
    public static final String DELETE =         "DELETE ";
    public static final String DELETE_CASCADE = "ON DELETE CASCADE ";
    public static final String INSERT_INTO =    "INSERT INTO ";
    public static final String SELECT_ALL =     "SELECT * ";
    public static final String SELECT =         "SELECT ";
    public static final String UPDATE =         "UPDATE ";
    public static final String INNER_JOIN =     "INNER JOIN ";

    // base database table names
    public static final String LOGS_TABLE =           "logs";
    public static final String CUSTOMERS_TABLE =      "customers";
    public static final String FLIGHTS_TABLE =        "flights";
    public static final String RESERVATIONS_TABLE =   "reservations";

    // base database colums
    public static final String CUSTOMER_COLUMNS =       "(username, password, admin) ";
    public static final String FLIGHTS_COLUMNS =        "(name, departure, destination, time, capacity, price, reserved) ";
    public static final String RESERVATIONS_COLUMNS =   "(seats, flight_name, customer_id) ";


}
