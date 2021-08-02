package com.indako.crud.dosen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.indako.crud.R;
import com.indako.crud.mahasiswa.MahasiswaActivity;
import com.indako.crud.mahasiswa.MahasiswaForm;
import com.indako.crud.model.Dosen;
import com.indako.crud.model.ServerMessage;
import com.indako.crud.remote.APIUtils;
import com.indako.crud.remote.DosenService;

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

public class DosenForm extends AppCompatActivity {

    DosenService dosenService;
    EditText edtNoInduk;
    EditText edtNama;
    Button btnSave;
    Button btnDel;
    TextView txtUId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_form);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtNoInduk = (EditText) findViewById(R.id.edtNPM);
        edtNama = (EditText) findViewById(R.id.edtUsername);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        dosenService = APIUtils.getDosenService();

        Bundle extras = getIntent().getExtras();
        final String id = extras.getString("id");
        String noInduk = extras.getString("NoInduk");
        String nama = extras.getString("Nama");

        edtNoInduk.setText(noInduk);
        edtNama.setText(nama);

        if(id != null && id.trim().length() > 0 ){
            setTitle("Edit Dosen");
        } else {
            setTitle("Tambah Dosen");
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dosen u = new Dosen();
                u.setNoInduk(edtNoInduk.getText().toString());
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

                Intent intent = new Intent(DosenForm.this, DosenActivity.class);
                startActivity(intent);
            }
        });

    }

    public void add(Dosen u){
        final RequestBody NoInduk = RequestBody.create(MediaType.parse("text/plain"), u.getNoInduk());
        final RequestBody Nama = RequestBody.create(MediaType.parse("text/plain"), u.getNama());

        Call<ServerMessage> call = dosenService.addDosen(NoInduk, Nama);
        call.enqueue(new Callback<ServerMessage>() {
            @Override
            public void onResponse(Call<ServerMessage> call, Response<ServerMessage> response) {
                if(response.code() == 200){
                    Toast.makeText(DosenForm.this, "User created successfully!", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(DosenForm.this, DosenActivity.class));
                } else if(response.code() == 500){
                    Toast.makeText(DosenForm.this, "Gagal Menambahkan, Internal Server Error, Coba Lagi!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerMessage> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void update(int id, Dosen u){
        final RequestBody Id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id));
        final RequestBody NPM = RequestBody.create(MediaType.parse("text/plain"), u.getNoInduk());
        final RequestBody Nama = RequestBody.create(MediaType.parse("text/plain"), u.getNama());

        Call<ServerMessage> call = dosenService.updateDosen(Id, NPM, Nama);
        call.enqueue(new Callback<ServerMessage>() {
            @Override
            public void onResponse(Call<ServerMessage> call, Response<ServerMessage> response) {
                if(response.code() == 200){
                    Toast.makeText(DosenForm.this, "User updated successfully!", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else if(response.code() == 500){
                    Toast.makeText(DosenForm.this, "Gagal Mengedit, Internal Server Error, Coba Lagi!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerMessage> call, Throwable t) {
                Toast.makeText(DosenForm.this, "Gagal Mengedit, Coba Lagi!", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void delete(int id){
        final RequestBody Id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id));

        Call<ServerMessage> call = dosenService.deleteDosen(Id);
        call.enqueue(new Callback<ServerMessage>() {
            @Override
            public void onResponse(Call<ServerMessage> call, Response<ServerMessage> response) {
                if(response.code() == 200){
                    Toast.makeText(DosenForm.this, "User deleted successfully!", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else if(response.code() == 500){
                    Toast.makeText(DosenForm.this, "Gagal Menghapus, Internal Server Error, Coba Lagi!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerMessage> call, Throwable t) {
                Toast.makeText(DosenForm.this, "Hapus Gagal, Coba Lagi!", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}