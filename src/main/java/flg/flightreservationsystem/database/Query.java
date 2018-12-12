package flg.flightreservationsystem.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.sql.Date;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import flg.flightreservationsystem.src.Flight;
import flg.flightreservationsystem.src.LogEntry;
import flg.flightreservationsystem.src.Reservation;

public class Query {

    // create new customer query
    public String createNewCustomer(final String username, final String password, final Boolean admin) {
        return  Actions.INSERT_INTO + Actions.CUSTOMERS_TABLE + " " + Actions.CUSTOMER_COLUMNS +
                "VALUES (\"" + username + "\", \"" + password + "\", \"" + Boolean.toString(admin) + "\");";
    }

    // find available seats query
    public String createNewReservation(final String customerID, final int seats, final Flight flight) {
        return Actions.INSERT_INTO + Actions.RESERVATIONS_TABLE + " " + Actions.RESERVATIONS_COLUMNS +
                "VALUES (\"" + seats + "\", \"" + flight.getFlightName() + "\", \"" + customerID + "\");";
    }

    // login customer / admin query
    public String loginCustOrAdmin(final String username, final String password, final boolean admin) {
        return  Actions.SELECT_ALL + "FROM " + Actions.CUSTOMERS_TABLE + " " +
                "WHERE username = \"" + username + "\" " +
                "AND password = \"" + password + "\"" +
                "AND admin = \"" + admin + "\";";
    }

    // find available seats query
    public String findAvailableSeats(final String departure, final String arrival, final String ticketAmount) {
        return  Actions.SELECT_ALL + "FROM " + Actions.FLIGHTS_TABLE + " " +
                "WHERE departure = \"" + departure + "\" " +
                "AND destination = \"" + arrival + "\"" +
                "AND flights.capacity - flights.reserved >= " + Integer.parseInt(ticketAmount) + ";";
    }

    // updated selected flights seat query
    public String updateFlightReserved(final String flightName, final int ticketAmount) {
        return  Actions.UPDATE + Actions.FLIGHTS_TABLE + " " +
                "SET reserved = reserved + " + ticketAmount + " " +
                "WHERE name = \"" + flightName + "\";";
    }

    // get customers reservations query
    public String getCustomerReservation(final String customerID) {
        return  Actions.SELECT + Actions.RESERVATIONS_TABLE + ".*, " +
                Actions.FLIGHTS_TABLE + ".*, " +
                Actions.CUSTOMERS_TABLE + ".* " +
                "FROM " + Actions.RESERVATIONS_TABLE + " " +
                Actions.INNER_JOIN + Actions.FLIGHTS_TABLE + " " +
                "ON " + Actions.FLIGHTS_TABLE + ".name = " + Actions.RESERVATIONS_TABLE + ".flight_name " +
                Actions.INNER_JOIN + Actions.CUSTOMERS_TABLE + " " +
                "ON " + Actions.CUSTOMERS_TABLE + ".customer_id = " + Actions.RESERVATIONS_TABLE + ".customer_id " +
                "WHERE " + Actions.RESERVATIONS_TABLE + ".customer_id = \"" + customerID + "\";";
    }

    // remove customer reservation query
    public String removeCustomerReservation(final int reservationID) {
        return  Actions.DELETE + "FROM " + Actions.RESERVATIONS_TABLE + " " +
                "WHERE reservation_id = \"" + reservationID + "\";";
    }

    // update seat amount query
    public String updateFlightSeatsAfterCancel(final String flightName, final int ticketAmount) {
        return  Actions.UPDATE + Actions.FLIGHTS_TABLE + " " +
                "SET reserved = reserved - " + ticketAmount + " " +
                "WHERE name = \"" + flightName + "\";";
    }

    // get logs query
    public String getLogs() {
        return  Actions.SELECT + Actions.LOGS_TABLE + ".*, " +
                Actions.CUSTOMERS_TABLE + ".username " +
                "FROM " + Actions.LOGS_TABLE + " " +
                Actions.INNER_JOIN + Actions.CUSTOMERS_TABLE + " " +
                "ON " + Actions.LOGS_TABLE + ".user = " + Actions.CUSTOMERS_TABLE + ".customer_id;";
    }

    // check for flight number query
    public String createNewFlight(
            final String flightNumber,
            final String departure,
            final String destination,
            final int time,
            final int capacity,
            final double price
    ) {
        return  Actions.INSERT_INTO + Actions.FLIGHTS_TABLE + Actions.FLIGHTS_COLUMNS +
                " VALUES (\"" + flightNumber + "\"," +
                " \"" + departure + "\"," +
                " \"" + destination + "\"," +
                " \"" + time + "\"," +
                " \"" + capacity + "\", " +
                " \"" + price + ", " +
                "0);";
    }


    private boolean checkInjection(String query) {
        return query.contains("\"; ");
    }

