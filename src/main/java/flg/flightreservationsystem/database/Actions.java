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
    public static final String FLIGHTS_NOT_FOUND =  "Sorry\n\nNo available seats matching your criterias found";

    // base SQL actions
    public static final String CREATE =         "CREATE TABLE ";
    public static final String DELETE_CASCADE = "ON DELETE CASCADE ";
    public static final String INSERT_INTO =    "INSERT INTO ";
    public static final String SELECT_ALL =     "SELECT * ";

    // base database table names
    public static final String LOGS_TABLE =           "logs ";
    public static final String CUSTOMERS_TABLE =      "customers ";
    public static final String FLIGHTS_TABLE =        "flights ";
    public static final String RESERVATIONS_TABLE =   "reservations ";

    // base database colums
    public static final String CUSTOMER_COLUMNS =   "(username, password, admin) ";
    public static final String FLIGHTS_COLUMNS =    "(name, departure, destination, time, capacity, price, reserved) ";


}
