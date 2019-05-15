package com.android.cen.andrew.k_pay.fragments;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.cen.andrew.k_pay.database.Account;
import com.android.cen.andrew.k_pay.database.AccountLab;
import com.android.cen.andrew.k_pay.interfaces.NavigationHost;
import com.android.cen.andrew.k_pay.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterFragment extends Fragment {
    private TextInputEditText mUsernameEditText;
    private TextInputEditText mPasswordEditText;
    private TextInputEditText mRepeatPasswordEditText;
    private TextInputLayout mRepeatPasswordInputLayout;
    private TextInputLayout mPasswordInputLayout;
    private TextInputLayout mUsernameInputLayout;
    private MaterialButton mCancelButton;
    private MaterialButton mRegisterButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // Initialize the variables
        mUsernameEditText = view.findViewById(R.id.reg_username_edit_text);
        mPasswordEditText = view.findViewById(R.id.reg_password_edit_text);
        mRepeatPasswordEditText = view.findViewById(R.id.reg_repeat_password_edit_text);
        mRepeatPasswordInputLayout = view.findViewById(R.id.reg_repeat_password_input_layout);
        mPasswordInputLayout = view.findViewById(R.id.reg_password_input_layout);
        mUsernameInputLayout = view.findViewById(R.id.reg_username_input_layout);
        mCancelButton = view.findViewById(R.id.cancel_button);
        mRegisterButton = view.findViewById(R.id.sign_up_button);

        // set cancel button listener
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost)getActivity()).navigateTo(new LoginFragment(), false, true);
            }
        });

        // set register button listener
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error = false;
                // if edit text is empty, prompt error
                if (mUsernameEditText.getText().toString().isEmpty()) {
                    mUsernameInputLayout.setError(getString(R.string.empty_error_label));
                    error = true;
                }
                if (mPasswordEditText.getText().toString().isEmpty()) {
                    mPasswordInputLayout.setError(getString(R.string.empty_error_label));
                    error = true;
                }
                if (mRepeatPasswordEditText.getText().toString().isEmpty()) {
                    mRepeatPasswordInputLayout.setError(getString(R.string.empty_error_label));
                    error = true;
                }

                // exit function if error occurs
                if (error) return;

                Account account = AccountLab.get(getActivity()).getAccount(mUsernameEditText.getText().toString());

                if (account != null) {
                    mUsernameInputLayout.setError("Username is not available");
                    mPasswordInputLayout.setError(null);
                    mRepeatPasswordInputLayout.setError(null);
                } else {
                    mUsernameInputLayout.setError(null);
                    if (mPasswordEditText.getText().toString().length() < 8) {
                        mPasswordInputLayout.setError("Password min length is 8");
                        mRepeatPasswordInputLayout.setError(null);
                    } else if (!mPasswordEditText.getText().toString().equals(mRepeatPasswordEditText.getText().toString())) {
                        mPasswordInputLayout.setError(null);
                        mRepeatPasswordInputLayout.setError("Password mismatch");
                    } else {
                        mPasswordInputLayout.setError(null);
                        mRepeatPasswordInputLayout.setError(null);
                        AccountLab.get(getActivity()).addAccount(
                                new Account(
                                        mUsernameEditText.getText().toString(),
                                        mPasswordEditText.getText().toString(),
                                        0)
                        );
                        new MaterialAlertDialogBuilder(getActivity(), R.style.Theme_MaterialComponents_Light_Dialog_Alert)
                                .setTitle("Sign up successful")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ((NavigationHost)getActivity()).navigateTo(new LoginFragment(), false, true);
                                    }
                                })
                                .show();
                    }
                }
            }
        });

        return view;
    }
}
