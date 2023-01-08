package com.example.uasbiodata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class Create extends AppCompatActivity {

    SQLHelper dbhelper;
    EditText ed_npm, ed_namalengkap, ed_tempatlahir, ed_tanggallahir, ed_jurusan, ed_alamat;
    Spinner jeniskelamin;
    CircleImageView imageView;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        ed_npm = (EditText) findViewById(R.id.txNomor_Add);
        ed_namalengkap = (EditText) findViewById(R.id.txNama_Add);
        ed_tempatlahir = (EditText) findViewById(R.id.txTempatLahir_Add);
        ed_tanggallahir = (EditText) findViewById(R.id.txTglLahir_Add);
        ed_jurusan = (EditText) findViewById(R.id.txJurusan_Add);
        ed_alamat = (EditText) findViewById(R.id.txAlamat_Add);

        dbhelper = new SQLHelper(this);
    }

    public void simpan(View view){
       /* SQLiteDatabase db = dbhelper.getWritableDatabase();
        String sql = "INSERT INTO tabel_biodata(row_npm,) VALUES()";

        db.execSQL(sql);
        Toast.makeText(Create.this, "data tersimpan", Toast.LENGTH_SHORT).show();
        MainActivity
                finiis*/
    }

    public void kembali (View view) {
        Intent i = new Intent(Create.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}