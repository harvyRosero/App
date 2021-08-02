package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.myapplication3.pojo.Lugares;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Perfil extends AppCompatActivity {

    TextView tv_nombre, tv_gmail, tv_celular;
    ImageButton btn_home, btn_fav, btn_config;
    SearchView searchlugar;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        String mail = getIntent().getStringExtra("mail");

        //funcion botones de cambio de activity

        btn_home = (ImageButton)findViewById(R.id.btn_home2);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Perfil.this, MainActivity2.class);
                startActivity(i);
            }
        });

        btn_fav = (ImageButton)findViewById(R.id.btn_fav2);
        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Perfil.this, Favoritos.class);
                startActivity(i);
            }
        });

        btn_config = (ImageButton)findViewById(R.id.btn_conf2);
        btn_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Perfil.this, Configuracion.class);
                startActivity(i);
            }
        });

        searchlugar = findViewById(R.id.search_perfil);
        searchlugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Perfil.this, MainActivity2.class);
                startActivity(i);
            }
        });

        tv_nombre = (TextView)findViewById(R.id.tv_nombre_perfil);

        ref = FirebaseDatabase.getInstance().getReference().child("usuarios");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        Lugares lg = snapshot1.getValue(Lugares.class);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}