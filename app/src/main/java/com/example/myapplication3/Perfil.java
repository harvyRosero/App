package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
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

public class Perfil extends AppCompatActivity {

    TextView tv_nombre, tv_gmail, tv_celular;
    ImageButton btn_home, btn_fav, btn_config;
    SearchView searchlugar;

    ArrayList<Usuarios> lista;

    DatabaseReference ref;

    //para firebase realtime
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //obtener datos guardados locales
        SharedPreferences dato = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String gmail = dato.getString("gmail", "");

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
                i.putExtra("estadoP", "true");
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
        tv_celular = (TextView)findViewById(R.id.tv_celular_perfil);
        tv_gmail = (TextView)findViewById(R.id.tv_gmail_perfil);

        lista = new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference().child("usuarios");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        Usuarios user = snapshot1.getValue(Usuarios.class);
                        //lista.add(user);
                        String correo = user.getCorreo();
                        if(correo.equals(gmail)){
                            String nombre = user.getNombre();
                            tv_nombre.setText(nombre);
                            tv_gmail.setText(user.getCorreo());
                            tv_celular.setText(user.getNumero());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}