package com.yaceen.dmntest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getInstance(Context context){
        if (instance == null){
            instance = new DatabaseHelper(context);
        }
      return instance;
    };

    private DatabaseHelper(Context context){
        super(context,"contacts.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS contacts( id INTEGER PRIMARY KEY AUTOINCREMENT,nom TEXT,phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts;");
        onCreate(db);
    }
}
