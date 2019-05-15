package com.android.cen.andrew.k_pay.interfaces;

import androidx.fragment.app.Fragment;

public interface NavigationHost {

    public void navigateTo(Fragment fragment, boolean addToBackstack, boolean animation);
}
