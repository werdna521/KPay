package com.android.cen.andrew.k_pay.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cen.andrew.k_pay.database.AccountLab;
import com.android.cen.andrew.k_pay.interfaces.NavigationHost;
import com.android.cen.andrew.k_pay.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {
    private TextInputEditText mUsernameEditText;
    private TextInputEditText mPasswordEditText;
    private TextInputLayout mPasswordLayout;
    private MaterialButton mLoginButton;
    private TextView mRegisterTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // initialize the variables
        mUsernameEditText = view.findViewById(R.id.username_edit_text);
        mPasswordEditText = view.findViewById(R.id.password_edit_text);
        mPasswordLayout = view.findViewById(R.id.password_input_layout);
        mLoginButton = view.findViewById(R.id.login_button);
        mRegisterTextView = view.findViewById(R.id.register_clickable);

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
                ((NavigationHost)getActivity()).navigateTo(new RegisterFragment(), false, true);
            }
        });

        return view;
    }

    private void doLogin() {
        String password = null;

        try {
            password = AccountLab.get(getActivity()).getAccount(mUsernameEditText.getText().toString()).getPassword();
        } catch (Exception e) {
            password = null;
        }

        // check credentials
        if (password == null) {
            mPasswordLayout.setError("Wrong password or username not available");
        } else {
            mPasswordLayout.setError(null);
            ((NavigationHost)getActivity()).navigateTo(new HomeFragment(), false, true);
        }
    }
}
