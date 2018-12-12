package flg.flightreservationsystem.activities;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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
                            logs.get(i).toString();
        }

        // set list with available flight options
        builder.setItems(systemLogs, null);

        // create "confirm" button and event
        builder.setPositiveButton("Confirm", (di, id) -> initialForm());

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
                .setPositiveButton("Confirm", (di, id) -> initialForm())

                // create "exit" button and event
                .setNegativeButton("Exit", (di, id) -> finish())

                // display alert
                .show();
    }

    private void initialForm() {

    }
}
