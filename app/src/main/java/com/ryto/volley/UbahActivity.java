package com.ryto.volley;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class UbahActivity extends AppCompatActivity {

    // implementasi
    private EditText kode_barang, nama_barang, harga_barang;
    // untuk menerima Data dari MainActivity
    private String ed_kode, ed_nama;
    private int ed_id, ed_harga;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_update);

        // deklarasi
        kode_barang = findViewById(R.id.ed_kode_barang);
        nama_barang = findViewById(R.id.ed_nama_barang);
        harga_barang = findViewById(R.id.ed_harga_barang);
        Button btn_simpan = findViewById(R.id.simpan_tambah_ubah);

        // mengubah text pada buttom
        btn_simpan.setText("Update");

        // menerima data dari MainActivity menggunakana "Bundle"
        Bundle intent = getIntent().getExtras();
        if (intent != null) {
            ed_id = intent.getInt("ed_id");
            ed_kode = intent.getString("ed_kode");
            ed_nama = intent.getString("ed_nama");
            ed_harga = intent.getInt("ed_harga");
        }

        // lalu "Bundle" ini, akan di set ke edittext
        kode_barang.setText(ed_kode);
        nama_barang.setText(ed_nama);
        harga_barang.setText(String.valueOf(ed_harga));

        // memberi action pada floating action buttom
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mengambil text dalam edittext
                String kode = kode_barang.getText().toString();
                String nama = nama_barang.getText().toString();
                String harga = harga_barang.getText().toString();

                // validasi kode, nama dan harga tidak boleh kosong
                if (kode.isEmpty()) { // kode_barang tidak lebih dari 6 digit
                    Toast.makeText(UbahActivity.this, "Kode Masih Kosong!", Toast.LENGTH_SHORT).show();
                } else if (nama.isEmpty()) {
                    Toast.makeText(UbahActivity.this, "Nama Masih Kosong!", Toast.LENGTH_SHORT).show();
                } else if (harga.isEmpty()) { // harga barang tidak boleh dari 9 digit
                    Toast.makeText(UbahActivity.this, "Harga Masih Kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    // mengupdate data
                    updateData(ed_id, kode, nama, harga);
                }
            }
        });
    }

    private void updateData(int id, String kode, String nama, String harga) {
        String url = "https://subkode.000webhostapp.com/volley_db/update_barang";
        // buat StringRequest volley dan jangan lupa requestnya POST "Request.Method.POST"
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // menerapkan ke model class menggunakan GSON
                // mengkonversi JSON ke java object
                ResponStatus responStatus = new Gson().fromJson(response, ResponStatus.class);
                int status_kode = responStatus.getStatus_kode();
                String status_pesan = responStatus.getStatus_pesan();

                // jika respon status kode yg dihasilkan 1 maka berhasil
                if (status_kode == 1) {
                    Toast.makeText(UbahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                    MainActivity.mInstance.MuatData(); // memanggil MainActivity untuk memproses method MemuatData()
                    finish(); // keluar
                } else if (status_kode == 2) {
                    Toast.makeText(UbahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                } else if (status_kode == 3) {
                    Toast.makeText(UbahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                } else if (status_kode == 4) {
                    Toast.makeText(UbahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                } else if (status_kode == 5) {
                    Toast.makeText(UbahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                } else if (status_kode == 6) {
                    Toast.makeText(UbahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                } else if (status_kode == 7) {
                    Toast.makeText(UbahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                } else if (status_kode == 8) {
                    Toast.makeText(UbahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                } else if (status_kode == 9) {
                    Toast.makeText(UbahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                } else if (status_kode == 10) {
                    Toast.makeText(UbahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UbahActivity.this, "Status Kesalahan Tidak Diketahui!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // jika respon tidak ditemukan maka akan menampilkan berbagai error berikut ini
                if (error instanceof TimeoutError) {
                    Toast.makeText(UbahActivity.this, "Network TimeoutError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(UbahActivity.this, "Nerwork NoConnectionError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(UbahActivity.this, "Network AuthFailureError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(UbahActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(UbahActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(UbahActivity.this, "Parse Error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UbahActivity.this, "Status Error Tidak Diketahui!", Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                // set ke params
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id", String.valueOf(id));
                hashMap.put("kode", kode);
                hashMap.put("nama", nama);
                hashMap.put("harga", harga);

                return hashMap;
            }
        };

        // memanggil AppController dan menambahkan dalam antrin
        // text "ubah_barang" anda bisa mengganti inisial yang lain
        AppController.getInstance().addToQueue(request, "ubah_barang");
    }
}
