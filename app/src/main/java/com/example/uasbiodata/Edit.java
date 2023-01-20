package com.example.uasbiodata;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uasbiodata.activity.Create;
import com.example.uasbiodata.database.SQLHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Edit extends AppCompatActivity {

    SQLHelper dbhelper;
    EditText etnpmedit, etnamaedit, ettempatedit, ettanggaledit, etalamatedit;
    Button btnedit;
    Spinner spjurusanedit;
    RadioGroup rgedit;
    RadioButton rblaki, rbperempuan;
    CircleImageView cliprofiledit;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormat;

    public static String ID_ = "ID";
    public static String NPM_ = "NPM";
    public static String NAMA_ = "NAMA";
    public static String TEMPAT_ = "TEMPAT";
    public static String TANGGAL_ = "TANGGAL";
    public static String JENIS_ = "JENIS";
    public static String ALAMAT_ = "ALAMAT";
    public static String JURUSAN_ = "JURUSAN";
    int id;
    String npm, nama, tempat, tanggal, jeniskelamin, alamat, jurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        dbhelper = new SQLHelper(this);
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        setLayout();
        setIntent();
        setKlik();
    }

    void setLayout() {
        etnpmedit = (EditText) findViewById(R.id.et_npm_edit);
        etnamaedit = (EditText) findViewById(R.id.et_nama_edit);
        ettempatedit = (EditText) findViewById(R.id.et_tempat_edit);
        ettanggaledit = (EditText) findViewById(R.id.et_tanggal_edit);
        etalamatedit = (EditText) findViewById(R.id.et_alamat_edit);

        rgedit = (RadioGroup) findViewById(R.id.rg_jenis_edit);
        rblaki = (RadioButton) findViewById(R.id.rb_laki_edit);
        rbperempuan = (RadioButton) findViewById(R.id.rb_perempuan_edit);
        spjurusanedit = (Spinner) findViewById(R.id.sp_jurusan_edit);
        cliprofiledit = (CircleImageView) findViewById(R.id.cli_profil_edit);

        btnedit = (Button) findViewById(R.id.btn_edit);

    }

    void setIntent() {

        id = getIntent().getIntExtra(ID_,0);
        //        mengambil data dari mainactivity
        if (getIntent().getStringExtra(NPM_) != null) {
            npm = getIntent().getStringExtra(NPM_);
            etnpmedit.setText(npm);
        }
        if (getIntent().getStringExtra(NAMA_) != null) {
            nama = getIntent().getStringExtra(NAMA_);
            etnamaedit.setText(nama);
        }
        if (getIntent().getStringExtra(TEMPAT_) != null) {
            tempat = getIntent().getStringExtra(TEMPAT_);
            ettempatedit.setText(tempat);
        }
        if (getIntent().getStringExtra(TANGGAL_) != null) {
            tanggal = getIntent().getStringExtra(TANGGAL_);
            ettanggaledit.setText(tanggal);
        }
        if (getIntent().getStringExtra(JENIS_) != null) {
            jeniskelamin = getIntent().getStringExtra(JENIS_);
            if (jeniskelamin.equals("Laki-laki")) {
                rblaki.setChecked(true);
            } else {
                rbperempuan.setChecked(true);
            }
        }
        if (getIntent().getStringExtra(ALAMAT_) != null) {
            alamat = getIntent().getStringExtra(ALAMAT_);
            etalamatedit.setText(alamat);
        }
        if (getIntent().getStringExtra(JURUSAN_) != null) {
            jurusan = getIntent().getStringExtra(JURUSAN_);
            if (jurusan.equals("Teknik Informatika")) {
                spjurusanedit.setSelection(0);
            } else {
                spjurusanedit.setSelection(1);
            }
        }
    }

    void setKlik() {

        ettanggaledit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etnpmedit.getText().length()==0){
                    Toast.makeText(Edit.this, "NPM/NIM harus diisi", Toast.LENGTH_SHORT).show();
                }else if(etnamaedit.getText().length()==0){
                    Toast.makeText(Edit.this, "Nama harus diisi", Toast.LENGTH_SHORT).show();
                }else if(ettempatedit.getText().length()==0){
                    Toast.makeText(Edit.this, "Tempat harus diisi", Toast.LENGTH_SHORT).show();
                }else if(ettanggaledit.getText().length()==0){
                    Toast.makeText(Edit.this, "Tanggal harus diisi", Toast.LENGTH_SHORT).show();
                }else if(etalamatedit.getText().length()==0){
                    Toast.makeText(Edit.this, "Alamat harus diisi", Toast.LENGTH_SHORT).show();
                }
                else {
                    editData();
                }
            }
        });
    }

    private void editData() {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE tabel_biodata SET " +
                    "Foto='" + "" +
                    "',Npm='" + etnpmedit.getText().toString() +
                    "',Nama='" + etnamaedit.getText().toString() +
                    "',TempatLahir='" + ettempatedit.getText().toString() +
                    "',Tanggal='" + ettanggaledit.getText().toString() +
                    "',Jk='" + rbperempuan.getText().toString() +
                    "',Alamat='" + etalamatedit.getText().toString() +
                    "',Jurusan='" + spjurusanedit.getSelectedItem().toString() +
                    "' WHERE _id=" + id
            );
            this.setResult(RESULT_OK);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                ettanggaledit.setText(dateFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}