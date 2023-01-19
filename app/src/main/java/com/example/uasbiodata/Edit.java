package com.example.uasbiodata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class Edit extends AppCompatActivity {

    EditText etnpmedit, etnamaedit, ettempatedit, ettanggaledit, etalamatedit;
    Button btnedit;
    Spinner spjurusanedit;
    RadioGroup rgedit;
    RadioButton rblaki, rbperempuan;

    private String ID_ = "ID";
    private String NPM_ = "NPM";
    private String NAMA_ = "NAMA";
    private String TEMPAT_ = "TEMPAT";
    private String TANGGAL_ = "TANGGAL";
    private String JENIS_ = "JENIS";
    private String ALAMAT_ = "ALAMAT";
    private String JURUSAN_ = "JURUSAN";
    String npm, nama, tempat, tanggal,jeniskelamin, alamat, jurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        setLayout();
        setIntent();
        setKlik();
    }

    void setLayout(){
        etnpmedit = (EditText) findViewById(R.id.et_npm_edit);
        etnamaedit = (EditText) findViewById(R.id.et_nama_edit);
        ettempatedit = (EditText) findViewById(R.id.et_tempat_edit);
        ettanggaledit = (EditText) findViewById(R.id.et_tanggal_edit);
        etalamatedit = (EditText) findViewById(R.id.et_alamat_edit);

        rgedit = (RadioGroup) findViewById(R.id.rg_jenis_edit);
        rblaki = (RadioButton) findViewById(R.id.rb_laki_edit);
        rbperempuan = (RadioButton) findViewById(R.id.rb_perempuan_edit);
        spjurusanedit = (Spinner) findViewById(R.id.sp_jurusan_edit);

        btnedit = (Button) findViewById(R.id.btn_edit);
    }

    void setIntent(){
        //        mengambil data dari mainactivity
        if(getIntent().getStringExtra("NPM_")!= null){
            npm = getIntent().getStringExtra("NPM_");
            etnpmedit.setText(npm);
        }
        if(getIntent().getStringExtra("NAMA_")!= null){
            nama = getIntent().getStringExtra("NAMA_");
            etnamaedit.setText(nama);
        }
        if(getIntent().getStringExtra("TEMPAT_")!= null){
            tempat = getIntent().getStringExtra("TEMPAT_");
            ettempatedit.setText(tempat);
        }
        if(getIntent().getStringExtra("TANGGAL_")!= null){
            tanggal = getIntent().getStringExtra("TANGGAL_");
            ettanggaledit.setText(tanggal);
        }
        if(getIntent().getStringExtra("JENIS_")!= null){
            jeniskelamin = getIntent().getStringExtra("JENIS_");
            if (jeniskelamin.equals("Laki-laki")){
                rblaki.setChecked(true);
            }else{
                rbperempuan.setChecked(true);
            }
        }
        if(getIntent().getStringExtra("ALAMAT_")!= null){
            alamat = getIntent().getStringExtra("ALAMAT_");
            etalamatedit.setText(alamat);
        }

        if(getIntent().getStringExtra("JURUSAN_")!= null){
            jurusan = getIntent().getStringExtra("JURUSAN_");
            if (jurusan.equals("Teknik Informatika")){
                spjurusanedit.setSelection(0);
            }else{
                spjurusanedit.setSelection(1);
            }
        }
    }

    void setKlik(){
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}