package flg.flightreservationsystem.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import flg.flightreservationsystem.R;

public class CreateAccountActivity extends AppCompatActivity {

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
        CONFIRM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // validate new account properties
                boolean valid = validate(USERNAME.getText().toString().trim()) && validate(PASSWORD.getText().toString().trim());

                // if properties are valid, create account
                if (valid) {
                    createAccount();
                }

                // if something is wrong with the properties, display error message
                else {
                    displayError(0);
                }
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

    private void createAccount() {
        Log.i("account", "ACCOUNT CREATION SUCCESSFULL");
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
                message.append("InvalidFormat\n\n");
                message.append("Fields need to have at least:\n");
                message.append("- One special symbol\n");
                message.append("- One number\n");
                message.append("- One uppercase alphabet, \n");
                message.append("- One lowercase alphabet");
                break;

            case 1:
                break;
        }

        // display sat message
        errorMessage(message);
    }

    private void errorMessage(final StringBuilder message) {

        // create a new alert dialog
        new AlertDialog.Builder(this)

                //set icon
                .setIcon(android.R.drawable.ic_dialog_alert)

                //set title
                .setTitle("Error")

                //set message
                .setMessage(message)

                // create "confirm" button
                .setPositiveButton("Confirm", null)

                // display alert
                .show();
    }
}
