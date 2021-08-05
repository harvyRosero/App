package com.example.myapplication3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btn, btn2, btn_recuperar_cont;
    private EditText editTextUsuario;
    private EditText editTextPass;
    private TextView tvMensaje;
    private Button btLogin;

    AwesomeValidation awesomeValidation;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextPass = findViewById(R.id.editTextPassword);
        btn = (Button)findViewById(R.id.btLogin);
        btLogin = findViewById(R.id.btLogin);

        tvMensaje = findViewById(R.id.textMensaje);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();


        //para enviarlo al home si ya esta registrado
        if(mUser != null){
            irAhome();
        }

        //libreria de autenticacion
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.et_gmail_registro, Patterns.EMAIL_ADDRESS, R.string.invalid_gmail);
        awesomeValidation.addValidation(this, R.id.et_contraseña_registro, ".{6,}", R.string.invalid_password);

        //boton iniciar sesion
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = editTextUsuario.getText().toString();
                String pass = editTextPass.getText().toString();

                if(!mail.isEmpty() && !pass.isEmpty()){
                    if(awesomeValidation.validate()){
                        firebaseAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //

                                if(task.isSuccessful()){

                                    //para guardar datos de manera local
                                    SharedPreferences pref = getSharedPreferences("datos", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor obj_edit = pref.edit();
                                    obj_edit.putString("gmail", mail);
                                    obj_edit.commit();
                                    irAhome();
                                }else{
                                    String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                    dameToasterror(errorCode);
                                }
                            }
                        });
                    }
                }else{
                    Toast.makeText(MainActivity.this, "ERROR, debde llenar todos los campos", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //funcion botones cambio de activity
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

    //funcion iniciar sesion

    private void irAhome() {
        Intent i = new Intent(MainActivity.this, MainActivity2.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }


    //funcion mostar typo de error
    private void dameToasterror(String error){
        switch (error) {
            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(MainActivity.this, "fomato de token incorrecto", Toast.LENGTH_SHORT).show();
                break;
            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                Toast.makeText(MainActivity.this, "token no corresponde", Toast.LENGTH_SHORT).show();
                break;
            case "ERROR_INVALID_ID_CREDENTIAL":
                Toast.makeText(MainActivity.this, "credencial formato incorrecto", Toast.LENGTH_SHORT).show();
                break;
            case "ERROR_INVALID_EMAIL":
                Toast.makeText(MainActivity.this, "formato correo incorrecto", Toast.LENGTH_SHORT).show();
                editTextUsuario.setError("ERROR formato direccion de correo");
                editTextUsuario.requestFocus();
                break;
            case "ERROR_WRONG_PASSWORD":
                Toast.makeText(MainActivity.this, "contraseña no es valida", Toast.LENGTH_SHORT).show();
                editTextPass.setError("la contraseña es incorrecta");
                editTextPass.requestFocus();
                editTextPass.setText("");
                break;
            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                Toast.makeText(MainActivity.this, "ya existe una cuenta con ese correo electronico", Toast.LENGTH_SHORT).show();
                break;
            case "ERROR_WEAK_PASSWORD":
                Toast.makeText(MainActivity.this, "contraseña no es valida", Toast.LENGTH_SHORT).show();
                break;
            case "ERROR_USER_NOT_FOUND":
                Toast.makeText(MainActivity.this, "no hay registro de este usuario", Toast.LENGTH_SHORT).show();
                break;
            case "ERROR_USER_DISABLED":
                Toast.makeText(MainActivity.this, "su cuenta ha sido inhabilitada", Toast.LENGTH_SHORT).show();
                break;
            case "ERROR_EMAIL_ALREADY_IN_USE":
                Toast.makeText(MainActivity.this, "la direccion de correo electronico ya esta siendo utilizada", Toast.LENGTH_SHORT).show();
                editTextUsuario.setError("la direccion de correo electronico ya esta siendo utilizada por otra centa");
                editTextUsuario.requestFocus();
                break;
            default:
                Toast.makeText(MainActivity.this, "Ocurrion un error", Toast.LENGTH_SHORT).show();
                break;

            }
        }
}