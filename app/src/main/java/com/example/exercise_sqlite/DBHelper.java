package com.example.exercise_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    public   static  final String DATABSE_NAME = "Biodata.db" ;
    public static final String MHS_TABLE_NAME = "contact";
    public static final String MHS_COLUMN_NAMA = "nama";
    public static final String MHS_COLUMN_PHONE = "nomor_telepon";
    public static final String MHS_COLUMN_EMAIL = "Email";
    public static final String MHS_COLUMN_ALAMAT= "Alamat";
    private HashMap hp;

    private  static  final  int DATABASE_VERSION = 1;
    public  DBHelper(Context context){
        super(context, DATABSE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE contact (ID INTEGER PRIMARY KEY, nama TEXT, telepon TEXT, email TEXT, alamat TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(db);
    }

    public boolean insertContact (String nama, String nomor_telepon, String Email, String Alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama",nama );
        contentValues.put("nomor_telepon", nomor_telepon);
        contentValues.put("email", Email);
        contentValues.put("alamat", Alamat);
        db.insert("contact", null, contentValues);
        return true;
    }
    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery ( " select * from contact where id="+id+"", null );
        return res;
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db,
                MHS_TABLE_NAME);
        return numRows;
    }
    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from contact", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){

            array_list.add(res.getString(res.getColumnIndex(MHS_COLUMN_NAMA)));
            res.moveToNext();
        }
        return array_list;
    }


}
