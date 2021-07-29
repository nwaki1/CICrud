package com.indako.crud.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mahasiswa {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("NoInduk")
    @Expose
    private String NoInduk;

    @SerializedName("Nama")
    @Expose
    private String Nama;

    @SerializedName("TahunMasuk")
    @Expose
    private int TahunMasuk;

    public Mahasiswa(){

    }

    public Mahasiswa(int id, String NoInduk, String Nama, int TahunMasuk) {
        this.id = id;
        this.NoInduk = NoInduk;
        this.Nama = Nama;
        this.TahunMasuk = TahunMasuk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Nama;
    }

    public void setName(String Nama) {
        this.Nama = Nama;
    }

    public int getTahunMasuk() {
        return TahunMasuk;
    }

    public void setTahunMasuk(int tahunMasuk) {
        TahunMasuk = tahunMasuk;
    }

    public String getNoInduk() {
        return NoInduk;
    }

    public void setNoInduk(String noInduk) {
        NoInduk = noInduk;
    }
}
