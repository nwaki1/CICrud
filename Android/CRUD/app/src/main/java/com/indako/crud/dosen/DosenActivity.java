package com.indako.crud.dosen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.indako.crud.MainActivity;
import com.indako.crud.R;
import com.indako.crud.mahasiswa.MahasiswaActivity;
import com.indako.crud.mahasiswa.MahasiswaAdapter;
import com.indako.crud.mahasiswa.MahasiswaForm;
import com.indako.crud.mataKuliah.MataKuliahActivity;
import com.indako.crud.model.Dosen;
import com.indako.crud.model.Mahasiswa;
import com.indako.crud.remote.APIUtils;
import com.indako.crud.remote.DosenService;
import com.indako.crud.remote.MahasiswaService;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DosenActivity extends AppCompatActivity {

    ListView listView;

    DosenService dosenService;
    List<Dosen> list = new ArrayList<Dosen>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen);
        setTitle("Dosen");

        listView = (ListView) findViewById(R.id.listView);
        dosenService = APIUtils.getDosenService();
    }

    @Override
    protected void onResume() {
        super.onResume();

        getUsersList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(DosenActivity.this, DosenForm.class);
                intent.putExtra("Nama", "");
                startActivity(intent);
                return true;
            case R.id.refresh:
                getUsersList();
                return true;
            case R.id.backhome:
                Intent inten2t = new Intent(DosenActivity.this, MainActivity.class);
                startActivity(inten2t);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getUsersList(){
        Call<List<Dosen>> call = dosenService.getDosen();
        call.enqueue(new Callback<List<Dosen>>() {
            @Override
            public void onResponse(Call<List<Dosen>> call, Response<List<Dosen>> response) {
                if(response.code() == 200){
                    list = response.body();
                    listView.setAdapter(new DosenAdapter(DosenActivity.this, R.layout.list_mahasiswa, list));
                } else if(response.code() == 500){
                    Toast.makeText(DosenActivity.this, "Load List Dosen Gagal, Tekan Tombol Refresh!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Dosen>> call, Throwable t) {
                Toast.makeText(DosenActivity.this, "Load List Dosen Gagal, Tekan Tombol Refresh!", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}