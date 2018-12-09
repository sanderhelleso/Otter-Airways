package flg.flightreservationsystem.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import flg.flightreservationsystem.src.Flight;

public class Query {

    // create new customer query
    public String createNewCustomer(final String username, final String password, final Boolean admin) {
        return Actions.INSERT_INTO + Actions.CUSTOMERS_TABLE + Actions.CUSTOMER_COLUMNS +
                "VALUES (\"" + username + "\", \"" + password + "\", \"" + Boolean.toString(admin) + "\");";
    }

    // login customer query
    public String loginCustomer(final String username, final String password) {
        return Actions.SELECT_ALL + "FROM " + Actions.CUSTOMERS_TABLE +
                "WHERE username = \"" + username + "\" " +
                "AND password = \"" + password + "\";";
    }

    // find available seats query
    public String findAvailableSeats(final String departure, final String arrival) {
        return Actions.SELECT_ALL + "FROM " + Actions.FLIGHTS_TABLE +
                "WHERE departure = \"" + departure + "\" " +
                "AND destination = \"" + arrival + "\";";
    }

    private boolean checkInjection(String query) {
        return query.contains("\"; ");
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
            MAP.put(false, Actions.DEFAULT_ERROR + e.getMessage() + Actions.CONTACT_ADMIN);
            return MAP;
        }

        // close connection
        finally {
            db.close();
        }
    }

    public HashMap<Boolean, String> login(String stmt, Database db) {

        // create a new hashmap to put boolean value and message
        final HashMap<Boolean, String> MAP = new HashMap<>();

        // validate query statement
        if (checkInjection(stmt)) {
            MAP.put(false, Actions.SQL_ERROR);
            return MAP;
        }

        // create cursor and run query
        final Cursor cursor = db.getReadableDatabase().rawQuery(stmt, null);

        // attempt to perform query
        try {

            // put result in map, depending on result message will be results ID or failure message
            String customerID = null;
            boolean customerFound = false;
            if (cursor.moveToFirst()) {
                customerID = cursor.getString(cursor.getColumnIndex("customer_id"));
                customerFound = true;
            }

            MAP.put(customerFound, customerID);
            return MAP;
        }

        catch (SQLiteException e) {

            // catch and display potensial errors
            MAP.put(false, Actions.DEFAULT_ERROR + e.getMessage() + Actions.CONTACT_ADMIN);
            return MAP;
        }

        // close connection and cursor
        finally {
            db.close();
            cursor.close();
        }
    }

    public HashMap<Boolean, Map.Entry<String, ArrayList<Flight>>> find(String stmt, Database db) {

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
