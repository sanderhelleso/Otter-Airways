package flg.flightreservationsystem.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

    /*
     * In the project, the username and password should have at least one special symbol
     * (!, @, #, or $), one number, one uppercase alphabet, and one lowercase alphabet.
     */

    public boolean createAccount(final String value) {

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
}
