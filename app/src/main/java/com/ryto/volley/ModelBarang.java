package com.ryto.volley;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelBarang {

    @SerializedName("id") //samakan dengan key/id yang akan kita ambil dari API
    @Expose
    private int id;
    @SerializedName("kode_barang") //samakan dengan key/id yang akan kita ambil dari API
    @Expose
    private String kode_barang;
    @SerializedName("nama_barang") //samakan dengan key/id yang akan kita ambil dari API
    @Expose
    private String nama_barang;
    @SerializedName("harga_barang") //samakan dengan key/id yang akan kita ambil dari API
    @Expose
    private int harga_barang;

    public ModelBarang(int id, String kode_barang, String nama_barang, int harga_barang) {
        this.id = id;
        this.kode_barang = kode_barang;
        this.nama_barang = nama_barang;
        this.harga_barang = harga_barang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKode_barang() {
        return kode_barang;
    }

    public void setKode_barang(String kode_barang) {
        this.kode_barang = kode_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public int getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(int harga_barang) {
        this.harga_barang = harga_barang;
    }
}
