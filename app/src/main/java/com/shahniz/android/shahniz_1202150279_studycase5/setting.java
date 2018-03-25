package com.shahniz.android.shahniz_1202150279_studycase5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class setting extends AppCompatActivity {
    //mendeklarasikan variabel
    int warna;
    TextView color;
    AlertDialog.Builder alert;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //membuat alert dialog baru namanya alert
        alert = new AlertDialog.Builder(this);
        //inisialisasi shared preferences
        SharedPreferences shared = getApplicationContext().getSharedPreferences("shared", 0);
        edit = shared.edit();
        //mengakses textview pada layout
        color = findViewById(R.id.color);
        //set warna dengan warna yang sudah ditentukan
        warna = shared.getInt("background", R.color.white);

        color.setText(getWarna(warna));
    }

    //method saat pilihan pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() ==android.R.id.home){
            this.onBackPressed(); //menjalankan method on back pressed
        }
        return true;
    }
    //saat tombol back diklik
    @Override
    public void onBackPressed() {
        //membuat intent baru
        startActivity(new Intent(setting.this, MainActivity.class));
        //mengakhiri activity
        finish();
    }
    //mendapatkan string warna yang digunakan untuk mengubah warna
    public String getWarna(int i){
        if (i==R.color.pink){
            return "Pink";
        }else if (i==R.color.brown){
            return "Brown";
        }else if (i==R.color.teal){
            return "Teal";
        }else{
            return "White";
        }
    }
    //mendapatkan id dari warna yang digunakan
    public int getIntColor(int i){
        if (i==R.color.pink){
            return R.id.btn_pink;
        }else if (i==R.color.brown){
            return R.id.btn_brown;
        }else if (i==R.color.teal){
            return R.id.btn_teal;
        }else {
            return R.id.btn_white;
        }
    }

    public void klik(View view) {
        //mengubah title pada alertnya
        alert.setTitle("Shape Color");
        //membuat view baru
        View v = getLayoutInflater().inflate(R.layout.settingwarna,null);
        alert.setView(v); //menampilkan view yang telah dibuat

        //mengakses radio group pada layout
        final RadioGroup radiogroup = v.findViewById(R.id.radio);
        radiogroup.check(getIntColor(warna));

        //saat menekan OK pada alert dialog
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int cek = radiogroup.getCheckedRadioButtonId();
                //mendapatkan id radio button yang dipilih
                switch (cek){
                    case R.id.btn_pink:
                        warna = R.color.pink;
                        break;
                    case R.id.btn_brown:
                        warna = R.color.brown;
                        break;
                    case R.id.btn_teal:
                        warna = R.color.teal;
                        break;
                    case R.id.btn_white:
                        warna = R.color.white;
                        break;
                }
                //set warnanya menjadi id yang dipilih
                color.setText(getWarna(warna));
                //menaruh shared preferences
                edit.putInt("background", warna);
                //commit shared preferences
                edit.commit();
            }
        });
        //saat menekan cancel di alert dialog
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });alert.create().show(); //membuat dan menampilkan alert
    }
}
