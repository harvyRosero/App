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
    List<List_element> elements;



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
        elements.add(new List_element( "Peñol", "Antioquia",
                "El Embalse Peñol-Guatapé, en ocasiones llamada Represa de Guatapé es un embalse colombiano localizado en el oriente de Antioquia de las Empresas Públicas de Medellín. "));
        elements.add(new List_element("Caño Cristales", "Meta",
                "El Embalse Peñol-Guatapé, en ocasiones llamada Represa de Guatapé es un embalse colombiano localizado en el oriente de Antioquia de las Empresas Públicas de Medellín. "));
        elements.add(new List_element("Catedral de zipaquira", "Zipaquira",
                "El Embalse Peñol-Guatapé, en ocasiones llamada Represa de Guatapé es un embalse colombiano localizado en el oriente de Antioquia de las Empresas Públicas de Medellín. "));
        elements.add(new List_element( "Cerro Azul", "Guaviare",
                "El Embalse Peñol-Guatapé, en ocasiones llamada Represa de Guatapé es un embalse colombiano localizado en el oriente de Antioquia de las Empresas Públicas de Medellín. "));
        elements.add(new List_element( "Puerto Nariño", "Amazonas",
                "El Embalse Peñol-Guatapé, en ocasiones llamada Represa de Guatapé es un embalse colombiano localizado en el oriente de Antioquia de las Empresas Públicas de Medellín. "));

        ListAdapter listAdapter = new ListAdapter(elements, this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_lista_lugares);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

}