package com.ryto.volley;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelBarang> arrayModelBarangs;

    // membuat kontruksi recyclerviewadapter
    public RecyclerViewAdapter(Context context, ArrayList<ModelBarang> arrayModelBarangs) {
        this.context = context;
        this.arrayModelBarangs = arrayModelBarangs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // membuat layout inflater
        View view = LayoutInflater.from(context).inflate(R.layout.list_items, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // mendapatkan posisi item
        ModelBarang modelBarang = arrayModelBarangs.get(position);

        // menset data
        holder.kode_barang.setText(modelBarang.getKode_barang());
        holder.nama_barang.setText(modelBarang.getNama_barang());
        holder.harga_barang.setText("Rp. " + modelBarang.getHarga_barang());
    }

    @Override
    public int getItemCount() {
        // mengembalikan data set
        return arrayModelBarangs.size();
    }

    // membuat class viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {

        // implementasi textview
        private TextView kode_barang, nama_barang, harga_barang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // mendeklarasi textview
            kode_barang = itemView.findViewById(R.id.kode_barang);
            nama_barang = itemView.findViewById(R.id.nama_barang);
            harga_barang = itemView.findViewById(R.id.harga_barang);

            // mendeklarasi item ketika diklik
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // mendapatkan posisi pada adapter
                    int position = getAdapterPosition();
                    // mengambil posisi pada arraymodelbarang
                    ModelBarang modelBarang = arrayModelBarangs.get(position);

                    String[] pilihan = {"Lihat", "Ubah", "Hapus"};
                    // menamplkan pilihan alertdialog
                    new AlertDialog.Builder(context)
                            .setTitle("Pilihan")
                            .setItems(pilihan, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) { // 0 sama dengan Lihat
                                        lihatDataBarang(modelBarang);
                                    } else if (which == 1) { // 1 sama dengan Ubah
                                        ubahDataBarang(modelBarang);
                                    } else if (which == 2) { // 2 sama dengan Hapus
                                        hapusDataBarang(position, modelBarang);
                                    }
                                }
                            })
                            .create()
                            .show();
                }
            });
        }

        // method untuk melihat data barang
        private void lihatDataBarang(@NonNull ModelBarang modelBarang) {
            // membuat rangkaiyan text deskripsi
            // fungsi tanda "\n" sama dengan ENTER.
            String deskripsi = "Kode: " + modelBarang.getKode_barang() +
                    "\nNama: " + modelBarang.getNama_barang() +
                    "\nHarga: Rp. " + modelBarang.getHarga_barang();

            // menampilkan deskripsi item ketika dipilih
            new AlertDialog.Builder(context)
                    .setTitle("Deskripsi Barang")
                    .setMessage(deskripsi)
                    .setPositiveButton("Ok!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
        }

        // method untuk mengubah data barang
        private void ubahDataBarang(@NonNull ModelBarang modelBarang) {
            // pindah ke UpdateActivity dan membawa data
            Intent intent = new Intent(context, UbahActivity.class);
            intent.putExtra("ed_id", modelBarang.getId());
            intent.putExtra("ed_kode", modelBarang.getKode_barang());
            intent.putExtra("ed_nama", modelBarang.getNama_barang());
            intent.putExtra("ed_harga", modelBarang.getHarga_barang());
            context.startActivity(intent);
        }

        // method untuk menghapus data barang
        private void hapusDataBarang(int position, @NonNull ModelBarang modelBarang) {
            String url = "https://ryto-147.000webhostapp.com/Ryto/Hapus_barang?id=" + modelBarang.getId();
            // buat StringRequest volley dan jangan lupa requestnya GET "Request.Method.GET"
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // menerapkan ke model class menggunakan GSON
                    // mengkonversi JSON ke java object
                    ResponStatus responStatus = new Gson().fromJson(response, ResponStatus.class);
                    int status_kode = responStatus.getStatus_kode();
                    String status_pesan = responStatus.getStatus_pesan();

                    // validasi status kode
                    if (status_kode == 1) {
                        // menampilkan toast berhasil
                        Toast.makeText(context, status_pesan, Toast.LENGTH_SHORT).show();

                        // menghapus pada tampilan arraylist!
                        notifyItemRemoved(position);
                        arrayModelBarangs.remove(position);
                    } else if (status_kode == 2) {
                        Toast.makeText(context, status_pesan, Toast.LENGTH_SHORT).show();
                    } else if (status_kode == 3) {
                        Toast.makeText(context, status_pesan, Toast.LENGTH_SHORT).show();
                    } else if (status_kode == 4) {
                        Toast.makeText(context, status_pesan, Toast.LENGTH_SHORT).show();
                    } else if (status_kode == 5) {
                        Toast.makeText(context, status_pesan, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Status Kesalahan Tidak Diketahui!", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // jika respon tidak ditemukan maka akan menampilkan berbagai error berikut ini
                    if (error instanceof TimeoutError) {
                        Toast.makeText(context, "Network TimeoutError", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof NoConnectionError) {
                        Toast.makeText(context, "Nerwork NoConnectionError", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof AuthFailureError) {
                        Toast.makeText(context, "Network AuthFailureError", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ServerError) {
                        Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof NetworkError) {
                        Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ParseError) {
                        Toast.makeText(context, "Parse Error", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Status Error Tidak Diketahui!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            // memanggil AppController dan menambahkan dalam antrin
            // text "hapus_barang" anda bisa mengganti inisial yang lain
            AppController.getInstance().addToQueue(request, "hapus_barang");
        }
    }
}
