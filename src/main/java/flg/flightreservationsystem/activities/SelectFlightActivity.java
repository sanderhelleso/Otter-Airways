package flg.flightreservationsystem.activities;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import flg.flightreservationsystem.R;
import flg.flightreservationsystem.src.Flight;
import flg.flightreservationsystem.src.ReserveSeatSearch;

public class SelectFlightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_flight);

        // load recieved data and create flights
        loadAndCreateFlights();
    }

    private void loadAndCreateFlights() {

        // instantiate object with recived values
        final ReserveSeatSearch SEARCH_DATA = getIntent().getParcelableExtra("reserveSearch");
        final ArrayList<Flight> AVAILABLE_FLIGHTS = (ArrayList<Flight>) getIntent().getSerializableExtra("availableFlights");
        Log.i("data", SEARCH_DATA.toString());
        // print out flight data
        AVAILABLE_FLIGHTS.forEach(flight -> Log.i("flight: ", flight.toString()));
    }
}
