package com.android.cen.andrew.k_pay.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.cen.andrew.k_pay.database.DbSchema.AccountTable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "accounts.db";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + AccountTable.TABLE_NAME + "(" +
                "_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AccountTable.Cols.UUID + ", " +
                AccountTable.Cols.USERNAME + ", " +
                AccountTable.Cols.PASSWORD + ", " +
                AccountTable.Cols.BALANCE +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
