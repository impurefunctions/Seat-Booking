package com.university.soa.bus;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashSet;
import java.util.Set;

import Models.AppStatus;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * This activity handles the process of saving booked seats.
 * It collects user details, verifies their phone number using Firebase Authentication,
 * and then saves the booking information to the Firebase Realtime Database.
 */
public class SavedSeats extends AppCompatActivity {
    /**
     * Checks for an active internet connection.
     */
    AppStatus appStatus;
    /**
     * Firebase Realtime Database reference for storing booking details.
     */
    DatabaseReference ref;
    /**
     * Firebase Database instance.
     */
    FirebaseDatabase Database;
    /**
     * Button to save user information and initiate the booking process.
     */
    Button Saveinfo;
    /**
     * Button to verify the OTP.
     */
    Button button;
    /**
     * TextView for displaying OTP-related messages.
     */
    TextView T1;
    /**
     * TextView for displaying details and instructions.
     */
    TextView T2;
    /**
     * A set of strings to hold the identifiers of the selected seats.
     */
    Set<String> selected;

    /**
     * Strings to store user input for name, employee code, passenger number, and phone number.
     */
    String str_name, str_empcode, str_psnum, str_phnmber, emp_code, number;
    /**
     * EditText fields for user to input their name, phone number, employee code, and passenger number.
     */
    EditText Pname, Pnumber, Empcode, passnumber, editText2;
    /**
     * Tag for logging purposes.
     */
    private static final String TAG = "PhoneLogin";
    /**
     * Flag to track if phone number verification is in progress.
     */
    private boolean mVerificationInProgress = false;
    /**
     * The verification ID received from Firebase.
     */
    private String mVerificationId;
    /**
     * The token for resending the verification code.
     */
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    /**
     * Callbacks for handling phone authentication state changes.
     */
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    /**
     * Firebase Authentication instance.
     */
    private FirebaseAuth mAuth;


    /**
     * Called when the activity is first created.
     * This method initializes the UI, Firebase services, and sets up event listeners.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booked_info);
        Database = FirebaseDatabase.getInstance();
        ref = Database.getReference().child("Booked Details");
        appStatus = new AppStatus(getApplicationContext());
        Saveinfo = findViewById(R.id.saveinfo);
        button = findViewById(R.id.button);
        Pname = findViewById(R.id.PName);
        Pnumber = findViewById(R.id.PhnNumber);
        Empcode = findViewById(R.id.EmpCode);
        passnumber = findViewById(R.id.PsNum);
        editText2 = findViewById(R.id.editText2);
        T1 = findViewById(R.id.Opt);
        T2 = findViewById(R.id.Details);
        if (getIntent() != null && getIntent().getExtras() != null
                && getIntent().hasExtra("employee")) {
            emp_code = getIntent().getStringExtra("employee");
        }

        seats = getSharedPreferences("seats", MODE_PRIVATE);
        selected = seats.getStringSet(emp_code, new HashSet<String>());

        ref = FirebaseDatabase.getInstance().getReference().child("booked details");

        mAuth = FirebaseAuth.getInstance();

        // Initialize phone auth callbacks
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                mVerificationInProgress = true;
                Toast.makeText(SavedSeats.this, "Verification Complete", Toast.LENGTH_SHORT).show();
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(SavedSeats.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(SavedSeats.this, "InValid Phone Number", Toast.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // Handle too many requests
                }
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                Log.d(TAG, "onCodeSent:" + verificationId);
                Toast.makeText(SavedSeats.this, "Verification code has been send on your number", Toast.LENGTH_LONG).show();
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };

        // Set listener for the Save Info button
        Saveinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appStatus.isOnline()) {
                    // Get text from EditText fields
                    str_name = Pname.getText().toString().trim();
                    str_phnmber = Pnumber.getText().toString().trim();
                    str_empcode = Empcode.getText().toString().trim();
                    str_psnum = passnumber.getText().toString().trim();
                    try {
                        // Validate input fields
                        if (str_name.isEmpty() || str_empcode.isEmpty() || str_phnmber.isEmpty() || str_psnum.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "All fields are Mandatory", Toast.LENGTH_LONG).show();
                        } else if (str_empcode.equals("0000")) {
                            // Start phone verification for a specific employee code
                            number = "9669553697"; // Add your number here
                            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                    "+91 " + number,
                                    60,
                                    java.util.concurrent.TimeUnit.SECONDS,
                                    SavedSeats.this,
                                    mCallbacks);

                            // Update UI for OTP entry
                            T2.setText("Please Enter the OTP Send to Your Registered Mobile Number " + number);
                            T1.setVisibility(INVISIBLE);
                            Saveinfo.setVisibility(INVISIBLE);
                            Pname.setVisibility(INVISIBLE);
                            Pnumber.setVisibility(INVISIBLE);
                            Empcode.setVisibility(INVISIBLE);
                            passnumber.setVisibility(INVISIBLE);
                            button.setVisibility(VISIBLE);
                            editText2.setVisibility(VISIBLE);
                        } else {
                            Toast.makeText(SavedSeats.this, "Invalid Employee Code", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Sorry, Error Occurred..", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please see that you have Active internet connection..", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Set listener for the OTP verification button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = editText2.getText().toString();
                if (!otp.isEmpty()) {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otp);
                    signInWithPhoneAuthCredential(credential);
                } else {
                    Toast.makeText(SavedSeats.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Signs in the user with the provided phone authentication credential.
     *
     * @param credential The phone auth credential to sign in with.
     */
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SavedSeats.this, "Verification Done", Toast.LENGTH_SHORT).show();
                            store(); // Proceed to store booking details
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(SavedSeats.this, "Invalid Verification Code", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    /**
     * Stores the booking details in the Firebase Realtime Database.
     * This method is called after successful phone number verification.
     */
    public void store() {
        str_name = Pname.getText().toString().trim();
        str_empcode = Empcode.getText().toString().trim();
        str_phnmber = Pnumber.getText().toString().trim();
        str_psnum = passnumber.getText().toString().trim();
        Log.i("Seats", "Selected: " + selected);

        // Save the selected seats under the employee's code
        ref.child(str_empcode).push().setValue(String.valueOf(selected));

        Toast.makeText(SavedSeats.this, "Booked Successfully.. Seat no.- " + printSelected(selected), Toast.LENGTH_LONG).show();
    }

    /**
     * Formats the set of selected seat identifiers into a comma-separated string.
     *
     * @param selectedSeats The set of selected seat identifiers.
     * @return A string representation of the selected seats.
     */
    private String printSelected(Set<String> selectedSeats) {
        StringBuilder result = new StringBuilder();
        String[] seats = selectedSeats.toArray(new String[0]);

        for (int i = 0; i < seats.length; i++) {
            result.append(seats[i]);
            if (i < seats.length - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    /**
     * Handles the back button press. Finishes the current activity.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
