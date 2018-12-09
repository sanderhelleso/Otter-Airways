package flg.flightreservationsystem.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import flg.flightreservationsystem.R;
import flg.flightreservationsystem.database.Database;
import flg.flightreservationsystem.database.Query;
import flg.flightreservationsystem.src.Flight;

public class ReserveSeatActivity extends AppCompatActivity {

    // instantiate database object
    Database db = new Database(this);

    // instantiate query object
    Query query = new Query();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seat);

        // initialize reserve seats form
        initializeForm();
    }

    private void initializeForm() {

        // departure input
        final EditText departure = findViewById(R.id.inputDeparture);

        // arrival input
        final EditText destination = findViewById(R.id.inputArrival);

        // amount of tickets input
        final EditText amount = findViewById(R.id.inputTickets);

        // add event to find available seats
        final Button confirm = findViewById(R.id.confirmFindSeats);
        confirm.setOnClickListener(v -> {

            // retrieve reserve seats data
            final String seatsFrom = departure.getText().toString().trim();
            final String seatsTo = destination.getText().toString().trim();
            final String ticketAmount = amount.getText().toString().trim();

            // attempt to find available seats
            final HashMap<Boolean, Map.Entry<String, ArrayList<Flight>>> RESULT = query.find(
                    query.findAvailableSeats(seatsFrom, seatsTo), db);

            // validate result
            final Map.Entry<Boolean, Map.Entry<String, ArrayList<Flight>>> entry = RESULT.entrySet().iterator().next();
            final Map.Entry<String, ArrayList<Flight>> data = entry.getValue();
            final Boolean success = entry.getKey();


            if (success) {

                // display data
                data.getValue().forEach(flight -> Log.i("flight: ", flight.toString()));
            }

            else {
                message(data.getKey(), success);
            }
        });
    }

    private void message(final String message, final Boolean success) {

        // create a new alert dialog
        new AlertDialog.Builder(this)

                //set icon
                .setIcon(success ? android.R.drawable.ic_dialog_info : android.R.drawable.ic_dialog_alert)

                //set title
                .setTitle(success ? "Success" : "Error")

                //set message
                .setMessage(message)

                // create "confirm" button and event
                .setPositiveButton("Confirm", null)

                // display alert
                .show();
    }
}
