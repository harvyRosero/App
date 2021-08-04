package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myapplication3.adapter.AdapterFav;
import com.example.myapplication3.adapter.AdapterLugar;
import com.example.myapplication3.pojo.AgregarFavoritos;
import com.example.myapplication3.pojo.Lugares;
import com.example.myapplication3.pojo.Usuarios;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Favoritos extends AppCompatActivity  {

    ImageButton btn_home, btn_perfil, btn_config;
    SearchView searchlugar;
    String gmail;

    //para traer datos a recycler view
    DatabaseReference ref;
    ArrayList<AgregarFavoritos> lista;
    RecyclerView rv;
    SearchView search_lugar;
    AdapterFav adapter;
    LinearLayoutManager lm;

    //para firebase realtime
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        //obtener datos del amcenamiento local
        SharedPreferences dato = getSharedPreferences("datos", Context.MODE_PRIVATE);
        gmail = dato.getString("gmail", "");

        //obtener datos del AdapterLugar
        String titulo = getIntent().getStringExtra("titulo");
        String descripcion = getIntent().getStringExtra("descripcion");
        String ubicacion = getIntent().getStringExtra("ubicacion");
        String imagen = getIntent().getStringExtra("imagen");

        //enviar datos a firebase realtime lugares favoritos

        AgregarFavoritos agregarFavoritos = new AgregarFavoritos(titulo, descripcion, ubicacion, gmail, imagen);
        myRef = database.getReference().child("Lugares Favoritos").push();
        myRef.setValue(agregarFavoritos);


        //funcion botones cambio de activity

        btn_home = (ImageButton)findViewById(R.id.btn_home2);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Favoritos.this, MainActivity2.class);
                startActivity(i);
            }
        });

        btn_perfil = (ImageButton)findViewById(R.id.btn_perfil2);
        btn_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Favoritos.this, Perfil.class);
                startActivity(i);
            }
        });

        btn_config = (ImageButton)findViewById(R.id.btn_conf2);
        btn_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Favoritos.this, Configuracion.class);
                startActivity(i);
            }
        });

        searchlugar = findViewById(R.id.searchView_fav);

        searchlugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Favoritos.this, MainActivity2.class);
                startActivity(i);

            }
        });

        //traer datos de firebase

        ref = FirebaseDatabase.getInstance().getReference().child("Lugares Favoritos");
        rv = findViewById(R.id.recyclerfav);
        search_lugar = findViewById(R.id.searchView_fav);
        lm = new LinearLayoutManager(this);

        rv.setLayoutManager(lm);
        lista = new ArrayList<>();
        adapter = new AdapterFav(lista);
        rv.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        AgregarFavoritos af = snapshot1.getValue(AgregarFavoritos.class);
                        lista.add(af);
                    }
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}