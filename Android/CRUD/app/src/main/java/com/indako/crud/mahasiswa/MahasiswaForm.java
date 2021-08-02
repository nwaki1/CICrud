package com.indako.crud.mahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.indako.crud.R;
import com.indako.crud.model.Mahasiswa;
import com.indako.crud.model.ServerMessage;
import com.indako.crud.remote.APIUtils;
import com.indako.crud.remote.MahasiswaService;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MahasiswaForm extends AppCompatActivity {

    MahasiswaService mahasiswaService;
    EditText edtNoInduk;
    EditText edtNama;
    EditText edtTahunMasuk;
    Button btnSave;
    Button btnDel;
    TextView txtUId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_form);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtNoInduk = (EditText) findViewById(R.id.edtNPM);
        edtNama = (EditText) findViewById(R.id.edtUsername);
        edtTahunMasuk = (EditText) findViewById(R.id.edtTahunMasuk);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        mahasiswaService = APIUtils.getMahasiswaService();

        Bundle extras = getIntent().getExtras();
        final String id = extras.getString("id");
        String noInduk = extras.getString("NoInduk");
        String nama = extras.getString("Nama");
        int tahunMasuk = extras.getInt("TahunMasuk");

        edtNoInduk.setText(noInduk);
        edtNama.setText(nama);
        edtTahunMasuk.setText("" + tahunMasuk);

        if(id != null && id.trim().length() > 0 ){
            setTitle("Edit Mahasiswa");
        } else {
            setTitle("Tambah Mahasiswa");
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mahasiswa u = new Mahasiswa();
                u.setNoInduk(edtNoInduk.getText().toString());
                u.setName(edtNama.getText().toString());
                u.setTahunMasuk(Integer.parseInt(edtTahunMasuk.getText().toString()));

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

                Intent intent = new Intent(MahasiswaForm.this, MahasiswaActivity.class);
                startActivity(intent);
            }
        });
    }

    public void add(Mahasiswa u){
        final RequestBody NPM = RequestBody.create(MediaType.parse("text/plain"), u.getNoInduk());
        final RequestBody Nama = RequestBody.create(MediaType.parse("text/plain"), u.getName());
        final RequestBody TahunMasuk = RequestBody.create(MediaType.parse("text/plain"), Integer.toString(u.getTahunMasuk()));

        Call<ServerMessage> call = mahasiswaService.addMahasiswa(NPM, Nama, TahunMasuk);
        call.enqueue(new Callback<ServerMessage>() {
            @Override
            public void onResponse(Call<ServerMessage> call, Response<ServerMessage> response) {
                if(response.code() == 200){
                    Toast.makeText(MahasiswaForm.this, "User created successfully!", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(MahasiswaForm.this, MahasiswaActivity.class));
                } else if(response.code() == 500){
                    Toast.makeText(MahasiswaForm.this, "Gagal Menambahkan, Internal Server Error, Coba Lagi!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerMessage> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void update(int id, Mahasiswa u){
        final RequestBody Id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id));
        final RequestBody NPM = RequestBody.create(MediaType.parse("text/plain"), u.getNoInduk());
        final RequestBody Nama = RequestBody.create(MediaType.parse("text/plain"), u.getName());
        final RequestBody TahunMasuk = RequestBody.create(MediaType.parse("text/plain"), Integer.toString(u.getTahunMasuk()));

        Call<ServerMessage> call = mahasiswaService.updateMahasiswa(Id, NPM, Nama, TahunMasuk);
        call.enqueue(new Callback<ServerMessage>() {
            @Override
            public void onResponse(Call<ServerMessage> call, Response<ServerMessage> response) {
                if(response.code() == 200){
                    Toast.makeText(MahasiswaForm.this, "User updated successfully!", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else if(response.code() == 500){
                    Toast.makeText(MahasiswaForm.this, "Gagal Mengedit, Internal Server Error, Coba Lagi!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerMessage> call, Throwable t) {
                Toast.makeText(MahasiswaForm.this, "Gagal Mengedit, Coba Lagi!", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void delete(int id){
        final RequestBody Id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id));

        Call<ServerMessage> call = mahasiswaService.deleteMahasiswa(Id);
        call.enqueue(new Callback<ServerMessage>() {
            @Override
            public void onResponse(Call<ServerMessage> call, Response<ServerMessage> response) {
                if(response.code() == 200){
                    Toast.makeText(MahasiswaForm.this, "User deleted successfully!", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else if(response.code() == 500){
                    Toast.makeText(MahasiswaForm.this, "Gagal Menghapus, Internal Server Error, Coba Lagi!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerMessage> call, Throwable t) {
                Toast.makeText(MahasiswaForm.this, "Hapus Gagal, Coba Lagi!", Toast.LENGTH_SHORT).show();
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