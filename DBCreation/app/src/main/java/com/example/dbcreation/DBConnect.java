package com.example.dbcreation;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.DialogTitle;

import com.google.android.material.tabs.TabLayout;

public class DBConnect extends SQLiteOpenHelper {

    private static final String tag = "DBConnect";

    private static final String dbname = "signup";

    public DBConnect(@Nullable Context context) {       //DB Constructor
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table Student (id integer primary key autoincrement, name text, username text, password text) ";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists Student");
        onCreate(db);
    }

    public void insertData(Data dt){
        SQLiteDatabase sdb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Name",dt.getName());
        cv.put("UserName",dt.getUname());
        cv.put("Password",dt.getPas());
        long student = sdb.insert("Student", null, cv);
        Log.e(TAG,"insertData: "+student);
    }
}
