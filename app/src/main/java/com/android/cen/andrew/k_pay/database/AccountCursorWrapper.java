package com.android.cen.andrew.k_pay.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import static com.android.cen.andrew.k_pay.database.DbSchema.*;

public class AccountCursorWrapper extends CursorWrapper {

    public AccountCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Account getAccount() {
        UUID uuid = UUID.fromString(getString(getColumnIndex(AccountTable.Cols.UUID)));
        String username = getString(getColumnIndex(AccountTable.Cols.USERNAME));
        String password = getString(getColumnIndex(AccountTable.Cols.PASSWORD));
        int balance = getInt(getColumnIndex(AccountTable.Cols.BALANCE));
        return new Account(uuid, username, password, balance);
    }
}
