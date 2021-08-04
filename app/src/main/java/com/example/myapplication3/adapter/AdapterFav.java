package com.example.myapplication3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication3.R;
import com.example.myapplication3.pojo.AgregarFavoritos;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterFav  extends RecyclerView.Adapter<AdapterFav.ViewholderFav> {

    List<AgregarFavoritos> lugarFavorito;

    public AdapterFav(List<AgregarFavoritos> lugarFavorito) {
        this.lugarFavorito = lugarFavorito;
    }

    @NonNull
    @Override
    public ViewholderFav onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_lugares, parent, false);
        AdapterFav.ViewholderFav holder = new ViewholderFav(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFav.ViewholderFav holder, int position) {
        AgregarFavoritos af = lugarFavorito.get(position);
        holder.titulo_f.setText(af.getLugar_name());
        holder.ubicacion_f.setText(af.getLugar_ubicacion());

        Picasso.get()
                .load(af.getImagen())
                .error(R.mipmap.lugar1_1)
                .into(holder.iv_lugar_f);

    }

    @Override
    public int getItemCount() {
        return lugarFavorito.size();
    }

    public class ViewholderFav extends RecyclerView.ViewHolder {

        TextView titulo_f, descripcion_f, ubicacion_f;
        ImageView iv_lugar_f;

        public ViewholderFav(@NonNull View itemView) {
            super(itemView);

            titulo_f = itemView.findViewById(R.id.tv_nombre_lugarf);
            descripcion_f = itemView.findViewById(R.id.tv_descripcion_lugarf);
            ubicacion_f = itemView.findViewById(R.id.tv_ubicacion_lugarf);
            iv_lugar_f = itemView.findViewById(R.id.iv_lugar_fav);

        }

    }
}
