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

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.myapplication3.pojo.UserComentarios;
import com.example.myapplication3.pojo.UserImage;
import com.example.myapplication3.pojo.Usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class Registro extends AppCompatActivity {

    Button btn_atras, btn_registrarse;
    EditText et_gmail, et_password, et_r_password, et_usename, et_celular;

    FirebaseAuth firebaseAuth;
    AwesomeValidation awesomeValidation;

    //para enviar datos a firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    //enviara datos , validar firebase
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        firebaseAuth = FirebaseAuth.getInstance();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.et_gmail_registro, Patterns.EMAIL_ADDRESS, R.string.invalid_gmail);
        awesomeValidation.addValidation(this, R.id.et_contraseña_registro, ".{6,}", R.string.invalid_password);


        et_gmail = findViewById(R.id.et_gmail_registro);
        et_password = findViewById(R.id.et_contraseña_registro);
        et_r_password = findViewById(R.id.et_r_password_registro);
        et_usename = findViewById(R.id.et_user_registro);
        et_celular = findViewById(R.id.et_telefono_registro);

        btn_atras = (Button)findViewById(R.id.btn_atras);
        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registro.this, MainActivity.class);
                startActivity(i);
            }


        });

        btn_registrarse = (Button)findViewById(R.id.btn_registrarse_registro);
        btn_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = et_gmail.getText().toString();
                String pass = et_password.getText().toString();
                String rPass = et_r_password.getText().toString();
                String user = et_usename.getText().toString();
                String celular = et_celular.getText().toString();


                if(!user.isEmpty() && !celular.isEmpty() && !mail.isEmpty() && !pass.isEmpty() && !rPass.isEmpty()){

                    if(pass.equals(rPass)){
                        if(awesomeValidation.validate()){
                            firebaseAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful() ){
                                        Toast.makeText(Registro.this, "usuario creado con exito", Toast.LENGTH_LONG).show();

                                        //envio de datos a firebase realtime
                                        Usuarios usuario = new Usuarios( user, mail, celular);
                                        myRef = database.getReference().child("usuarios").push();
                                        myRef.setValue(usuario);

                                        UserImage userImage = new UserImage("...",mail, user);
                                        myRef = database.getReference().child("foto perfil").push();
                                        myRef.setValue(userImage);

                                        Intent i = new Intent(Registro.this, MainActivity.class);
                                        startActivity(i);
                                    }else{
                                        String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                        dameToasterror(errorCode);
                                    }
                                }
                            });
                        }

                    }else{
                        Toast.makeText(Registro.this, "La contraseña no coincide", Toast.LENGTH_LONG).show();
                    }


                }else{
                    Toast.makeText(Registro.this, "Debe llenar Todos los campos", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void dameToasterror(String error){
        switch (error){
            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(Registro.this, "fomato de token incorrecto", Toast.LENGTH_SHORT).show();
                break;
            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                Toast.makeText(Registro.this, "token no corresponde", Toast.LENGTH_SHORT).show();
                break;
            case "ERROR_INVALID_ID_CREDENTIAL":
                Toast.makeText(Registro.this, "credencial formato incorrecto", Toast.LENGTH_SHORT).show();
                break;
            case "ERROR_INVALID_EMAIL":
                Toast.makeText(Registro.this, "formato correo incorrecto", Toast.LENGTH_SHORT).show();
                et_gmail.setError("ERROR formato direccion de correo");
                et_gmail.requestFocus();
                break;
            case "ERROR_WRONG_PASSWORD":
                Toast.makeText(Registro.this, "contraseña no es valida", Toast.LENGTH_SHORT).show();
                et_password.setError("la contraseña es incorrecta");
                et_password.requestFocus();
                et_password.setText("");
                break;
            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                Toast.makeText(Registro.this, "ya existe una cuenta con ese correo electronico", Toast.LENGTH_SHORT).show();
                break;
            case "ERROR_WEAK_PASSWORD":
                Toast.makeText(Registro.this, "contraseña no es valida", Toast.LENGTH_SHORT).show();
                break;
            case "ERROR_USER_NOT_FOUND":
                Toast.makeText(Registro.this, "no hay registro de este usuario", Toast.LENGTH_SHORT).show();
                break;
            case "ERROR_USER_DISABLED":
                Toast.makeText(Registro.this, "su cuenta ha sido inhabilitada", Toast.LENGTH_SHORT).show();
                break;
            case "ERROR_EMAIL_ALREADY_IN_USE":
                Toast.makeText(Registro.this, "la direccion de correo electronico ya esta siendo utilizada", Toast.LENGTH_SHORT).show();
                et_gmail.setError("la direccion de correo electronico ya esta siendo utilizada por otra centa");
                et_gmail.requestFocus();
                break;
            default:
                Toast.makeText(Registro.this, "ERROR al registrarse", Toast.LENGTH_SHORT).show();
                break;

        }
    }




}