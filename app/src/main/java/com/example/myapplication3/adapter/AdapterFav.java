package com.example.myapplication3.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication3.Favoritos;
import com.example.myapplication3.InfoLugar;
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
                .resize(400, 300)
                .into(holder.iv_lugar_f);


        String titulo = af.getLugar_name();
        String descripcion = af.getLugar_descripcion();
        String ubicacion = af.getLugar_ubicacion();
        String imagen = af.getImagen();
        String latitud = af.getLatitud();
        String longitud = af.getLongitud();
        String clima = af.getClima();

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.itemView.getContext(), InfoLugar.class);
                i.putExtra("titulo", titulo);
                i.putExtra("descripcion", descripcion);
                i.putExtra("ubicacion", ubicacion);
                i.putExtra("imagen", imagen);
                i.putExtra("longitud", longitud);
                i.putExtra("latitud", latitud);
                i.putExtra("clima", clima);
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lugarFavorito.size();
    }

    public class ViewholderFav extends RecyclerView.ViewHolder {

        TextView titulo_f, descripcion_f, ubicacion_f;
        ImageView iv_lugar_f;
        CardView cv;

        public ViewholderFav(@NonNull View itemView) {
            super(itemView);

            titulo_f = itemView.findViewById(R.id.tv_nombre_lugarf);
            ubicacion_f = itemView.findViewById(R.id.tv_ubicacion_lugarf);
            iv_lugar_f = itemView.findViewById(R.id.iv_lugar_fav);
            cv = itemView.findViewById(R.id.cv_row_lugaresfav);

        }

    }
}
