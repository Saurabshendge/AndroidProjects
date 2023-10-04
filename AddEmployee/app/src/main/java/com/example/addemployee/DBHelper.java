package com.example.addemployee;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Testdb.db";
    public static final String TABLE_NAME="Employees";
    /*public static final String Col1="ID";
    public static final String Col2="Name";
    public static final String Col3="Mobile Num";
    public static final String Col4="Address";*/
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create Table if not exists "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, title Text, message Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists " + TABLE_NAME);
        onCreate(db);
    }
    public void addData(Data data){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", data.getTitle());
        cv.put("message", data.getMessage());
        long TABLE_NAME = sqLiteDatabase.insert("TABLE_NAME", null, cv);
    }
}
