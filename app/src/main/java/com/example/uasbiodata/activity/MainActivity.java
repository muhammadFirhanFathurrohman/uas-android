package com.example.uasbiodata.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uasbiodata.Edit;
import com.example.uasbiodata.R;
import com.example.uasbiodata.database.SQLHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    SQLHelper dbhelper;
    Button btncreate;
    FloatingActionButton btnfab;
    TextView tvdata;
    protected ListView listView;
    protected Cursor cursor;
    protected ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //inisialisasi
        setLayout();
        setKlik();
        dbhelper = new SQLHelper(this);
        getData();

//        pengecekan jika tidak ada data di listview
        if (cursor.getCount() == 0) {
            btnfab.setVisibility(View.GONE);
            tvdata.setText("Data masih kosong");
        } else {
//            jika tidak ada , hapus
            btncreate.setVisibility(View.GONE);
            tvdata.setText("Data Mahasiswa ");
        }
    }
//        menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedmode) {
        switch (selectedmode) {
            case R.id.menu_petunjuk:
                Intent intent = new Intent(MainActivity.this, Petunjuk.class);
                startActivity(intent);
                break;
//            case R.id.menu_keluar:
//                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setMessage("Keluar dari aplikasi ? ").setCancelable(false).
//                        setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                                MainActivity.this.finish();
//                            }
//                        }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                                dialogInterface.cancel();
//                            }
//                        }).show();
        }
    }
//    akhir menu

    void setLayout() {
        listView = (ListView) findViewById(R.id.lv_data);
        btncreate = (Button) findViewById(R.id.btn_create);
        btnfab = (FloatingActionButton) findViewById(R.id.btn_fab);
        tvdata = (TextView) findViewById(R.id.tv_data);
    }

    void setKlik() {
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Create.class);
                startActivity(intent);
            }
        });
        btnfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Create.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = cursor.getInt(0);
                String npm = cursor.getString(2) ;
                String nama = cursor.getString(3) ;
                String tempalahir = cursor.getString(4) ;

                 AlertDialog.Builder alerBuilder = new AlertDialog.Builder(MainActivity.this);
                 alerBuilder.setTitle("Pilih Option : ");

                 String[] options = {"Lihat Data"};

                 alerBuilder.setItems(options,(dialogInterface, j) -> {
                    switch (j){
                        case 0:
                            AlertDialog.Builder lihatdata = new AlertDialog.Builder(MainActivity.this);
                            lihatdata.setTitle("Lihat Data : ");

                            lihatdata.setMessage(
                                    "Npm : " + npm +"\n" +
                                            "Nama : " + nama + "\n" +
                                            "Tempat lahir : " + tempalahir + "\n"
                                    );

                            lihatdata.show();
                            break;
                    }
                 });
                 alerBuilder.show();
            }
        });
    }

    private void getData() {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        try {
            cursor = db.rawQuery("SELECT * FROM tabel_biodata", null);
            adapter = new SimpleCursorAdapter(this, R.layout.list_item, cursor,
                    new String[]{"Nama", "Npm"},
                    new int[]{ R.id.lv_nama, R.id.lv_npm});

            listView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}