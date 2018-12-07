package flg.flightreservationsystem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    // instantiate new database
    private SQLiteDatabase database;

    // instantiate new query
    private final Query QUERY = new Query();

    // constructor to implement database
    public Database(Context context) {
        super(context, "otter_airways", null, 1);
    }


    /**
     * The following method creates and setup the database
     * schema and it`s corresponding data for the tables
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create log table
        db.execSQL(QUERY.createLogTable());
    }


    /**
     * The following method updates the given database using
     * name, version number and the upgraded version number
     *
     *  NOTE: Due to no upgrades will be made, method remains empty
     * @param db
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
