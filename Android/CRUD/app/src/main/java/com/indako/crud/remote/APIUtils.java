package com.indako.crud.remote;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "https://apibackendci.000webhostapp.com/";

    public static MahasiswaService getMahasiswaService(){
        return RetrofitClient.getClient(API_URL).create(MahasiswaService.class);
    }
}