    public HashMap<String, ArrayList<LogEntry>> logs(String stmt, Database db) {

        // instantiate new map to store data
        final HashMap<String, ArrayList<LogEntry>> MAP = new HashMap<>();

        // instantiante new arraylist
        final ArrayList<LogEntry> LOGS = new ArrayList<>();

        // validate query statement
        if (checkInjection(stmt)) {
            MAP.put(Actions.SQL_ERROR, LOGS);
            return MAP;
        }

        // attempt to read statement
        final Cursor cursor = db.getReadableDatabase().rawQuery(stmt, null);
        try {

            if (cursor.moveToFirst()) {

                // itterate over result and create logs
                while (!cursor.isAfterLast()) {

                    // retrieve log values
                    final int entryID = Integer.parseInt(cursor.getString(cursor.getColumnIndex("entry_id")));
                    final String type = cursor.getString(cursor.getColumnIndex("type"));
                    final String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
                    final String username = cursor.getString(cursor.getColumnIndex("username"));

                    // add log to list
                    LOGS.add(new LogEntry(entryID, type, timestamp, username));

                    // move to reservation flight
                    cursor.moveToNext();
                }
            }

            // fill map and return
            MAP.put(Actions.LOGS_FOUND, LOGS);
            return MAP;
        }

        // if any error, throw and display
        catch (SQLiteException e) {

            // catch and display potensial errors
            MAP.put(Actions.DEFAULT_ERROR + e.getMessage() + Actions.CONTACT_ADMIN, LOGS);
            return MAP;
        }

        // close connection
        finally {
            db.close();
            cursor.close();
        }
    }


    public HashMap<String, ArrayList<Reservation>> reservations(String stmt, Database db) {

        // instantiate new map to store data
        final HashMap<String, ArrayList<Reservation>> MAP = new HashMap<>();

        // instantiante new arraylist
        final ArrayList<Reservation> RESERVATIONS = new ArrayList<>();

        // validate query statement
        if (checkInjection(stmt)) {
            MAP.put(Actions.SQL_ERROR, RESERVATIONS);
            return MAP;
        }

        // attempt to read statement
        final Cursor cursor = db.getReadableDatabase().rawQuery(stmt, null);
        try {

            if (cursor.moveToFirst()) {

                // itterate over result and create reservations
                while (!cursor.isAfterLast()) {

                    // retrieve reservation values
                    final String flightName =   cursor.getString(cursor.getColumnIndex("name"));
                    final String departure =    cursor.getString(cursor.getColumnIndex("departure"));
                    final String destination =  cursor.getString(cursor.getColumnIndex("destination"));
                    final int time =            Integer.parseInt(cursor.getString(cursor.getColumnIndex("time")));
                    final int capacity =        Integer.parseInt(cursor.getString(cursor.getColumnIndex("capacity")));
                    final double price =        Double.parseDouble(cursor.getString(cursor.getColumnIndex("price")));
                    final int reserved =        Integer.parseInt(cursor.getString(cursor.getColumnIndex("reserved")));
                    final int reservationID =   Integer.parseInt(cursor.getString(cursor.getColumnIndex("reservation_id")));
                    final int seats =           Integer.parseInt(cursor.getString(cursor.getColumnIndex("seats")));
                    final int customerID =      Integer.parseInt(cursor.getString(cursor.getColumnIndex("customer_id")));
                    final String username =     cursor.getString(cursor.getColumnIndex("username"));

                    // add reservations  to list
                    RESERVATIONS.add(new Reservation(
                            flightName,
                            departure,
                            destination,
                            time,
                            capacity,
                            price,
                            reserved,
                            reservationID,
                            seats,
                            customerID,
                            username
                            )
                    );

                    // move to reservation flight
                    cursor.moveToNext();
                }
            }

            // fill map and return
            MAP.put(Actions.RESERVATIONS_FOUND, RESERVATIONS);
            return MAP;
        }

        // if any error, throw and display
        catch (SQLiteException e) {

            // catch and display potensial errors
            MAP.put(Actions.DEFAULT_ERROR + e.getMessage() + Actions.CONTACT_ADMIN, RESERVATIONS);
            return MAP;
        }

        // close connection
        finally {
            db.close();
            cursor.close();
        }
    }


    public boolean write(String stmt, Database db) {

        // validate query statement
        if (checkInjection(stmt)) {
            return false;
        }

        // attempt to excecute statement
        try {

            db.getWritableDatabase().execSQL(stmt);
            return true;
        }

        // if any error, throw and log
        catch (SQLiteException e) {
            Log.e("SQL_ERROR", e.getMessage());
            throw (e);
        }

        // close connection
        finally {
            db.close();
        }
    }

    public HashMap<Boolean, String> insert(String query, Database db) {

        // create a new hashmap to put boolean value and message
        final HashMap<Boolean, String> MAP = new HashMap<>();

        // validate insert statement
        if (checkInjection(query)) {
            MAP.put(false, Actions.SQL_ERROR);
            return MAP;
        }

        try {

            // run query
            db.getWritableDatabase().execSQL(query);
            MAP.put(true, Actions.ACCOUNT_CREATED);
            return MAP;

        }

        catch (SQLiteException e) {

            // catch and display potensial errors
            MAP.put(false, Actions.DEFAULT_ERROR + "The username is taken by another user.");
            return MAP;
        }

        // close connection
        finally {
            db.close();
        }
    }

