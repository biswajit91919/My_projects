package com.example.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;


public class MainActivity extends AppCompatActivity {
    private EditText emailEt, passwordEt;
    private Button SignInButton;
    private TextView SignUpTv;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        emailEt = findViewById(R.id.email);
        passwordEt = findViewById(R.id.password);
        SignInButton = findViewById(R.id.login);
        progressDialog = new ProgressDialog(this);
        SignUpTv = findViewById(R.id.signUpTv);
        SignInButton.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
        SignUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenet = new Intent(MainActivity.this, signuactivity.class);
                startActivity(intenet);
                finish();
            }
        });
    }

    private void Login(){
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailEt.setError("Enter your email");
            return;
        } else if (TextUtils.isEmpty(password)) {
            passwordEt.setError("Enter your password");
            return;
        }
        firebaseAuth.fetchSignInMethodsForEmail(emailEt.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                boolean check = !task.getResult().getSignInMethods().isEmpty();
                if(!check){
                    Toast.makeText(getApplicationContext(),"Email not found",Toast.LENGTH_SHORT).show();
                }
            }
        });
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Login Sucessfully",Toast.LENGTH_LONG).show();
                    Intent intenet=new Intent(MainActivity.this,dashboardactivity.class);
                    startActivity(intenet);
                    finish();
                }
                else {
                    Toast.makeText(MainActivity.this,"Sign In fail!",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    }