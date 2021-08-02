package com.indako.crud.remote;

import com.indako.crud.model.MataKuliah;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "https://apibackendci.000webhostapp.com/";

    public static MahasiswaService getMahasiswaService(){
        return RetrofitClient.getClient(API_URL).create(MahasiswaService.class);
    }

    public static DosenService getDosenService(){
        return RetrofitClient.getClient(API_URL).create(DosenService.class);
    }

    public static MataKuliahService getMataKuliahService(){
        return RetrofitClient.getClient(API_URL).create(MataKuliahService.class);
    }
}
