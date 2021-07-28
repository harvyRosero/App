package com.example.myapplication3.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication3.Detalles;
import com.example.myapplication3.R;
import com.example.myapplication3.pojo.Lugares;

import java.util.List;

public class AdapterLugar extends RecyclerView.Adapter<AdapterLugar.ViewHolderLugares> {

    List<Lugares> lugaList;

    public AdapterLugar(List<Lugares> lugaList) {
        this.lugaList = lugaList;
    }

    @NonNull
    @Override
    public ViewHolderLugares onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element, parent, false);
        ViewHolderLugares holder = new ViewHolderLugares(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLugar.ViewHolderLugares holder, int position) {

        Lugares lg = lugaList.get(position);
        holder.tv_nombre_lugar.setText(lg.getNombre());
        holder.tv_descripcion_lugar.setText(lg.getDescripcion());
        holder.tv_ubicacion_lugar.setText(lg.getUbicacion());

        holder.btn_detalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.itemView.getContext(), Detalles.class);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lugaList.size();
    }

    public class ViewHolderLugares extends RecyclerView.ViewHolder {

        TextView tv_nombre_lugar, tv_descripcion_lugar,tv_ubicacion_lugar;
        ImageButton btn_detalle, btn_agregar;
        public ViewHolderLugares(@NonNull View itemView) {
            super(itemView);

            tv_nombre_lugar = itemView.findViewById(R.id.name_textview);
            tv_descripcion_lugar = itemView.findViewById(R.id.status_textview);
            tv_ubicacion_lugar = itemView.findViewById(R.id.city_textview);

            btn_detalle = itemView.findViewById(R.id.btn_home_agregar);
        }
    }
}