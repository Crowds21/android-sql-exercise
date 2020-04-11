package com.example.exercise5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_STAFF = "create table Staff(" +
            "id integer primary key autoincrement," +
            "image blob,"+
            "name text," +
            "salary real," +
            "depart text)";

    private Context mContext;

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d("SQL","Create2");
        mContext = (Context) context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("SQL","dbhelperCreate");
        db.execSQL(CREATE_STAFF);
        Toast.makeText(mContext,"Create Success!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(mContext,"Update Success!",Toast.LENGTH_SHORT).show();
    }

}
