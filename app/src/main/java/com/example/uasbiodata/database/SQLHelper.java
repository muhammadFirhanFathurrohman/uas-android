package com.example.uasbiodata.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.appcompat.widget.ThemedSpinnerAdapter;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "biodata.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE = "tabel_biodata";

    public static final String row_foto = "Foto";
    public static final String row_npm = "Npm";
    public static final String row_nama = "Nama";
    public static final String row_tempatLahir = "TempatLahir";
    public static final String row_tglLahir = "Tanggal";
    public static final String row_jk = "Jk";
    public static final String row_alamat = "Alamat";
    public static final String row_jurusan = "Jurusan";

    public SQLHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE +
                "(_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                row_foto + " TEXT NOT NULL, "+
                row_npm + " TEXT NOT NULL, "+
                row_nama + " TEXT NOT NULL, "+
                row_tempatLahir + " TEXT NOT NULL, "+
                row_tglLahir + " TEXT NOT NULL, "+
                row_jk + " TEXT NOT NULL, "+
                row_alamat + " TEXT NOT NULL, "+
                row_jurusan + " TEXT NOT NULL);";
        Log.d("Data", "oncreate: " + sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
