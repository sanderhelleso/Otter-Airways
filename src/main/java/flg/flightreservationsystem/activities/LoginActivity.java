package flg.flightreservationsystem.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import flg.flightreservationsystem.R;
import flg.flightreservationsystem.database.Actions;
import flg.flightreservationsystem.database.Database;
import flg.flightreservationsystem.database.Query;

public class LoginActivity extends AppCompatActivity {

    // instantiate database object
    private Database db = new Database(this);

    // instantiate query object
    private Query query = new Query();

    // confirm intent
    private Intent data = new Intent();
    private boolean confirm = false;

    // customer ID & username
    private String customerID;
    private String customerUN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // check for confirm
        isConfirm();

        // initialize login form
        initializeForm();
    }

    private void isConfirm() {
        // check if activity were opened due to confirmation purposes
        if (getIntent().getBooleanExtra("confirm", true)) {
            confirm = true;
        }
    }

    private void initializeForm() {

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

            // check for empty values
            if (TextUtils.isEmpty(username)) {
                USERNAME.setError("Please enter your username");
                return;
            }

            else if (TextUtils.isEmpty(password)) {
                PASSWORD.setError("Please enter you password");
                return;
            }

            // attempt to login user with recieved data properties
            login(username, password);
        });
    }

    private void login(final String username, final String password) {

        // get hashmap and response (success / error, message)
        final HashMap<Boolean, Map<String, String>> resultMap = query.login(query.loginCustomer(username, password), db);
        final Map.Entry<Boolean, Map<String, String>> entry = resultMap.entrySet().iterator().next();
        final Boolean success = entry.getKey();

        // retireve customers username and userID
        customerID = entry.getValue().entrySet().iterator().next().getKey();
        customerUN = entry.getValue().entrySet().iterator().next().getValue();

        // display message
        message(success ? Actions.LOGIN_SUCCESS : Actions.LOGIN_FAILED, success);
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


                    // if success, procced to next activity
                    if (success) {

                        if (confirm) {
                            data.putExtra("customerUN", customerUN);
                            data.putExtra("customerID", customerID);
                            setResult(1, data);
                        }

                        else {
                            // start next activity
                            switch (getIntent().getStringExtra("redirect_to")) {
                                case "cancel":
                                    // start "cancel seats" activity
                                    startActivity(new Intent(
                                            this, CancelReservationActivity.class)
                                            .putExtra("customerID", customerUN)
                                    );
                                    break;

                                case "manage":
                                    // start "manage system" activity
                                    startActivity(new Intent(
                                            this, ManageSystemActivity.class)
                                            .putExtra("customerID", customerUN)
                                    );
                                    break;
                            }
                        }
                    }

                    else {
                        setResult(-1, data);
                    }

                    // finish current activity
                    finish();
                })

                // display alert
                .show();
    }


}
