package com.shahniz.android.shahniz_1202150279_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //mendeklarasikan variabel
    database db;
    RecyclerView recyclerview;
    adapter adapter;
    ArrayList<data> listitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //memberikan akses recyclerview yang ada di layout
        recyclerview = findViewById(R.id.rview);
        //membuat arraylist baru
        listitem = new ArrayList<>();
        //membuat database baru
        db = new database(this);
        //memanggil method getAllItem
        db.getAllItem(listitem);
        //inisialisasi shared preferences
        SharedPreferences shared = this.getApplicationContext().getSharedPreferences("shared", 0);
        int color = shared.getInt("background", R.color.white);
        //membuat adapter baru
        adapter = new adapter(this, listitem, color);
        //menghindari perubahan yang tidak diperlukan saat menambahkan/menghapus item pada recycler view
        recyclerview.setHasFixedSize(true);
        //menentukan layoutnya linear
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        //inisiasi adapter untuk recycler view
        recyclerview.setAdapter(adapter);
        //menjalankan method swipe
        swipe();
    }

    //membuat method untuk menghapus item pada todolist
    public void swipe() {
        //membuat touch helper baru untuk recycler view
        ItemTouchHelper.SimpleCallback simple = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int posisi = viewHolder.getAdapterPosition();
                data now = adapter.getItem(posisi);
                //apabila todolist digeser ke kanan atau kiri maka data akan terhapus
                if (direction==ItemTouchHelper.LEFT||direction==ItemTouchHelper.RIGHT){
                    if (db.hapusdata(now.getTodo())){
                        adapter.removeitem(posisi);
                    }
                }
            }
        };
        //menentukan itemtouchhelper untuk recycler view
        ItemTouchHelper helper =  new ItemTouchHelper(simple);
        helper.attachToRecyclerView(recyclerview);
    }
    //saat menu pada activity dibuat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //method ketika item dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //mendapatkan id dari item
        int id = item.getItemId();
        //jika id yang dipilih setting
        if (id==R.id.setting){
            startActivity(new Intent(MainActivity.this, setting.class)); //membuat intent baru dari awal ke setting
            //mengakhiri activity setelah intent dijalankan
            finish();
        }
        return true;
    }
    //method ketika tombol fab diklik
    public void tambah(View view) {
        //membuat intent baru
        startActivity(new Intent(MainActivity.this, todo.class));
        //mengakhiri activity setelah intent dijalankan
        finish();
    }
}
