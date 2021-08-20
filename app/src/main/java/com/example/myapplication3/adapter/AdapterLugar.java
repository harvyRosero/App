package com.example.myapplication3.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication3.Comentarios;
import com.example.myapplication3.Favoritos;
import com.example.myapplication3.InfoLugar;
import com.example.myapplication3.R;
import com.example.myapplication3.pojo.AgregarFavoritos;
import com.example.myapplication3.pojo.EstadoBotones;
import com.example.myapplication3.pojo.Lugares;
import com.example.myapplication3.pojo.Usuarios;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AdapterLugar extends RecyclerView.Adapter<AdapterLugar.ViewHolderLugares> {
    List<Lugares> lugaList;
    List<EstadoBotones> BotonesList;

    public AdapterLugar(List<Lugares> lugaList ) {
        this.lugaList = lugaList;
    }


    //para firebase realtime
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference ref;

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

        Picasso.get()
                .load(lg.getImagen())
                .error(R.drawable.alogo)
                .resize(800, 700)
                .into(holder.iv_lugar);

        holder.btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.itemView.getContext(), Favoritos.class);
                i.putExtra("estadoA", "true");
                i.putExtra("titulo", lg.getNombre());
                i.putExtra("ubicacion", lg.getUbicacion());
                i.putExtra("imagen", lg.getImagen());
                i.putExtra("latitud", lg.getLatitud());
                i.putExtra("longitud", lg.getLongitud());
                i.putExtra("clima", lg.getClima());
                i.putExtra("descripcion", lg.getDescripcion());
                i.putExtra("recomendaciones", lg.getRecomendaciones());
                holder.itemView.getContext().startActivity(i);

            }
        });

        holder.iv_lugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.itemView.getContext(), InfoLugar.class);
                i.putExtra("titulo", lg.getNombre());
                i.putExtra("descripcion", lg.getDescripcion());
                i.putExtra("ubicacion", lg.getUbicacion());
                i.putExtra("imagen", lg.getImagen());
                i.putExtra("recomendaciones", lg.getRecomendaciones());
                i.putExtra("clima", lg.getClima());
                i.putExtra("latitud", lg.getLatitud());
                i.putExtra("longitud", lg.getLongitud());
                holder.itemView.getContext().startActivity(i);
            }
        });

        //obtener datos guardados locales sharetpreferences
        SharedPreferences dato =  holder.itemView.getContext().getSharedPreferences("datos", Context.MODE_PRIVATE);
        String gmail = dato.getString("gmail", "");


        //funcion botones
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        holder.btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EstadoBotones boton = new EstadoBotones(gmail, "ok", lg.getNombre());
                myRef = database.getReference().child("like lugares").push();
                myRef.setValue(boton);

                holder.btn_like.setVisibility(View.GONE);
            }
        });

        //traer los datos del usuario
        ref = FirebaseDatabase.getInstance().getReference().child("like lugares");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        EstadoBotones botones = snapshot1.getValue(EstadoBotones.class);
                        if(botones.getCorreo().equals(gmail)){
                            if(lg.getNombre().equals(botones.getNombre_lugar())){
                                holder.btn_like.setVisibility(View.GONE);
                            }else{
                                holder.btn_dislike.setVisibility(View.VISIBLE);
                            }

                        }else{
                            holder.btn_like.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });




        holder.btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.itemView.getContext(), Comentarios.class);
                i.putExtra("lugar", lg.getNombre());
                holder.itemView.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lugaList.size();
    }

    public static class ViewHolderLugares extends RecyclerView.ViewHolder {

        TextView tv_nombre_lugar, tv_descripcion_lugar,tv_ubicacion_lugar, url_imagen;
        ImageButton btn_agregar,  btn_comment, btn_dislike, btn_like;
        ImageView iv_lugar;

        public ViewHolderLugares(@NonNull View itemView) {
            super(itemView);

            tv_nombre_lugar = itemView.findViewById(R.id.name_textview);
            tv_descripcion_lugar = itemView.findViewById(R.id.status_textview);
            tv_ubicacion_lugar = itemView.findViewById(R.id.city_textview);

            btn_agregar = itemView.findViewById(R.id.btn_home_agregar);
            btn_like = itemView.findViewById(R.id.btn_home_like);
            btn_comment = itemView.findViewById(R.id.btn_home_comment);
            btn_dislike = itemView.findViewById(R.id.btn_home_like2);

            iv_lugar = itemView.findViewById(R.id.iv_image_lugar);
        }
    }
}
