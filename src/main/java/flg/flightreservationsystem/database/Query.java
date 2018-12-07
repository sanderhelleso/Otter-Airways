package flg.flightreservationsystem.database;

public class Query {

    // base SQL actions
    private final String CREATE = "CREATE TABLE ";

    /**
     * creates the "log" table
     */
    public String createLogTable() {

        // table name
        final String TABLE_NAME = "log";

        // table rows
        final String ID =           "INT NOT NULL PRIMARY KEY AUTOINCREMENT,\n";
        final String TIMESTAMP =    "TIMESTAMP DATETIME NOT NULL,\n";
        final String USER =         "USER VARCHAR(4) NOT NULL,\n";
        final String DESCRIPTION =  "VARCHAR(40) NULL";

        // create log table and return statement
        return CREATE + TABLE_NAME +
                "(" +
                    ID +
                    TIMESTAMP +
                    USER +
                    DESCRIPTION +
                ");";

    }
}
