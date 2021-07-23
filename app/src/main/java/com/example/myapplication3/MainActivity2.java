package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity  {

    ImageButton btn_fav, btn_perfil, btn_conf;
    List<list_element> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init();

        //funciones botones home

        btn_fav = (ImageButton)findViewById(R.id.btn_fav2);
        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this, Favoritos.class);
                startActivity(i);
            }
        });

        btn_perfil = (ImageButton)findViewById(R.id.btn_perfil2);
        btn_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this, Perfil.class);
                startActivity(i);
            }
        });

        btn_conf = (ImageButton)findViewById(R.id.btn_conf2);
        btn_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this, Configuracion.class);
                startActivity(i);
            }
        });
    }

    public void init(){
        elements = new ArrayList<>();
        elements.add(new list_element( "Peñol", "Antioquia", "descripcion..."));
        elements.add(new list_element("Caño Cristales", "Meta", "descripcion..."));
        elements.add(new list_element("Catedral de zipaquira", "Zipaquira", "descripcion..."));
        elements.add(new list_element( "Cerro Azul", "Guaviare", "descripcion..."));
        elements.add(new list_element( "Puerto Nariño", "Amazonas", "descripcion..."));

        ListAdapter listAdapter = new ListAdapter(elements, this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_lista_lugares);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }



}