package com.indako.crud.dosen;

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
import com.indako.crud.model.Dosen;

import java.util.List;

public class DosenAdapter extends ArrayAdapter<Dosen> {
    private Context context;
    private List<Dosen> dosen;

    public DosenAdapter(@NonNull Context context, int resource, @NonNull List<Dosen> objects) {
        super(context, resource, objects);
        this.context = context;
        this.dosen = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_mahasiswa, parent, false);

        TextView txtUserId = (TextView) rowView.findViewById(R.id.txtUserId);
        TextView txtUsername = (TextView) rowView.findViewById(R.id.txtUsername);

        txtUserId.setText(String.format("NoInduk: %s", dosen.get(position).getNoInduk()));
        txtUsername.setText(String.format("Nama: %s", dosen.get(position).getNama()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity User Form
                Intent intent = new Intent(context, DosenForm.class);
                intent.putExtra("id", String.valueOf(dosen.get(position).getId()));
                intent.putExtra("NoInduk", dosen.get(position).getNoInduk());
                intent.putExtra("Nama", dosen.get(position).getNama());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
