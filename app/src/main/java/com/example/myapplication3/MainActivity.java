package com.example.myapplication3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.myapplication3.pojo.UserImage;
import com.example.myapplication3.pojo.Usuarios;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    Button btn, btn2, btn_recuperar_cont;
    private EditText editTextUsuario;
    private EditText editTextPass;
    private TextView tvMensaje;
    private Button btLogin;

    AwesomeValidation awesomeValidation;

    //------------------
    private SignInButton signInButton;
    private GoogleSignInClient mGoogleSignInClient;
    private String TAG = "Google";
    private FirebaseAuth mAuth;
    private int RC_SIGN_IN = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton = findViewById(R.id.sign_in_button1);
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            irAhome();
        }

        //---------------

        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextPass = findViewById(R.id.editTextPassword);
        btn = (Button)findViewById(R.id.btLogin);
        btLogin = findViewById(R.id.btLogin);


        mAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();

        SharedPreferences dato = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String gmail = dato.getString("gmail", "");

        //para enviarlo al home si ya esta registrado

        if(mUser != null && !gmail.isEmpty()){
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
                        mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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

        //--------------------------------------------------


    private void signIn(){
        Intent i = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(i, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSigInResult(task);
        }

    }

    private void handleSigInResult(Task<GoogleSignInAccount> completeTask){
        try{
            GoogleSignInAccount acc = completeTask.getResult(ApiException.class);
            Toast.makeText(MainActivity.this, "Cargando informacion...", Toast.LENGTH_LONG).show();
            FirebaseGoogleAuth(acc);

        }catch (ApiException e){
            Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_LONG).show();
            FirebaseGoogleAuth(null);
        }

    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acct){
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "...", Toast.LENGTH_LONG).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);

                }else{
                    Toast.makeText(MainActivity.this, "Error2", Toast.LENGTH_LONG).show();
                    updateUI(null);
                }
            }
        });
    }

    private void updateUI(FirebaseUser fUser){
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        String personName = account.getDisplayName();
        String email = account.getEmail();
        String image = account.getPhotoUrl().toString();

        Usuarios usuario = new Usuarios( personName, email, "00000000000", ".........");
        myRef = database.getReference().child("usuarios").push();
        myRef.setValue(usuario);

        UserImage userImage = new UserImage(image, email, personName);
        myRef = database.getReference().child("foto perfil").push();
        myRef.setValue(userImage);

        //para guardar datos de manera local
        SharedPreferences pref = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_edit = pref.edit();
        obj_edit.putString("gmail", email);
        obj_edit.commit();

        if(account != null){
            Intent i = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(i);
        }
    }

}