    public HashMap<Boolean, String> insertNewFlight(String query, Database db) {

        // create a new hashmap to put boolean value and message
        final HashMap<Boolean, String> MAP = new HashMap<>();

        // validate insert statement
        if (checkInjection(query)) {
            MAP.put(false, Actions.SQL_ERROR);
            return MAP;
        }

        try {

            // run query
            db.getWritableDatabase().execSQL(query);
            MAP.put(true, Actions.NEW_FLIGHT_ADDED);
            return MAP;

        }

        catch (SQLiteException e) {

            // catch and display potensial errors
            MAP.put(false, Actions.DEFAULT_ERROR + "Flight Number already exists. ");
            return MAP;
        }

        // close connection
        finally {
            db.close();
        }
    }

    public HashMap<Boolean, Map<String, String>> login(String stmt, Database db) {

        // create a new hashmap to put boolean value and message
        final HashMap<Boolean, Map<String, String>> MAP = new HashMap<>();
        final HashMap<String, String> ENTRY = new HashMap<>();

        // validate query statement
        if (checkInjection(stmt)) {
            ENTRY.put("ERROR", Actions.SQL_ERROR);
            MAP.put(false, ENTRY);
            return MAP;
        }

        // create cursor and run query
        final Cursor cursor = db.getReadableDatabase().rawQuery(stmt, null);

        // attempt to perform query
        try {

            // put result in map, depending on result message will be results UN or failure message
            String customerUN = null;
            String customerID = null;
            boolean customerFound = false;
            if (cursor.moveToFirst()) {
                customerUN = cursor.getString(cursor.getColumnIndex("username"));
                customerID = cursor.getString(cursor.getColumnIndex("customer_id"));
                customerFound = true;
            }

            // fill map and return
            ENTRY.put(customerID, customerUN);
            MAP.put(customerFound, ENTRY);
            return MAP;
        }

        catch (SQLiteException e) {

            // catch and display potensial errors
            ENTRY.put("ERROR", Actions.DEFAULT_ERROR + e.getMessage() + Actions.CONTACT_ADMIN);
            MAP.put(false, ENTRY);
            return MAP;
        }

        // close connection and cursor
        finally {
            db.close();
            cursor.close();
        }
    }

    public HashMap<Boolean, Map.Entry<String, ArrayList<Flight>>> flight(String stmt, Database db) {

        // create a new hashmap to put boolean value and message
        final HashMap<Boolean, Map.Entry<String, ArrayList<Flight>>> MAP = new HashMap<>();

        // instantiate empty arraylist to hold flights
        final ArrayList<Flight> flights = new ArrayList<>();

        // validate query statement
        if (checkInjection(stmt)) {
            MAP.put(false, new AbstractMap.SimpleEntry<>(Actions.SQL_ERROR, flights));
            return MAP;
        }

        // create cursor
        final Cursor cursor = db.getReadableDatabase().rawQuery(stmt, null);

        // attempt to perform query
        try {

            // put result in map, depending on result message will be results ID or failure message
            boolean availableSeats = false;
            if (cursor.moveToFirst()) {
                availableSeats = true;

                // itterate over result and create flights
                while (!cursor.isAfterLast()) {

                    // retrieve flight values
                    final String name =         cursor.getString(cursor.getColumnIndex("name"));
                    final String departure =    cursor.getString(cursor.getColumnIndex("departure"));
                    final String destination =  cursor.getString(cursor.getColumnIndex("destination"));
                    final int time =            Integer.parseInt(cursor.getString(cursor.getColumnIndex("time")));
                    final int capacity =        Integer.parseInt(cursor.getString(cursor.getColumnIndex("capacity")));
                    final double price =        Double.parseDouble(cursor.getString(cursor.getColumnIndex("price")));
                    final int reserved =        Integer.parseInt(cursor.getString(cursor.getColumnIndex("reserved")));

                    // add flight to list
                    flights.add(new Flight(name, departure, destination, time, capacity, price, reserved));

                    // move to next flight
                    cursor.moveToNext();
                }
            }

            MAP.put(availableSeats, new AbstractMap.SimpleEntry<>(Actions.FLIGHTS_NOT_FOUND, flights));
            return MAP;
        }

        catch (SQLiteException e) {

            // catch and display potensial errors
            MAP.put(false, new AbstractMap.SimpleEntry<>(
                    Actions.DEFAULT_ERROR +
                    e.getMessage() +
                    Actions.CONTACT_ADMIN, flights)
            );
            return MAP;
        }

        // close connection and cursor
        finally {
            db.close();
            cursor.close();
        }
    }
}
