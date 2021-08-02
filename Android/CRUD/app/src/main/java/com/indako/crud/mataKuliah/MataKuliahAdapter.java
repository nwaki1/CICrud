package com.indako.crud.mataKuliah;

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
import com.indako.crud.mahasiswa.MahasiswaForm;
import com.indako.crud.model.Mahasiswa;
import com.indako.crud.model.MataKuliah;

import java.util.List;

public class MataKuliahAdapter extends ArrayAdapter<MataKuliah> {
    private Context context;
    private List<MataKuliah> mataKuliah;

    public MataKuliahAdapter(@NonNull Context context, int resource, @NonNull List<MataKuliah> objects) {
        super(context, resource, objects);
        this.context = context;
        this.mataKuliah = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_mahasiswa, parent, false);

        TextView txtUserId = (TextView) rowView.findViewById(R.id.txtUserId);
        TextView txtUsername = (TextView) rowView.findViewById(R.id.txtUsername);

        txtUserId.setText(String.format("KodeMatkul: %s", mataKuliah.get(position).getKodeMatkul()));
        txtUsername.setText(String.format("Nama: %s", mataKuliah.get(position).getNama()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity User Form
                Intent intent = new Intent(context, MataKuliahForm.class);
                intent.putExtra("id", String.valueOf(mataKuliah.get(position).getId()));
                intent.putExtra("KodeMatkul", mataKuliah.get(position).getKodeMatkul());
                intent.putExtra("Nama", mataKuliah.get(position).getNama());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
