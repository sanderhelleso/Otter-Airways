package flg.flightreservationsystem.activities;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import flg.flightreservationsystem.R;
import flg.flightreservationsystem.database.Database;
import flg.flightreservationsystem.database.Query;
import flg.flightreservationsystem.src.LogEntry;

public class ManageSystemActivity extends AppCompatActivity {

    // instantate new database
    private final Database db = new Database(this);

    // initiate new query
    private final Query query = new Query();

    // instantiate new log list
    private ArrayList<LogEntry> logs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_system);

        // load logs
        loadData();
    }

    private void loadData() {

        // attempt to fetch system logs
        final HashMap<String, ArrayList<LogEntry>> data = query.logs(
                query.getLogs(), db
        );

        // assign reservations
        logs = data.entrySet().iterator().next().getValue();

        // check for valid reservations
        if (logs.size() <= 0) {
            displayNoLogsFound();
        }

        else {
            displayLogs();
        }
    }

    private void displayLogs() {

        // prevent alert from dissmissing on outside click
        this.setFinishOnTouchOutside(false);

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // prevent alert from dismissing on back click
        builder.setCancelable(false);

        // set title
        builder.setTitle("System Logs\n\n");

        // revert list to get most recent at top
        Collections.reverse(logs);

        // create list of logs
        String[] systemLogs = new String[logs.size()];
        for (int i = 0; i < logs.size(); i++) {
            systemLogs[i] = "\nLog Entry #" + logs.get(i).getEntryID() +
                            "\nCustomer's Username: " +
                            logs.get(i).getUsername() + "\n" +
                            logs.get(i).toString()  +
                            logs.get(i).getMessage().replaceAll("_", "\n");
        }

        // set list with available flight options
        builder.setItems(systemLogs, null);

        // create "confirm" button and event
        builder.setPositiveButton("Confirm", (di, id) -> requestAddFlight());

        // create "exit" button and event
        builder.setNegativeButton("Exit", (di, id) -> finish());

        // create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void displayNoLogsFound() {

        // prevent alert from dissmissing on outside click
        this.setFinishOnTouchOutside(false);

        // create a new alert dialog
        new AlertDialog.Builder(this)

                // prevent alert from dismissing on back click
                .setCancelable(false)

                //set icon
                .setIcon(android.R.drawable.ic_dialog_alert)

                //set title
                .setTitle("  No Logs At This Moment")

                //set message
                .setMessage("\n\nNo logs were found at this moment.")

                // create "confirm" button and event
                .setPositiveButton("Confirm", (di, id) -> requestAddFlight())

                // create "exit" button and event
                .setNegativeButton("Exit", (di, id) -> finish())

                // display alert
                .show();
    }

    private void requestAddFlight() {
        // create a new alert dialog
        new AlertDialog.Builder(this)

                // prevent alert from dismissing on back click
                .setCancelable(false)

                //set icon
                .setIcon(android.R.drawable.ic_dialog_info)

                //set title
                .setTitle("  Add New Flight")

                //set message
                .setMessage("\n\nDo you wish to add a new flight?\n")

                // create "continue button and event
                .setPositiveButton("Yes, Continue", (di, id) -> initialForm())

                // create "exit" button and event
                .setNegativeButton("No, Exit", (di, id) -> finish())

                // display alert
                .show();
    }

    private void initialForm() {

        // "add flight" button
        final Button addFlightBtn = findViewById(R.id.addFlightBtn);

        // add event to button
        addFlightBtn.setOnClickListener(v -> validate());
    }

    private void validate() {

        // recieve form elements
        final EditText newFlightNumber = findViewById(R.id.newFlightNumber);
        final EditText newFlightDeparture = findViewById(R.id.newFlightDeparture);
        final EditText newFlightDestination = findViewById(R.id.newFlightDestination);
        final EditText newFlightTime = findViewById(R.id.newFlightTime);
        final EditText newFlightCapacity = findViewById(R.id.newFlightCapacity);
        final EditText newFlightPrice = findViewById(R.id.newFlightPrice);

        // recieve element data
        final String flightNumber = newFlightNumber.getText().toString().trim();
        final String flightDeparture = newFlightDeparture.getText().toString().trim();
        final String flightDestination = newFlightDestination.getText().toString().trim();
        final String flightTime = newFlightTime.getText().toString().trim();
        final String flightCapacity = newFlightCapacity.getText().toString().trim();
        final String flightPrice = newFlightPrice.getText().toString().trim();

        // check for empty values
        if (TextUtils.isEmpty(flightNumber)) {
            newFlightNumber.setError("Please enter a Flight Number");
        }

        else if (TextUtils.isEmpty(flightDeparture)) {
            newFlightDeparture.setError("Please enter the Flight Departure");
        }

        else if (TextUtils.isEmpty(flightDestination)) {
            newFlightDestination.setError("Please enter the Flight Destination");
        }

        else if (TextUtils.isEmpty(flightTime)) {
            newFlightTime.setError("Please enter a Flight Time for departure");
        }

        else if (TextUtils.isEmpty(flightCapacity)) {
            newFlightCapacity.setError("Please enter a maximum Flight Capacity");
        }

        else if (TextUtils.isEmpty(flightPrice)) {
            newFlightPrice.setError("Please enter a valid Flight Price per ticket");
        }

        // attempt to add new flight
        else {

            // get hashmap and response (success / error, message)
            HashMap<Boolean, String> resultMap = query.insertNewFlight(
                    query.createNewFlight(
                            flightNumber,
                            flightDeparture,
                            flightDestination,
                            Integer.parseInt(flightTime),
                            Integer.parseInt(flightCapacity),
                            Double.parseDouble(flightPrice)
                    ), db
            );

            Map.Entry<Boolean, String> entry = resultMap.entrySet().iterator().next();
            Boolean success = entry.getKey();
            String message = entry.getValue();
            message(message, success);
        }
    }

    private void message(final String message, final Boolean success) {

        // prevent alert from dissmissing on outside click
        this.setFinishOnTouchOutside(false);

        // create a new alert dialog
        new AlertDialog.Builder(this)

                // prevent alert from dismissing on back click
                .setCancelable(false)

                //set icon
                .setIcon(success ? android.R.drawable.ic_dialog_info : android.R.drawable.ic_dialog_alert)

                //set title
                .setTitle(success ? "  Success" : "  Error")

                //set message
                .setMessage(message)

                // create "confirm" button and event
                .setPositiveButton("Confirm", (di, id) -> finish())

                // display alert
                .show();
    }
}
