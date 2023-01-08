package com.example.uasbiodata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class Create extends AppCompatActivity {

    SQLHelper dbhelper;
    EditText ednpm, ednamalengkap, edtempatlahir, edtanggallahir, edjurusan, edalamat, edTextDate;
    Button simpan;
    Spinner spjeniskelamin;
    CircleImageView climageView;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        ednpm = (EditText) findViewById(R.id.edNpm);
        ednamalengkap = (EditText) findViewById(R.id.edNama);
        edtempatlahir = (EditText) findViewById(R.id.edTempat);
        edtanggallahir = (EditText) findViewById(R.id.edTanggal);
        edTextDate = (EditText) findViewById(R.id.editTextDate);
        edjurusan = (EditText) findViewById(R.id.edJurusan);
        edalamat = (EditText) findViewById(R.id.edAlamat);
        spjeniskelamin = (Spinner) findViewById(R.id.edJeniskelamin);
        climageView = (CircleImageView) findViewById(R.id.image_profile);
        simpan = (Button) findViewById(R.id.but_in_simpan);


        dbhelper = new SQLHelper(this);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gambar = "";
                String npm = ednpm.getText().toString();
                String nama = ednamalengkap.getText().toString();
                String tempatLahir = edtempatlahir.getText().toString();
                String tglLahir = edTextDate.getText().toString();
                String alamat = edjurusan.getText().toString();
                String jurusan = edjurusan.getText().toString();
                String jenisKelamin = spjeniskelamin.getSelectedItem().toString();

                addData(npm, nama, tempatLahir, tglLahir, jenisKelamin, jurusan, alamat, gambar);
            }
        });
    }

    private void addData(String npm, String nama, String tempat, String tanggal, String jeniskelamin, String jurusan, String alamat, String image) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        try {
            db.execSQL("INSERT INTO " + SQLHelper.TABLE + "(" +
                    SQLHelper.row_foto + "," +
                    SQLHelper.row_npm + "," +
                    SQLHelper.row_nama + "," +
                    SQLHelper.row_tempatLahir + "," +
                    SQLHelper.row_tglLahir + "," +
                    SQLHelper.row_jk + "," +
                    SQLHelper.row_jurusan + "," +
                    SQLHelper.row_alamat +
                    ")" +
                    " VALUES('" +
                    image + "','" +
                    npm + "','" +
                    nama + "','" +
                    tempat + "','" +
                    tanggal + "','" +
                    jeniskelamin + "','" +
                    jurusan + "','" +
                    alamat + "');"

            );
            ednpm.setText("");
            ednamalengkap.setText("");
            edtempatlahir.setText("");
            edtanggallahir.setText("");
            edjurusan.setText("");
            edalamat.setText("");

        } catch (Exception e) {
            ednpm.setText(e.toString());
        }
    }

    public void kembali(View view) {
//        Intent i = new Intent(Create.this, MainActivity.class);
//        startActivity(i);
        finish();
    }
}