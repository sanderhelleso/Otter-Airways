package flg.flightreservationsystem.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import flg.flightreservationsystem.MainActivity;
import flg.flightreservationsystem.R;
import flg.flightreservationsystem.database.Database;
import flg.flightreservationsystem.database.Query;
import flg.flightreservationsystem.src.Flight;

public class ReserveSeatActivity extends AppCompatActivity {

    // instantiate database object
    private Database db = new Database(this);

    // instantiate query object
    private Query query = new Query();

    // store ticket amount
    private int ticketAmount = 0;

    // selected flight index
    private int selectedFlightIndex = 0;

    // list to store flights
    private ArrayList<Flight> flights;

    // selected flight
    private Flight flight;

    // logged in customers ID
    private String customerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seat);

        // initialize reserve seats form
        initializeForm();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1337) {

            Log.i("resultcode", String.valueOf(resultCode));
            if (resultCode == 1) {
                customerID = data.getStringExtra("customerID");
                confirmSelectedFlight();
            }

            else if (resultCode == -1) {
                finish();
            }
        }
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
            final String seatsFrom =    departure.getText().toString().trim();
            final String seatsTo =      destination.getText().toString().trim();
            ticketAmount =              Integer.parseInt(amount.getText().toString().trim());

            // attempt to find available seats
            final HashMap<Boolean, Map.Entry<String, ArrayList<Flight>>> RESULT = query.find(
                    query.findAvailableSeats(seatsFrom, seatsTo), db);

            // validate result
            final Map.Entry<Boolean, Map.Entry<String, ArrayList<Flight>>> entry = RESULT.entrySet().iterator().next();
            final Map.Entry<String, ArrayList<Flight>> data = entry.getValue();
            final Boolean success = entry.getKey();


            if (success) {

                // assign flights and display available
                flights = data.getValue();
                displayAvailableFlights();
            }

            else {
                message(data.getKey(), success);
            }
        });
    }

    private void displayAvailableFlights() {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // set title
        builder.setTitle("Found " + flights.size() + " Available Flights\n");

        // create list of flights
        String[] availableFlights = new String[flights.size()];
        for (int i = 0; i < flights.size(); i++) {
            availableFlights[i] = flights.get(i).getName();
        }

        // initial selected flight in case user dont manually select option
        flight = flights.get(selectedFlightIndex);

        // set list with available flight options
        builder.setSingleChoiceItems(availableFlights, selectedFlightIndex, (dialog, which) -> {

            // update checked item
            ListView lv = ((AlertDialog) dialog).getListView();
            lv.setTag(which);
            selectedFlightIndex = (Integer)lv.getTag();
            flight = flights.get(selectedFlightIndex);
        });

        // set "Select" button
        builder.setPositiveButton("Select", (dialog, which) -> {

            // continue to confirm flight with selected flight object
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent,1337);
        });

        // set "Cancel" button
        builder.setNegativeButton("Cancel", null);

        // create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void confirmSelectedFlight() {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // set title
        builder.setTitle("Confirm information for flight " + flight.getName());

        // intantate new decimal formater to ensure number contains two decimals
        DecimalFormat df = new DecimalFormat("#.00");

        // get total price
        Double totalPrice = Double.parseDouble(String.valueOf(
                flight.getPrice() * ticketAmount)
        );

        // build message with price per ticket and total price
        StringBuilder message = new StringBuilder("Price: per ticket: $");
        message.append(df.format(flight.getPrice()));
        message.append("\nAmount of tickets: ");
        message.append(ticketAmount);
        message.append("\n\nTotal Price: $").append(df.format(totalPrice));

        // set built message
        builder.setMessage(flight.toString() + message.toString() + "\n");

        // set "Confirm" button
        builder.setPositiveButton("Confirm", (dialog, which) -> {
            // user clicked OK
        });

        // set "Cancel" button
        builder.setNegativeButton("Back", (dialog, which) -> {
            displayAvailableFlights();
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
