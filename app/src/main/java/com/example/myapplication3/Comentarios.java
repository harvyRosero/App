package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myapplication3.adapter.AdapterComentario;
import com.example.myapplication3.adapter.AdapterLugar;
import com.example.myapplication3.pojo.AgregarFavoritos;
import com.example.myapplication3.pojo.Lugares;
import com.example.myapplication3.pojo.UserComentarios;
import com.example.myapplication3.pojo.UserImage;
import com.example.myapplication3.pojo.Usuarios;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Comentarios extends AppCompatActivity {

    EditText mt_mensaje;
    ImageButton btn_enviar;

    //para enviar datos a firebase realtime
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    DatabaseReference ref;
    ArrayList<UserComentarios> lista;
    RecyclerView rv;
    AdapterComentario adapter;
    LinearLayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        //obtener datos guardados locales
        SharedPreferences dato = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String gmail = dato.getString("gmail", "");

        mt_mensaje = findViewById(R.id.mt_comentario_coments_p);
        btn_enviar = findViewById(R.id.btn_enviar_mensaje_comentario);


        // traer datos de firebase para enviar
        ref = FirebaseDatabase.getInstance().getReference().child("foto perfil");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        UserImage image = snapshot1.getValue((UserImage.class));
                        //lista.add(user);
                        String correo2 = image.getCorreo();
                        String nombre = image.getNombre();
                        if(correo2.equals(gmail)){

                            btn_enviar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String mensaje = mt_mensaje.getText().toString();
                                    String imagen = image.getImagePerfil();
                                    String nombre_lugar = getIntent().getStringExtra("lugar");

                                    if(!mensaje.isEmpty()){
                                        //enviar datos de firebase

                                        UserComentarios userComentarios = new UserComentarios(nombre, mensaje,
                                                gmail, imagen, nombre_lugar);

                                        myRef = database.getReference().child("comentarios").push();
                                        myRef.setValue(userComentarios);

                                        mt_mensaje.setText("");

                                        finish();
                                        startActivity(getIntent());
                                    }else{
                                        Toast.makeText(Comentarios.this, "mensaje vacio", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //traer datos de firebase

        ref = FirebaseDatabase.getInstance().getReference().child("comentarios");
        rv = findViewById(R.id.recycler_v_comentario_p);
        lm = new LinearLayoutManager(this);
        String nombre_lugar = getIntent().getStringExtra("lugar");

        rv.setLayoutManager(lm);
        lista = new ArrayList<>();
        adapter = new AdapterComentario(lista);
        rv.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        UserComentarios uc = snapshot1.getValue(UserComentarios.class);
                        if(uc.getLugar().equals(nombre_lugar)){
                            lista.add(uc);
                        }

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