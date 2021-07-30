package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.example.myapplication3.adapter.AdapterLugar;
import com.example.myapplication3.pojo.Lugares;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity  {

    ImageButton btn_fav, btn_perfil, btn_conf;

    DatabaseReference ref;
    ArrayList<Lugares> lista;
    RecyclerView rv;
    SearchView search_lugar;
    AdapterLugar adapter;
    LinearLayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //init();

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


        ref = FirebaseDatabase.getInstance().getReference().child("lugares");
        rv = findViewById(R.id.recycler_view_lista_lugares);
        search_lugar = findViewById(R.id.search_home);
        lm = new LinearLayoutManager(this);

        rv.setLayoutManager(lm);
        lista = new ArrayList<>();
        adapter = new AdapterLugar(lista);
        rv.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        Lugares lg = snapshot1.getValue(Lugares.class);
                        lista.add(lg);
                    }
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        search_lugar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                buscar(newText);
                return true;
            }
        });
    }


    private void buscar(String newText) {
        ArrayList<Lugares> miLista = new ArrayList<>();
        for(Lugares obj: lista){
            if(obj.getUbicacion().toLowerCase().contains(newText.toLowerCase())){
                miLista.add(obj);
            }
            AdapterLugar adapterLugar = new AdapterLugar(miLista);
            rv.setAdapter(adapterLugar);
        }
    }

}