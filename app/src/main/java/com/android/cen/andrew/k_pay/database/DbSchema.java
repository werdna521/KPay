package com.android.cen.andrew.k_pay.database;

public class DbSchema {

    public static class AccountTable {
        public static final String TABLE_NAME = "accounts";

        public static class Cols {
            public static final String UUID = "uuid";
            public static final String USERNAME = "username";
            public static final String PASSWORD = "password";
            public static final String BALANCE = "balance";
        }
    }
}
