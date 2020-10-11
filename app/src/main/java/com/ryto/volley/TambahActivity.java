package com.ryto.volley;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TambahActivity extends AppCompatActivity {

    // implementasi
    private EditText kode_barang, nama_barang, harga_barang;
    private Button btn_simpan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_update);

        // deklarasi
        kode_barang = findViewById(R.id.ed_kode_barang);
        nama_barang = findViewById(R.id.ed_nama_barang);
        harga_barang = findViewById(R.id.ed_harga_barang);
        btn_simpan = findViewById(R.id.simpan_tambah_ubah);

        // memberi action pada floating action buttom
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mengambil text dalam edittext
                String kode = kode_barang.getText().toString().toUpperCase(); // agar mendapatkan text capslock
                String nama = nama_barang.getText().toString();
                String harga = harga_barang.getText().toString();

                // validasi kode, nama dan harga tidak boleh kosong
                if (kode.isEmpty()) {
                    Toast.makeText(TambahActivity.this, "Kode Masih Kososng!", Toast.LENGTH_SHORT).show();
                } else if (nama.isEmpty()) {
                    Toast.makeText(TambahActivity.this, "Nama Masih Kosong!", Toast.LENGTH_SHORT).show();
                } else if (harga.isEmpty()) {
                    Toast.makeText(TambahActivity.this, "Harga Masih Kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    // menyimpan data ke database
                    try {
                        simpanData(kode, nama, harga);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void simpanData(String kode, String nama, String harga) throws UnsupportedEncodingException {
        // karena text ini kita masukkan ke-link maka kode dan nama kita konversikan ke bentuk "utf-8"
        // contoh : "Samsung Galaxy M2" ==> dikonversi menjadi "Samsung+Galaxy+M2"
        //
        // dan ketika di simpan ke database text tidak akan berupa text konversi melaikan text aslinya.
        String konv_kode = URLEncoder.encode(kode, "utf-8");
        String konv_nama = URLEncoder.encode(nama, "utf-8");

        String url = "https://ryto-147.000webhostapp.com/Ryto/Tambah_Barang?kode=" + konv_kode + "&nama=" + konv_nama + "&harga=" + harga;
        // buat StringRequest volley dan jangan lupa requestnya GET "Request.Method.GET"
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // menerapkan ke model class menggunakan GSON
                // mengkonversi JSON ke java object
                ResponStatus responStatus = new Gson().fromJson(response, ResponStatus.class);
                int status_kode = responStatus.getStatus_kode();
                String status_pesan = responStatus.getStatus_pesan();

                // jika status kode sama dengan 1 maka berhasil
                if (status_kode == 1) {
                    Toast.makeText(TambahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                    MainActivity.mInstance.MuatData(); // memanggil MainActivity untuk memproses method MemuatData()
                    finish(); // keluar
                } else if (status_kode == 2) {
                    Toast.makeText(TambahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                } else if (status_kode == 3) {
                    Toast.makeText(TambahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                } else if (status_kode == 4) {
                    Toast.makeText(TambahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                } else if (status_kode == 5) {
                    Toast.makeText(TambahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                } else if (status_kode == 6) {
                    Toast.makeText(TambahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                } else if (status_kode == 7) {
                    Toast.makeText(TambahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                } else if (status_kode == 8) {
                    Toast.makeText(TambahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                } else if (status_kode == 9) {
                    Toast.makeText(TambahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                } else if (status_kode == 10) {
                    Toast.makeText(TambahActivity.this, status_pesan, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TambahActivity.this, "Status Kesalahan Tidak Diketahui!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // jika respon tidak ditemukan maka akan menampilkan berbagai error berikut ini
                if (error instanceof TimeoutError) {
                    Toast.makeText(TambahActivity.this, "Network TimeoutError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(TambahActivity.this, "Nerwork NoConnectionError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(TambahActivity.this, "Network AuthFailureError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(TambahActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(TambahActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(TambahActivity.this, "Parse Error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TambahActivity.this, "Status Error Tidak Diketahui!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // memanggil AppController dan menambahkan dalam antrin
        // text "tambah_barang" anda bisa mengganti inisial yang lain
        AppController.getInstance().addToQueue(request, "tambah_barang");
    }
}
