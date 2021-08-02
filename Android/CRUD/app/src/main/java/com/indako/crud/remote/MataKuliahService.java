package com.indako.crud.remote;

import com.indako.crud.model.MataKuliah;
import com.indako.crud.model.ServerMessage;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface MataKuliahService {
    @GET("MataKuliahApi")
    Call<List<MataKuliah>> getMataKuliah();

    @Multipart
    @POST("MataKuliahApi/insert")
    Call<ServerMessage> addMataKuliah(@Part("KodeMatkul") RequestBody kodeMatkul,
                                     @Part("Nama") RequestBody nama
    );

    @Multipart
    @POST("MataKuliahApi/update")
    Call<ServerMessage> updateMataKuliah(@Part("id") RequestBody id,
                                        @Part("KodeMatkul") RequestBody kodeMatkul,
                                        @Part("Nama") RequestBody nama);

    @Multipart
    @POST("MataKuliahApi/delete")
    Call<ServerMessage> deleteMataKuliah(@Part("id") RequestBody id);
}
