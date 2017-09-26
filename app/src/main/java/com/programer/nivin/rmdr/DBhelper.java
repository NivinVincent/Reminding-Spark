package com.programer.nivin.rmdr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nivin Vincent on 9/22/2017.
 */
public class DBhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Reminder.db";
    public static final String COLUMN_ID="id";
    public static final String TABLE_NAME="Reminder_Table";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_CATEGORY_NAME="category";
    public static final String COLUMN_CERTIFICATE="certificate";
    public static final String COLUMN_DATE="date";
    public static final int DATABASE_VERSION=1;

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_table = "create table Reminder_Table( id integer primary key,name TEXT,category TEXT,certificate TEXT,date TEXT);";
        db.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
    }

    public void addValue(Remind remind) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",remind.getName());
        values.put("category",remind.getCategory());
        values.put("certificate",remind.getCertificate());
        values.put("date",remind.getDate());
        Log.i("namehelp",remind.getName());
        Log.i("categhelp",remind.getCategory());
        Log.i("certihelp",remind.getCertificate());
        Log.i("datehelp",remind.getDate());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Remind> getAllReminder(){
        List<Remind> allreminder = new ArrayList<Remind>();
        String selectquery = "select * from "+TABLE_NAME;
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectquery,null);

        if(cursor.moveToFirst()){
            do {
                Remind remind = new Remind();
                remind.setId(Integer.parseInt(cursor.getString(0)));
                remind.setName(cursor.getString(1));
                remind.setCategory(cursor.getString(2));
                remind.setCertificate(cursor.getString(3));
                remind.setDate(cursor.getString(4));
                allreminder.add(remind);

            }while (cursor.moveToNext());
        }

        return allreminder;
    }

    public void removedata(int id) {
        //Open the database
    Log.i("removedata..","enter..");
        SQLiteDatabase db = this.getWritableDatabase();
        //Execute sql query to remove from database
        //NOTE: When removing by String in SQL, value must be enclosed with ''
        db.execSQL("DELETE FROM Reminder_Table where id = "+id+";");

        //Close the database
        db.close();
    }


}
