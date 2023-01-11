package com.example.uasbiodata.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.uasbiodata.R;
import com.example.uasbiodata.database.SQLHelper;

import de.hdodenhof.circleimageview.CircleImageView;

public class Create extends AppCompatActivity {

    SQLHelper dbhelper;
    EditText  etnpm, etnama, ettempat, ettanggal, etjurusan, etalamat;
    Button btnsimpan, btnkembali;
    Spinner spjenis;
    CircleImageView cliprofil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
//        inisialisasi
        dbhelper = new SQLHelper(this);
        setLayout();
        setKlik();

    }

    void setLayout(){
        etnpm = (EditText) findViewById(R.id.et_npm);
        etnama = (EditText) findViewById(R.id.et_nama);
        ettanggal = (EditText) findViewById(R.id.et_tanggal);
        ettempat = (EditText) findViewById(R.id.et_tempat);
        etjurusan = (EditText) findViewById(R.id.et_jurusan);
        etalamat = (EditText) findViewById(R.id.et_alamat);

        spjenis = (Spinner) findViewById(R.id.sp_jenis);
        cliprofil = (CircleImageView) findViewById(R.id.cli_profil);
        btnsimpan = (Button) findViewById(R.id.btn_simpan);
        btnkembali = (Button) findViewById(R.id.btn_kembali);
    }

    void setKlik(){
        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gambar = "";
                String npm = etnpm.getText().toString();
                String nama = etnama.getText().toString();
                String tempat = ettempat.getText().toString();
                String tanggal = ettanggal.getText().toString();
                String jurusan = etjurusan.getText().toString();
                String alamat = etalamat.getText().toString();
                String jenis = spjenis.getSelectedItem().toString();

                addData(npm, nama, tempat, tanggal, jurusan, alamat, jenis, gambar);
                Intent intent = new Intent(Create.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Create.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addData(String npm, String nama, String tempat, String tanggal, String jeniskelamin,
                         String jurusan, String alamat, String image) {
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
            etnpm.setText("");
            etnama.setText("");
            ettempat.setText("");
            ettanggal.setText("");
            etjurusan.setText("");
            etalamat.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}