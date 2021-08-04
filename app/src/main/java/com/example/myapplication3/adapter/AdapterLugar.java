package com.example.myapplication3.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
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

import com.example.myapplication3.Favoritos;
import com.example.myapplication3.InfoLugar;
import com.example.myapplication3.R;
import com.example.myapplication3.pojo.AgregarFavoritos;
import com.example.myapplication3.pojo.Lugares;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
        // holder.iv_lugar.setImageURI(Uri.parse(lg.getUrlimagen()));
        holder.tv_nombre_lugar.setText(lg.getNombre());
        holder.tv_descripcion_lugar.setText(lg.getDescripcion());
        holder.tv_ubicacion_lugar.setText(lg.getUbicacion());
        holder.url_imagen.setText(lg.getUrlimagen());
        //holder.iv_lugar.setImageBitmap(returnBitMap(lg.getUrl_imagen()));


        holder.btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.itemView.getContext(), Favoritos.class);
                i.putExtra("titulo", lg.getNombre());
                i.putExtra("descripcion", lg.getDescripcion());
                i.putExtra("ubicacion", lg.getUbicacion());
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
                i.putExtra("imagen", lg.getUrlimagen());
                holder.itemView.getContext().startActivity(i);
            }
        });

        //funcion botones

        holder.btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "diste like", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "comentarios", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return lugaList.size();
    }

    public static class ViewHolderLugares extends RecyclerView.ViewHolder {

        TextView tv_nombre_lugar, tv_descripcion_lugar,tv_ubicacion_lugar, url_imagen;
        ImageButton btn_agregar, btn_like, btn_comment;
        ImageView iv_lugar;
        public ViewHolderLugares(@NonNull View itemView) {
            super(itemView);

            tv_nombre_lugar = itemView.findViewById(R.id.name_textview);
            tv_descripcion_lugar = itemView.findViewById(R.id.status_textview);
            tv_ubicacion_lugar = itemView.findViewById(R.id.city_textview);
            url_imagen = itemView.findViewById(R.id.url_image);

            btn_agregar = itemView.findViewById(R.id.btn_home_agregar);
            btn_like = itemView.findViewById(R.id.btn_home_like);
            btn_comment = itemView.findViewById(R.id.btn_home_comment);

            iv_lugar = itemView.findViewById(R.id.iv_image_lugar);


        }

    }


    Bitmap bitmap;
    public Bitmap returnBitMap(final String url){

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection)imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return bitmap;
    }

}
