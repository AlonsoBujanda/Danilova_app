package com.example.danilova.placeholder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "Signup.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Signup.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table allusers(users TEXT primary Key,password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {
        MyDatabase.execSQL("drop table if exists allusers");

    }
    public Boolean insertData(String users, String password) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("users", users);
        contentValues.put("password", password);
        long result = MyDatabase.insert("allusers", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean cheackUsers(String users) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where users = ?", new String[]{users});

        if (cursor.getCount() > 0) {
            return true;

        } else {
            return false;
        }

    }
    public Boolean checkUsersPassword(String users, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where users = ? and password = ?", new String[]{users,password});

        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
}

