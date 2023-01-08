package com.example.uasbiodata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] daftar;
    ListView listView;
    protected Cursor cursor;
    SQLHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbhelper = new SQLHelper(this);
        refreshlist();
    }

    public void refreshlist(){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tabel_biodata", null);
        daftar  = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int i=0; i<cursor.getCount(); i++){
            cursor.moveToPosition(i);
            daftar[i]=i+1+" | "+cursor.getString(0).toString()+" | "+cursor.getString(2);
        }


        listView = (ListView) findViewById(R.id.listBiodata);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);
        ((ArrayAdapter)listView.getAdapter()).notifyDataSetInvalidated();
    }

    public void create (View view) {
        Intent i = new Intent(MainActivity.this, Create.class);
        startActivity(i);
        finish();
    }

    public void keluar(View view){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Apakah anda ingin keluar dari aplikasi ?").setCancelable(false).
                setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.finish();
                    }
                }).
                setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();
    }

    public void petunjuk (View view) {
        Intent i = new Intent(MainActivity.this, Petunjuk.class);
        startActivity(i);
        finish();
    }
}