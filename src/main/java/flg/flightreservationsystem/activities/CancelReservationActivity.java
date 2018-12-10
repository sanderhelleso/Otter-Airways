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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1337) {
            if (resultCode == 1) {
                customerID = data.getStringExtra("customerID");
            }
        }
    }

    private void loadData() {

        HashMap<String, ArrayList<Reservation>> data = query.read(query.getCustomerReservation(customerID), db);
        data.entrySet().iterator().next().getValue().forEach(reservation -> Log.i("reservation", reservation.toString()));
    }


}
