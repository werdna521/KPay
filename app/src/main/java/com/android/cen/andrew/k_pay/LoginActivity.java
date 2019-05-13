package com.android.cen.andrew.k_pay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private static final String PREFERENCE_NAME = "accounts";
    private TextInputEditText mUsernameEditText;
    private TextInputEditText mPasswordEditText;
    private TextInputLayout mPasswordLayout;
    private MaterialButton mLoginButton;
    private TextView mRegisterTextView;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        // get the shared preferences
        mSharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);

        // initialize the variables
        mUsernameEditText = findViewById(R.id.username_edit_text);
        mPasswordEditText = findViewById(R.id.password_edit_text);
        mPasswordLayout = findViewById(R.id.password_input_layout);
        mLoginButton = findViewById(R.id.login_button);
        mRegisterTextView = findViewById(R.id.register_clickable);

        // set button onclick listener
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        // set register text view clickable onclick listener
        mRegisterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void doLogin() {
        String password = null;

        try {
            password = mSharedPreferences.getString(mUsernameEditText.getText().toString(), null);
        } catch (Exception e) {
            password = null;
        }

        // check credentials
        if (password == null) {
            mPasswordLayout.setError("Wrong password or username not available");
        } else {
            // TODO go to home page
            mPasswordLayout.setError(null);
        }
    }
}
