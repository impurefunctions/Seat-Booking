package com.university.soa.bus.SeatClass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.university.soa.bus.R;
import com.university.soa.bus.RadioButton.checkbox;
import com.university.soa.bus.RadioButton.checkbox1;

/**
 * This activity allows the user to select a tour.
 * It displays a list of buttons for different tours.
 * Based on the selection, it navigates to the appropriate timing selection or seat selection activity.
 */
public class TourSelection extends AppCompatActivity {
    /**
     * Buttons for tour selection.
     */
    Button mtour1, mtour2, mtour3, mtour4, mtour5, mtour6;
    /**
     * The main layout of the activity.
     */
    RelativeLayout Rl;
    /**
     * The layout containing the login form.
     */
    LinearLayout ll;
    /**
     * A text view.
     */
    TextView t1;
    /**
     * The employee code of the logged-in user.
     */
    String str_empcode;

    /**
     * Called when the activity is first created.
     * This method initializes the UI and sets up the tour selection buttons.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get the employee code from the intent
        if (getIntent() != null && getIntent().getExtras() != null
                && getIntent().hasExtra("employee")) {
            str_empcode = getIntent().getStringExtra("employee");
        }

        ll = findViewById(R.id.layout1);
        t1 = findViewById(R.id.seat);
        Rl = findViewById(R.id.home);
        mtour1 = findViewById(R.id.Tour1);
        mtour2 = findViewById(R.id.Tour2);
        mtour3 = findViewById(R.id.Tour3);
        mtour4 = findViewById(R.id.Tour4);
        mtour5 = findViewById(R.id.Tour5);
        mtour6 = findViewById(R.id.Tour6);

        Rl.setVisibility(View.VISIBLE);
        ll.setVisibility(View.GONE);

        // Set click listeners for the tour buttons
        mtour1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(TourSelection.this,
                        checkbox.class);
                myIntent.putExtra("employee", str_empcode + "T1");
                startActivity(myIntent);
            }
        });
        mtour2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(TourSelection.this,
                        checkbox1.class);
                myIntent.putExtra("employee", str_empcode + "T2");
                startActivity(myIntent);
            }
        });
        mtour3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(TourSelection.this,
                        SeatSelection.class);
                myIntent.putExtra("employee", str_empcode + "T3");
                startActivity(myIntent);
            }
        });
        mtour4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(TourSelection.this,
                        SeatSelection.class);
                myIntent.putExtra("employee", str_empcode + "T4");
                startActivity(myIntent);
            }
        });
        mtour5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(TourSelection.this,
                        SeatSelection.class);
                myIntent.putExtra("employee", str_empcode + "T5");
                startActivity(myIntent);
            }
        });
        mtour6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(TourSelection.this,
                        SeatSelection.class);
                myIntent.putExtra("employee", str_empcode + "T6");
                startActivity(myIntent);
            }
        });
    }
}
