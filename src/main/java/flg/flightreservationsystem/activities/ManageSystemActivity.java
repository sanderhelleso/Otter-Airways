package flg.flightreservationsystem.activities;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import flg.flightreservationsystem.R;

public class ManageSystemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_system);

        // display logs
        displayLogs();
    }

    private void displayLogs() {

        // prevent alert from dissmissing on outside click
        this.setFinishOnTouchOutside(false);

        // create a new alert dialog
        new AlertDialog.Builder(this)

                // prevent alert from dismissing on back click
                .setCancelable(false)

                //set icon
                .setIcon(android.R.drawable.ic_dialog_info)

                //set title
                .setTitle("  Logs")

                //set message
                .setMessage("\n\nThis is some log info")

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
