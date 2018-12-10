package flg.flightreservationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import flg.flightreservationsystem.activities.CancelReservationActivity;
import flg.flightreservationsystem.activities.CreateAccountActivity;
import flg.flightreservationsystem.activities.LoginActivity;
import flg.flightreservationsystem.activities.ManageSystemActivity;
import flg.flightreservationsystem.activities.ReserveSeatActivity;
import flg.flightreservationsystem.database.Database;

public class MainActivity extends AppCompatActivity {

    // load database

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
        final Button CREATE_ACCOUNT = findViewById(R.id.create_account);
        CREATE_ACCOUNT.setOnClickListener(v -> {
            
            // open intent on button click
            Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
            this.startActivity(intent);
        });
    }

    // initialize reserve seat intent
    void initializeReserveSeat() {

        // fetch element by id
        final Button RESERVE_SEAT = findViewById(R.id.reserve_seat);
        RESERVE_SEAT.setOnClickListener(v -> {

            // open intent on button click
            Intent intent = new Intent(MainActivity.this, ReserveSeatActivity.class);
            intent.putExtra("redirect_to", "reserve");
            this.startActivity(intent);
        });
    }

    // initialize cancel reservation intent
    void initializeCancelReservation() {

        // fetch element by id
        final Button CANCEL_RESERVATION =  findViewById(R.id.cancel_reservation);
        CANCEL_RESERVATION.setOnClickListener(v -> {

            // open intent on button click

            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("redirect_to", "cancel");
            this.startActivity(intent);
        });
    }

    // initialize cancel reservation intent
    void initializeManageSystem() {

        // fetch element by id
        final Button MANAGE_SYSTEM = findViewById(R.id.manage_system);
        MANAGE_SYSTEM.setOnClickListener(v -> {


            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("redirect_to", "manage");
            this.startActivity(intent);
        });
    }
}
