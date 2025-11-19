package com.university.soa.bus.RadioButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.university.soa.bus.R;
import com.university.soa.bus.SeatClass.SeatSelection;

/**
 * This activity allows the user to select a bus timing.
 * It displays a list of radio buttons with different time slots.
 * Based on the selection, it navigates to the SeatSelection activity.
 */
public class checkbox extends Activity {
    /**
     * Radio buttons for time selection.
     */
    RadioButton R1, R2, R3, R4;
    /**
     * Button to confirm the time selection.
     */
    Button B1;
    /**
     * The employee code of the logged-in user.
     */
    String str_empcode;

    /**
     * Called when the activity is first created.
     * This method initializes the UI components and sets up the radio buttons.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           Otherwise it is null.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing);

        // Get the employee code from the intent
        if (getIntent() != null && getIntent().getExtras() != null
                && getIntent().hasExtra("employee")) {
            str_empcode = getIntent().getStringExtra("employee");
        }

        R1 = findViewById(R.id.Opt1);
        R2 = findViewById(R.id.Opt2);
        R3 = findViewById(R.id.Opt3);
        R4 = findViewById(R.id.Opt4);
        B1 = findViewById(R.id.OK);

        B1.setText(R.string.OK);
        R1.setText(R.string.Opt1);
        R2.setText(R.string.Opt2);
        R3.setText(R.string.Opt3);
        R4.setText(R.string.Opt4);

        // Set a click listener for the OK button
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check which radio button is selected and start the SeatSelection activity accordingly
                if (R1.isChecked()) {
                    Intent intents = new Intent(checkbox.this, SeatSelection.class);
                    intents.putExtra("employee", str_empcode + "R1");
                    startActivity(intents);
                } else if (R2.isChecked()) {
                    Intent intentm = new Intent(getApplicationContext(), SeatSelection.class);
                    intentm.putExtra("employee", str_empcode + "R2");
                    startActivityForResult(intentm, 0);
                } else if (R3.isChecked()) {
                    Intent intentw = new Intent(getApplicationContext(), SeatSelection.class);
                    intentw.putExtra("employee", str_empcode + "R3");
                    startActivityForResult(intentw, 0);
                } else if (R4.isChecked()) {
                    Intent intenth = new Intent(getApplicationContext(), SeatSelection.class);
                    intenth.putExtra("employee", str_empcode + "R4");
                    startActivityForResult(intenth, 0);
                }
            }
        });
    }
}
