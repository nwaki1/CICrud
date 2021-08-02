package com.indako.crud.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dosen {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("NoInduk")
    @Expose
    private String NoInduk;

    @SerializedName("Nama")
    @Expose
    private String Nama;

    public Dosen(){

    }

    public Dosen(int id, String NoInduk, String Nama) {
        this.id = id;
        this.NoInduk = NoInduk;
        this.Nama = Nama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getNoInduk() {
        return NoInduk;
    }

    public void setNoInduk(String noInduk) {
        NoInduk = noInduk;
    }
}
