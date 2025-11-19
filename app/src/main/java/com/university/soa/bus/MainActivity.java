package com.university.soa.bus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Properties;

import Models.AppStatus;

/**
 * The main activity of the application.
 * This activity handles user login and navigation to the seat booking screen.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * The employee code entered by the user.
     */
    String str_empcode, a, b;

    /**
     * EditText for the employee code.
     */
    EditText edt_empcode;

    /**
     * Button to navigate to the new user registration screen.
     */
    Button newuser;

    /**
     * Button to log in.
     */
    Button login;

    /**
     * Properties object.
     */
    Properties prop;
    /**
     * Firebase database reference.
     */
    DatabaseReference ref;
    /**
     * Firebase database instance.
     */
    FirebaseDatabase Database;
    /**
     * LinearLayout for the main layout.
     */
    LinearLayout ll;
    /**
     * TextView for the seat label.
     */
    TextView t1;
    /**
     * RelativeLayout for the home screen.
     */
    RelativeLayout Rl;
    /**
     * Checks the network status.
     */
    AppStatus appStatus;

    /**
     * Initializes the activity.
     * This method sets up the UI components and Firebase database reference.
     *
     * @param savedInstanceState A bundle containing the activity's previously saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        t1 = findViewById(R.id.seat);
        ll = findViewById(R.id.layout1);
        Rl = findViewById(R.id.home);
        ll.setVisibility(View.VISIBLE);
        t1.setVisibility(View.VISIBLE);
        Rl.setVisibility(View.GONE);
        prop = new Properties();
        // auth = FirebaseAuth.getInstance();
        Database = FirebaseDatabase.getInstance();
        ref = Database.getReference().child("Employee Details : ");
        appStatus = new AppStatus(getApplicationContext());
        login = findViewById(R.id.btn_login);

        newuser = findViewById(R.id.newuser);
        edt_empcode = findViewById(R.id.CodeNum);


        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginRegistration.class));
            }
        });


        //OnClickListeners
        findViewById(R.id.newuser).setOnClickListener(this);

    }

    /**
     * Creates the options menu.
     *
     * @param menu The menu to inflate.
     * @return True if the menu is created successfully.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    /**
     * Handles click events for the login button.
     * Validates the employee code and navigates to the booking screen.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (appStatus.isOnline()) {
            str_empcode = edt_empcode.getText().toString().trim();
            SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
            try {
                if (str_empcode.length() == 0) {

                    Toast.makeText(getApplicationContext(),
                            "Please enter your Employee Code",
                            Toast.LENGTH_LONG).show();
                }
                else if (str_empcode.equals("1234") || str_empcode.equals("0000")
                        || str_empcode.equals("1111")) {
                    Toast.makeText(this, "Welcome User",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, book.class);
                    intent.putExtra("employee", str_empcode);
                    startActivity(intent);
                } else if (str_empcode.equals("1234")) {
                    Toast.makeText(this, "Welcome User", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, book.class));

                } else if (str_empcode.equals("0000")) {
                    Toast.makeText(this, "Welcome User", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, book.class));

                } else if (str_empcode.equals("1111")) {
                    Toast.makeText(this, "Welcome User", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, book.class));


                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "We did not find any account with the given Employee Code in this device", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please see that you have Active internet connection..", Toast.LENGTH_LONG).show();
        }
    }

}
