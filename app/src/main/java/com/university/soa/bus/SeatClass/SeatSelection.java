package com.university.soa.bus.SeatClass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.university.soa.bus.R;
import com.university.soa.bus.SavedSeats;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This activity displays the seat selection screen.
 * It uses a RecyclerView with a GridLayoutManager to show the seats.
 * Users can select and deselect seats, and the selection is saved to SharedPreferences.
 */
public class SeatSelection extends AppCompatActivity implements OnSeatSelected {

    /**
     * The number of columns in the seat grid.
     */
    private static final int COLUMNS = 5;
    /**
     * A set of strings representing the positions of the selected seats.
     */
    static Set<String> positions;
    /**
     * Button to confirm the seat booking.
     */
    Button mBook;
    /**
     * TextView to display the selected time.
     */
    TextView time;
    /**
     * Toast for displaying messages to the user.
     */
    Toast mToast;
    /**
     * The number of booked seats.
     */
    int bookCount = 0;
    /**
     * The employee code of the logged-in user.
     */
    String str_empcode;
    /**
     * SharedPreferences for storing the selected seats.
     */
    SharedPreferences seats;
    /**
     * Editor for modifying the SharedPreferences.
     */
    SharedPreferences.Editor edit;

    /**
     * Called when the activity is first created.
     * This method initializes the UI, sets up the RecyclerView, and handles seat selection.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Get the employee code from the intent
        if (getIntent() != null && getIntent().getExtras() != null
                && getIntent().hasExtra("employee")) {
            str_empcode = getIntent().getStringExtra("employee");
        }

        mBook = findViewById(R.id.button2);
        time = findViewById(R.id.show);
        seats = getSharedPreferences("seats", MODE_PRIVATE);
        positions = new HashSet<>(seats.getStringSet(str_empcode, new HashSet<String>()));

        mBook.setText(R.string.button2);

        // Create the list of items for the RecyclerView
        List<AbstractItem> items = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            if (i % COLUMNS == 0 || i % COLUMNS == 4) {
                items.add(new EdgeItem(String.valueOf(i)));
            } else if (i % COLUMNS == 1 || i % COLUMNS == 3) {
                items.add(new CenterItem(String.valueOf(i)));
            } else {
                items.add(new EmptyItem(String.valueOf(i)));
            }
        }

        // Set a click listener for the book button
        mBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (bookCount == 0) {
                    showToast("Please Select Seats");
                } else {
                    edit = seats.edit();
                    showToast(positions.size() + "seats selected");
                    edit.putStringSet(str_empcode, positions);
                    edit.commit();
                    Intent myIntent = new Intent(SeatSelection.this,
                            SavedSeats.class);
                    myIntent.putExtra("employee", str_empcode);
                    startActivity(myIntent);
                }
            }
        });

        // Set up the RecyclerView
        GridLayoutManager manager = new GridLayoutManager(this, COLUMNS);
        RecyclerView recyclerView = findViewById(R.id.lst_items);
        int spaceInPixels = 0;
        recyclerView.addItemDecoration(new RecyclerViewItemDecorator(spaceInPixels));
        recyclerView.setLayoutManager(manager);
        AirplaneAdapter adapter = new AirplaneAdapter(this, items, positions);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Shows a toast message.
     *
     * @param message The message to display.
     */
    private void showToast(String message) {
        if (mToast != null)
            mToast.cancel();
        mToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    /**
     * Called when a seat is selected or deselected.
     *
     * @param count    The total number of selected seats.
     * @param selected A set of strings representing the selected seat positions.
     */
    @Override
    public void onSeatSelected(int count, Set<String> selected) {
        bookCount = count;
        positions = selected;
        showToast(positions.size() + "seats selected");
    }
}
