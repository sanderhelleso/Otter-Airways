package flg.flightreservationsystem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.HashMap;

public class Database extends SQLiteOpenHelper {

    // default error messages
    final String SQL_ERROR =               "Custom SQL detected. Abort query";
    final String DEFAULT_ERROR =           "Hmm..Something went wrong. The following error occured: \n";
    final String ACCOUNT_SUCCESS_CREATED = "Account Successfuly Created";

    // instantiate new database
    private SQLiteDatabase db;

    // instantiate new query
    private final Statement STATEMENT = new Statement();

    // constructor to implement database
    public Database(Context context) {
        super(context, "otter_airways", null, 1);
    }

    /**
     * The following method creates and setup the database schema
     * and insert it`s corresponding defaulr data for the tables
     * @param db SQLiteDatabase object
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create database tables
        db.execSQL(STATEMENT.createLogsTable());
        db.execSQL(STATEMENT.createCustomersTable());
        db.execSQL(STATEMENT.createFlightsTable());
        db.execSQL(STATEMENT.createReservationsTable());

        // insert default users and flights
        STATEMENT.insertDefaultCustomers().forEach((stmt) -> db.execSQL(stmt));
        STATEMENT.insertDefaultFlights().forEach((stmt) -> db.execSQL(stmt));

        Log.i("DB_CREATED", "Database created...");
    }

    public HashMap<Boolean, String> insert(String query) {

        // create a new hashmap to put boolean value and message
        final HashMap<Boolean, String> MAP = new HashMap<>();

        // validate insert statement
        if (checkInjection(query)) {
            MAP.put(false, SQL_ERROR);
            return MAP;
        }

        // get writable database
        db = getWritableDatabase();

        try {

            // run query
            db.execSQL(query);
            MAP.put(true, ACCOUNT_SUCCESS_CREATED);
            return MAP;

        }

        catch (SQLiteException e) {

            Log.d("DB ERROR", e.getMessage());
            MAP.put(false, DEFAULT_ERROR + e.getMessage());
            return MAP;
        }

        // close connection
        finally {
             db.close();
        }
    }

    private boolean checkInjection(String query) {
        return query.contains("\"; ");
    }


    /**
     * The following method updates the given database using
     * name, version number and the upgraded version number
     *
     *  NOTE: Due to no upgrades will be made, method remains empty
     * @param db SQLiteDatabase object
     * @param oldVersion integer old db version number
     * @param newVersion integer new db version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    /**
     * getter for read and write database
     */

    public SQLiteDatabase getReadDatabase() {
        return getReadableDatabase();
    }

    public SQLiteDatabase getWriteDatabase() {
        return getReadableDatabase();
    }

}
