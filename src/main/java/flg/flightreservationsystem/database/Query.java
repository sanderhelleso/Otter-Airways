package flg.flightreservationsystem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Query {

    // create new customer query
    public String createNewCustomer(String username, String password, Boolean admin) {
        return Actions.INSERT_INTO + Actions.CUSTOMERS_TABLE + Actions.CUSTOMER_COLUMNS +
                "VALUES (\"" + username + "\", \"" + password + "\", \"" + Boolean.toString(admin) + "\");";

    }
}
