package com.indako.crud.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MataKuliah {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("KodeMatkul")
    @Expose
    private String KodeMatkul;

    @SerializedName("Nama")
    @Expose
    private String Nama;

    public MataKuliah(){

    }

    public MataKuliah(int id, String KodeMatkul, String Nama) {
        this.id = id;
        this.KodeMatkul = KodeMatkul;
        this.Nama = Nama;
    }

    public String getKodeMatkul() {
        return KodeMatkul;
    }

    public void setKodeMatkul(String kodeMatkul) {
        KodeMatkul = kodeMatkul;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
