package com.indako.crud.mataKuliah;

import androidx.appcompat.app.AppCompatActivity;

import com.indako.crud.MainActivity;
import com.indako.crud.R;
import com.indako.crud.mahasiswa.MahasiswaActivity;
import com.indako.crud.mahasiswa.MahasiswaAdapter;
import com.indako.crud.mahasiswa.MahasiswaForm;
import com.indako.crud.model.Mahasiswa;
import com.indako.crud.model.MataKuliah;
import com.indako.crud.remote.APIUtils;
import com.indako.crud.remote.MahasiswaService;
import com.indako.crud.remote.MataKuliahService;

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

public class MataKuliahActivity extends AppCompatActivity {

    ListView listView;

    MataKuliahService mataKuliahService;
    List<MataKuliah> list = new ArrayList<MataKuliah>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mata_kuliah);
        setTitle("MataKuliah");

        listView = (ListView) findViewById(R.id.listView);
        mataKuliahService = APIUtils.getMataKuliahService();
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
                Intent intent = new Intent(MataKuliahActivity.this, MataKuliahForm.class);
                intent.putExtra("Nama", "");
                startActivity(intent);
                return true;
            case R.id.refresh:
                getUsersList();
                return true;
            case R.id.backhome:
                Intent inten2t = new Intent(MataKuliahActivity.this, MainActivity.class);
                startActivity(inten2t);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getUsersList(){
        Call<List<MataKuliah>> call = mataKuliahService.getMataKuliah();
        call.enqueue(new Callback<List<MataKuliah>>() {
            @Override
            public void onResponse(Call<List<MataKuliah>> call, Response<List<MataKuliah>> response) {
                if(response.code() == 200){
                    list = response.body();
                    listView.setAdapter(new MataKuliahAdapter(MataKuliahActivity.this, R.layout.list_mahasiswa, list));
                } else if(response.code() == 500){
                    Toast.makeText(MataKuliahActivity.this, "Load List Mata Kuliah Gagal, Tekan Tombol Refresh!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MataKuliah>> call, Throwable t) {
                Toast.makeText(MataKuliahActivity.this, "Load List Mata Kuliah Gagal, Tekan Tombol Refresh!", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}