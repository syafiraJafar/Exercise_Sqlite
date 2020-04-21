package com.example.exercise_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.widget.EditText;

public class Profile extends AppCompatActivity {
    protected Cursor cursor;
    DBHelper mydata;
    EditText nama, noTelepon,email, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mydata = new DBHelper(this);
        nama = (EditText) findViewById(R.id.masukan_Name);
        noTelepon = (EditText) findViewById(R.id.Masukan_Tlpn);
        email = (EditText) findViewById(R.id.masukan_Email);
        alamat = (EditText) findViewById(R.id.masukan_Alamat);
        SQLiteDatabase db = mydata.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM contact WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(0).toString());
            noTelepon.setText(cursor.getString(1).toString());
            email.setText(cursor.getString(2).toString());
            alamat.setText(cursor.getString(3).toString());

        }


    }
}
