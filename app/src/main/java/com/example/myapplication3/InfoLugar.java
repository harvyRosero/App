package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

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
import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class InfoLugar extends AppCompatActivity {

    private TextView tv_titulo, tv_clima, tv_recomendaciones;
    private TextView tv_descripcion;
    private TextView tv_ubicacion;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_lugar);

        // new getImageURL(imageView).execute(urlImage);

        tv_titulo = findViewById(R.id.tv_titulo_info);
        tv_descripcion = findViewById(R.id.tv_descripcion_info);
        tv_ubicacion = findViewById(R.id.tv_ubicacion_info);
        tv_recomendaciones = findViewById(R.id.tv_recomendaciones_info);
        tv_clima = findViewById(R.id.tv_clima_info);

        String titulo = getIntent().getStringExtra("titulo");
        String descrip = getIntent().getStringExtra("descripcion");
        String ubicacion = getIntent().getStringExtra("ubicacion");
        String urlImage = getIntent().getStringExtra("imagen");
        String recomendaciones = getIntent().getStringExtra("recomendaciones");
        String clima = getIntent().getStringExtra("clima");

        tv_titulo.setText(titulo);
        tv_descripcion.setText(descrip);
        tv_ubicacion.setText(ubicacion);
        //tv_recomendaciones.setText(recomendaciones);
        tv_clima.setText(clima);

        imageView = findViewById(R.id.miImage);
        Picasso.get()
                .load(urlImage)
                .error(R.drawable.alogo)
                .resize(700, 500)
                .into(imageView);
    }


}