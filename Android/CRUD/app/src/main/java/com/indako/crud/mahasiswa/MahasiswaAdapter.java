package com.indako.crud.mahasiswa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.indako.crud.R;
import com.indako.crud.model.Mahasiswa;

import java.util.List;

public class MahasiswaAdapter extends ArrayAdapter<Mahasiswa> {
    private Context context;
    private List<Mahasiswa> mahasiswa;

    public MahasiswaAdapter(@NonNull Context context, int resource, @NonNull List<Mahasiswa> objects) {
        super(context, resource, objects);
        this.context = context;
        this.mahasiswa = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_mahasiswa, parent, false);

        TextView txtUserId = (TextView) rowView.findViewById(R.id.txtUserId);
        TextView txtUsername = (TextView) rowView.findViewById(R.id.txtUsername);

        txtUserId.setText(String.format("NoInduk: %s", mahasiswa.get(position).getNoInduk()));
        txtUsername.setText(String.format("Nama: %s", mahasiswa.get(position).getName()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity User Form
                Intent intent = new Intent(context, MahasiswaForm.class);
                intent.putExtra("id", String.valueOf(mahasiswa.get(position).getId()));
                intent.putExtra("NoInduk", mahasiswa.get(position).getNoInduk());
                intent.putExtra("Nama", mahasiswa.get(position).getName());
                intent.putExtra("TahunMasuk", mahasiswa.get(position).getTahunMasuk());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
