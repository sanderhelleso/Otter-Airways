package flg.flightreservationsystem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class Database extends SQLiteOpenHelper {

    // instantiate new database
    private SQLiteDatabase database;

    // instantiate new query
    private final Statement STATEMENT = new Statement();

    // constructor to implement database
    public Database(Context context) {
        super(context, "otter_airways", null, 1);
    }


    /**
     * The following method creates and setup the database
     * schema and it`s corresponding data for the tables
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

        // insert default users
        STATEMENT.insertDefaultCustomers().forEach((stmt) -> db.execSQL(stmt));

        
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
}
