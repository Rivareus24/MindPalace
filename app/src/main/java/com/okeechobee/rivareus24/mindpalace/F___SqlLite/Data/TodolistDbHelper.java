package com.okeechobee.rivareus24.mindpalace.F___SqlLite.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodolistDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "todolist.db";  // name of db in the device

    private static final int DATABASE_VERSION = 1; // Increment it whenever the db schema changes

    public TodolistDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TODOLIST_TABLE = "CREATE TABLE " +
                TodolistContract.TodolistEntry.TABLE_NAME + "(" +
                TodolistContract.TodolistEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TodolistContract.TodolistEntry.COLUMN_DESC + "TEXT NOT NULL," +
                TodolistContract.TodolistEntry.COLUMN_PRIORITY + "INTEGER NOT NULL" +
                ");";
        db.execSQL(SQL_CREATE_TODOLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // utile se davvero vuoi cambiare lo scheme nella version successiva
        db.execSQL("DROP TABLE IF EXISTS " + TodolistContract.TodolistEntry.TABLE_NAME);
        onCreate(db);
    }
}
