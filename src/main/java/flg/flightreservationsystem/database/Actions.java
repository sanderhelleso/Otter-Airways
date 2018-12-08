package flg.flightreservationsystem.database;

public class Actions {

    // default error messages
    public static final String SQL_ERROR =          "Custom SQL detected. Abort query";
    public static final String DEFAULT_ERROR =      "Hmm..Something went wrong.\nThe following error occured: \n\n";
    public static final String CONTACT_ADMIN =       "\n\nPlease contact the administrator"
    public static final String ACCOUNT_CREATED =    "Account Successfuly Created";

    // base SQL actions
    public static final String CREATE =         "CREATE TABLE ";
    public static final String DELETE_CASCADE = "ON DELETE CASCADE ";
    public static final String INSERT_INTO =    "INSERT INTO ";

    // base database table names
    public static final String LOGS_TABLE =           "logs ";
    public static final String CUSTOMERS_TABLE =      "customers ";
    public static final String FLIGHTS_TABLE =        "flights ";
    public static final String RESERVATIONS_TABLE =   "reservations ";

    // base database colums
    public static final String CUSTOMER_COLUMNS =   "(username, password, admin) ";
    public static final String FLIGHTS_COLUMNS =    "(name, departure, destination, time, capacity, reserved, price) ";


}
