package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements RecyclerAdapter.list_item_click {

    ImageButton btn_fav, btn_perfil, btn_conf;

    private RecyclerView recycler_view_lugares;
    private static final int LISTA_NUMEROS = 100;
    private Toast mToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recycler_view_lugares = findViewById(R.id.list_recycler_view);
        recycler_view_lugares.addItemDecoration(new DividerItemDecoration(
                this , DividerItemDecoration.VERTICAL));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_view_lugares.setLayoutManager(linearLayoutManager);

        RecyclerAdapter mAdapter = new RecyclerAdapter(LISTA_NUMEROS, this);
        recycler_view_lugares.setAdapter(mAdapter);



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


    @Override
    public void onListItemClick(int clickedItem) {
        if(mToast!= null){
            mToast.cancel();
        }

        String mensajeToast = "item "+ clickedItem +" ha sido clickeado";
        mToast = Toast.makeText(this, mensajeToast, Toast.LENGTH_SHORT);
        mToast.show();
    }
}