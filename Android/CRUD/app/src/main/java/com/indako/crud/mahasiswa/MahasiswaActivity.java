package com.indako.crud.mahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.indako.crud.MainActivity;
import com.indako.crud.R;
import com.indako.crud.model.Mahasiswa;
import com.indako.crud.remote.APIUtils;
import com.indako.crud.remote.MahasiswaService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MahasiswaActivity extends AppCompatActivity {

    ListView listView;

    MahasiswaService mahasiswaService;
    List<Mahasiswa> list = new ArrayList<Mahasiswa>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);
        setTitle("Mahasiswa");

        listView = (ListView) findViewById(R.id.listView);
        mahasiswaService = APIUtils.getMahasiswaService();
    }


    @Override
    protected void onResume() {
        super.onResume();

        getUsersList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(MahasiswaActivity.this, MahasiswaForm.class);
                intent.putExtra("Nama", "");
                startActivity(intent);
                return true;
            case R.id.refresh:
                getUsersList();
                return true;
            case R.id.backhome:
                Intent inten2t = new Intent(MahasiswaActivity.this, MainActivity.class);
                startActivity(inten2t);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getUsersList(){
        Call<List<Mahasiswa>> call = mahasiswaService.getMahasiswa();
        call.enqueue(new Callback<List<Mahasiswa>>() {
            @Override
            public void onResponse(Call<List<Mahasiswa>> call, Response<List<Mahasiswa>> response) {
                if(response.code() == 200){
                    list = response.body();
                    listView.setAdapter(new MahasiswaAdapter(MahasiswaActivity.this, R.layout.list_mahasiswa, list));
                } else if(response.code() == 500){
                    Toast.makeText(MahasiswaActivity.this, "Load List Mahasiswa Gagal, Tekan Tombol Refresh!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Mahasiswa>> call, Throwable t) {
                Toast.makeText(MahasiswaActivity.this, "Load List Mahasiswa Gagal, Tekan Tombol Refresh!", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}