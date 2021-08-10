package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication3.pojo.UserImage;
import com.example.myapplication3.pojo.Usuarios;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class FotoPerfil extends AppCompatActivity {

    DatabaseReference ref;

    //para enviar datos a firebase realtime
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    Button btn_elegir_img, btn_enviar_img;

    private StorageReference myStorage;
    private static final int GALERY_INTENT = 1;
    private ProgressDialog mProgressDialog;
    private ImageView uploadImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_perfil);

        //obtener datos guardados locales sharetpreferences
        SharedPreferences dato = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String gmail = dato.getString("gmail", "");



        //abrir galeria celular
        myStorage = FirebaseStorage.getInstance().getReference();
        uploadImage = (ImageView)findViewById(R.id.upload_image_foto_perfil);
        btn_elegir_img = findViewById(R.id.btn_elegir_img);
        btn_elegir_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, GALERY_INTENT);
            }
        });


        mProgressDialog = new ProgressDialog(this);

        //traer imagen de usuario
        ref = FirebaseDatabase.getInstance().getReference().child("foto perfil");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        UserImage imagen = snapshot1.getValue(UserImage.class);
                        String correo = imagen.getCorreo();
                        if(correo.equals(gmail)){
                            String urlImagen = imagen.getImagePerfil();
                            Picasso.get()
                                    .load(urlImagen)
                                    .resize(200, 200)
                                    .transform(new Perfil.CircleTransform())
                                    .error(R.mipmap.foto_perfil)
                                    .into(uploadImage);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //descargar url de storage firebase
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALERY_INTENT && resultCode == RESULT_OK){

            mProgressDialog.setTitle("subiendo...");
            mProgressDialog.setMessage("subiendo foto...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

            Uri uri = data.getData();

            StorageReference filePath = myStorage.child("foto_perfil").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mProgressDialog.dismiss();

                    SharedPreferences dato = getSharedPreferences("datos", Context.MODE_PRIVATE);
                    String gmail = dato.getString("gmail", "");

                    // url de imagen
                    Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            //traer los datos del usuario
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
                                                String imagen = uri.toString();

                                                //enviar imagen  firebase realtime imagen usuario
                                                UserImage foto = new UserImage( imagen, gmail, nombre);
                                                myRef = database.getReference().child("foto perfil").push();
                                                myRef.setValue(foto);
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });



                            btn_enviar_img = findViewById(R.id.btn_enviar_img);
                            btn_enviar_img.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent(FotoPerfil.this, Perfil.class);
                                    startActivity(i);
                                }
                            });

                        }
                    });

                    Toast.makeText(FotoPerfil.this, "cargando imagen...", Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}