package com.okeechobee.rivareus24.mindpalace.F___SqlLite.Data;

import android.provider.BaseColumns;

public class TodolistContract {

    public static final class TodolistEntry implements BaseColumns {

        public static final String TABLE_NAME = "todolist";
        public static final String COLUMN_DESC = "description";
        public static final String COLUMN_PRIORITY = "priority";
    }
}
