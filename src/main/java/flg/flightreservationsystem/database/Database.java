package flg.flightreservationsystem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

    // instantiate new database
    private SQLiteDatabase db;

    // instantiate new trigger
    private final Trigger TRIGGER = new Trigger();

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

        // set database triggers
        db.execSQL(TRIGGER.newCustomer());
        db.execSQL(TRIGGER.newReservation());
        db.execSQL(TRIGGER.newFlight());
        //db.execSQL(TRIGGER.cancelReservation());

        // log that database was created
        Log.i("DB_CREATED", "Database created...");
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

    public SQLiteDatabase getDb() {
        return db;
    }
}
