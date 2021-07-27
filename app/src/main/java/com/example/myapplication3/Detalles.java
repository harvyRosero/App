package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Detalles extends AppCompatActivity {

    TextView tv_datos, tv_ubicacion, tv_descrip;
    private List_element itemDetalle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        recibirDatos();

    }

    private void initViews(){
        tv_datos = findViewById(R.id.name_textview);
    }

    private void recibirDatos(){
        tv_datos = (TextView)findViewById(R.id.txt_detalle_titulo);
        String dato = getIntent().getStringExtra("itemDetalle");
        tv_datos.setText(dato);

    }

}