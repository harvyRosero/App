package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class InfoLugar extends AppCompatActivity {

    private TextView tv_titulo;
    private TextView tv_descripcion;
    private TextView tv_ubicacion;
    private ImageView imageView;
    private Button btn_subir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_lugar);

        imageView = findViewById(R.id.miImage);
        Picasso.get()
                .load("gs://appturism-7b352.appspot.com/lugares/lajas0.jpg")
                .error(R.mipmap.lugar1_1)
                .into(imageView);


    }


}