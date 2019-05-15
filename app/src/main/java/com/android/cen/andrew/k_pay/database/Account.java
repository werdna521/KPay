package com.android.cen.andrew.k_pay.database;

import java.util.UUID;

public class Account {
    private UUID mUUID;
    private String mUsername;
    private String mPassword;
    private int mBalance;

    public Account(String username, String password, int balance) {
        this(UUID.randomUUID(), username, password, balance);
    }

    public Account(UUID uuid, String username, String password, int balance) {
        mUUID = uuid;
        mUsername = username;
        mPassword = password;
        mBalance = balance;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public int getBalance() {
        return mBalance;
    }

    public void setBalance(int balance) {
        mBalance = balance;
    }
}
