package com.example.uasbiodata.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.uasbiodata.R;

public class Petunjuk extends AppCompatActivity {

    Button btnkembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petunjuk);
//        inisilisasi
        setLayout();
        setKlik();
    }

    void setLayout(){
        btnkembali = (Button) findViewById(R.id.btn_kembali);
    }

    void setKlik(){
        btnkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Petunjuk.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}