package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Configuracion extends AppCompatActivity {

    ImageButton btn_home, btn_fav, btn_perfil;
    Button btn_cerrar_sesion;
    private StorageReference storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);


        //funcion botones
        btn_cerrar_sesion = (Button)findViewById(R.id.btn_cerrar_sesion);
        btn_cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //para limpiar el almacenamiento local
                SharedPreferences.Editor editor = getSharedPreferences("datos", MODE_PRIVATE).edit();
                editor.clear().apply();

                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Configuracion.this, "sesion cerrada!", Toast.LENGTH_SHORT).show();
                irAlogin();
            }
        });

        btn_home = (ImageButton)findViewById(R.id.btn_home2);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Configuracion.this, MainActivity2.class);
                startActivity(i);
            }
        });

        btn_fav = (ImageButton)findViewById(R.id.btn_fav2);
        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Configuracion.this, Favoritos.class);
                i.putExtra("estadoC", "true");
                startActivity(i);
            }
        });

        btn_perfil = (ImageButton)findViewById(R.id.btn_perfil2);
        btn_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Configuracion.this, Perfil.class);
                startActivity(i);
            }
        });


    }

    //funcion cerrar sesion
    private void irAlogin() {
        Intent i = new Intent(Configuracion.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}