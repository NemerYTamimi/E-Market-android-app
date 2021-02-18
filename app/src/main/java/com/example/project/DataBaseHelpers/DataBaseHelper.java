package com.example.project.DataBaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.project.Modules.User;

public class DataBaseHelper extends SQLiteOpenHelper {

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE if exists User");
        db.execSQL("CREATE TABLE User(Email TEXT primary key,city TEXT, PHONE TEXT,firstName Text,lastname TEXT,GENDER boolean,password Text, good String,fav String)");
        db.execSQL("CREATE TABLE admin(Email TEXT primary key,city TEXT, PHONE TEXT,firstName Text,lastname TEXT,GENDER boolean,password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public Cursor getAllUsers() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM user", null);
    }

    public void insertUser(User user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", user.getEmail());
        contentValues.put("firstname", user.getFirstName());
        contentValues.put("PHONE", user.getPhoneNumber());
        contentValues.put("GENDER", user.isMale());
        contentValues.put("lastname", user.getLastName());
        contentValues.put("password", user.getPassword());
        contentValues.put("city", user.getCity());
        contentValues.put("good",user.getGoods());
        contentValues.put("fav",user.getFavGoods());
        sqLiteDatabase.insert("user", null, contentValues);
    }
    public Cursor getAllAdmins() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM admin", null);
    }

    public void insertAdmin(User user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", user.getEmail());
        contentValues.put("firstname", user.getFirstName());
        contentValues.put("PHONE", user.getPhoneNumber());
        contentValues.put("GENDER", user.isMale());
        contentValues.put("lastname", user.getLastName());
        contentValues.put("password", user.getPassword());
        contentValues.put("city", user.getCity());
        sqLiteDatabase.insert("admin", null, contentValues);}


    public void updateUser(User user)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", user.getFirstName());
        contentValues.put("PHONE", user.getPhoneNumber());
        contentValues.put("GENDER", user.isMale());
        contentValues.put("lastname", user.getLastName());
        contentValues.put("password", user.getPassword());
        contentValues.put("city", user.getCity());
        contentValues.put("good",user.getGoods());
        contentValues.put("fav",user.getFavGoods());
        sqLiteDatabase.update("user",contentValues," email = '"+user.getEmail()+"'",null);

    }
    public void execSQL(String SQL){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(SQL);
    }
    public void deleteUser(String email)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.delete("user","email = '"+email+"'",null);
    }
}