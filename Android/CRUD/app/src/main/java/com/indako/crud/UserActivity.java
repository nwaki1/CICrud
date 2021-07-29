package com.indako.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.indako.crud.model.Mahasiswa;
import com.indako.crud.model.ServerMessage;
import com.indako.crud.remote.APIUtils;
import com.indako.crud.remote.MahasiswaService;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    MahasiswaService mahasiswaService;
    EditText edtUId;
    EditText edtUsername;
    Button btnSave;
    Button btnDel;
    TextView txtUId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        setTitle("Users");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = (TextView) findViewById(R.id.txtUId);
        edtUId = (EditText) findViewById(R.id.edtUId);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        mahasiswaService = APIUtils.getMahasiswaService();

        Bundle extras = getIntent().getExtras();
        final String userId = extras.getString("user_id");
        String userName = extras.getString("user_name");

        edtUId.setText(userId);
        edtUsername.setText(userName);

        if(userId != null && userId.trim().length() > 0 ){
            edtUId.setFocusable(false);
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mahasiswa u = new Mahasiswa();
                u.setName(edtUsername.getText().toString());
                if(userId != null && userId.trim().length() > 0){
                    //update user
                    updateUser(Integer.parseInt(userId), u);
                } else {
                    //add user
                    addUser(u);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser(Integer.parseInt(userId));

                Intent intent = new Intent(UserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addUser(Mahasiswa u){
        final RequestBody NPM = RequestBody.create(MediaType.parse("text/plain"), "123");
        final RequestBody Nama = RequestBody.create(MediaType.parse("text/plain"), u.getName());
        final RequestBody TahunMasuk = RequestBody.create(MediaType.parse("text/plain"), "1997");

        Call<ServerMessage> call = mahasiswaService.addMahasiswa(NPM, Nama, TahunMasuk);
        call.enqueue(new Callback<ServerMessage>() {
            @Override
            public void onResponse(Call<ServerMessage> call, Response<ServerMessage> response) {
                if(response.isSuccessful()){
                    Toast.makeText(UserActivity.this, "User created successfully!", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(UserActivity.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ServerMessage> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateUser(int id, Mahasiswa u){
        final RequestBody Id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id));
        final RequestBody NPM = RequestBody.create(MediaType.parse("text/plain"), "123");
        final RequestBody Nama = RequestBody.create(MediaType.parse("text/plain"), u.getName());
        final RequestBody TahunMasuk = RequestBody.create(MediaType.parse("text/plain"), "1997");

        Call<ServerMessage> call = mahasiswaService.updateMahasiswa(Id, NPM, Nama, TahunMasuk);
        call.enqueue(new Callback<ServerMessage>() {
            @Override
            public void onResponse(Call<ServerMessage> call, Response<ServerMessage> response) {
                if(response.isSuccessful()){
                    Toast.makeText(UserActivity.this, "User updated successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UserActivity.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ServerMessage> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteUser(int id){
        final RequestBody Id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id));

        Call<ServerMessage> call = mahasiswaService.deleteMahasiswa(Id);
        call.enqueue(new Callback<ServerMessage>() {
            @Override
            public void onResponse(Call<ServerMessage> call, Response<ServerMessage> response) {
                if(response.isSuccessful()){
                    Toast.makeText(UserActivity.this, "User deleted successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UserActivity.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ServerMessage> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}