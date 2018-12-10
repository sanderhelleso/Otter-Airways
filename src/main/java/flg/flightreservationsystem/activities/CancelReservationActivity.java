package flg.flightreservationsystem.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import flg.flightreservationsystem.R;
import flg.flightreservationsystem.database.Database;
import flg.flightreservationsystem.database.Query;
import flg.flightreservationsystem.src.Reservation;

public class CancelReservationActivity extends AppCompatActivity {

    // instantiate database
    Database db = new Database(this);

    // instantiate new query
    Query query = new Query();

    // logged in customers ID
    private String customerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_reservation);

        // attempt to load logged in customers reserved data
        loadData();
    }

    private void loadData() {

        // retrieve customer id from login activity
        customerID = getIntent().getStringExtra("customerID");

        // validate id
        if (customerID != null) {
            final HashMap<String, ArrayList<Reservation>> data = query.read(query.getCustomerReservation(customerID), db);
            data.entrySet().iterator().next().getValue().forEach(r -> Log.i("reservation: ", r.toString()));
        }
    }


}
