package com.indako.crud.remote;
import com.indako.crud.model.Mahasiswa;
import com.indako.crud.model.ServerMessage;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface MahasiswaService {

    @GET("MahasiswaApi")
    Call<List<Mahasiswa>> getMahasiswa();

    @Multipart
    @POST("MahasiswaApi/insert")
    Call<ServerMessage> addMahasiswa(@Part("NoInduk") RequestBody noInduk,
                                     @Part("Nama") RequestBody nama,
                                     @Part("TahunMasuk") RequestBody tahunMasuk
                                 );

    @Multipart
    @POST("MahasiswaApi/update")
    Call<ServerMessage> updateMahasiswa(@Part("id") RequestBody id,
                                    @Part("NoInduk") RequestBody noInduk,
                                    @Part("Nama") RequestBody nama,
                                    @Part("TahunMasuk") RequestBody tahunMasuk);

    @Multipart
    @POST("MahasiswaApi/delete")
    Call<ServerMessage> deleteMahasiswa(@Part("id") RequestBody id);
}
