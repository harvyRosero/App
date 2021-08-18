package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication3.pojo.EstadoBotones;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class InfoLugar extends AppCompatActivity {

    private TextView tv_titulo, tv_clima, tv_recomendaciones, tv_like;
    private TextView tv_descripcion;
    private TextView tv_ubicacion;
    private ImageView imageView;
    Button btn_maps;


    //para firebase realtime
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_lugar);

        SharedPreferences dato = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String gmail = dato.getString("gmail", "");

        tv_titulo = findViewById(R.id.tv_titulo_info);
        tv_descripcion = findViewById(R.id.tv_descripcion_info);
        tv_ubicacion = findViewById(R.id.tv_ubicacion_info);
        tv_recomendaciones = findViewById(R.id.tv_recomendaciones_info);
        tv_clima = findViewById(R.id.tv_clima_info);
        btn_maps = findViewById(R.id.btn_ir_a_maps);
        tv_like = findViewById(R.id.tv_likes_info);

        String titulo = getIntent().getStringExtra("titulo");
        String descripcion = getIntent().getStringExtra("descripcion");
        String ubicacion = getIntent().getStringExtra("ubicacion");
        String urlImage = getIntent().getStringExtra("imagen");
        String recomendaciones = getIntent().getStringExtra("recomendaciones");
        String clima = getIntent().getStringExtra("clima");
        String latitud = getIntent().getStringExtra("latitud");
        String longitud = getIntent().getStringExtra("longitud");

        tv_titulo.setText(titulo);
        tv_descripcion.setText(descripcion);
        tv_ubicacion.setText(ubicacion);
        tv_recomendaciones.setText(recomendaciones);
        tv_clima.setText(clima);

        imageView = findViewById(R.id.miImage);
        Picasso.get()
                .load(urlImage)
                .error(R.drawable.alogo)
                .resize(700, 500)
                .into(imageView);

        btn_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InfoLugar.this, MapsActivity.class);
                i.putExtra("latitud", latitud);
                i.putExtra("longitud", longitud);
                i.putExtra("titulo", titulo);
                startActivity(i);
            }
        });
    }


}