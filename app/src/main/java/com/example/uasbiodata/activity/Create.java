package com.example.uasbiodata.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.uasbiodata.R;
import com.example.uasbiodata.database.SQLHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Create extends AppCompatActivity {

    SQLHelper dbhelper;
    EditText  etnpm, etnama, ettempat, ettanggal, etalamat;
    RadioGroup rgJeniskelamin;
    RadioButton rbIdTerpilih;
    Button btnsimpan;
    Spinner spjurusan;
    CircleImageView cliprofil;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        dbhelper = new SQLHelper(this);
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        inisialisasi
        setLayout();
        setKlik();
    }

    void setLayout(){
        etnpm = (EditText) findViewById(R.id.et_npm);
        etnama = (EditText) findViewById(R.id.et_nama);
        ettanggal = (EditText) findViewById(R.id.et_tanggal);
        ettempat = (EditText) findViewById(R.id.et_tempat);
        etalamat = (EditText) findViewById(R.id.et_alamat);

         rgJeniskelamin = (RadioGroup) findViewById(R.id.rg_jenis);

        spjurusan = (Spinner) findViewById(R.id.sp_jurusan);
        cliprofil = (CircleImageView) findViewById(R.id.cli_profil);
        btnsimpan = (Button) findViewById(R.id.btn_simpan);
    }

    void setKlik(){
        ettanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foto = "";
                String npm = etnpm.getText().toString();
                String nama = etnama.getText().toString();
                String tempat = ettempat.getText().toString();
                String tanggal = ettanggal.getText().toString();
                //                mengambil id yang terpilih
                int idTerpilih = rgJeniskelamin.getCheckedRadioButtonId();
                rbIdTerpilih = (RadioButton) findViewById(idTerpilih);
                String jenis = rbIdTerpilih.getText().toString();

                String jurusan = spjurusan.getSelectedItem().toString();
                String alamat = etalamat.getText().toString();



                if(etnpm.getText().length()==0){
                    Toast.makeText(Create.this, "NPM/NIM harus diisi", Toast.LENGTH_SHORT).show();
                }else if(etnama.getText().length()==0){
                    Toast.makeText(Create.this, "Nama harus diisi", Toast.LENGTH_SHORT).show();
                }else if(ettempat.getText().length()==0){
                    Toast.makeText(Create.this, "Tempat harus diisi", Toast.LENGTH_SHORT).show();
                }else if(ettanggal.getText().length()==0){
                    Toast.makeText(Create.this, "Tanggal harus diisi", Toast.LENGTH_SHORT).show();
                }else if(etalamat.getText().length()==0){
                    Toast.makeText(Create.this, "Alamat harus diisi", Toast.LENGTH_SHORT).show();
                }
                else {
                    addData(foto, npm, nama, tempat, tanggal, jenis, alamat, jurusan);
                    Intent intent = new Intent(Create.this, MainActivity.class);
                    Create.this.finish();
                    startActivity(intent);
                }
            }
        });
    }

    private void addData(String foto, String npm, String nama, String tempat, String tanggal, String jeniskelamin,
                         String alamat, String jurusan) {
         SQLiteDatabase db = dbhelper.getWritableDatabase();
         try {
            db.execSQL("INSERT INTO " + SQLHelper.TABLE + "(" +
                    SQLHelper.row_foto + "," +
                    SQLHelper.row_npm + "," +
                    SQLHelper.row_nama + "," +
                    SQLHelper.row_tempatLahir + "," +
                    SQLHelper.row_tglLahir + "," +
                    SQLHelper.row_jk + "," +
                    SQLHelper.row_alamat + "," +
                    SQLHelper.row_jurusan +
                    ")" +
                    " VALUES('" +
                    foto + "','" +
                    npm + "','" +
                    nama + "','" +
                    tempat + "','" +
                    tanggal + "','" +
                    jeniskelamin + "','" +
                    alamat + "','" +
                    jurusan + "');"
            );
            etnpm.setText("");
            etnama.setText("");
            ettempat.setText("");
            ettanggal.setText("");
            etalamat.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDateDialog(){
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                ettanggal.setText(dateFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

}