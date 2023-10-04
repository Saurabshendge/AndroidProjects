package com.example.practiceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnect extends SQLiteOpenHelper {

    public DBConnect(Context context) {     //constructor
        super(context, "Employee.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {       //table creation method for database
        db.execSQL("create table users(id TEXT primary key, name TEXT, contact TEXT, dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");
    }

    public Boolean insertUserData(String id, String name, String contact, String dob){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("name", name);
        cv.put("contact", contact);
        cv.put("dob", dob);
        long result = db.insert("users", null, cv);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean updateUserData(String id, String name, String contact, String dob){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("contact", contact);
        cv.put("dob", dob);

        Cursor cr=db.rawQuery("Select * from users where id = ?",new String[]{id});

        if(cr.getCount()>0) {
            long result = db.update("users", cv, "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else{
            return false;
        }
    }

    public Boolean deleteUserData(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cr=db.rawQuery("Select * from users where id = ?",new String[]{id});

        if(cr.getCount()>0) {
            long result = db.delete("users", "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else{
            return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cr=db.rawQuery("Select * from users ",null);

        return cr;
    }
}
