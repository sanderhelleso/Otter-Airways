package flg.flightreservationsystem.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import flg.flightreservationsystem.R;
import flg.flightreservationsystem.database.Actions;
import flg.flightreservationsystem.database.Database;
import flg.flightreservationsystem.database.Query;
import flg.flightreservationsystem.src.Flight;
import flg.flightreservationsystem.src.ReserveSeatSearch;

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
        confirm.setOnClickListener((View v) -> {

            // retrieve reserve seats data
            final String seatsFrom = departure.getText().toString().trim();
            final String seatsTo = destination.getText().toString().trim();
            final String ticketAmount = amount.getText().toString().trim();

            // retrieve logged in customers ID
            final String customerID = getIntent().getStringExtra("customerID");

            // attempt to find available seats
            final HashMap<Boolean, Map.Entry<String, ArrayList<Flight>>> RESULT = query.find(
                    query.findAvailableSeats(seatsFrom, seatsTo), db);

            // validate result
            final Map.Entry<Boolean, Map.Entry<String, ArrayList<Flight>>> entry = RESULT.entrySet().iterator().next();
            final Map.Entry<String, ArrayList<Flight>> data = entry.getValue();
            final Boolean success = entry.getKey();


            if (success) {

                // create a new search object containing logged in users ID, amount of tickets and list of found flights
                final ReserveSeatSearch RESERVE_SEAT_SEARCH = new ReserveSeatSearch(
                        Integer.parseInt(customerID),
                        Integer.parseInt(ticketAmount)
                );

                //data.getValue().forEach(flight -> Log.i("flight: ", flight.toString()));
                displayAvailableFlights(data.getValue());
            }

            else {
                message(data.getKey(), success);
            }
        });
    }

    private void displayAvailableFlights(ArrayList<Flight> flights) {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // set title
        builder.setTitle("Found " + flights.size() + " Available Flights\n");

        // check first item
        int checkedItem = 0;

        // create list of flights
        String[] availableFlights = new String[flights.size()];
        for (int i = 0; i < flights.size(); i++) {
            availableFlights[i] = flights.get(i).getName();
        }

        builder.setSingleChoiceItems(availableFlights, checkedItem, (dialog, which) -> {
            // user checked an item
        });

        // set "Select" button
        builder.setPositiveButton("Select", (dialog, which) -> {
            // user clicked OK
            confirmSelectedFlight(flights.get(checkedItem), flights);
        });

        // set "Cancel" button
        builder.setNegativeButton("Cancel", null);

        // create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void confirmSelectedFlight(Flight flight, ArrayList<Flight> flights) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // set title
        builder.setTitle("Confirm information for flight " + flight.getName());

        builder.setMessage(flight.toString());

        // set "Confirm" button
        builder.setPositiveButton("Confirm", (dialog, which) -> {
            // user clicked OK
        });

        // set "Cancel" button
        builder.setNegativeButton("Back", (dialog, which) -> {
            displayAvailableFlights(flights);
        });

        // create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
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
