package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.myapplication3.adapter.AdapterFav;
import com.example.myapplication3.adapter.AdapterLugar;
import com.example.myapplication3.pojo.AgregarFavoritos;
import com.example.myapplication3.pojo.Lugares;
import com.example.myapplication3.pojo.UserImage;
import com.example.myapplication3.pojo.Usuarios;
import com.google.android.gms.maps.model.Circle;
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
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

public class Perfil extends AppCompatActivity {

    private TextView tv_nombre, tv_gmail, tv_celular, tv_nacionalidad;
    private ImageButton btn_home, btn_fav, btn_config;
    private Button btn_avtualizar_info;
    private ImageView uploadImage;

    //para enviar datos a firebase realtime
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference ref;

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

        tv_nombre = (TextView)findViewById(R.id.tv_nombre_perfil);
        tv_celular = (TextView)findViewById(R.id.tv_celular_perfil);
        tv_gmail = (TextView)findViewById(R.id.tv_gmail_perfil);
        tv_nacionalidad = findViewById(R.id.tv_nacionalidad_perfil);

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
                            tv_nombre.setText(nombre);
                            tv_gmail.setText(user.getCorreo());
                            tv_celular.setText(user.getNumero());
                            tv_nacionalidad.setText(user.getNacionalidad());

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //ir a foto perfil
        uploadImage = (ImageView)findViewById(R.id.uploadImageVp);
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Perfil.this, FotoPerfil.class);
                startActivity(i);
            }
        });

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
                                    .resize(300, 300)
                                    .transform(new CircleTransform())
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

        //boton actulizar informacion

        btn_avtualizar_info = findViewById(R.id.btn_actualizar_info_perfil);
        btn_avtualizar_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Perfil.this, FotoPerfil.class);
                startActivity(i);
            }
        });

    }

    //tranformar imageview en circulo
    public static class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }

}