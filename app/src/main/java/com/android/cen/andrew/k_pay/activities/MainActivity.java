package com.android.cen.andrew.k_pay.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.cen.andrew.k_pay.fragments.LoginFragment;
import com.android.cen.andrew.k_pay.interfaces.NavigationHost;
import com.android.cen.andrew.k_pay.R;

public class MainActivity extends AppCompatActivity implements NavigationHost {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.container, new LoginFragment()).commit();
    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack, boolean animation) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();

        if (animation) {
            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        }

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.replace(R.id.container, fragment).commit();
    }
}
