package flg.flightreservationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import flg.flightreservationsystem.activities.CancelReservationActivity;
import flg.flightreservationsystem.activities.CreateAccountActivity;
import flg.flightreservationsystem.activities.ManageSystemActivity;
import flg.flightreservationsystem.activities.ReserveSeatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize main menu
        initializeMenu();
    }

    void initializeMenu() {

        // initialize each of the menu option
        initializeCreateAccount();
        initializeReserveSeat();
        initializeCancelReservation();
        initializeManageSystem();
    }

    // initialize create account intent
    void initializeCreateAccount() {

        // fetch element by id
        final Button CREATE_ACCOUNT = (Button) findViewById(R.id.create_account);
        CREATE_ACCOUNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // open intent on button click
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    // initialize reserve seat intent
    void initializeReserveSeat() {

        // fetch element by id
        final Button RESERVE_SEAT = (Button) findViewById(R.id.reserve_seat);
        RESERVE_SEAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // open intent on button click
                Intent intent = new Intent(MainActivity.this, ReserveSeatActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    // initialize cancel reservation intent
    void initializeCancelReservation() {

        // fetch element by id
        final Button CANCEL_RESERVATION = (Button) findViewById(R.id.cancel_reservation);
        CANCEL_RESERVATION.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // open intent on button click
                Intent intent = new Intent(MainActivity.this, CancelReservationActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    // initialize cancel reservation intent
    void initializeManageSystem() {

        // fetch element by id
        final Button MANAGE_SYSTEM = (Button) findViewById(R.id.manage_system);
        MANAGE_SYSTEM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // open intent on button click
                Intent intent = new Intent(MainActivity.this, ManageSystemActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
