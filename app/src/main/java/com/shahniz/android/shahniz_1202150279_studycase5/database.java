package com.shahniz.android.shahniz_1202150279_studycase5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Shahniz on 25/03/2018.
 */

public class database extends SQLiteOpenHelper{
    //deklarasi variabel yang digunakan
    Context context;
    SQLiteDatabase datab;

    public static final String nama_db = "TODO.db";
    public static final String nama_tabel = "Todo";
    public static final String kolom1 = "nama";
    public static final String kolom2 = "deskripsi";
    public static final String kolom3 = "prioritas";

    //konstruktor
    public database(Context context) {
        super(context, nama_db, null, 1);
        this.context = context;
        this.datab = this.getWritableDatabase();
        datab.execSQL("create table if not exists "+nama_tabel+" (nama varchar(50) primary key, deskripsi varchar(50), prioritas varchar(50)) ");
    }
    //method saat database dibuat
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists "+nama_tabel+" (nama varchar(50) primary key, deskripsi varchar(50), prioritas varchar(50)) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+nama_tabel);
        onCreate(sqLiteDatabase);
    }
    public boolean inputdata (data item){
        //membuat content values baru
        ContentValues contentvalues = new ContentValues();
        //menyamakan kolom dan nilainya
        contentvalues.put(kolom1, item.getTodo());
        contentvalues.put(kolom2, item.getDesc());
        contentvalues.put(kolom3, item.getPrior());
        long hasil = datab.insert(nama_tabel, null, contentvalues);
        if (hasil==-1){
            return false;
        }else {
            return true;
        }
    }
    //method untuk menghapus data pada database
    public boolean hapusdata (String nama){
        return datab.delete(nama_tabel, kolom1+"=\""+nama+"\"", null)>0;
    }
    //method untuk mengakses dan membaca data dari database
    public void getAllItem (ArrayList<data> list){
        Cursor cursor = this.getReadableDatabase().rawQuery("select nama, deskripsi, prioritas from "+nama_tabel, null);
        while (cursor.moveToNext()){
            list.add(new data(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
    }
    public void clearTable() {
        datab.execSQL("delete from "+nama_tabel);
    }
}
