package com.okeechobee.rivareus24.mindpalace.F___SqlLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.okeechobee.rivareus24.mindpalace.F___SqlLite.Data.TodolistContract;
import com.okeechobee.rivareus24.mindpalace.F___SqlLite.Data.TodolistDbHelper;
import com.okeechobee.rivareus24.mindpalace.R;

import static android.provider.BaseColumns._ID;
import static com.okeechobee.rivareus24.mindpalace.F___SqlLite.Data.TodolistContract.TodolistEntry.TABLE_NAME;

public class SQLiteActivity extends AppCompatActivity {

    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        TodolistDbHelper dbHelper = new TodolistDbHelper(this);

        mDb = dbHelper.getWritableDatabase();

        Cursor cursor = getAllTodos();

    }

    private Cursor getAllTodos(){
        return mDb.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                TodolistContract.TodolistEntry.COLUMN_PRIORITY
        );
    }

    private long addNewTodo(String description, int priority){
        ContentValues cv = new ContentValues();
        cv.put(TodolistContract.TodolistEntry.COLUMN_DESC, description);
        cv.put(TodolistContract.TodolistEntry.COLUMN_PRIORITY, priority);
        return mDb.insert(TABLE_NAME, null, cv);
    }

    private boolean removeGuest(long id){
        return mDb.delete(TABLE_NAME, _ID + "=" + id, null) > 0;
    }
}
