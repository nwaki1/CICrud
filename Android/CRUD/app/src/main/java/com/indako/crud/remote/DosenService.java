package com.indako.crud.remote;

import com.indako.crud.model.Dosen;
import com.indako.crud.model.Mahasiswa;
import com.indako.crud.model.ServerMessage;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DosenService {
    @GET("DosenApi")
    Call<List<Dosen>> getDosen();

    @Multipart
    @POST("DosenApi/insert")
    Call<ServerMessage> addDosen(@Part("NoInduk") RequestBody noInduk,
                                     @Part("Nama") RequestBody nama);

    @Multipart
    @POST("DosenApi/update")
    Call<ServerMessage> updateDosen(@Part("id") RequestBody id,
                                        @Part("NoInduk") RequestBody noInduk,
                                        @Part("Nama") RequestBody nama);

    @Multipart
    @POST("DosenApi/delete")
    Call<ServerMessage> deleteDosen(@Part("id") RequestBody id);
}
