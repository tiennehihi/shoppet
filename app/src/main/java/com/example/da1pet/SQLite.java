package com.example.da1pet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite extends SQLiteOpenHelper {
    Context context;
    String table_user = "CREATE TABLE user(id_user INTEGER PRIMARY KEY AUTOINCREMENT,username VARCHAR(50),password VARCHAR(50),number INTEGER)";
    public SQLite(@Nullable Context context) {
        super(context, "user.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table_user);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
