package com.university.soa.bus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.university.soa.bus.SeatClass.TourSelection;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * This activity allows the user to select a date for booking a bus seat.
 * It displays buttons for today, tomorrow, and the day after tomorrow.
 * When a date is selected, it navigates to the TourSelection activity.
 */
public class book extends AppCompatActivity {

    /**
     * Buttons for selecting the booking date.
     */
    Button B1, B2, B3;
    /**
     * The employee code of the logged-in user.
     */
    String str_empcode;
    /**
     * The selected date for booking.
     */
    String date;

    /**
     * Called when the activity is first created.
     * This method initializes the UI components and sets up the date selection buttons.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book);

        // Get the employee code from the intent
        if (getIntent() != null && getIntent().getExtras() != null
                && getIntent().hasExtra("employee")) {
            str_empcode = getIntent().getStringExtra("employee");
        }

        B1 = findViewById(R.id.button7);
        B2 = findViewById(R.id.button9);
        B3 = findViewById(R.id.button8);

        // Set up the date buttons
        final Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault());

        // Today's date
        c.add(Calendar.DAY_OF_YEAR, 0);
        Date today = c.getTime();
        String todayStr = df.format(today.getTime());
        B1.setText(todayStr);
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(book.this, TourSelection.class);
                intent.putExtra("employee", str_empcode + "D1");
                startActivity(intent);
            }
        });

        // Tomorrow's date
        c.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = c.getTime();
        String tm = df.format(tomorrow.getTime());
        B3.setText(tm);
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(book.this, TourSelection.class);
                intent.putExtra("employee", str_empcode + "D2");
                startActivity(intent);
            }
        });

        // Day after tomorrow's date
        c.add(Calendar.DAY_OF_YEAR, 1);
        Date dtm = c.getTime();
        String dtmStr = df.format(dtm.getTime());
        B2.setText(dtmStr);
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(book.this, TourSelection.class);
                intent.putExtra("employee", str_empcode + "D3");
                startActivity(intent);
            }
        });
    }
}
