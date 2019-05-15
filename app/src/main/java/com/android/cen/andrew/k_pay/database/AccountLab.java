package com.android.cen.andrew.k_pay.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.android.cen.andrew.k_pay.database.DbSchema.*;

public class AccountLab {
    private static AccountLab sAccountLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private AccountLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new DbHelper(mContext).getWritableDatabase();
    }

    private ContentValues getContentValues(Account account) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AccountTable.Cols.UUID, account.getUUID().toString());
        contentValues.put(AccountTable.Cols.USERNAME, account.getUsername());
        contentValues.put(AccountTable.Cols.PASSWORD, account.getPassword());
        contentValues.put(AccountTable.Cols.BALANCE, account.getBalance());
        return contentValues;
    }

    private AccountCursorWrapper queryAccounts(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                AccountTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new AccountCursorWrapper(cursor);
    }

    public static AccountLab get(Context context) {
        if (sAccountLab == null) {
            sAccountLab = new AccountLab(context);
        }
        return sAccountLab;
    }

    public Account getAccount(String username) {
        AccountCursorWrapper cursor = queryAccounts(
                AccountTable.Cols.USERNAME + " = ?",
                new String[] { username }
        );
        Account account = null;

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            account = cursor.getAccount();
        } catch (Exception e) {
        } finally {
            cursor.close();
        }

        return account;
    }

    public List<Account> getAccounts() {
        AccountCursorWrapper cursor = queryAccounts(null, null);
        List<Account> accounts = new ArrayList<>();

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                accounts.add(cursor.getAccount());
                cursor.moveToNext();
            }
        } catch (Exception e) {
        } finally {
            cursor.close();
        }

        return accounts;
    }

    public void addAccount(Account account) {
        ContentValues contentValues = getContentValues(account);
        mDatabase.insert(AccountTable.TABLE_NAME, null, contentValues);
    }

    public void deleteAccount(Account account) {
        mDatabase.delete(
                AccountTable.TABLE_NAME,
                AccountTable.Cols.USERNAME + " = ?",
                new String[] { account.getUsername() }
                );
    }

    public void updateAccount(Account account, String username, String password, int balance) {
        ContentValues contentValues = getContentValues(
                new Account(account.getUUID(), username, password, balance)
        );
        mDatabase.update(
                AccountTable.TABLE_NAME,
                contentValues,
                AccountTable.Cols.UUID + " = ?",
                new String[] { account.getUUID().toString() }
                );
    }
}
