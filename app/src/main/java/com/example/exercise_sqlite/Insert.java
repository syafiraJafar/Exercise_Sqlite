package com.example.exercise_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insert extends AppCompatActivity {
Button simpan;
EditText nama, noTelepon,email, alamat;
int from_Where_I_Am_Coming = 0;
private com.example.exercise_sqlite.DBHelper mydata ;
int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);


        nama = (EditText) findViewById(R.id.masukan_Name);
        noTelepon = (EditText) findViewById(R.id.Masukan_Tlpn);
        email = (EditText) findViewById(R.id.masukan_Email);
        alamat = (EditText) findViewById(R.id.masukan_Alamat);
        simpan = (Button) findViewById(R.id.simpn);
        mydata = new com.example.exercise_sqlite.DBHelper(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            int Value = extras.getInt("id");
            if (Value>0){
                Cursor rs = mydata.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();
                String nam = rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_NAMA));
                String phon = rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_PHONE));
                String mail = rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_EMAIL));
                String almt = rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_ALAMAT));
                if (!rs.isClosed()){
                    rs.close();
                }
                Button b = (Button) findViewById(R.id.simpn);
                b.setVisibility(View.INVISIBLE);

                nama.setText((CharSequence)nam);
                nama.setFocusable(false);
                nama.setClickable(false);

                noTelepon.setText((CharSequence)phon);
                noTelepon.setFocusable(false);
                noTelepon.setClickable(false);

                email.setText((CharSequence)mail);
                email.setFocusable(false);
                email.setClickable(false);

                alamat.setText((CharSequence)almt);
                alamat.setFocusable(false);
                alamat.setClickable(false);
            }
        }
    }
    public  void  run(View view)
    {
        if (nama.getText().toString().equals("")|| noTelepon.getText().toString().equals("")|| email.getText().toString().equals("")|| alamat.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(),
                    "Data Harus Disi Semua !", Toast.LENGTH_LONG).show();
        }else {
            mydata.insertContact(nama.getText().toString(), noTelepon.getText().toString(), email.getText().toString(),alamat.getText().toString());
            Toast.makeText(getApplicationContext(),
                    "Insert data Berhasil !", Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
    }
}
