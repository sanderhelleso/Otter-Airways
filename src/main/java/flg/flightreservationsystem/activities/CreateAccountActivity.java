package flg.flightreservationsystem.activities;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import flg.flightreservationsystem.R;
import flg.flightreservationsystem.database.Database;
import flg.flightreservationsystem.database.Query;

public class CreateAccountActivity extends AppCompatActivity {

    // instantiate database object
    Database db = new Database(this);

    // instantiate query object
    Query query = new Query();

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

            if (username.length() > 20) {
                displayError(1);
                return;
            }

            // validate new account properties
            boolean valid = validate(username) && validate(password);

            // if properties are valid, attempt to create account
            if (valid) {

                // get hashmap and response (success / error, message)
                HashMap<Boolean, String> resultMap = db.insert(query.createNewCustomer(username, password, false));
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
     * In the project, the username and password should have at least one special symbol
     * (!, @, #, or $), one number, one uppercase alphabet, and one lowercase alphabet.
     */

    private boolean validate(final String value) {

        /*
        ------- REGEX TO HANDLE VALIDATION -------
               at least 4 total combinations
               at least 1 numeric character
               at least 1 lowercase letter
               at least 1 uppercase letter
               at least 1 special character
        ------------------------------------------
        */
        final String REQUIRED_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";

        // compile regex pattern
        Pattern pattern = Pattern.compile(REQUIRED_PATTERN);

        // initialize matcher and compare value against pattern
        Matcher matcher = pattern.matcher(value);

        // return result of match
        return matcher.matches();
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
                .setTitle(success ? "Success" : "Error")

                //set message
                .setMessage(message)

                // create "confirm" button and event
                .setPositiveButton("Confirm", (di, id) -> {

                    // if success, finish and return to main menu
                    if (success) { finish(); }
                })

                // display alert
                .show();
    }
}
