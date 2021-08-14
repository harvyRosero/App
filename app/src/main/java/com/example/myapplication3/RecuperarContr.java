package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarContr extends AppCompatActivity {

    Button btn_atras, btn_enviar;
    EditText gmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contr);

        gmail = findViewById(R.id.et_gmail_recuperar_contra);
        btn_enviar = findViewById(R.id.btn_enviar_recuperar_contra);
        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        //funcion botones

        btn_atras = (Button)findViewById(R.id.btn_atras_recuperar_c);
        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecuperarContr.this, MainActivity.class);
                startActivity(i);
            }
        });
    }//fin oncreate

    public void validate(){
        String email = gmail.getText().toString().trim();

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            gmail.setError("Direccion de correo invalido");
            return;
        }

        sendEmaii(email);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(RecuperarContr.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void sendEmaii(String email){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAdress = email;

        auth.sendPasswordResetEmail(emailAdress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RecuperarContr.this, "Proceso exitoso, te hemos enviado un link a tu cuenta google", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(RecuperarContr.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(RecuperarContr.this, "ERROR, correo invalido", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}