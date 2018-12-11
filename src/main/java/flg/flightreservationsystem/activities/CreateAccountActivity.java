package flg.flightreservationsystem.activities;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import flg.flightreservationsystem.R;
import flg.flightreservationsystem.database.Database;
import flg.flightreservationsystem.database.Query;
import flg.flightreservationsystem.helpers.Validate;

public class CreateAccountActivity extends AppCompatActivity {

    // instantiate database
    Database db = new Database(this);

    // instantiate query
    Query query = new Query();

    // instantiane validator
    Validate validate = new Validate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // initialize create account form
        initializeForm();
    }

    private void initializeForm() {

        // username input
        final EditText USERNAME = findViewById(R.id.inputUsername);

        // password input
        final EditText PASSWORD = findViewById(R.id.inputPassword);

        // add event to confirm account
        final Button CONFIRM = findViewById(R.id.confirmCreateAccount);
        CONFIRM.setOnClickListener(v -> {

            String username = USERNAME.getText().toString().trim();
            String password = PASSWORD.getText().toString().trim();

            // check for empty values
            if (TextUtils.isEmpty(username)) {
                USERNAME.setError("Please enter a username");
                return;
            }

            else if (TextUtils.isEmpty(password)) {
                PASSWORD.setError("Please enter a password");
                return;
            }

            if (username.length() > 20) {
                displayError(1);
                return;
            }

            // validate new account properties
            boolean valid = validate.createAccount(username) && validate.createAccount(password);

            // if properties are valid, attempt to create account
            if (valid) {

                // get hashmap and response (success / error, message)
                HashMap<Boolean, String> resultMap = query.insert(query.createNewCustomer(username, password, false), db);
                Map.Entry<Boolean, String> entry = resultMap.entrySet().iterator().next();
                Boolean success = entry.getKey();
                String message = entry.getValue();

                Log.i("ERROR", "STATUS: " + success + " - MESSAGE: " + message);
                message(message, success);
            }

            // if something is wrong with the properties, display error message
            else {
                displayError(0);
            }
        });
    }

    /*
        ------- DISPLAY ERROR MESSAGE DEPENDING ON EVENT -------
                     0 = invalid credential format
                     1 = username allready taken
        --------------------------------------------------------
     */
    private void displayError(final int type) {

        // instantiate a new sb
        StringBuilder message = new StringBuilder();
        switch (type) {
            case 0:

                // set error message for invalid credential format
                message.append("Invalid Format\n\n");
                message.append("Fields need to have at least:\n");
                message.append("- One special symbol\n");
                message.append("- One number\n");
                message.append("- One uppercase alphabet\n");
                message.append("- One lowercase alphabet\n");
                message.append("- Between 4 - 20 characters");
                break;

            case 1:
                break;
        }

        // display sat message
        message(message.toString(), false);
    }

    private void message(final String message, final Boolean success) {

        // create a new alert dialog
        new AlertDialog.Builder(this)

                //set icon
                .setIcon(success ? android.R.drawable.ic_dialog_info : android.R.drawable.ic_dialog_alert)

                //set title
                .setTitle(success ? "  Success" : "  Error")

                //set message
                .setMessage(message)

                // create "confirm" button and event
                .setPositiveButton("Confirm", (di, id) -> {
                    if (success) {
                        finish();
                    }
                })

                // display alert
                .show();
    }
}
