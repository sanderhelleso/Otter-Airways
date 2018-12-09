package flg.flightreservationsystem.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.HashMap;

public class Query {

    // create new customer query
    public String createNewCustomer(final String username, final String password, final Boolean admin) {
        return Actions.INSERT_INTO + Actions.CUSTOMERS_TABLE + Actions.CUSTOMER_COLUMNS +
                "VALUES (\"" + username + "\", \"" + password + "\", \"" + Boolean.toString(admin) + "\");";
    }

    // login customer query
    public String loginCustomer(final String username, final String password) {
        return Actions.SELECT_ALL + "FROM " + Actions.CUSTOMERS_TABLE +
                "WHERE username = \"" + username + "\" AND password = \"" + password + "\";";
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

    public HashMap<Boolean, String> select(String stmt, Database db) {

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
}
