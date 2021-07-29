package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn, btn2, btn_recuperar_cont;
    private EditText editTextUsuario;
    private EditText editTextPass;
    private TextView tvMensaje;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextPass = findViewById(R.id.editTextPassword);
        btn = (Button)findViewById(R.id.btLogin);
        btLogin = findViewById(R.id.btLogin);

        tvMensaje = findViewById(R.id.textMensaje);


        btn.setOnClickListener(new View.OnClickListener() {
            String usuario="prueba";
            String password="123";
            @Override
            public void onClick(View v) {

                switch (v.getId()){
                  case R.id.btLogin:
                        final String user = editTextUsuario.getText().toString();
                        final String pass = editTextPass.getText().toString();
                        if(usuario.equals(user) && password.equals(pass)){
                            saveLoginSharedPreferences(user);
                            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                            startActivity(intent);
                        }else{
                            tvMensaje.setText("Usuario o contraseña Incorrecto");
                        }
                        break;
               }
            }
        private void saveLoginSharedPreferences(String user){
            SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("user", user);
            editor.apply();
        }

        });

        btn2 = (Button)findViewById(R.id.btn_registrarse);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Registro.class);
                startActivity(i);
            }
        });

        btn_recuperar_cont = (Button)findViewById(R.id.btn_olvide_contraseña);
        btn_recuperar_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RecuperarContr.class);
                startActivity(i);
            }
        });
    }
}