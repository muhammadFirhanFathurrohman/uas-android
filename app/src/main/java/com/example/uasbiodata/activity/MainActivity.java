package com.example.uasbiodata.activity;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
            case R.id.menu_keluar:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Keluar dari aplikasi ? ").setCancelable(false).
                        setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MainActivity.this.finish();
                            }
                        }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
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
//        tombol tambah diklik
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Create.class);
                startActivityForResult(intent, 202);
            }
        });
//        tombol fab diklik
        btnfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Create.class);
                startActivityForResult(intent, 202);
            }
        });
//        klik isi data listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                mengambil data dari database
                int id = cursor.getInt(0);
                String npm = cursor.getString(2);
                String nama = cursor.getString(3);
                String tempalahir = cursor.getString(4);
                String tanggallahir = cursor.getString(5);
                String jeniskelamin = cursor.getString(6);
                String alamat = cursor.getString(7);
                String jurusan = cursor.getString(8);

// membuat alert
                AlertDialog.Builder alerBuilder = new AlertDialog.Builder(MainActivity.this);
                alerBuilder.setTitle("Pilih Opsi : ");
//                 pilihan alert
                String[] options = {"Lihat Data", "Edit Data", "Hapus Data"};
                alerBuilder.setItems(options, (dialogInterface, j) -> {
                    switch (j) {
//                       lihat data diklik
                        case 0:
                            AlertDialog.Builder lihatdata = new AlertDialog.Builder(MainActivity.this);
                            lihatdata.setTitle("Lihat Data : ");
                            lihatdata.setMessage(
                                    "Npm : " + "\t" + npm + "\n" + "\n" +
                                            "Nama : " + "\t" + nama + "\n" + "\n" +
                                            "Tempat Lahir : " + "\t" + tempalahir + "\n" + "\n" +
                                            "Tanggal Lahir : " + "\t" + tanggallahir + "\n" + "\n" +
                                            "Jenis Kelamin : " + "\t" + jeniskelamin + "\n" + "\n" +
                                            "Alamat : " + "\t" + alamat + "\n" + "\n" +
                                            "Jurusan : " + "\t" + jurusan
                            );
                            lihatdata.show();
                            break;
//                             edit data diklik
                        case 1:
                            Intent editdata = new Intent(MainActivity.this, Edit.class);
                            editdata.putExtra(Edit.ID_, id);
                            editdata.putExtra(Edit.NPM_, npm);
                            editdata.putExtra(Edit.NAMA_, nama);
                            editdata.putExtra(Edit.TEMPAT_, tempalahir);
                            editdata.putExtra(Edit.TANGGAL_, tanggallahir);
                            editdata.putExtra(Edit.JENIS_, jeniskelamin);
                            editdata.putExtra(Edit.ALAMAT_, alamat);
                            editdata.putExtra(Edit.JURUSAN_, jurusan);
                            startActivityForResult(editdata, 201);
                            break;
//                             hapus data diklik
                        case 2:
                            AlertDialog.Builder alerthapus = new AlertDialog.Builder(MainActivity.this);
                            alerthapus.setMessage("Apakah ingin menghapus data ini ?").setCancelable(false).
                                    setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            SQLiteDatabase db = dbhelper.getWritableDatabase();
                                            try {
                                                db.execSQL("DELETE FROM " + SQLHelper.TABLE + " where _id='"
                                                        + id + "'");
                                                Toast.makeText(MainActivity.this,
                                                        "berhasil dihapus",
                                                        Toast.LENGTH_SHORT).show();
                                                getData();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                Toast.makeText(MainActivity.this,
                                                        "gagal dihapus",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    }).show();
                            break;
                    }
                });
                alerBuilder.show();
            }
        });
    }

    //    mengambil data supaya nampil di lisview
    private void getData() {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        try {
            cursor = db.rawQuery("SELECT * FROM tabel_biodata", null);
            adapter = new SimpleCursorAdapter(this, R.layout.list_item, cursor,
                    new String[]{"Nama", "Npm"},
                    new int[]{R.id.lv_nama, R.id.lv_npm});

            listView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            getData();
        }
    }
}