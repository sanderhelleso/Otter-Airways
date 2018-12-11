package flg.flightreservationsystem.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import flg.flightreservationsystem.R;
import flg.flightreservationsystem.database.Database;
import flg.flightreservationsystem.database.Query;
import flg.flightreservationsystem.src.Flight;
import flg.flightreservationsystem.src.Reservation;

public class CancelReservationActivity extends AppCompatActivity {

    // instantiate database
    private final Database db = new Database(this);

    // instantiate new query
    private final Query query = new Query();

    // index to keep track of current selected index
    private int selectedReservationIndex = 0;

    // list to store reservations
    private ArrayList<Reservation> reservations;

    // selected reservation
    private Reservation reservation;

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

            // attempt to fetch logged in customers reserved seats
            final HashMap<String, ArrayList<Reservation>> data = query.read(
                    query.getCustomerReservation(customerID), db
            );

            // assign reservations
            reservations = data.entrySet().iterator().next().getValue();

            // check for valid reservations
            if (reservations.size() <= 0) {

            }

            else {
                displayAvailableReservations();
            }
        }
    }

    private void displayAvailableReservations() {

        // prevent alert from dissmissing on outside click
        this.setFinishOnTouchOutside(false);

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // prevent alert from dismissing on back click
        builder.setCancelable(false);

        // set title
        builder.setTitle("My Reservations\n\n");

        // create list of reservations
        String[] userReservations = new String[reservations.size()];
        for (int i = 0; i < reservations.size(); i++) {
            userReservations[i] = reservations.get(i).getFlightName();
        }

        // initial selected reservation in case user dont manually select option
        reservation = reservations.get(selectedReservationIndex);

        // set list with available flight options
        builder.setSingleChoiceItems(userReservations, selectedReservationIndex, (dialog, which) -> {

            // update checked item
            ListView lv = ((AlertDialog) dialog).getListView();
            lv.setTag(which);
            selectedReservationIndex = (Integer)lv.getTag();
            reservation = reservations.get(selectedReservationIndex);
        });

        // set "Select" button
        builder.setPositiveButton("See Details", (dialog, which) -> {
            displayReservationDetails();
        });

        // set "Cancel" button
        builder.setNegativeButton("Exit", (dialog, which) -> finish());

        // create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void displayReservationDetails() {

        // prevent alert from dissmissing on outside click
        this.setFinishOnTouchOutside(false);

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // prevent alert from dismissing on back click
        builder.setCancelable(false);

        // set title
        builder.setTitle("Reservation #" + reservation.getReservationID());

        // set built message
        builder.setMessage(reservation.toString());

        // set "Confirm" button
        builder.setPositiveButton("Cancel Reservation", (dialog, which) -> {

            // update screen with confirm cancel reservation alert
            confirmCancelReservation();
        });

        // set "Cancel" button
        builder.setNegativeButton("Back", (dialog, which) -> displayAvailableReservations());

        // create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void confirmCancelReservation() {

        // prevent alert from dissmissing on outside click
        this.setFinishOnTouchOutside(false);

        // create a new alert dialog
        new AlertDialog.Builder(this)

                // prevent alert from dismissing on back click
                .setCancelable(false)

                //set icon
                .setIcon(android.R.drawable.ic_dialog_alert)

                //set title
                .setTitle("  Cancel Reservation")

                //set message
                .setMessage("\n\nAre you sure you want to cancel the reservation for flight " + reservation.getFlightName() + "?. Canceling the reservation will remove your reserved tickets.\n\nThis action is NOT be reversible.\n\n\nDo you want to proceed?")

                // create "confirm" button and event
                .setPositiveButton("Yes, Cancel Now", (di, id) -> {

                })

                // create "back" button and event
                .setNegativeButton("No, Back", (di, id) -> displayReservationDetails())

                // display alert
                .show();
    }

}
