package com.indako.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.indako.crud.dosen.DosenActivity;
import com.indako.crud.mahasiswa.MahasiswaActivity;
import com.indako.crud.mahasiswa.MahasiswaForm;
import com.indako.crud.mataKuliah.MataKuliahActivity;
import com.indako.crud.model.Mahasiswa;
import com.indako.crud.remote.APIUtils;
import com.indako.crud.remote.MahasiswaService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnMahasiswa;
    Button btnDosen;
    Button btnMataKuliah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMahasiswa = (Button) findViewById(R.id.btnMahasiswa);
        btnDosen = (Button) findViewById(R.id.btnDosen);
        btnMataKuliah = (Button) findViewById(R.id.btnMatkul);

        btnMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MahasiswaActivity.class);
                startActivity(intent);
            }
        });

        btnDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DosenActivity.class);
                startActivity(intent);
            }
        });

        btnMataKuliah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MataKuliahActivity.class);
                startActivity(intent);
            }
        });
    }

}