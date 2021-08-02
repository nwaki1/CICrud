package com.indako.crud.mataKuliah;

import androidx.appcompat.app.AppCompatActivity;

import com.indako.crud.R;
import com.indako.crud.mahasiswa.MahasiswaActivity;
import com.indako.crud.mahasiswa.MahasiswaForm;
import com.indako.crud.model.Mahasiswa;
import com.indako.crud.model.MataKuliah;
import com.indako.crud.model.ServerMessage;
import com.indako.crud.remote.APIUtils;
import com.indako.crud.remote.MahasiswaService;
import com.indako.crud.remote.MataKuliahService;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MataKuliahForm extends AppCompatActivity {

    MataKuliahService mataKuliahService;
    EditText edtNoInduk;
    EditText edtNama;
    Button btnSave;
    Button btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mata_kuliah_form);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtNoInduk = (EditText) findViewById(R.id.edtKodeMatkul);
        edtNama = (EditText) findViewById(R.id.edtUsername);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        mataKuliahService = APIUtils.getMataKuliahService();

        Bundle extras = getIntent().getExtras();
        final String id = extras.getString("id");
        String kodeMatkul = extras.getString("KodeMatkul");
        String nama = extras.getString("Nama");

        edtNoInduk.setText(kodeMatkul);
        edtNama.setText(nama);

        if(id != null && id.trim().length() > 0 ){
            setTitle("Edit Mata Kuliah");
        } else {
            setTitle("Tambah Mata Kuliah");
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MataKuliah u = new MataKuliah();
                u.setKodeMatkul(edtNoInduk.getText().toString());
                u.setNama(edtNama.getText().toString());

                if(id != null && id.trim().length() > 0){
                    //update user
                    update(Integer.parseInt(id), u);
                } else {
                    //add user
                    add(u);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(Integer.parseInt(id));

                Intent intent = new Intent(MataKuliahForm.this, MataKuliahActivity.class);
                startActivity(intent);
            }
        });
    }

    public void add(MataKuliah u){
        final RequestBody KodeMatkul = RequestBody.create(MediaType.parse("text/plain"), u.getKodeMatkul());
        final RequestBody Nama = RequestBody.create(MediaType.parse("text/plain"), u.getNama());

        Call<ServerMessage> call = mataKuliahService.addMataKuliah(KodeMatkul, Nama);
        call.enqueue(new Callback<ServerMessage>() {
            @Override
            public void onResponse(Call<ServerMessage> call, Response<ServerMessage> response) {
                if(response.code() == 200){
                    Toast.makeText(MataKuliahForm.this, "User created successfully!", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(MataKuliahForm.this, MataKuliahActivity.class));
                } else if(response.code() == 500){
                    Toast.makeText(MataKuliahForm.this, "Gagal Menambahkan, Internal Server Error, Coba Lagi!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerMessage> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void update(int id, MataKuliah u){
        final RequestBody Id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id));
        final RequestBody KodeMatkul = RequestBody.create(MediaType.parse("text/plain"), u.getKodeMatkul());
        final RequestBody Nama = RequestBody.create(MediaType.parse("text/plain"), u.getNama());

        Call<ServerMessage> call = mataKuliahService.updateMataKuliah(Id, KodeMatkul, Nama);
        call.enqueue(new Callback<ServerMessage>() {
            @Override
            public void onResponse(Call<ServerMessage> call, Response<ServerMessage> response) {
                if(response.code() == 200){
                    Toast.makeText(MataKuliahForm.this, "User updated successfully!", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else if(response.code() == 500){
                    Toast.makeText(MataKuliahForm.this, "Gagal Mengedit, Internal Server Error, Coba Lagi!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerMessage> call, Throwable t) {
                Toast.makeText(MataKuliahForm.this, "Gagal Mengedit, Coba Lagi!", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void delete(int id){
        final RequestBody Id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id));

        Call<ServerMessage> call = mataKuliahService.deleteMataKuliah(Id);
        call.enqueue(new Callback<ServerMessage>() {
            @Override
            public void onResponse(Call<ServerMessage> call, Response<ServerMessage> response) {
                if(response.code() == 200){
                    Toast.makeText(MataKuliahForm.this, "User deleted successfully!", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else if(response.code() == 500){
                    Toast.makeText(MataKuliahForm.this, "Gagal Menghapus, Internal Server Error, Coba Lagi!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerMessage> call, Throwable t) {
                Toast.makeText(MataKuliahForm.this, "Hapus Gagal, Coba Lagi!", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}