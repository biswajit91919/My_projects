package com.example.authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class signuactivity extends AppCompatActivity {
    int code;
    private EditText emailEt, passwordEt1, passwordEt2;
    private Button SignUpButton;
    private TextView SignInTv;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        firebaseAuth = FirebaseAuth.getInstance();
        emailEt = findViewById(R.id.email);
        passwordEt1 = findViewById(R.id.password1);
        passwordEt2 = findViewById(R.id.password2);
        SignUpButton = findViewById(R.id.register);
        progressDialog = new ProgressDialog(this);
        SignInTv = findViewById(R.id.SignInTv);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
        SignInTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenet = new Intent(signuactivity.this, MainActivity.class);
                startActivity(intenet);
                finish();
            }
        });
    }

    private void Register() {
        String email = emailEt.getText().toString();
        String password1 = passwordEt1.getText().toString();
        String password2 = passwordEt2.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailEt.setError("Enter your email");
            return;
        } else if (TextUtils.isEmpty(password1)) {
            passwordEt1.setError("Enter your password");
            return;
        } else if (TextUtils.isEmpty(password2)) {
            passwordEt2.setError("Confirm your password");
            return;
        } else if (!password1.equals(password2)) {
            passwordEt2.setError("Different password");
            return;
        } else if (password1.length() < 4) {
            passwordEt1.setError("length should be greater than 4");
            return;
        } else if (!isValidEmail(email)) {
            emailEt.setError("Invalid email");
            return;
        }
        firebaseAuth.fetchSignInMethodsForEmail(emailEt.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                boolean check = !task.getResult().getSignInMethods().isEmpty();
                if(!check){

                }
                else{
                    Toast.makeText(getApplicationContext(),"Email already exist",Toast.LENGTH_SHORT).show();
                }
            }
        });
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(signuactivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
                    Intent intenet = new Intent(signuactivity.this, dashboardactivity.class);
                    startActivity(intenet);
                    finish();
                } else {
                    Toast.makeText(signuactivity.this, "Please check all details!!", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }

        });
    }
    private Boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}