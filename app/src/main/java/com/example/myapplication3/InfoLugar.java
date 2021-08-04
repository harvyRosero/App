package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class InfoLugar extends AppCompatActivity {

    private TextView tv_titulo;
    private TextView tv_descripcion;
    private TextView tv_ubicacion;
    private ImageView imageView;
    private Button btn_subir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_lugar);

        // new getImageURL(imageView).execute(urlImage);

        tv_titulo = findViewById(R.id.tv_titulo_info);
        tv_descripcion = findViewById(R.id.tv_descripcion_info);
        tv_ubicacion = findViewById(R.id.tv_ubicacion_info);

        String titulo = getIntent().getStringExtra("titulo");
        String descrip = getIntent().getStringExtra("descripcion");
        String ubicacion = getIntent().getStringExtra("ubicacion");
        String urlImage = getIntent().getStringExtra("imagen");

        tv_titulo.setText(titulo);
        tv_descripcion.setText(descrip);
        tv_ubicacion.setText(ubicacion);

        imageView = findViewById(R.id.miImage);
        Picasso.get()
                .load(urlImage)
                .error(R.drawable.alogo)
                .into(imageView);
    }

    /*

    public class getImageURL extends AsyncTask<String, Void, Bitmap> {

        ImageView imgV;

        public getImageURL(ImageView imgV){
            this.imgV = imgV;
        }

        @Override
        protected Bitmap doInBackground(String... url) {

            String urlDisplay = url[0];
            bitmap =  null;
            try{
                InputStream str = new java.net.URL(urlDisplay).openStream();
                bitmap = BitmapFactory.decodeStream(str);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return bitmap;
        }



        @Override
        protected void onPostExecute (Bitmap bitmap){
            super.onPostExecute(bitmap);

            imgV.setImageBitmap(bitmap);
        }


    }

     */
}