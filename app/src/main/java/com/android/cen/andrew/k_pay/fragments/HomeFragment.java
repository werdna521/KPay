package com.android.cen.andrew.k_pay.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.android.cen.andrew.k_pay.R;

public class HomeFragment extends Fragment {
    private NestedScrollView mNestedScrollView;
    private Toolbar mToolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // initialize the variables
        mNestedScrollView = view.findViewById(R.id.nested_view);
        mToolbar = view.findViewById(R.id.toolbar);

        // set background shape
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mNestedScrollView.setBackgroundResource(R.drawable.grid_shape1);
        }

        // set toolbar as action bar
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);

        return view;
    }
}
