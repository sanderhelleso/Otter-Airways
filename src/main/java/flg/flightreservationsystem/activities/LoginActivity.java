package flg.flightreservationsystem.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import flg.flightreservationsystem.MainActivity;
import flg.flightreservationsystem.R;
import flg.flightreservationsystem.database.Database;
import flg.flightreservationsystem.database.Query;

public class LoginActivity extends AppCompatActivity {

    // instantiate database object
    Database db = new Database(this);

    // instantiate query object
    Query query = new Query();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // initialize login form
        initializeForm();
    }

    void initializeForm() {

        // username input
        final EditText USERNAME = findViewById(R.id.inputUsernameLogin);

        // password input
        final EditText PASSWORD = findViewById(R.id.inputPasswordLogin);

        // add event to log in customer
        final Button CONFIRM = findViewById(R.id.confirmLogin);
        CONFIRM.setOnClickListener(v -> {

            // retrieve login data
            final String username = USERNAME.getText().toString().trim();
            final String password = PASSWORD.getText().toString().trim();

            // attempt to login user with recieved data properties
            login(username, password);
        });
    }

    private void login(final String username, final String password) {

        // get hashmap and response (success / error, message)
        HashMap<Boolean, String> resultMap = query.select(query.loginCustomer(username, password), db);
        Map.Entry<Boolean, String> entry = resultMap.entrySet().iterator().next();
        Boolean success = entry.getKey();
        String message = entry.getValue();

        Log.i("ERROR", "STATUS: " + success + " - MESSAGE: " + message);
        message(message, success);
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
                .setPositiveButton("Confirm", (di, id) -> {

                    // finish login activity
                    finish();

                    // if success, procced to next acitvity
                    if (success) {

                        // start next activity
                        switch (getIntent().getStringExtra("redirect_to")) {

                            case "reserve":
                                // start "reserve" activity
                                this.startActivity(new Intent(this, ReserveSeatActivity.class));
                                break;

                            case "cancel":
                                // start "cancel seats" activity
                                this.startActivity(new Intent(this, CancelReservationActivity.class));
                                break;

                            case "manage":
                                // start "manage system" activity
                                this.startActivity(new Intent(this, CancelReservationActivity.class));
                                break;

                        }
                    }
                })

                // display alert
                .show();
    }


}
